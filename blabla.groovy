import groovy.sql.Sql
import groovy.swing.SwingBuilder
import groovyx.gpars.dataflow.Select
import javax.swing.*
import java.util.regex.*
import java.awt.*
import javafx.scene.layout.VBox
import javax.swing.WindowConstants as WC
import java.awt.event.ActionEvent
import java.text.DecimalFormat

def db = 'jdbc:sqlserver://vm-srv-sql1:1433;databaseName=TestDBDev'
def user = 'arge_dev'
def password = 'arge'
def eingabefeld = new JTextField()
def driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
def sql = Sql.newInstance(db, user, password, driver)
def gui = new SwingBuilder()
//label = new JLabel()

gui.edt {
    frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true)
            {
                vbox {
                    textlabel = label("Was benötigst du?")
                    hbox {
                        input = textField(columns: 30, actionPerformed: { echo.text = input.text.toUpperCase() })

                        //HIER MUSS NOCH GANZ VIEL STEHEN
                    }
                    hbox {
                        button(text: 'zeige mir die Liste an', actionPerformed: {
                            (sql.eachRow("SELECT * FROM Einkaufsliste") {
                                println "${it.Ware}"
                            })
                        })

                        hbox {
                            button(text: 'Mitteilen', actionPerformed: {
                                println input.text.length()
                                if (input.text.length() > 50) {
                                    throw new Exception("zu wenig Zeichen!", JOptionPane.showMessageDialog(null, "Zu viele Zeichen angegeben!", "ERROR", JOptionPane.ERROR_MESSAGE))
                                } else {
                                    println "Alles super hier"
                                }
                                if (input.text.length().equals(0)) {
                                    throw new Exception("zu wenig Zeichen!", JOptionPane.showMessageDialog(null, "Zu wenig Zeichen angegeben!", "ERROR", JOptionPane.ERROR_MESSAGE))
                                } else {
                                    println "alles super hier!"
                                }
                                //sql.executeInsert"""INSERT INTO Einkaufsliste (Ware) VALUES (${input.text})"""
                                println "${input.text} wurde vermerkt!"
                                frame(title: 'Preis', defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE, pack: true, show: true) {
                                    hbox {
                                        inputP = textField(columns: 30, actionPerformed: { echo.text = inputP.text.toUpperCase()})
                                            textlabelP = label("Was kostet ${input.text}?")
                                                    button(text:"Mitteilen", actionPerformed: {
                                                        if(inputP.text.length().equals(0) ) {throw new Exception("zu wenig Zeichen!", JOptionPane.showMessageDialog(null, "Zu wenig Zeichen angegeben!", "ERROR", JOptionPane.ERROR_MESSAGE))}
                                                            else if (inputP.text.matches(".*[a-zA-Z]+.*")) {throw new Exception("Eingabe enthält Buchstaben. Erneut eingeben!", JOptionPane.showMessageDialog(null, "Eingabe enthält Buchstaben. Erneut eingeben!", "ERROR", JOptionPane.ERROR_MESSAGE))}
                                                        else { println "alles super hier!"}
                                                        String o = "${inputP.text}"
                                                        def y = o as Double
                                                        //sql.executeInsert"""INSERT INTO Einkaufsliste (Preis, Ware) VALUES (${inputP.text},${input.text})"""
                                                        println "${inputP.text} wurde vermerkt"
                                                        frame(title: 'Menge', defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE, pack: true, show: true){
                                                            hbox {
                                                                inputM = textField(columns: 30, actionPerformed: { echo.text = inputM.text.toUpperCase()})
                                                                textlabelM = label ("Wieviel hättest du gerne von ${input.text}?")
                                                                button(text:"Mitteilen", actionPerformed: {
                                                                    if(inputM.text.length().equals(0) ) {throw new Exception("zu wenig Zeichen!", JOptionPane.showMessageDialog(null, "Zu wenig Zeichen angegeben!", "ERROR", JOptionPane.ERROR_MESSAGE))}
                                                                        else if (inputM.text.matches(".*[a-zA-Z]+.*")) {throw new Exception("Eingabe enthält Buchstaben. Erneut eingeben!", JOptionPane.showMessageDialog(null, "Eingabe enthält Buchstaben. Erneut eingeben!", "ERROR", JOptionPane.ERROR_MESSAGE))}
                                                                    else { println "alles super hier!"}
                                                                    String m = "${inputM.text}"
                                                                    def z = m as Double
                                                                    sql.executeInsert"""INSERT INTO Einkaufsliste (Menge, Preis, Ware) VALUES(${z}, ${y}, ${input.text})"""
                                                                    println "${inputM.text} wurde vermerkt"
                                                                                                                                    })
                                                                button(text:"Schliessen", actionPerformed: {gui.dispose()})
                                                                }
                                                            }
                                                        })
                                                    }
                                        }
                                })
                            }
                            hbox{
                                button(text: 'Einkaufsliste resetten', actionPerformed: {
                                    frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE, pack: true, show: true) {
                                        hbox {
                                            textlabel = label('Bist du dir wirklich sicher?')
                                            button(text: 'Aber sicher doch', actionPerformed: {
                                                sql.execute "DELETE FROM Einkaufsliste"
                                                println "Einkaufsliste wurde resetted"
                                            })
                                            button(text: 'Nein, lass mal sein', actionPerformed: { gui.dispose() })
                                        }
                                    }

                                })

                            }
                        }
                    }
                }
            }
