import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**The analyzer class for methods.
 * Will take in a .txt file of a Java, parse through the methods and determine if they are syntactically correct.
 * If the methods don't follow correct syntax, this class will fix it.
 */
public class MethodAnalyzer {
    private File fileName;

    public MethodAnalyzer(File fileName) {
        this.fileName = fileName;
    }

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }



    //need to create a method that uses DecisionAnalyzer and LoopAnalyzer to iterate through and make sure that
    //methods from the File read in are implemented correctly
    public void analyzer(File fileName){
        try {
            FileReader reader = new FileReader(fileName);


        }catch (IOException e){}
    }

    //takes the contents from the analyzer method and does any corrections that might be needed
    public void corrector(File fileName){

    }

}
