package Task_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task_4 {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("data/calculator.log", true)) {
            Scanner scanner = new Scanner(System.in);
            //scanner.close();
            boolean isRunning = true;

            while (isRunning) {
                System.out.print("Enter the first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter the second number: ");
                double num2 = scanner.nextDouble();

                System.out.print("Select an operation (+, -, *, /): ");
                char operator = scanner.next().charAt(0);

                double result = performOperation(num1, num2, operator);
                System.out.println("Result: " + result);

                logOperation(fileWriter, num1, num2, operator, result);

                System.out.print("Do you want to continue? (yes/no): ");
                String choice = scanner.next();
                isRunning = choice.equalsIgnoreCase("yes");
                
            }
            scanner.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static double performOperation(double num1, double num2, char operator) {
        double result = 0.0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("Invalid operation");
        }

        return result;
    }

    private static void logOperation(FileWriter fileWriter, double num1, double num2, char operator, double result) {
        String logMessage = String.format("%f %c %f = %f%n", num1, operator, num2, result);

        try {
            fileWriter.write(logMessage);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
