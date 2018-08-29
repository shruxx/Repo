import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.*
import groovy.lang.GroovyShell

def swing=new SwingBuilder()

def guiPanel={
    swing.panel(){
        label("Dateien kopieren und Ausgangsverzeichnis löschen")
    }
}
swing.edt {
    frame(title: 'Kopieren und löschen',
            defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
            pack: true,
            show: true) {

        vbox {
            hbox{   textlabel = label('Dateien verschieben und löschen!')
                button(
                        text: 'Bestätigen',
                        actionPerformed: {
                            new GroovyShell().evaluate(new File("C:/Coding/Groovy/copydel.groovy"))}
                )
            }
                    }
    }
}