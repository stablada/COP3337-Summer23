import java.util.StringTokenizer;
public abstract class Cipher
{
    private String message;
    StringBuffer encrypted_message, decrypted_message;

    public Cipher(String text) {message = text;}

    public final void encrypt() {
        encrypted_message = new StringBuffer();
        StringTokenizer words = new StringTokenizer(message);
        while(words.hasMoreTokens())
        {
            String s = words.nextToken();
            s = encode(s) + " ";
            encrypted_message.append(s);
        }
    }
    public final void decrypt(String message) {
        decrypted_message = new StringBuffer();
        StringTokenizer words = new StringTokenizer(message);
        while(words.hasMoreTokens())
        {
            String s = words.nextToken();
            s = decode(s) + " ";
            decrypted_message.append(s);
        }
    }

    public String getEncodedMessage() {return encrypted_message.toString();}
    public String getDecodedMessage() {return decrypted_message.toString();}
    public abstract String encode(String s);
    public abstract String decode(String s);
}