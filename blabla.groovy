import groovy.sql.Sql
import groovy.swing.SwingBuilder
import groovyx.gpars.dataflow.Select

import javax.swing.*
import java.awt.*
import javax.swing.WindowConstants as WC

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
                        input = textField(columns: 50, actionPerformed: { echo.text = input.text.toUpperCase()})

                        //HIER MUSS NOCH GANZ VIEL STEHEN
                    }
                    hbox {
                        button(text: 'zeige mir die List an', actionPerformed: {
                            (sql.eachRow("SELECT * FROM Einkaufsliste") {
                               println "${it.Ware}"
                            })
                        })

                        hbox {
                            button(text: 'Mitteilen', actionPerformed: {
                                sql.executeInsert"""INSERT INTO Einkaufsliste (Ware) VALUES (${input.text})"""
                                println "${input.text} wurde vermerkt!"
                            })
                        hbox{
                            button(text: 'Einkaufsliste resetten', actionPerformed: {
                                frame(title: 'Einkaufsliste', defaultCloseOperation: JFrame.EXIT_ON_CLOSE, pack: true, show: true){
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
            }