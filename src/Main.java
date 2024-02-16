import java.util.Scanner;

public class Main {
    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3) throw new Exception();

        String[] romanNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        boolean nullPartRoman = false;
        boolean secondPartRoman = false;
        for (int x = 0; x < romanNums.length; x++) {
            if (parts[0].equals(romanNums[x])) {
                parts[0] = Integer.toString(x);
                nullPartRoman = true;
            }
            if (parts[2].equals(romanNums[x])) {
                parts[2] = Integer.toString(x);
                secondPartRoman = true;
            }
        }

        if (nullPartRoman != secondPartRoman || !parts[0].matches("[0-9]+") || !parts[2].matches("[0-9]+") || !"+-*/".contains(parts[1]))
            throw new Exception();

        return calculation(parts, nullPartRoman);
    }

    public static String calculation(String[] parts, boolean wasRoman) throws Exception {
        String[] romanNums = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[2]);
        if (x < 1 || x > 10 || y < 1 || y > 10) throw new Exception();
        int result = switch (parts[1]) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "/" -> x / y;
            case "*" -> x * y;
            default -> 0;
        };

        if (wasRoman) {
            if (result < 0) throw new Exception();
            return arabicToRoman(result, romanNums);
        }

        return Integer.toString(result);
    }

    public static String arabicToRoman(int input, String[] romanNums) {
        String[] romanMoreThanTen = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        return romanMoreThanTen[input / 10] + romanNums[input % 10];
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        System.out.printf(calc(input));
    }
}
