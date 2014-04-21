
import static groovyx.javafx.GroovyFX.start
import groovyx.javafx.beans.FXBindable
import javafx.stage.Screen
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color
import javafx.collections.FXCollections
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.stage.Screen

start {
    stage(title: 'GroovyFX Draw', visible: true) {
        scene = scene(id: "sc", fill: WHITE, 
            width: Screen.getPrimary()
                .getVisualBounds().getWidth(),
            height: Screen.getPrimary()
              .getVisualBounds().getHeight()) {

            canvas = canvas(id: "drawcanvas",
                width: bind(sc.width()),
                height: bind(sc.height()))
            GraphicsContext graphicsContext =
              canvas.graphicsContext2D
            canvas.onMousePressed { 
                MouseEvent event->
                graphicsContext.beginPath()
                graphicsContext.moveTo(event.getX(),
                                       event.getY())
                graphicsContext.stroke()
            } 
            canvas.onMouseDragged {
                MouseEvent event->
                graphicsContext.lineTo(event.getX(),
                                       event.getY())
                graphicsContext.stroke()
            }
              
            hbox(spacing: 10, padding: 10) {
                button("Reset", onAction: {
                 graphicsContext.clearRect(1, 1, 
                 graphicsContext.getCanvas().getWidth()-2, 
                 graphicsContext.getCanvas().getHeight()-2)
                    })
                colorChooser = choiceBox(value:"Black",
                  items: FXCollections.observableArrayList(
                  "Black", "Blue", "Red", "Green", "Brown",
                    "Orange")){
                    onSelect {
                        control, item ->
                                
                      Color newColor
                      switch(item){
                      case "Black": newColor = Color.BLACK
                          break
                      case "Blue": newColor = Color.BLUE
                          break
                      case "Red": newColor = Color.RED
                          break
                      case "Green": newColor = Color.GREEN
                          break
                      case "Brown": newColor = Color.BROWN
                          break
                      case "Orange": newColor = Color.ORANGE
                          break
                      default: newColor = Color.BLACK
                          break
                      }
                        graphicsContext.setStroke(newColor)
                    }
                }
                sizeChooser = choiceBox(value: 1,
                    items: FXCollections.observableArrayList(
                        1, 2, 3, 4, 5)){
                    onSelect {
                        control, item ->
                        Number idx = (Number)item
                    switch(idx.intValue()){
                    case 1: graphicsContext.setLineWidth(1)
                        break
                    case 2: graphicsContext.setLineWidth(2)
                        break
                    case 3: graphicsContext.setLineWidth(3)
                        break
                    case 4: graphicsContext.setLineWidth(4)
                        break
                    case 5: graphicsContext.setLineWidth(5)
                        break
                    default: graphicsContext.setLineWidth(1)
                        break
                        }
                    }
                }
            }  
        }
    }
}