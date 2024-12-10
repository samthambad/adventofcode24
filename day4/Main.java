import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static boolean xmas_path(List<String> lines, int row, int col, char prev) {
        System.out.println("xmas_path called, row:" + row + " col:" + col);
        // out of bounds
        if (row < 0 || col < 0 || row > lines.size() - 1 || col > lines.get(0).length() - 1) {
            return false;
        }
        char letter = lines.get(row).charAt(col);
        if (letter == 'S') {
            System.out.println("perhaps");
            return prev == 'A' ? true : false;
        }
        // check letter
        else if (letter == 'M' && prev != 'X') {
            return false;
        } else if (letter == 'A' && prev != 'M') {
            return false;
        } else {
            System.out.println("else condition entered");
            // check all directions, total of 8 directions to check
            // u
            return xmas_path(lines, row - 1, col, letter)
                    // ur
                    || xmas_path(lines, row - 1, col + 1, letter)
                    // r
                    || xmas_path(lines, row, col + 1, letter)
                    // dr
                    || xmas_path(lines, row + 1, col + 1, letter)
                    // d
                    || xmas_path(lines, row + 1, col, letter)
                    // dl
                    || xmas_path(lines, row + 1, col - 1, letter)
                    // l
                    || xmas_path(lines, row, col - 1, letter)
                    // ul
                    || xmas_path(lines, row - 1, col - 1, letter);
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");

        try {
            int count = 0;
            Stream<String> input = Files.lines(path);
            // each line is an element in the list
            List<String> lines = input.collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i += 1) {
                for (int j = 0; j < lines.get(0).length(); j += 1) {
                    if (lines.get(i).charAt(j) == 'X') {
                        System.out.println('x');
                        if (xmas_path(lines, i, j, 'X')) {
                            System.out.println("true returned");
                            count += 1;
                        }
                    }
                }
            }
            System.out.println("final count: " + count);
        } catch (Exception e) {
        }
    }
}
