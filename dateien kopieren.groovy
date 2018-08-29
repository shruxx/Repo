import java.nio.file.*

//Klasse beschreiben
class Ordner
{
    //Methode auswählen
    public static void main(String[] args)
    {
    //Variablen definieren
    Path source= Paths.get("C:/Coding/Projekte/Post/");
    Path target= Paths.get("C:/Coding/Projekte/Versicherungsordner");
    //Files.copy(source,target.resolve(source.getFileName()),StandardCopyOption.REPLACE_EXISTING);
    
    Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor("C:/Coding/Projekte/Versicherungsordner"));
 
 println('Datei wurde erfolgreich wegsortiert')
     }
}