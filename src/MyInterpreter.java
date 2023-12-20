
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class MyInterpreter {
    private HashMap<String, Integer> hashMap;

    public MyInterpreter() {

        this.hashMap = new HashMap<>();
    }

    public void interpreter(String str1) {
        String[] arr = str1.split(";");

        for (String s : arr) {
            s = s.trim(); // remove whitespace
            if (!s.isEmpty()) {
                if (!parseAndExecuteString(s)) {
                    System.out.println();
                    System.out.println("Output:");
                    System.out.println("error!");
                    return;
                }
            }
        }

        // Print output
        System.out.println();
        System.out.println("Output:");
        for (String id : hashMap.keySet()) {
            System.out.println(id + " = " + hashMap.get(id));
        }
    }

    public boolean parseAndExecuteString(String str2) {
        String[] arr1 = str2.split("=");
        if (arr1.length != 2) {
            return false;
        }

        String identifier = arr1[0].trim();
        if (!isValidIdentifier(identifier)) {
            return false;
        }

        String expression = arr1[1].trim();
        int value = evaluateExpression(addValueToString(expression));

        if (value == Integer.MIN_VALUE) {
            return false;
        }

        hashMap.put(identifier, value);
        return true;
    }

    // Checking identifier
    public boolean isValidIdentifier(String identifier) {

        return identifier.matches("[a-z_A-Z]\\w*");
    }

    public boolean isNumericString(String str3) {
        for (int j = 0; j < str3.length(); j++) {
            if (!Character.isDigit(str3.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    public String addValueToString(String str4) {
        String strings = "";
        for (int n = 0; n < str4.length(); n++) {
            String st = String.valueOf(str4.charAt(n));
            if (hashMap.containsKey(st)) {
                strings += hashMap.get(st);
            } else {
                strings += str4.charAt(n);
            }
        }
        return strings;
    }

    public int evaluateExpression(String expression) {
        int result;
        if (expression.length() > 1 && expression.charAt(0) == '0') {
            return Integer.MIN_VALUE;
        } else if (isNumericString(expression)) {
            return Integer.parseInt(expression);
        } else {
            /* Implement expression evaluation:
            Converting infix expression to postfix expression
            Evaluating postfix expression
            */
            String postfixString = InfixToPostfix.infixToPostfix(expression);
            result = EvaluatePostfix.evaluatePostfix(postfixString);
        }
        return result;
    }

    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        System.out.println();
        System.out.println("Read the data of the file: ");
        System.out.println(data);
        return data;
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a file: ");
        String data = readFileAsString(scan.nextLine());

        MyInterpreter mypreter = new MyInterpreter();
        mypreter.interpreter(data);

    }
}