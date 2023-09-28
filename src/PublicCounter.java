import java.io.File;

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

    public int counter(File fileName){
        int publicCounter = 0;



        return publicCounter;
    }
}
