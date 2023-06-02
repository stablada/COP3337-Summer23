import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Reverser extends Transpose
{
    public Reverser(String s) {super(s);}

    public String reverseText(String word) {
        StringTokenizer code = new StringTokenizer(word);
        ArrayList<String> temp = new ArrayList<String>();
        while(code.hasMoreTokens()) {
            temp.add(code.nextToken());
        }
        Collections.reverse(temp);
        String result = "";
        for(String s : temp) {
            result += s + " ";
        }
        return result;
    }
    public String decode(String word) {return super.decode(word);}
}