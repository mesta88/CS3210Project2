/**The analyzer class for methods.
 * Will take in a .txt file of a Java, parse through the methods and determine if they are syntactically correct.
 * If the methods don't follow correct syntax, this class will fix it.
 */
public class methodAnalyzer {
    private String fileName;

    public methodAnalyzer(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }



}
