package CoffeeMachine;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();

        System.out.println("Write action (buy, fill, take, remaining, exit):");

        while (true) {
            String input = scanner.nextLine();

            if ("exit".equals(input)) {
                break;
            }

            machine.processInput(input);
        }


        scanner.close();
        System.out.println("Coffee machine turned off.");
    }
}