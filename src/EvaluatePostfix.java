
import java.util.Stack;
public class EvaluatePostfix {
    static int evaluatePostfix(String postExpression) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postExpression.length(); i++) {
            char c = postExpression.charAt(i);

            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                int n = 0;

                while (Character.isDigit(c)) {
                    n = n * 10 + (int)(c - '0');
                    i++;
                    c = postExpression.charAt(i);
                }
                i--;
                stack.push(n);
            }

            // If the scanned character is an operator, pop
            // two elements from stack apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
