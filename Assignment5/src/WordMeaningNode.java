public class WordMeaningNode {
    private WordMeaning wordMeaning;
    private WordMeaningNode next;

    public WordMeaningNode(WordMeaning wordMeaning){
        this.wordMeaning = wordMeaning;
        next = null;
    }

    public WordMeaning getWordMeaning(){
        return wordMeaning;
    }

    public WordMeaningNode getNext(){
        return next;
    }

    public void setNext(WordMeaningNode next){
        this.next = next;
    }
}
