import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Preprocessor {
    Stack<Character> stack = new Stack<>();
    String input;
    public Preprocessor(File file){
        try {
            input = Files.readString(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file.");
            throw new RuntimeException(e);
        }

    }
    public boolean checkDelimiters(){
        for(int i = 0; i < input.length(); i++){
            switch(input.charAt(i)){
                case '(', '{', '[':
                    stack.push(input.charAt(i));
                    break;
                case ')', '}', ']':
                    if(stack.isEmpty()){
                        return false;
                    }
                    if(input.charAt(i) != stack.peek()){
                        return false;
                    }
                    stack.pop();
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    public boolean checkSyntax() {
        Stack<String> stack = new Stack<>();
        String[] lines = input.split("\n");

        for (String line : lines) {
            String trimmedLine = line.trim();

            if (trimmedLine.startsWith("if") || trimmedLine.startsWith("for")) {
                stack.push(trimmedLine);
            } else if (trimmedLine.startsWith("}")) {
                if (stack.isEmpty()) {
                    return false; // Found a closing bracket without corresponding opening statement
                }

                String top = stack.peek();
                if (trimmedLine.equals("}")) {
                    if (!top.equals("{")) {
                        return false; // Found a closing bracket without corresponding opening statement
                    }
                    stack.pop();
                } else if (top.startsWith("if") && trimmedLine.equals("} else {")) {
                    stack.pop(); // Matching if-else found, pop the if statement from stack
                    stack.push("if-else"); // Push if-else marker onto the stack
                } else if (top.startsWith("if") && trimmedLine.equals("}")) {
                    stack.pop(); // Matching if statement found, pop it from the stack
                } else {
                    return false; // Found an unexpected closing bracket
                }
            }
        }

        return stack.isEmpty();
    }
}
