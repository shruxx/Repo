import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.*
import javax.swing.WindowConstants as WC

def eingabefeld = new JTextField()
def gui = new SwingBuilder()

gui.edt {
    frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true)
            {
                vbox {
                    textlabel = label('Was benötigst du?')
                    hbox{
                        input = textField(columns:20, actionPerformed: {echo.text = input.text.toUpperCase()})
                        //HIER MUSS NOCH GANZ VIEL STEHEN

                     hbox{
                         button(text:'Mitteilen', actionPerformed: {println input.text})
                     }
                    }
                }
            }
}
