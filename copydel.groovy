def ant = new AntBuilder()
def antdel = new AntBuilder()

    ant.sequential{
             echo("wir sind mittendrin!")
             def Posteingang="C:/Coding/Test/"
             mkdir(dir: Posteingang)
             copy(todir: Posteingang) {
                 fileset(dir:"C:/Coding/Quelle/")
                    }
                  }
                antdel.delete(dir: "C:/Coding/Quelle/")
                ant.echo("fertig!")