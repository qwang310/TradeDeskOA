package assignment.service;


import assignment.dao.Result;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/*
This service handle all the calculation of number of request
and number of occurrences in all the files
 */

@Service
public class ProcessingServiceNew {

    @Setter
    @Resource(name = "wordDictionary")
    private ConcurrentHashMap<String, Result> map;



    public Result countWord(String word) throws FileNotFoundException {
        Result result;
        synchronized(map) {
            if (map.containsKey(word)) {
                result = map.get(word);
                result.setNumberOfRequests(result.getNumberOfRequests() + 1);
                map.put(word, result);
                return result;
            } else {
                result = new Result(word, 1, 0);
                map.put(word, result);
                return result;
            }
        }

    }


}
