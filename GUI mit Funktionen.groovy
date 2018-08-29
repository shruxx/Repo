import groovy.swing.SwingBuilder
import javax.swing.JFrame
import javax.swing.JFileChooser

def chooser = new JFileChooser()
def gui = new SwingBuilder()

gui.edt {
    frame(title: 'Erweiterte GUI', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true)
            {
                vbox {
                    textlabel = label('Hier wird gearbeitet')
                    hbox {
                        button(text: 'So ist das hier', actionPerformed: {
                            new GroovyShell().evaluate(new File("C:/Coding/Groovy/copydel.groovy"))
                        })
                        hbox {
                            button(text: 'mach die Hütte dicht', actionPerformed: { gui.dispose() })
                            hbox {
                                button(text: 'welcher Ordner darf es denn sein?', actionPerformed: {
                                    chooser.showOpenDialog(null)
                                    if(chooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
                                    chooser.getSelectedFile().getName()
                                    new GroovyShell().evaluate(new File("${chooser.getSelectedFile().getName()}"))


                                }
)
                            }
                        }

                    }
                }
            }
}