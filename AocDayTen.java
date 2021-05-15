import java.util.*;
class AocDayTen {
    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<Integer>();
        AocDayTen obj = new AocDayTen();

        input.add(0);
        obj.getInput(input);
        
        System.out.println(obj.buildChain(input));
    }

    void getInput(ArrayList<Integer> input) {
        Scanner in = new Scanner(System.in);
        while(true) {
            String number = in.nextLine();
            if(number.equals(""))
                break;
            try {
                input.add(Integer.parseInt(number));
            } catch(Exception e) {
                break;
            }
        }
        in.close();
    }

    int buildChain(ArrayList<Integer> input) {
        Collections.sort(input);
        int last = input.get(input.size() - 1);
        input.add(last + 3);

        int oneJoltCount = 0;
        int threeJoltCount = 0;

        for(int i = 0; i < input.size() - 1; i++) {
            if(input.get(i) == (input.get(i + 1) - 1))
                oneJoltCount++;
            else if(input.get(i) == (input.get(i + 1) - 3))
                threeJoltCount++;
        }

        return oneJoltCount * threeJoltCount;
    }
}
