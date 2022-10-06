/*
Pig Latin Translator
    translates phrases into pig latin
*/
import java.util.Scanner;

public class PigLatinStr {
    Scanner input = new Scanner(System.in);
    String ending = "ay", vowelEnd = "yay", compileStr = "", newStr = "", token = " ";
    boolean isVowel = false;

    private void getInput(){
        System.out.println("Enter a sentence: ");
        String originalStr = input.nextLine();
        input.close();
        Translate(originalStr);
    }

    private void Translate(String originalStr){
        String[] strArr = originalStr.split(token);
        for(int k = 0; k <= strArr.length-1; k++){
            String tmpStr = "";
            tmpStr = strArr[k];
            char[] chaArr = tmpStr.toCharArray();

            // set boolean to TRUE if initial character is a vowel
            if(chaArr[0] == 'a' || chaArr[0] == 'e' || chaArr[0] == 'i' || chaArr[0] == 'o' || chaArr[0] == 'u'){ isVowel = true; }

            int arrSize = tmpStr.length();
            char initial = chaArr[0];
            for(int j = 0; j < chaArr.length-1; j++){ chaArr[j] = chaArr[j+1]; }
            chaArr[arrSize-1] = initial;
            compileStr = String.valueOf(chaArr);
            
            if(isVowel){ compileStr = compileStr + vowelEnd; }
            else if(!isVowel){ compileStr = compileStr + ending; }

            if(k != strArr.length){
                newStr = newStr + compileStr + token;
            }
            else{
                newStr = newStr + compileStr;
            }
        }
        System.out.println(newStr);
    }

    public static void main(String[] args){
        PigLatinStr translate = new PigLatinStr();
        translate.getInput();
    }
}
