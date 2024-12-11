import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static boolean[][] visited;
    private static List<String> lines;
    private static List<List<Integer>> directions = new ArrayList<>();
    private static final String XMAS = "XMAS";

    public static void get_directions() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    directions.add(List.of(i, j));
                }
            }
        }
    }

    public static boolean has_xmas(int row, int col, List<Integer> direction) {
        int dx = direction.get(0);
        int dy = direction.get(1);
        for (int k = 0; k < 4; k++) {
            int ii = row + k * dx;
            int jj = col + k * dy;
            if (!(ii >= 0 && ii < lines.size() && jj >= 0 && jj < lines.get(ii).length())) {
                return false;
            }
            if (lines.get(ii).charAt(jj) != XMAS.charAt(k)) {
                return false;
            }
        }
        return true;
    }
    // public static boolean xmas_path(List<String> lines, int row, int col, char
    // prev) {
    // // System.out.println("xmas_path called, row:" + row + " col:" + col);
    // // out of bounds
    // if (row < 0 || col < 0 || row > lines.size() - 1 || col >
    // (lines.get(row).length() - 1)) {
    // return false;
    // }
    // if (visited[row][col]) {
    // return false;
    // }
    // char letter = lines.get(row).charAt(col);

    // if (letter == 'S') {
    // return prev == 'A' ? true : false;
    // }
    // // check letter
    // else if (letter == 'M' && prev != 'X') {
    // System.out.println("invalid letter after X");
    // return false;
    // } else if (letter == 'A' && prev != 'M') {
    // return false;
    // }
    // // to prevent going back to the same position
    // visited[row][col] = true;
    // // check all directions, total of 8 directions to check
    // // u
    // boolean found = xmas_path(lines, row - 1, col, letter)
    // // ur
    // || xmas_path(lines, row - 1, col + 1, letter)
    // // r
    // || xmas_path(lines, row, col + 1, letter)
    // // dr
    // || xmas_path(lines, row + 1, col + 1, letter)
    // // d
    // || xmas_path(lines, row + 1, col, letter)
    // // dl
    // || xmas_path(lines, row + 1, col - 1, letter)
    // // l
    // || xmas_path(lines, row, col - 1, letter)
    // // ul
    // || xmas_path(lines, row - 1, col - 1, letter);
    // visited[row][col] = false;
    // return found;

    // }

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");

        try {
            int count = 0;
            Stream<String> input = Files.lines(path);
            get_directions();
            // each line is an element in the list
            lines = input.collect(Collectors.toList());
            int rows = lines.size();
            int cols = lines.get(0).length();
            visited = new boolean[rows][cols];
            for (int i = 0; i < rows; i += 1) {
                for (int j = 0; j < cols; j += 1) {
                    for (List<Integer> d : directions) {
                        if (has_xmas(i, j, d))
                            count++;
                    }
                }
            }
            System.out.println("final count: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
