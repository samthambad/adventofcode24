
// The levels are either all increasing or all decreasing.
// Any two adjacent levels differ by at least one and at most three.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static ArrayList<String> removed1(String line) {
        List<String> list = Arrays.stream(line.split(" "))
                .collect(Collectors.toList());
        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 0; i < list.size(); i = i + 1) {
            ArrayList<String> copy = new ArrayList<>();
            for (String item : list) {
                // Creating a new instance of each element
                copy.add(item);
            }
            copy.remove(i);
            String oneRemoved = String.join(" ", copy);
            ans.add(oneRemoved);
        }
        return ans;
    }

    public static boolean lineChecker(String line, int removed_count) {
        boolean increasing = true;
        String[] xs = line.split(" ");
        int prev = 0;
        for (int i = 0; i < xs.length; i = i + 1) {
            if (xs[i].length() == 0) {
                continue;
            }
            int curr = Integer.parseInt(xs[i]);
            if (i == 0) {
                prev = Integer.parseInt(xs[i]);
            } else if (i == 1) {
                if (curr < prev) {
                    increasing = false;
                }
                int difference = Math.abs(curr - prev);
                if (difference < 1 || difference > 3) {
                    if (removed_count == 0) {
                        boolean possible = false;
                        // remove all the levels and check again
                        ArrayList<String> possibilities = removed1(line);
                        for (String string : possibilities) {
                            if (lineChecker(string, 1)) {
                                possible = true;
                                break;
                            }
                        }
                        return possible;
                    }
                    return false;
                }
                prev = curr;
            } else {
                if (increasing && curr < prev) {
                    if (removed_count == 0) {
                        boolean possible = false;
                        // remove all the levels and check again
                        ArrayList<String> possibilities = removed1(line);
                        for (String string : possibilities) {
                            if (lineChecker(string, 1)) {
                                possible = true;
                                break;
                            }
                        }
                        return possible;
                    }
                    return false;
                } else if (!increasing && curr > prev) {
                    if (removed_count == 0) {
                        boolean possible = false;
                        // remove all the levels and check again
                        ArrayList<String> possibilities = removed1(line);
                        for (String string : possibilities) {
                            if (lineChecker(string, 1)) {
                                possible = true;
                                break;
                            }
                        }
                        return possible;
                    }
                    return false;
                } else {
                    int difference = Math.abs(curr - prev);
                    if (difference < 1 || difference > 3) {
                        if (removed_count == 0) {
                            boolean possible = false;
                            // remove all the levels and check again
                            ArrayList<String> possibilities = removed1(line);
                            for (String string : possibilities) {
                                if (lineChecker(string, 1)) {
                                    possible = true;
                                    break;
                                }
                            }
                            return possible;
                        }
                        return false;
                    }
                    // otherwise continue
                    prev = curr;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try {
            // read line by line
            int acc = 0;
            List<String> input_list = Files.lines(path).toList();
            for (String line : input_list) {
                if (lineChecker(line, 0)) {
                    acc = acc + 1;
                }
            }
            System.out.println(acc);
        } catch (IOException ex) {
            // handle exception...
            System.out.println("Error found");
        }
    }
}
