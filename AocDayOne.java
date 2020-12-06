import java.io.*;
import java.util.*;

class AocDayOne {
    public static void main(String[] args) throws Exception {
        AocDayOne obj = new AocDayOne();
        HashSet<Integer> input = obj.inputPuzzleData();
        System.out.println(obj.result(input));
    }

    HashSet<Integer> inputPuzzleData() {
        Scanner in = new Scanner(System.in);
        HashSet<Integer> puzzleInput = new HashSet();
        while(true) {
            int num;
            try {
               num = in.nextInt(); 
            } catch(Exception e) {
                break;
            }
            puzzleInput.add(num);
        }
        in.close();
        return puzzleInput;
    }

    int result(HashSet<Integer> data) {
        int sum = 2020;
        int product = 0;
        for(Integer number : data) {
            int numberToFind = sum - number;
            if(data.contains(numberToFind)) {
                product = number * numberToFind;
                break;
            }
        }
        return product;
    }
}