package assignment.controller;

import java.io.FileNotFoundException;
import assignment.dao.Result;
import assignment.service.ProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class FindWordController {

    @Autowired
    private ProcessingService processingService;


    @RequestMapping(value="/findWord", method = RequestMethod.GET)
    public ResponseEntity<?> findWord(@RequestParam String word) throws FileNotFoundException {
        if(word == null || !word.matches("[a-zA-Z]+")){
            return new ResponseEntity<String>("Not a valid English word", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Result>(processingService.countWord(word), OK);

    }
}
