import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static ArrayList<ArrayList<Integer>> splitList(List<String> xs) {
    ArrayList<Integer> list0 = new ArrayList<Integer>();
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    for (String s : xs) {
        if (s.length() >0) {
            String[] splitted = s.split("   ");
            list0.add(Integer.parseInt(splitted[0]));
            list1.add(Integer.parseInt(splitted[1]));
        }
    }
    ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
    ans.add(list0);
    ans.add(list1);
    return ans;
  }
    public static int smallest(ArrayList<Integer> xs) {
        int min = xs.get(0);
        for (int s : xs) {
            if (s < min) {
                min = s;
            }
        }
        xs.remove(Integer.valueOf(min));
        return min;
    }
    public static int similarity(ArrayList<Integer> first, ArrayList<Integer> second) {
        int acc = 0;
        for (int each1 : first) {
            int counter = 0;
            for (int each2 : second) {
                if (each2 == each1) {
                    counter = counter + 1;
                }
            }
            acc = acc + counter * each1;
        }
        return acc;
    }
	public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try {
            List<String> input_list = Files.readAllLines(path);
            ArrayList<ArrayList<Integer>> splitted =  splitList(input_list);
            ArrayList<Integer> first_xs = splitted.get(0);
            ArrayList<Integer> second_xs = splitted.get(1);
            // run a for loop to find the smallest in each array
            int acc = 0;
            /*
            while(first_xs.size() > 0) {
                acc = acc + Math.abs(smallest(first_xs) - smallest(second_xs));
            }*/
            // Part2
            System.out.println(similarity(first_xs, second_xs));
        } catch (IOException ex) {
             // handle exception...
             System.out.println("Error found");
        }
	}
}
										    
