import groovy.sql.Sql
import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.*
import javax.swing.WindowConstants as WC

def url = 'jdbc:sqlserver://vm-srv-sql1:1433;databaseName=TestDBDev'
def user = 'arge_webintranet'
def password = 'arge'
def eingabefeld = new JTextField()
def driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
def sql = Sql.newInstance(url, user, password, driver)
def gui = new SwingBuilder()

gui.edt {
    frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true)
            {
                vbox {
                    textlabel = label('Was benötigst du?')
                    hbox {
                        input = textField(columns: 20, actionPerformed: { echo.text = input.text.toUpperCase() })
                        //HIER MUSS NOCH GANZ VIEL STEHEN

                        hbox {
                            button(text: 'Mitteilen', actionPerformed: {(sql.eachRow("SELECT * FROM ARGE_AD_Gruppen") {
                                println "${it.Group_ID} | ${it.Group_Name}"
                                //println(input.text);
                                })
                            }
                            )
                        }
                    }
                }
            }
}