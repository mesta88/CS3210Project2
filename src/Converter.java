import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

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
            // Read the original file, copy contents to file writer.
            // This re-writes the original contents, as requested.
            while ((readChar = fileReader.read()) != -1) {
                fileWriter.write(readChar);
            }

        } catch (IOException e) {
            System.out.println("Could not find file " + sourceFile);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter("fixedFile.java"));

            // Create a stack to keep track of which braces are open.
            Stack<Integer> expectedIndentation = new Stack<>();
            expectedIndentation.push(0);
            String line;
            boolean isInComment = false;

            // Re-loop over the file, reading line by line
            while ((line = reader.readLine()) != null) {
                line = line.replace("    ", "\t");

                // Skip empty lines.
                if (line.isEmpty()) {
                    // Write line to the fixedFile
                    writer.write(line);
                    writer.newLine();
                    continue;
                }

                // Check if this is the start of a comment.
                if (line.contains("/**")) {
                    isInComment = true;
                }

                // Ignore comments.
                if (isInComment) {
                    // Write line to the fixedFile
                    writer.write(line);
                    writer.newLine();

                    // Check if this is the end of a comment.
                    if (line.contains("*/")) {
                        isInComment = false;
                    }

                    continue;
                }

                // Get the contents of the line
                // Get the number of indentation spaces at the front of the line
                int whitespaceCount = 0;
                for (char c : line.toCharArray()) {
                    if (c == '\t') {
                        whitespaceCount++;
                    } else {
                        break;
                    }
                }

                if (line.contains("}")) {
                    expectedIndentation.pop();
                }
                // If indentation doesn't match expected, put open or close brace as required
                while (expectedIndentation.peek() != whitespaceCount) {
                    if (whitespaceCount > expectedIndentation.peek()) {
                        for (int i = 0; i < expectedIndentation.peek(); i++) {
                            writer.write('\t');
                        }
                        writer.write('{');
                        expectedIndentation.push(expectedIndentation.peek() + 1);
                    } else if (whitespaceCount < expectedIndentation.peek()) {
                        for (int i = 0; i < (expectedIndentation.peek() - 1); i++) {
                            writer.write('\t');
                        }
                        writer.write('}');
                        expectedIndentation.pop();
                    }
                    writer.write('\n');
                }

                if (line.contains("{")) {
                    //adds expected indentation to the stack.
                    expectedIndentation.push(whitespaceCount + 1);
                }
                // Write line to the fixedFile
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();
        } catch (IOException exception) {
            System.out.println("Could not find file " + sourceFile);
        }

        File fixedFile = new File("fixedFile.java");

        int count = 0;
        // Creates a CompilationUnit using JavaParser to parse through the Java file and find each instance of "public".
        try {
            CompilationUnit cu = JavaParseUtility.parseJavaFile(fixedFile);

            //counts how many times public appears in the provided file
            PublicCounter counter = new PublicCounter(cu);
            count = counter.countPublicModifiers();
            // System.out.println("Number of public modifiers: " + count);
            // append the public modifiers count to the end of output.txt
        } catch (IOException ex) {
            System.out.println("Could not find file " + fixedFile);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fixedFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile, true));

            writer.append('\n');
            String line;

            while ((line = reader.readLine()) != null) {
                writer.append(line);
                writer.append('\n');
            }

            writer.append('\n');
            writer.append("Public count: " + count);

            reader.close();
            writer.close();

        }catch (IOException exception){
            System.out.println("Could not find file " + fixedFile);
        }


    }
}
