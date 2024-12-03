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
        Stream<String> input = Files.lines(path);
        List<String> lines = input.collect(Collectors.toList());
        // get the length of input
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

    }

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        char[] char_arr = getCharArray(path);
        int acc = 0;
        ArrayList<Character> num_buff = new ArrayList<>();
        for (int i = 0; i < char_arr.length; i += 1) {
            if (char_arr[i] == 'm' && char_arr[i + 1] == 'u'
                    && char_arr[i + 2] == 'l' && char_arr[i + 3] == '(') {
                i += 4;
                if (i > char_arr.length - 1) {
                    break;
                }
                while (char_arr[i] != ',') {
                    num_buff.add(char_arr[i]);
                }
                // check if number in num_buff is valid
            }
        }
    }
}
