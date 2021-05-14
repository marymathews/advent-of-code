import java.util.*;
import java.lang.*;

class AocDayNine {
    public static void main(String[] args) {
        AocDayNine obj = new AocDayNine();

        ArrayList<Long> input = new ArrayList<Long>();
        obj.getInput(input);
        System.out.println(obj.findWeakNumber(input));
    }

    void getInput(ArrayList<Long> input) {
        Scanner in = new Scanner(System.in);
        while(true) {
            String number = in.nextLine();
            if(number.equals(""))
                break;
            try {
                input.add(Long.parseLong(number));
            } catch(Exception e) {
                break;
            }
        }
        in.close();
    }

    Long findWeakNumber(ArrayList<Long> input) {
        HashSet<Long> set = new HashSet<Long>();
        int removeIndex = 0;
        int returnIndex = 0;
        for(int i = 0; i < 24; i++)
            set.add(input.get(i));
        for(int i = 25; i < input.size(); i++) {
            if(!checkTwoSum(input.get(i), set)) {
                returnIndex = i;
                break;
            }
            else {
                set.add(input.get(i));
                set.remove(input.get(removeIndex));
                removeIndex++;
            }
        }
        return input.get(returnIndex);
    }

    boolean checkTwoSum(Long target, HashSet<Long> set) {
        for(Long element : set) {
            Long difference = target - element;
            if(set.contains(difference))
                return true;
        }
        return false;
    }
}