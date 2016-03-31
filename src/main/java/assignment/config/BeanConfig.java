package assignment.config;

import assignment.dao.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
    public class BeanConfig {

    @Bean
    public ConcurrentHashMap<String, Result> wordMap() {
        return new ConcurrentHashMap<String, Result>();
    }


    @Bean
    public Map<String, Result> wordDictionary() throws FileNotFoundException {

        String txtfilesPath = "src/main/resources/txtfiles/";
        File folder = new File(txtfilesPath);
        File[] listOfFiles = folder.listFiles();
        Map<String, Result> wordDictionary = new HashMap<String, Result>();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                Scanner inputFile = new Scanner(file);
                beginScanFile(inputFile, wordDictionary);
            }
        }

        return wordDictionary;
    }



    public void beginScanFile(Scanner inputFile, Map<String, Result> wordDictionary) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        while(inputFile.hasNextLine()) {
            String input = inputFile.nextLine();
            lines.add(input);
        }

        for(String line : lines){
            String lettersOnly = line.replaceAll("[^a-zA-Z\\s]", " ").trim();
            String[] stringList = lettersOnly.split("\\s+");

            for(String eachString : stringList){
                if(!wordDictionary.containsKey(eachString)){
                    wordDictionary.put(eachString, new Result(eachString, 0, 1));
                }else{
                    Result result = wordDictionary.get(eachString);
                    result.setNumberOfOccurrences(result.getNumberOfOccurrences() + 1);
                    wordDictionary.put(eachString, result);
                }

            }

        }

    }


}
