import groovy.sql.Sql
import groovy.swing.SwingBuilder
import groovyx.gpars.dataflow.Select

import javax.swing.*
import java.awt.*
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

gui.edt {
    frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true)
            {
                vbox {
                    textlabel = label('Was benötigst du?')
                    hbox {
                        input = textField(columns: 30, actionPerformed: { echo.text = input.text.toUpperCase()})


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
                                if (input.text.length() < 50){println "toll!"}
                                else { throw new Exception("Zu viele Zeichen eingegeben!")}
                                if(input.text.length() > 1) {println "du musst was eingeben!"}
                                else { throw new Exception("Zu wenig Zeichen angegeben!")}
                                //sql.executeInsert"""INSERT INTO Einkaufsliste (Ware) VALUES (${input.text})"""
                                println "${input.text} wurde vermerkt!"
                                frame(title: 'Preis', defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE, pack: true, show: true){
                                    hbox {
                                        inputP = textField(columns: 30, actionPerformed: { echo.text = inputP.text.toUpperCase()})
                                            textlabelP = label("Was kostet ${input.text}?")
                                                    button(text:"Mitteilen", actionPerformed: {
                                                        if(input.text.length() > 1) {println "Cool"}
                                                        else { throw new Exception("Zu wenig Zeichen angegeben!")}
                                                        //sql.executeInsert"""INSERT INTO Einkaufsliste (Preis, Ware) VALUES (${inputP.text},${input.text})"""
                                                        println "${inputP.text} wurde vermerkt"
                                                        frame(title: 'Menge', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true){
                                                            hbox {
                                                                inputM = textField(columns: 30, actionPerformed: { echo.text = inputM.text.toUpperCase()})
                                                                textlabelM = label ("Wieviel hättest du gerne von (${input.text})?")
                                                                button(text:"Mitteilen", actionPerformed: {
                                                                    if(inputM.text.length() > 1) {println "fast fertig!"}
                                                                    else { throw new Exception("Zu wenig Zeichen angegeben!")}
                                                                    String m = "${inputM.text}"
                                                                    def z = m as Double
                                                                    sql.executeInsert"""INSERT INTO Einkaufsliste (Menge, Preis, Ware) VALUES(${z}, ${inputP.text}, ${input.text})"""
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
                                    frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE, pack: true, show: true){
                                        hbox {
                                            textlabel = label ('Bist du dir wirklich sicher?')
                                            button(text:'Aber sicher doch', actionPerformed: {
                                                sql.execute "DELETE FROM Einkaufsliste"
                                                println "Einkaufsliste wurde resetted"
                                            })
                                            button(text:'Nein, lass mal sein', actionPerformed: {gui.dispose()})}}

                                })

                            }
                        }
                    }
                }
            }