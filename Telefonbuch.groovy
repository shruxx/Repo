import groovy.sql.Sql
import groovy.swing.SwingBuilder
import javax.swing.*
import java.awt.*

def db = 'jdbc:sqlserver://vm-srv-sql1:1433;databaseName=TestDBDev'
def user = 'arge_dev'
def password = 'arge'
def driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
def sql = Sql.newInstance(db, user, password, driver)
def telegui = new SwingBuilder()


telegui.edt {
    frame(title: "Telefonbuch", defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true)
            {
                vbox {
                    textlabel = label("Möchtest du jemanden eintragen?")
                    hbox {
                        input = textField(columns: 50, actionPerformed: {
                            echo.text = input.text.toUpperCase()
                        })
                    }
                    //HIER MUSS NOCH GANZ VIEL STEHEN
                    hbox {
                        button(text: 'Trage die Nummer ein', actionPerformed: {sql.execute vorname = """"INSERT INTO Einkaufsliste (Ware) VALUES (${input.text})"""
                        println "${input.text} wurde erfolgreich eingetragen!"})
                        }
                    }
                }
            }
