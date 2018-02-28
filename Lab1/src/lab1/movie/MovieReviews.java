package lab1.movie;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lab1.data.DataReader;
public class MovieReviews {
    private TreeMap<String,Double> scoreOfEachWord;
    private TreeMap<String,Integer> occurences;
    private String dataFile;
    private String ignoredWordsFile;
    private TreeSet<String> ignoredWords;
    DataReader dataReader;
    public MovieReviews(String dataFile,String ignoredWordsFile){
        dataReader = new DataReader(dataFile,ignoredWordsFile);
        dataReader.inputIgnoredWord();
        dataReader.readDataFromFile();
        scoreOfEachWord = dataReader.getScoreOfEachWord();
        occurences = dataReader.getOccurences();
        ignoredWords = dataReader.getIgnoredWords();
    }
    public double wordScore(String word){
        word = word.toLowerCase();
        Double result = scoreOfEachWord.get(word);
        if(result == null) result = 2.0;
        return result;
    }
    public double reviewScore(String review){
        String parts[] = review.split("\\W");
        int n=0;
        double result = 0;
        for(String s:parts){
            if(!ignoredWords.contains(s)){
                result+=wordScore(s);
                n++;
            }
        }
        result/=n;
        return result;
    }
    public String mostPositive(){
        double maxScore = 0.0;
        String result = new String();
        Set<String> keys = occurences.keySet();
        for(String key:keys){
            if(occurences.get(key)>=2){
                if(scoreOfEachWord.get(key)>maxScore){
                    maxScore = scoreOfEachWord.get(key);
                    result = key;
                }
            }
        }
        return result;
    }
    public String mostNegative(){
        double minScore = 100;
        String result = new String();
        Set<String> keys = occurences.keySet();
        for(String key:keys){
            if(occurences.get(key)>=2){
                if(scoreOfEachWord.get(key)<minScore){
                    minScore = scoreOfEachWord.get(key);
                    result = key;
                }
            }
        }
        return result;
    }
}
