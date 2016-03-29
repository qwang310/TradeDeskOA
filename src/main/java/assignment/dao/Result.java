package assignment.dao;

public class Result {

    private String word;
    private int numberOfRequests;
    private int numberOfOccurrences;

    public Result(String word, int count, int occurrences) {
        this.word = word;
        this.numberOfRequests = count;
        this.numberOfOccurrences = occurrences;
    }

    public String getWord() {
        return word;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int getNumberOfOccurrences(){
        return numberOfOccurrences;
    }

    public void setNumberOfRequests(int count){
        this.numberOfRequests = count;
    }

    public void setNumberOfOccurrences(int occurrences){
        this.numberOfOccurrences = occurrences;
    }

}
