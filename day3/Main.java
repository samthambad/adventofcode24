import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        char[] charArray = getCharArray(path);

        String input = new String(charArray);
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        Pattern activate = Pattern.compile("do()");
        Matcher activate_matcher = activate.matcher(input);
        Pattern deactivate = Pattern.compile("don't()");
        Matcher deactivate_matcher = deactivate.matcher(input);
        System.out.println(matcher);

        long acc = 0;
        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            acc += (long) num1 * num2;
        }
        System.out.println(acc);
    }
}
