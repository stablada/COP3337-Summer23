public class WordList {
    private WordMeaningNode head;
    private WordMeaningNode tail;
    private int size;

    public WordList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void add(WordMeaning wordMeaning){
        WordMeaningNode newNode = new WordMeaningNode(wordMeaning);
        if (head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void delete(WordMeaning wordMeaning){
        WordMeaningNode current = head;
        WordMeaningNode previous = null;
        while (current != null){
            if (current.getWordMeaning().equals(wordMeaning)){
                if (previous == null){
                    head = current.getNext();
                }
                else{
                    previous.setNext(current.getNext());
                }
                size--;
                break;
            }
            previous = current;
            current = current.getNext();
        }
    }

    public WordMeaningNode getHead(){
        return head;
    }

    public WordMeaningNode getTail(){
        return tail;
    }

    public int getSize(){
        return size;
    }
}
