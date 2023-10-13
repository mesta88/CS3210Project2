import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.AccessSpecifier;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import javassist.compiler.ast.Keyword;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class used for counting how many times the keyword "public" is found in the file parsed.
 */
public class PublicCounter {
    private File fileName;
    private CompilationUnit cu;

    public PublicCounter(CompilationUnit cu) {
        this.cu = cu;
    }

    public File getFileName() {
        return fileName;
    }

    public void setFileName(File fileName) {
        this.fileName = fileName;
    }

    public int countPublicModifiers() {
        AtomicInteger publicCount = new AtomicInteger(0);
        VoidVisitorAdapter<Void> visitor = new VoidVisitorAdapter<Void>() {
            //Checks for public used before class/interface declarations
            @Override
            public void visit(ClassOrInterfaceDeclaration pub, Void arg) {
                if (pub.isPublic()) {
                    publicCount.incrementAndGet();
                }
                super.visit(pub, arg);
            }

            //Check for public used before constructor declarations
            @Override
            public void visit(ConstructorDeclaration pub, Void arg){
                if (pub.isPublic()) {
                    publicCount.incrementAndGet();
                }
                super.visit(pub, arg);
            }

            //Checks for public used before method declaration
            @Override
            public void visit(MethodDeclaration pub, Void arg) {
                if (pub.isPublic()) {
                    publicCount.incrementAndGet();
                }
                super.visit(pub, arg);
            }
        };
        visitor.visit(cu, null);
        return publicCount.get();
    }
}