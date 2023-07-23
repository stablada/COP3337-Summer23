import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Dictionary {
    public static WordList wordList = new WordList();
    public static ArrayList<String> deleted = new ArrayList<String>();


    public static void main(String args[]) throws IOException, ClassNotFoundException {
        init();
        MainUI mainUI = new MainUI();
    }

    public static ArrayList<WordMeaningNode> queryName(String keyword){
            keyword = keyword.toLowerCase();
            ArrayList<WordMeaningNode> output = new ArrayList<WordMeaningNode>();
            WordMeaningNode current = wordList.getHead();
            for(int i = 0; i < wordList.getSize(); i++){
                if(current.getWordMeaning().getWord().toLowerCase().contains(keyword)){
                    output.add(current);
                }
                current = current.getNext();
            }
            if (output.size() == 0)
                return null;
            else
                return output;
    }





    public static void init() throws IOException, ClassNotFoundException {
        try {
            File save = new File("wordList.db");
            if (!save.createNewFile()) {
                FileInputStream is1 = new FileInputStream("wordList.db");
                ObjectInputStream is2 = new ObjectInputStream(is1);
                wordList = (WordList) is2.readObject();
                is2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File save = new File("delWordList.db");
            if (!save.createNewFile()) {
                FileInputStream is1 = new FileInputStream("delWorldList.db");
                ObjectInputStream is2 = new ObjectInputStream(is1);
                deleted = (ArrayList<String>) is2.readObject();
                is2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void close() throws IOException {
        FileOutputStream os1 = new FileOutputStream("inventory.db");
        ObjectOutputStream os2 = new ObjectOutputStream(os1);
        os2.writeObject(wordList);
        os2.close();

        FileOutputStream os3 = new FileOutputStream("deleted.db");
        ObjectOutputStream os4 = new ObjectOutputStream(os3);
        os4.writeObject(deleted);
        os4.close();
    }
}
