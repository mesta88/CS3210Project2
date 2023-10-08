import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The analyzer class for methods.
 * Will take in a .txt file of a Java, parse through the methods and determine if they are syntactically correct.
 * If the methods don't follow correct syntax, this class will fix it.
 */
public class MethodAnalyzer {
    //private static final String FILE_PATH = "SampleJavaFile.java";
    //private File fileName;
    private CompilationUnit cu;

    public MethodAnalyzer(CompilationUnit cu) {
        this.cu = cu;
    }

//    public File getFileName() {
//        return fileName;
//    }
//
//    public void setFileName(File fileName) {
//        this.fileName = fileName;
//    }


    /**
     * analyzes the syntax provided from the AST created by CompilationUnit
     */

    public void methodSyntaxAnalyzer() {
        VoidVisitorAdapter<Void> visitor = new VoidVisitorAdapter<>() {
            @Override
            public void visit(MethodDeclaration n, Void arg) {
                if(!n.getBody().isPresent()){
                    n.setBody(new BlockStmt());
                }
                super.visit(n, arg);
            }
        };

        visitor.visit(cu, null);
    }
}



