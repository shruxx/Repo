import groovy.sql.Sql
import groovy.swing.SwingBuilder
import groovyx.gpars.dataflow.Select
import javax.swing.*
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

