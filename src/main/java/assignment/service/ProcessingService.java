package assignment.service;


import assignment.dao.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ProcessingService {


    @Resource(name = "wordMap")
    private HashMap<String, Result> map;

    private String txtfilesPath = "src/main/resources/txtfiles/";


    public Result countWord(String word) throws FileNotFoundException {
        Result result;
        if(map.containsKey(word)){
            result = map.get(word);
            result.setNumberOfRequests(result.getNumberOfRequests() + 1);
            map.put(word, result);
        }else{
            map.put(word, new Result(word, 1, 0));
            result = beginScanFolder(word);
            map.put(word, result);
        }

        return result;
    }

    public Result beginScanFolder(String word) throws FileNotFoundException {

        File folder = new File(txtfilesPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                Scanner inputFile = new Scanner(file);
                beginScanFile(inputFile, word);
            }
        }

        return map.get(word);

    }




    public void beginScanFile(Scanner inputFile, String word) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Result result = map.get(word);

        while(inputFile.hasNextLine()) {
            String input = inputFile.nextLine();
            lines.add(input);
        }

        for(String line : lines){
            String lettersOnly = line.replaceAll("[^a-zA-Z\\s]", "").trim();
            String[] stringList = lettersOnly.split("\\s+");

            for(String eachString : stringList){
                if(eachString.equals(word)){
                    result.setNumberOfOccurrences(result.getNumberOfOccurrences() + 1);
                }
            }

        }

        map.put(word, result);

    }

    public void setMap(HashMap<String, Result> map){
        this.map = map;
    }

    public void setTxtfilesPath(String path){
        this.txtfilesPath = path;
    }

}
