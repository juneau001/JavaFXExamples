import static groovyx.javafx.GroovyFX.start
start {
    stage(title: "GroovyFX YoYo", width: 500,
        height: 400, visible: true) {
        scene(fill: GROOVYBLUE) {
            circle = circle(centerX: 250,
                centerY: 60,
                radius:60,
                fill: WHITE,
                stroke: BLACK){
                effect: boxBlur(10, 10, 3)
            }
            line   = line (startX: 250,
                endX: 250,
                startY: 0,
                endY: 0,
                strokeWidth: 3)
            myTxt = text("Groovy!",
                x:  200, y: 150, fill: ORANGE,
                font: "bold 26pt Arial"){
                  fade = fadeTransition(
                              duration: 5000.ms,  
                              fromValue: 0.0,
                              toValue: 1.0)
                  effect dropShadow(offsetY: 4)
                }
        }
    }

    def tl = timeline(cycleCount: INDEFINITE,
        autoReverse: true) {
        at(1000.ms) {
         change(circle, 'centerY') to 340 tween EASE_BOTH
         change(line, 'startY') to 280 tween EASE_BOTH
        }
    }
    
    tl.play()
    fade.playFromStart()
}

