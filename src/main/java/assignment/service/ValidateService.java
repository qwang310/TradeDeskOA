package assignment.service;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/*
-Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
-Must be between 5 and 12 characters in length.
-Must not contain any sequence of characters immediately followed by the same sequence.
 */

@Service
public class ValidateService {


    public Boolean Validate(String password){

        if(password == null || password.length() == 0){
            return false;
        }
        Boolean firstRule;
        Boolean secondRule;
        Boolean thirdRule;

        firstRule = FirstRule(password);
        secondRule = SecondRule(password);
        thirdRule = ThirdRule(password);


        if(!firstRule || !secondRule || !thirdRule){
            return false;
        }

        return true;
    }

    public Boolean FirstRule(String password){

        if(password.length() > 12 || password.length() < 5){
            return false;
        }
        return true;

//        String regex = "^.{6,12}$";
//        if (Pattern.matches(regex, password)) {
//            return true;
//        }else{
//            return false;
//        }
    }


    public Boolean SecondRule(String password){

        char[] charArray = password.toCharArray();
        Boolean lowerCaseLetterFlag = false;
        Boolean numberFlag = false;

        for(int i = 0;i < charArray.length;i++){
            char currentCharacter = charArray[i];
            if(Character.isLetterOrDigit(currentCharacter)){
                if(Character.isLowerCase(currentCharacter)){
                    lowerCaseLetterFlag = true;
                }else if(Character.isDigit(currentCharacter)){
                    numberFlag = true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }

        if(!lowerCaseLetterFlag || !numberFlag){
            return false;
        }

        return true;
    }



    public Boolean ThirdRule(String password){
        String regex = ".*(..+)\\1.*";
        if (Pattern.matches(regex, password)) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean ThirdRuleSecond(String password) {
        char[] charArray = password.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char currentCharacter = charArray[i];

            for (int j = i + 2; j < charArray.length; j++) {
                if (charArray[j] == currentCharacter) {
                    int index = 1;
                    while (i + index < j && j + index < charArray.length) {
                        if (charArray[i + index] != charArray[j + index]) {
                            break;
                        }
                        index++;
                    }
                    if (i + index == j) {
                        return false;
                    }
                }

            }
        }

        return true;

    }



}
