import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Converter {

    // java file path is first arg, path to new text file is second arg; so args must = 2.
    // for example, to run this program, you would use the command (in terminal or command prompt)
    // "java Converter /path/to/java/file /path/to/new/text/file"
    // if sample java file is in same directory as this program, just use "java Converter (file.java) output.txt"
    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }

        // first arg
        File sourceFile = new File(args[0]);

        // second arg
        File destinationFile = new File(args[1]);

        // creates a new FileReader object, to read from the source file
        try (FileReader fileReader = new FileReader(sourceFile);

             // creates a new FileWriter object, to write to the dest file
             FileWriter fileWriter = new FileWriter(destinationFile)) {

            // int is used to check for end of file. -1 means end of file, any other int means there are more chars in file to read
            int readChar;
            while ((readChar = fileReader.read()) != -1) {
                fileWriter.write(readChar);
            }

        } catch (IOException e) {
            // do nothing on exception
        }

        //Used to create an AST then calls the analyzers to check/correct the javaFile
        try {
            CompilationUnit cu = JavaParseUtility.parseJavaFile(sourceFile);
            //run the methodAnalyzer/DecisionAnalyzer/LoopAnalyzer
            MethodAnalyzer methodAnalyzer = new MethodAnalyzer(cu);
            methodAnalyzer.methodSyntaxAnalyzer();


            //LoopAnalyzer loopAnalyzer = new LoopAnalyzer(cu);
            //DecisionAnalyzer decisionAnalyzer = new DecisionAnalyzer(cu);

            //counts how many times public appears in the provided file
            PublicCounter counter = new PublicCounter(cu);
            int count = counter.countPublicModifiers();
            System.out.println("Number of public modifiers:" + count);
        }catch (IOException ex){
            System.out.println("Could not find file " + sourceFile);
        }

        //after this, it will need to append the changes made by the MethodAnalyzer/other classes
        //as well as append the counter at the end of the file
    }
}
