import java.util.Scanner;

class Main {

private static final String[] ROMAN_NUMERALS = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    try {
        String result = calc(input);
        System.out.println(result);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

public static String calc(String input) throws Exception {
    String[] expression = input.split(" ");
    if (expression.length != 3) {
        throw new Exception("Invalid input!");
    }
    int a, b;
    try {
        a = Integer.parseInt(expression[0]);
        b = Integer.parseInt(expression[2]);
    } catch (NumberFormatException e) {
        throw new Exception("Invalid input!");
    }
    if (a < 1 || a > 10 || b < 1 || b > 10) {
        throw new Exception("Numbers should be between 1 and 10!");
    }
    int result;
    switch (expression[1]) {
        case "+":
            result = a + b;
            break;
        case "-":
            result = a - b;
            break;
        case "*":
            result = a * b;
            break;
        case "/":
            result = a / b;
            break;
        default:
            throw new Exception("Invalid operation!");
    }
    if (result < 1 && !expression[1].equals("/")) {
        throw new Exception("Roman numerals can only be positive!");
    }
    if (isRomanNumeral(expression[0]) && isRomanNumeral(expression[2])) {
        return fromArabicToRoman(result);
    } else if (!isRomanNumeral(expression[0]) && !isRomanNumeral(expression[2])) {
        return String.valueOf(result);
    } else {
        throw new Exception("Cannot mix Roman and Arabic numerals!");
    }
}

private static boolean isRomanNumeral(String s) {
    for (String romanNumeral : ROMAN_NUMERALS) {
        if (romanNumeral.equals(s)) {
            return true;
        }
    }
    return false;
}

private static String fromArabicToRoman(int number) {
    StringBuilder romanNumber = new StringBuilder();
    while (number > 0) {
        if (number >= 1000) {
            romanNumber.append("M");
            number -= 1000;
        } else if (number >= 900) {
            romanNumber.append("CM");
            number -= 900;
        } else if (number >= 500) {
            romanNumber.append("D");
            number -= 500;
        } else if (number >= 400) {
            romanNumber.append("CD");
            number -= 400;
        } else if (number >= 100) {
            romanNumber.append("C");
            number -= 100;
        } else if (number >= 90) {
            romanNumber.append("XC");
            number -= 90;
        } else if (number >= 50) {
            romanNumber.append("L");
            number -= 50;
        } else if (number >= 40) {
            romanNumber.append("XL");
            number -= 40;
        } else if (number >= 10) {
            romanNumber.append("X");
            number -= 10;
        } else if (number >= 9) {
            romanNumber.append("IX");
            number -= 9;
        } else if (number >= 5) {
            romanNumber.append("V");
            number -= 5;
        } else if (number >= 4) {
            romanNumber.append("IV");
            number -= 4;
        } else {
            romanNumber.append("I");
            number -= 1;
        }
    }
    return romanNumber.toString();
}
}