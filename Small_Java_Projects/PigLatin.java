/*
    Single Word Pig Latin
*/
import java.util.Scanner;

public class PigLatin {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String ending = "ay";
        String vowelEnd = "yay";
        System.out.println("Enter a word: ");
        String originalStr = input.nextLine();
        char[] chaArr = originalStr.toCharArray();
        boolean isVowel = false;

        // set boolean to TRUE if initial character is a vowel
        if(chaArr[0] == 'a' || chaArr[0] == 'e' || chaArr[0] == 'i' || chaArr[0] == 'o' || chaArr[0] == 'u'){ isVowel = true; }

        // store initial char
        char initial = chaArr[0];
        for(int i = 0; i< chaArr.length-1; i++){
            chaArr[i] = chaArr[i+1];
        }

        // create the Pig Latin string
        int arrSize = originalStr.length();
        chaArr[arrSize-1] = initial;
        String newStr = String.valueOf(chaArr);
        
        if(isVowel){ System.out.println(newStr + vowelEnd); }
        else{ System.out.println(newStr + ending); }

        input.close();
    }
}
