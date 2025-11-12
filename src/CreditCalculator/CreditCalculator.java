package CreditCalculator;

import java.util.*;

public class CreditCalculator {
    private Scanner scanner;

    public CreditCalculator() {
        this.scanner = new Scanner(System.in);
    }

    public void begin() {
        System.out.println("Enter the loan principal:");
        int principal = scanner.nextInt();

        System.out.println("What do you want to calculate?");
        System.out.println("type \"m\" - for number of monthly payments,");
        System.out.println("type \"p\" - for the monthly payment:");
        scanner.nextLine();
        String choice = scanner.nextLine();

        switch (choice) {
            case "m":
                calcMonths(principal);
                break;
            case "p":
                calcPayment(principal);
                break;
            default:
                System.out.println("Invalid.");
        }
    }

    private void calcMonths(int principal) {
        System.out.println("Enter the monthly payment:");
        int payment = scanner.nextInt();

        if (payment >= principal) {
            System.out.println("It will take 1 month to repay the loan");
        } else {
            int months = (int) Math.ceil((double) principal / payment);
            System.out.println("It will take " + months + " months to repay the loan");
        }
    }

    private void calcPayment(int principal) {
        System.out.println("Enter the number of months:");
        int months = scanner.nextInt();

        double basePayment = (double) principal / months;
        int payment = (int) Math.ceil(basePayment);

        if (principal % months == 0) {
            System.out.println("Your monthly payment = " + payment);
        } else {
            int lastPayment = principal - payment * (months - 1);
            System.out.println("Your monthly payment = " + payment + " and the last payment = " + lastPayment);
        }
    }
}