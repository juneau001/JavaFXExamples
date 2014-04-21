
import static groovyx.javafx.GroovyFX.start
import groovyx.javafx.beans.FXBindable
import javafx.scene.control.Label

/**
 * GroovyFX Binding
 * @author Juneau
 */

String textEntry
start{
    
    stage(title: "GroovyFX Introduction", width: 600,
        height: 300, visible: true){
        scene(fill: groovyblue){
            gridPane(hgap: 5, vgap: 10,
                     padding: 20){
                hbox(spacing: 5, row:1,
                     columnSpan:2 ){
                    label("Place Text Here:",
                          textFill: white,
                          halignment: center,
                          valignment: center)
                    text = textField(
                            promptText: "Type here",
                            prefColumnCount: 25)
                }
               
                button("Save", font: "14pt arial",
                        row: 2, column: 1, 
                    onAction: {
                        textEntry = text.getText()
                        println textEntry
                    })
                label(text: bind(text, 'text'),
                       row: 3, column: 1)
            }
        }
    }
}


