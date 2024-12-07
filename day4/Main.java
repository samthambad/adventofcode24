import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static boolean xmas_path(List<String> lines, char letter, int row, int col, char prev) {
        if (row < 0 || col < 0 || row > lines.size() - 1 || col > lines.get(0).length() - 1) {
            return false;
        }
        if (letter == 'S') {
            return prev == 'A' ? true : false;
        }
        // check letter
        else if (letter == 'M' && prev != 'X') {
            return false;
        } else if (letter == 'A' && prev != 'M') {
            return false;
            // } else if (row == 0 && col == 0) {
            // // r
            // return xmas_path(lines, lines.get(row).charAt(col + 1), row, col + 1, letter)
            // // dr
            // || xmas_path(lines, lines.get(row + 1).charAt(col + 1), row + 1, col + 1,
            // letter)
            // // d
            // || xmas_path(lines, lines.get(row + 1).charAt(col), row + 1, col, letter);
            // } else if (row == 0 && col == lines.get(0).length()) {
            // // l
            // return xmas_path(lines, lines.get(row).charAt(col - 1), row, col - 1, letter)
            // // dl
            // || xmas_path(lines, lines.get(row + 1).charAt(col - 1), row + 1, col - 1,
            // letter)
            // // d
            // || xmas_path(lines, lines.get(row + 1).charAt(col), row + 1, col, letter);
            // } else if (row == lines.size() - 1 && col == 0) {
            // // u
            // return xmas_path(lines, lines.get(row - 1).charAt(col), row - 1, col, letter)
            // // ur
            // || xmas_path(lines, lines.get(row - 1).charAt(col + 1), row - 1, col + 1,
            // letter)
            // // r
            // || xmas_path(lines, lines.get(row).charAt(col + 1), row, col + 1, letter);
            // } else if (row == lines.size() - 1 && col == lines.get(0).length()) {
            // // u
            // return xmas_path(lines, lines.get(row - 1).charAt(col), row - 1, col, letter)
            // // ul
            // || xmas_path(lines, lines.get(row - 1).charAt(col - 1), row - 1, col - 1,
            // letter)
            // // l
            // || xmas_path(lines, lines.get(row).charAt(col - 1), row, col - 1, letter);
        } else {
            // check all directions, total of 8 directions to check
            // u
            return xmas_path(lines, lines.get(row - 1).charAt(col), row - 1, col, letter)
                    // ur
                    || xmas_path(lines, lines.get(row - 1).charAt(col + 1), row - 1, col + 1, letter)
                    // r
                    || xmas_path(lines, lines.get(row).charAt(col + 1), row, col + 1, letter)
                    // dr
                    || xmas_path(lines, lines.get(row + 1).charAt(col + 1), row + 1, col + 1, letter)
                    // d
                    || xmas_path(lines, lines.get(row + 1).charAt(col), row + 1, col, letter)
                    // dl
                    || xmas_path(lines, lines.get(row + 1).charAt(col - 1), row + 1, col - 1, letter)
                    // l
                    || xmas_path(lines, lines.get(row).charAt(col - 1), row, col - 1, letter)
                    // ul
                    || xmas_path(lines, lines.get(row - 1).charAt(col - 1), row - 1, col - 1, letter);
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
                for (int j = 0; j < lines.size(); j += 1) {
                    if (lines.get(i).charAt(j) == 'X') {
                        System.out.println(xmas_path(lines, 'X', i, j, 'X'));
                        if (xmas_path(lines, 'X', i, j, 'X')) {
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
