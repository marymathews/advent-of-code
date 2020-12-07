import java.util.*;

class AocDayOne {
    final static int sum = 2020;
    public static void main(String[] args) throws Exception {
        AocDayOne obj = new AocDayOne();
        HashSet<Integer> input = obj.inputPuzzleData();
        System.out.println(obj.resultTwoSum(input, sum));
        System.out.println(obj.resultThreeSum(input, sum));
    }

    HashSet<Integer> inputPuzzleData() {
        Scanner in = new Scanner(System.in);
        HashSet<Integer> puzzleInput = new HashSet<Integer>();
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

    int resultTwoSum(HashSet<Integer> data, int sum) {
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

    int resultThreeSum(HashSet<Integer> data, int sum) {
        HashSet<Integer> dataCopy = new HashSet<Integer>();
        dataCopy.addAll(data);
        int product = 0;
        for(Integer number : data) {
            int twoSum = sum - number;
            dataCopy.remove(number);
            product = number * resultTwoSum(dataCopy, twoSum);
            if(product != 0)
                break;
        }
        return product;
    }
}