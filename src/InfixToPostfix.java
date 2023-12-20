
import java.util.Stack;
import java.lang.Character;
public class InfixToPostfix {
    // Checking letters
    static boolean isletterNotDigit(char c) {
        if (Character.isLetterOrDigit(c))
            return true;
        else
            return false;
    }

    // Operator having higher precedence
    static int getPrecedence(char ch) {

        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        else
            return -1;
    }

    // Operator has Left --> Right associativity
    static boolean hasLeftAssociativity(char ch) {
        if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
            return true;
        } else {
            return false;
        }
    }

    // Converting infix to postfix expression
    static String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<>();
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            // If the scanned Token is an operand, add it to output
            if (isletterNotDigit(c)) {
                output.append(c);
            }
            // If the scanned Token is an '(', push it to the stack
            else if (c == '(') {
                stack.push(c);
            }
            // If the scanned Token is an ')' pop and append it to output from the stack
            // until an '(' is encountered
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    output.append(' ');
                    output.append(stack.pop());
                }
                stack.pop();
            }
            // If an operator is encountered then taken the further action based on
            // the precedence of the operator
            else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek()) && hasLeftAssociativity(c)) {
                    output.append(' ');
                    output.append(stack.pop());
                }
                output.append(' ');
                stack.push(c);
            }
        }

        // pop all the remaining operators from the stack and append them to output
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "This expression is invalid";
            }
            output.append(' ');
            output.append(stack.pop());
        }

        String outputString = String.valueOf(output);
        return outputString;
    }
}
