import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JavaParseUtility {

        public static CompilationUnit parseJavaFile(File fileName) throws IOException{
            return StaticJavaParser.parse(fileName);
        }

}
