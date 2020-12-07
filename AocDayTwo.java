import java.io.IOException;
import java.util.*;

class AocDayTwo {
    final String regexPattern = "[0-9]+-[0-9]+\s[a-z]:\s[a-z]+";
    /* Set 2nd input to isValidPassword() to false for part 1 and true for part 2 */
    public static void main(String[] args) throws IOException {
        AocDayTwo obj = new AocDayTwo();
        int validPasswordCount = 0;
        ArrayList<String> puzzleInput = obj.inputPuzzle();
        for(String puzzle : puzzleInput) {
            if(obj.validateInput(puzzle) && obj.isValidPassword(puzzle, false))
                validPasswordCount++;
        }
        System.out.println("\n" + validPasswordCount);
    }

    /* Enter Enter and CTRL + D (Mac OS) or Enter and CTRL + Z (Windows) to terminate input (EOF character) */
    ArrayList<String> inputPuzzle() {
        ArrayList<String> input = new ArrayList<String>();
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            input.add(in.nextLine());
        }
        in.close();
        return input;
    }

    boolean validateInput(String value) {
        return value.matches(regexPattern);
    }

    boolean isValidPassword(String value, boolean isNew) {
        int firstNumber = 0, secondNumber = 0;
        char letterToFind = ' ';
        String password = "";
        try {
            String[] hypenSplitString = value.split("-");
            String[] spaceSplitString = hypenSplitString[1].split(" ");
            firstNumber = Integer.parseInt(hypenSplitString[0]);
            secondNumber = Integer.parseInt(spaceSplitString[0]);
            letterToFind = spaceSplitString[1].charAt(0);
            password = spaceSplitString[2];
            if(isNew) {
                return isValidAccordingToNewRules(firstNumber, secondNumber, letterToFind, password);
            } else {
                return isValidAccordingToOldRules(firstNumber, secondNumber, letterToFind, password);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean isValidAccordingToOldRules(int min, int max, char letter, String password) {
        int count = 0;
        for(int i = 0; i < password.length(); i++) {
            if(password.charAt(i) == letter)
                count++;
        }
        return (min <= count && count <= max);
    }

    boolean isValidAccordingToNewRules(int pos1, int pos2, char letter, String password) {
        boolean characterAtPosition1 = false, characterAtPosition2 = false;
        pos1--;
        pos2--;
        if(isValidIndex(pos1, password.length() - 1) && password.charAt(pos1) == letter)
            characterAtPosition1 = true;
        if(isValidIndex(pos2, password.length() - 1) && password.charAt(pos2) == letter)
            characterAtPosition2 = true;
        if(characterAtPosition1 && characterAtPosition2)
            return false;
        return (characterAtPosition1 || characterAtPosition2);       
    }

    boolean isValidIndex(int index, int maxIndex) {
        return (index >=0 && index <= maxIndex);
    }
}