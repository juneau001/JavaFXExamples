import static groovyx.javafx.GroovyFX.start

start {
    stage(title: 'GroovyFX Hello World', visible: true) {
        scene(fill: BLACK, width: 500, height: 250) {
            hbox(padding: 60) {
                text(text: 'Groovy', font: '80pt sanserif') {
                    fill linearGradient(endX: 0, stops: [PALEGREEN, SEAGREEN])
                }
                text(text: 'FX', font: '80pt sanserif') {
                    fill linearGradient(endX: 0, stops: [CYAN, DODGERBLUE])
                    effect dropShadow(color: DODGERBLUE, radius: 25, spread: 0.25)
                }
            }
        }
    }
}