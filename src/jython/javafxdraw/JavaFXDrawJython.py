import sys
from javafx.application import Application
from javafx.scene.paint import Color
from javafx.collections import FXCollections
from javafx.scene.canvas import Canvas
from javafx.scene.canvas import GraphicsContext
import javafx.scene.control
from javafx.scene import Group
from javafx.scene import Scene
from javafx.scene.input import MouseEvent
from javafx.scene.layout import BorderPane
from javafx.scene.layout import HBox
from javafx.scene.layout import StackPane
import javafx.stage
from javafx.stage import Stage
import javafx.scene.control

class JythonFXDraw(Application):

    def start(self, primaryStage):
        primaryStage.setTitle("JythonFX Draw")
        root = StackPane()
        screen = javafx.stage.Screen.getPrimary()
        rect = screen.visualBounds
        canvas = Canvas(rect.width/2 + 50,
                        rect.height/2 + 300)
        graphics_context = canvas.graphicsContext2D
        
        def resetAction(event):
            graphics_context.clearRect(1,1,
            graphics_context.canvas.width-2,
            graphics_context.canvas.height-2)
            
        def colorAction(newval):
            idx = newval.value
            if idx == 0:
                new_color = Color.BLACK
            elif idx == 1:
                new_color = Color.BLUE
            elif idx == 2:
                new_color = Color.RED
            elif idx == 3:
                new_color = Color.GREEN
            elif idx == 4:
                new_color = Color.BROWN
            elif idx == 5:
                new_color = Color.ORANGE
            else:
                new_color = Color.BLACK

            graphics_context.setStroke(new_color)
            
        def sizeAction(newval):
            idx = newval.value
            if idx == 0:
                graphics_context.lineWidth = 1
            elif idx == 1:
                graphics_context.lineWidth = 2
            elif idx == 2:
                graphics_context.lineWidth = 3
            elif idx == 3:
                graphics_context.lineWidth = 4
            elif idx == 4:
                graphics_context.lineWidth = 5
            else:
                graphics_context.lineWidth = 1
            
            
        resetButton = javafx.scene.control.Button(
            "Reset", onAction=resetAction)
        resetButton.translateX = 10
        
        colorChooser = javafx.scene.control.ChoiceBox(
        FXCollections.observableArrayList(
        "Black", "Blue", "Red", "Green", "Brown", "Orange"
        ))
        
        cssm = colorChooser.selectionModel
        cssm.selectedIndexProperty().addListener(colorAction)
        
        sizeChooser = javafx.scene.control.ChoiceBox(
            FXCollections.observableArrayList
            ("1", "2", "3", "4", "5")
        )
        
        scsm = sizeChooser.selectionModel
        scsm.selectedIndexProperty().addListener(sizeAction)
        
        def mouse_pressed(event):
            graphics_context.beginPath()
            graphics_context.moveTo(event.x, event.y)
            graphics_context.stroke()
            
        def mouse_dragged(event):
            graphics_context.lineTo(event.x, event.y)
            graphics_context.stroke()
        
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                               mouse_pressed)
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                               mouse_dragged)
        
        buttonBox = HBox()
        buttonBox.children.addAll(colorChooser,
                                  sizeChooser, resetButton)
        
        self.init_draw(graphics_context,
                       canvas.layoutX, canvas.layoutY)
        
        container = BorderPane()
        container.top = buttonBox
        container.center = canvas
        root.children.add(container)
        
        scene = Scene(root, rect.height, rect.width)
        primaryStage.title = "JythonFX Draw"
        primaryStage.scene = scene
        primaryStage.show()
        
    def init_draw(self, gc, x, y):
        canvas_width = gc.canvas.width
        canvas_height = gc.canvas.height
        gc.fill()
        gc.strokeRect(
            x,
            y,
            canvas_width,
            canvas_height
        )
        
if __name__ == "__main__":
    Application.launch(JythonFXDraw().class, [])
    