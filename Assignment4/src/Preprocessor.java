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
    public boolean checkSyntax(){
        return false;
        //check the syntax by comparing to format
    }
}
