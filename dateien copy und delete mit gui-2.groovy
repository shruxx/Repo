import groovy.swing.SwingBuilder
import javax.swing.JFrame

sb = new SwingBuilder()

def dialog(event){
    dialog = sb.dialog(title: 'Entry')
    def panel = sb.panel{
        vbox {
            hbox{
                label(text: 'Wenn du kopieren und löschen willst, drücke die 1. Ansonsten irgendwas anderes')
            }
            hbox{
                button(text: '1', actionPerformed:{new GroovyShell().evaluate(new File("C:/Coding/Groovy/copydel.groovy"))}  )
                button(text: 'Irgendwas anderes', actionPerformed:{sb.dispose()})
            }
        }
    }
    dialog.getContentPane().add(panel)
    dialog.pack()
    dialog.show()
}


new SwingBuilder().frame( title: "Jetzt wird kopiert!",defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true ){
    panel{
        button(actionPerformed: this.&dialog, "Kommen Sie herein bitte!")
    }
}