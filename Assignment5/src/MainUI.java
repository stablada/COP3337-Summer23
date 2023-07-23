import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainUI extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel DictList;
    private JPanel Deleted;
    private JTable deletedTable;
    private JTable dictionaryTable;
    private JButton saveButton;
    private JButton addButton;
    private JButton deleteButton;

    private String[] listNames = {"Word", "Meaning"};
    private String[] delCNames = {"Word"};


    MainUI(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Dictionary");
        this.setSize(600, 400);

        this.setVisible(true);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newWord = "";
                String newMeaning = "";
                Object[] options = {"Add", "Append"};
                Object answer = JOptionPane.showOptionDialog(null, "Would you like to Add a new word or Append a meaning to an already existing word?", "Add Menu",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
                switch ((String) answer) {
                    case "Add":
                        newWord = JOptionPane.showInputDialog("Enter the word you would like to add:");
                        newMeaning = JOptionPane.showInputDialog("Enter the meaning of the word you would like to add:");
                        Dictionary.wordList.add(new WordMeaning(newWord, newMeaning));
                        break;
                    case "Append":
                        newWord = JOptionPane.showInputDialog("Enter the word you would like to append a meaning to:");

                        newMeaning = JOptionPane.showInputDialog("Enter the meaning you would like to append to the word:");
                        Dictionary.wordList.add(new WordMeaning(newWord, newMeaning));
                        break;
                }
                updateListTable();
                updateDeletedTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wordToDelete = JOptionPane.showInputDialog("Enter the word you would like to delete:");
                if(Dictionary.queryName(wordToDelete) != null) {
                    WordMeaningNode delWord = Dictionary.queryName(wordToDelete).get(0);
                    Dictionary.wordList.delete(delWord.getWordMeaning());
                    Dictionary.deleted.add(wordToDelete);
                } else {
                    JOptionPane.showMessageDialog(null, "Word not found");
                }
                //add word to deletedList
                updateListTable();
                updateDeletedTable();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Dictionary.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void updateListTable(){
        String[][] data = new String[Dictionary.wordList.getSize()][2];
        WordMeaningNode current = Dictionary.wordList.getHead();
        for(int i = 0; i < Dictionary.wordList.getSize(); i++){
            data[i][0] = current.getWordMeaning().getWord();
            data[i][1] = current.getWordMeaning().getMeaning();
            current = current.getNext();
        }
        dictionaryTable.setModel(new DefaultTableModel(data, listNames));
    }

    public void updateDeletedTable(){
        String[][] data = new String[Dictionary.deleted.size()][1];
        for(int i = 0; i < Dictionary.deleted.size(); i++){
            data[i][0] = Dictionary.deleted.get(i);
        }
        deletedTable.setModel(new DefaultTableModel(data, delCNames));
    }
}
