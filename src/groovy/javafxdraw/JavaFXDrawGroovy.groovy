package groovy.javafxdraw

import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.geometry.Rectangle2D
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Screen
import javafx.stage.Stage

/**
 * JavaFX Draw Application - Groovy Implementation
 * @author Juneau
 */
class JavaFXDrawGroovy extends Application {
    static void main(args)
    {
        Application.launch(JavaFXDrawGroovy.class, args)
    }
    
    void start(Stage stage)
    {
        def root = new StackPane()
        def screen = Screen.getPrimary()
        def rect = screen.getVisualBounds()
        def canvas = new Canvas(rect.getWidth()/2 + 50,
            rect.getHeight()/2 + 300 )
        def graphicsContext = canvas.getGraphicsContext2D()

        def resetButton = new Button("Reset")
        resetButton.setOnAction({
                graphicsContext.clearRect(1, 1, 
                graphicsContext.getCanvas().getWidth()-2, 
                graphicsContext.getCanvas().getHeight()-2)
            } as EventHandler)
        resetButton.setTranslateX(10)

        // Set up the pen color chooser
        def colorChooser = new ChoiceBox(
         FXCollections.observableArrayList(
         "Black", "Blue", "Red", "Green", "Brown", "Orange"
        ))
        // Select the first option by default
        colorChooser.getSelectionModel().selectFirst()
        
        colorChooser.getSelectionModel().
            selectedIndexProperty().addListener(
            { ObservableValue ov,
              Object old, Object newval ->
                    Number idx = (Number)newval
                    Color newColor
                    switch(idx.intValue()){
                    case 0: newColor = Color.BLACK
                        break
                    case 1: newColor = Color.BLUE
                        break
                    case 2: newColor = Color.RED
                        break
                    case 3: newColor = Color.GREEN
                        break
                    case 4: newColor = Color.BROWN
                        break
                    case 5: newColor = Color.ORANGE
                        break
                    default: newColor = Color.BLACK
                        break
                    }
                    graphicsContext.setStroke(newColor)
                } as ChangeListener)
            
        colorChooser.setTranslateX(5)

        def sizeChooser = new ChoiceBox(
            FXCollections.observableArrayList(
            "1", "2", "3", "4", "5"
        ))
        // Select the first option by default
        sizeChooser.getSelectionModel().selectFirst()

        sizeChooser.getSelectionModel()
           .selectedIndexProperty().addListener(
             { ObservableValue ov,
               Object old, Object newval ->
                  Number idx = (Number)newval

                  switch(idx.intValue()){
                  case 0: graphicsContext.setLineWidth(1)
                      break
                  case 1: graphicsContext.setLineWidth(2)
                      break
                  case 2: graphicsContext.setLineWidth(3)
                      break
                  case 3: graphicsContext.setLineWidth(4)
                      break
                  case 4: graphicsContext.setLineWidth(5)
                      break
                  default: graphicsContext.setLineWidth(1)
                      break
                  }
                } as ChangeListener)
            
        sizeChooser.setTranslateX(5)

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, {
                MouseEvent event->
                graphicsContext.beginPath()
                graphicsContext.moveTo(
                    event.getX(), event.getY()
                )
                graphicsContext.stroke()
            } as EventHandler)

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, {
                MouseEvent event->
                graphicsContext.lineTo(
                    event.getX(), event.getY()
                )
                graphicsContext.stroke()
            } as EventHandler)
            
        def buttonBox = new HBox()
        buttonBox.getChildren().addAll(
            colorChooser,sizeChooser, resetButton)

        initDraw(graphicsContext, 
                 canvas.getLayoutX(),
                 canvas.getLayoutY())

        def container = new BorderPane()
        container.setTop(buttonBox)

        container.setCenter(canvas)

        root.getChildren().add(container)
        Scene scene = new Scene(root,
                  rect.getHeight(), rect.getWidth())
        stage.setTitle("JavaFX Draw")
        stage.setScene(scene)
        stage.show()
    }
     
    void initDraw(gc, x, y){
        def canvasWidth = gc.getCanvas().getWidth()
        def canvasHeight = gc.getCanvas().getHeight()

        gc.fill()
        gc.strokeRect(
            x,            //x of the upper left corner
            y,            //y of the upper left corner
            canvasWidth,  //width of the rectangle
            canvasHeight) //height of the rectangle

    }
}