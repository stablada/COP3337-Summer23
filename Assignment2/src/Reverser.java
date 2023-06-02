public class Reverser extends Transpose
{
    public Reverser(String s)
    {
        super(s);
    }
    public String reverseText(String word)
    {
        StringBuffer result = new StringBuffer(word);
        for (int i = 0; i < word.length(); i++)
        {
            char ch = word.charAt(i);
            result.append(ch);
        }
        return result.toString();
    }
    public String decode(String word)
    {
        return reverseText(word);
    }
}