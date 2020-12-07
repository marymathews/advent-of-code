import java.io.IOException;
import java.util.*;

class AocDayTwo {
    final String regexPattern = "[0-9]+-[0-9]+\s[a-z]:\s[a-z]+";
    public static void main(String[] args) throws IOException {
        AocDayTwo obj = new AocDayTwo();
        int validPasswordCount = 0;
        ArrayList<String> puzzleInput = obj.inputPuzzle();
        for(String puzzle : puzzleInput) {
            if(obj.validateInput(puzzle) && obj.isValidPassword(puzzle))
                validPasswordCount++;
        }
        System.out.println("\n" + validPasswordCount);
    }

    /* Enter CTRL + D (Mac OS) or CTRL + Z (Windows) to terminate input (EOF character) */
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

    boolean isValidPassword(String value) {
        int minCount = 0, maxCount = 0;
        char letterToFind = ' ';
        String password = "";
        try {
            String[] hypenSplitString = value.split("-");
            String[] spaceSplitString = hypenSplitString[1].split(" ");
            minCount = Integer.parseInt(hypenSplitString[0]);
            maxCount = Integer.parseInt(spaceSplitString[0]);
            letterToFind = spaceSplitString[1].charAt(0);
            password = spaceSplitString[2];
            return isValid(minCount, maxCount, letterToFind, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean isValid(int min, int max, char letter, String password) {
        int count = 0;
        for(int i = 0; i < password.length(); i++) {
            if(password.charAt(i) == letter)
                count++;
        }
        return (min <= count && count <= max);
    }
}