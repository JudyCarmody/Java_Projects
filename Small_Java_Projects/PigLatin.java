/*
    Single Word Pig Latin
*/
import java.util.Scanner;

public class PigLatin {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String ending = "ay";
        System.out.println("Enter a word: ");
        String originalStr = input.nextLine();
        char[] chaArr = originalStr.toCharArray();

        /*
            if initial character is NOT a vowel,
            move it to the end of array and add "ay"
        */
        if(chaArr[0] != 'a' && chaArr[0] != 'e' && chaArr[0] != 'i' && chaArr[0] != 'o' && chaArr[0] != 'u'){
            char initial = chaArr[0];    // store initial char if NOT vowel.
            for(int i = 0; i< chaArr.length-1; i++){
                chaArr[i] = chaArr[i+1];
            }
            //String initialStr = String.valueOf(initial);
            int arrSize = originalStr.length();
            chaArr[arrSize-1] = initial;
            String newStr = String.valueOf(chaArr);
            System.out.println(newStr);
            System.out.println(newStr + ending);
        }
        input.close();
    }
}
