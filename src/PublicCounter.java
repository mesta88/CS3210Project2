import java.io.File;

/**
 * Class used for counting how many times the keyword "public" is found in the file parsed.
 */
public class PublicCounter {
    private File fileName;

    public PublicCounter(File fileName) {
        this.fileName = fileName;
    }

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    /*takes in the fileName, then using JavaParser, goes through to check for the keyword public
    incrementing as it goes through.
     */
    public int counter(File fileName){
        int publicCounter = 0;





        return publicCounter;
    }
}
