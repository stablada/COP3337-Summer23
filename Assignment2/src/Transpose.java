public class Transpose extends Cipher
{
    Transpose(String s)
    {
        super(s);
    }
    public String encode(String word)
    {
        StringBuffer result = new StringBuffer(word);
        result.reverse();
        return result.toString();
    }
    public String decode(String word)
    {
        return encode(word);
    }
}