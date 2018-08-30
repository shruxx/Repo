import groovy.sql.Sql

def url = 'jdbc:sqlserver://vm-srv-sql1:1433;databaseName=TestDBDev'
def user = 'arge_webintranet'
def password = 'arge'
def driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
def sql = Sql.newInstance(url, user, password, driver)
def query1 = "SELECT * FROM TestDBDev.dbo.ARGE_AD_Gruppen"

sql.eachRow("SELECT * FROM ARGE_AD_Gruppen"){
    println "${it.Group_ID} | ${it.Group_Name}"
}