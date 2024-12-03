import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static char[] getCharArray(Path path) {
        try {
            Stream<String> input = Files.lines(path);
            List<String> lines = input.collect(Collectors.toList());
            int length = 0;
            for (String s : lines) {
                length += s.length();
            }
            char[] char_arr = new char[length];
            int idx = 0;
            for (String s : lines) {
                for (char c : s.toCharArray()) {
                    char_arr[idx++] = c;
                }
            }
            return char_arr;
        } catch (Exception e) {
        }
        return new char[0];
        // get the length of input

    }

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        char[] char_arr = getCharArray(path);
        System.out.println(char_arr.length);
        int first_num = 0;
        int second_num = 0;
        boolean get_second = false;
        int acc = 0;
        ArrayList<Character> num_buff = new ArrayList<>();
        for (int i = 0; i < char_arr.length; i += 1) {
            if (get_second) {
                while (i < char_arr.length && char_arr[i] != ')') {
                    num_buff.add(char_arr[i++]);
                }
                // List -> String -> int
                StringBuilder sb = new StringBuilder();
                for (Character ch : num_buff) {
                    sb.append(ch);
                }
                String numberString = sb.toString();
                try {
                    second_num = Integer.parseInt(numberString);
                    System.out.println("first_num: " + first_num + " second num" + second_num);
                    System.out.println("acc: " + acc);
                    acc += first_num * second_num;
                } catch (NumberFormatException e) {
                }
                get_second = false;
                first_num = 0;
                second_num = 0;
                num_buff = new ArrayList<>();

            }
            // getting the 1st number
            else if (char_arr[i] == 'm' && char_arr[i + 1] == 'u'
                    && char_arr[i + 2] == 'l' && char_arr[i + 3] == '(') {
                i += 4;
                if (i > char_arr.length - 1) {
                    break;
                }
                while (i < char_arr.length && char_arr[i] != ',') {
                    num_buff.add(char_arr[i++]);
                }
                // List -> String -> int
                StringBuilder sb = new StringBuilder();
                for (Character ch : num_buff) {
                    sb.append(ch);
                }
                String numberString = sb.toString();
                try {
                    first_num = Integer.parseInt(numberString);
                    get_second = true;
                } catch (NumberFormatException e) {
                }
                num_buff = new ArrayList<>();
            }
        }
        System.out.println(acc);
    }
}
