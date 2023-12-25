package PostfixExpressionCalculator;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class PostfixExpressionCalculator {
    public static void main(String[] args) {
        String fileName = "in.dat";
        Scanner inputStream = null;

        try {
            inputStream = new Scanner(new File(fileName));
            System.out.println("Hello! This is a postfix expression calculator.\n");

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine().trim();
                double result = evaluatePostfix(line);
                System.out.println("The value of \"" + line + "\" is " + result);
            }

            inputStream.close();
            System.out.println("\nBye-bye!");

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
    }

    public static double evaluatePostfix(String expression) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            double operand2, operand1, result;

            switch (token) {
                case "+":
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    result = operand1 + operand2;
                    break;
                case "-":
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    result = operand1 - operand2;
                    break;
                case "*":
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    result = operand1 * operand2;
                    break;
                case "/":
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    result = operand1 / operand2;
                    break;
                case "^":
                    operand2 = stack.pop();
                    operand1 = stack.pop();
                    result = Math.pow(operand1, operand2);
                    break;
                case "#":
                    operand1 = stack.pop();
                    result = Math.sqrt(operand1);
                    break;
                case "_":
                    operand1 = stack.pop();
                    result = -operand1;
                    break;
                default:
                    stack.push(Double.parseDouble(token));
                    continue;
            }
            stack.push(result);
        }
        return stack.pop();
    }
}