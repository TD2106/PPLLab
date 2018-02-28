package lab1.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataReader {
    private TreeMap<String,Double> scoreOfEachWord;
    private TreeMap<String,Integer> occurences;
    private TreeMap<String,Integer> totalScore;
    private TreeSet<String> ignoredWords;
    private String dataFileName;
    private String ignoredWordFileName;
    public DataReader(String dataFileName,String ignoredWordFileName){
        scoreOfEachWord = new TreeMap<>();
        occurences = new TreeMap<>();
        totalScore = new TreeMap<>();
        ignoredWords = new TreeSet<>();
        this.dataFileName = dataFileName;
        this.ignoredWordFileName = ignoredWordFileName;
    }

    public void inputIgnoredWord(){
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(ignoredWordFileName))){
            String s;
            while((s = reader.readLine())!=null){
                String parts[] = s.split("\\W");
                for(String word:parts){
                    word = word.toLowerCase();
                    ignoredWords.add(word);
                }
            }
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }

    public TreeMap<String, Double> getScoreOfEachWord() {
        return scoreOfEachWord;
    }

    public TreeMap<String, Integer> getOccurences() {
        return occurences;
    }

    public TreeSet<String> getIgnoredWords() {
        return ignoredWords;
    }

    public void readDataFromFile(){
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(this.dataFileName))){
            String s,parts[];
            while((s = reader.readLine())!= null){
                s=s.replaceAll("\\w+-\\w+","");
                parts = s.split("\\W");
                Integer reviewScore = Integer.parseInt(parts[0]);
                Integer temp;
                for(int i=1;i<parts.length;i++){
                    parts[i] = parts[i].toLowerCase();

                    if(!ignoredWords.contains(parts[i])&&parts[i].length()>1){
                        temp = occurences.get(parts[i]);
                        if(temp == null) temp = 0;
                        occurences.put(parts[i],temp+1);
                        temp = totalScore.get(parts[i]);
                        if(temp == null) temp = 0;
                        totalScore.put(parts[i],temp+reviewScore);
                    }
                }

            }
            Set<String> keys = occurences.keySet();
            for(String key:keys){
                scoreOfEachWord.put(key, totalScore.get(key).doubleValue()/occurences.get(key).doubleValue());
            }

        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
