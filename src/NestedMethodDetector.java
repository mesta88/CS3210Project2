import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;

public class NestedMethodDetector extends VoidVisitorAdapter<Void> {
    private boolean hasNestedMethods = false;

    @Override
    public void visit(MethodDeclaration n, Void arg){
        for (MethodDeclaration nestedMethod : n.findAll(MethodDeclaration.class)){
            if(!nestedMethod.equals(n)){
                hasNestedMethods = true;
                break;
            }
        }
        super.visit(n, arg);
    }

    public boolean hasNestedMethods(){
        return hasNestedMethods;
    }

    public void fixNestedMethods(CompilationUnit cu){
        //this approach doesn't work
//        cu.findAll(MethodDeclaration.class).forEach(method ->{
//            ArrayList<MethodDeclaration> nestedMethods = new ArrayList<>();
//            method.findAll(MethodDeclaration.class).forEach(nestedMethod ->{
//                if(!nestedMethod.equals(method)){
//                    nestedMethods.add(nestedMethod);
//                }
//            });
//
//            for (MethodDeclaration nestedMethod : nestedMethods){
//                nestedMethod.remove();
//            }
//
//            for (MethodDeclaration nestedMethod : nestedMethods){
//                method.getParentNode().ifPresent(classOrInterfaceDeclaration -> {
//                    classOrInterfaceDeclaration.addMember(nestedMethod.clone());
//                });
//            }
//        });

        //Another approach that also doesn't work.
        NodeIterator nodeIterator = new NodeIterator(node -> {
            if(node instanceof MethodDeclaration){
                MethodDeclaration method = (MethodDeclaration) node;
                for (MethodDeclaration nestedMethod : method.findAll(MethodDeclaration.class)){
                    if(!nestedMethod.equals(method)){
                        //removes nested method from it current location
                        nestedMethod.remove();
                        //adds the same method(nested) to the class after OG method
                        method.getParentNode().ifPresent(parent -> {
                            nestedMethod.clone();
                        });
                    }
                }
            }
            return true;
        });
        nodeIterator.explore(cu);
    }
}
