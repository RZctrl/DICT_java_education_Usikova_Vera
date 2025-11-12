package CreditCalculator;

import java.util.*;

public class CreditCalculator {
    private Scanner scanner;

    public CreditCalculator() {
        this.scanner = new Scanner(System.in);
    }

    public void begin() {
        System.out.println("What do you want to calculate?");
        System.out.println("type \"n\" - for number of monthly payments,");
        System.out.println("type \"a\" for annuity monthly payment amount,");
        System.out.println("type \"p\" - for the monthly payment:");



        String choice = scanner.nextLine();

        switch (choice) {
            case "n":
                calcNumberOfPay();
                break;
            case "a":
                calcAnnuityPay();
                break;
            case "p":
                calcLoanPrincipal();
                break;
            default:
                System.out.println("Invalid.");
        }
    }

    private void calcNumberOfPay() {
        System.out.println("Enter the loan principal:");
        double principal = scanner.nextDouble();

        System.out.println("Enter the monthly payment:");
        double payment = scanner.nextDouble();

        System.out.println("Enter the loan interest:");
        double annualInterest = scanner.nextDouble();



        double monthlyInterest = annualInterest / (12 * 100);


        double n = Math.log(payment / (payment - monthlyInterest * principal))
                / Math.log(1 + monthlyInterest);

        int months = (int) Math.ceil(n);

        displayMonthAsYear(months);
    }

    private void calcAnnuityPay() {
        System.out.println("Enter the loan principal:");
        double principal = scanner.nextDouble();

        System.out.println("Enter the number of periods:");
        int periods = scanner.nextInt();

        System.out.println("Enter the loan interest:");
        double annualInterest = scanner.nextDouble();


        double monthlyInterest = annualInterest / (12 * 100);


        double annuityPayment = principal *
                (monthlyInterest * Math.pow(1 + monthlyInterest, periods)) /
                (Math.pow(1 + monthlyInterest, periods) - 1);

        System.out.println("Your monthly payment = " + Math.round(annuityPayment) + "!");
    }

    private void calcLoanPrincipal() {
        System.out.println("Enter the annuity payment:");
        double payment = scanner.nextDouble();

        System.out.println("Enter the number of periods:");
        int periods = scanner.nextInt();

        System.out.println("Enter the loan interest:");
        double annualInterest = scanner.nextDouble();


        double monthlyInterest = annualInterest / (12 * 100);


        double principal = payment /
                ((monthlyInterest * Math.pow(1 + monthlyInterest, periods)) /
                        (Math.pow(1 + monthlyInterest, periods) - 1));

        System.out.println("Your loan principal = " + Math.round(principal) + "!");
    }

    private void displayMonthAsYear(int totalMonths) {
        int years = totalMonths / 12;
        int months = totalMonths % 12;

        if (years == 0) {
            System.out.println("It will take " + months + " months to repay this loan!");
        } else if (months == 0) {
            System.out.println("It will take " + years + (years == 1 ? " year" : " years") + " to repay this loan!");
        } else {
            System.out.println("It will take " + years + (years == 1 ? " year" : " years") +
                    " and " + months + " months to repay this loan!");
        }
    }
}