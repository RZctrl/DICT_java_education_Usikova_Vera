package CreditCalculator;

public class CalculatorLogic {

    public void dataAnalyze(String type, String principalStr, String periodsStr,
                            String interestStr, String paymentStr) {


        if (!valParameters(type, principalStr, periodsStr, interestStr, paymentStr)) {
            System.out.println("Incorrect parameters");
            return;
        }




        double principal = principalStr != null ? Double.parseDouble(principalStr) : 0;
        int periods = periodsStr != null ? Integer.parseInt(periodsStr) : 0;
        double annualInterest = interestStr != null ? Double.parseDouble(interestStr) : 0;
        double payment = paymentStr != null ? Double.parseDouble(paymentStr) : 0;


        if (principal < 0 || periods < 0 || annualInterest < 0 || payment < 0) {
            System.out.println("Incorrect parameters");
            return;
        }

        double monthInterest = annualInterest / (12 * 100);

        switch (type) {
            case "diff":
                calcDiffPay(principal, periods, monthInterest);
                break;
            case "annuity":
                calcuAnnuity(principal, periods, monthInterest, payment);
                break;
            default:
                System.out.println("Incorrect parameters");
        }
    }

    private boolean valParameters(String type, String principalStr, String periodsStr,
                                  String interestStr, String paymentStr) {



        if (type == null || (!type.equals("diff") && !type.equals("annuity"))) {
            return false;
        }


        if (interestStr == null) {
            return false;
        }

        if (type.equals("diff")) {
            if (paymentStr != null) {
                return false;
            }
            return principalStr != null && periodsStr != null;
        }


        if (type.equals("annuity")) {
            int provParams = 0;
            if (principalStr != null) provParams++;
            if (periodsStr != null) provParams++;
            if (paymentStr != null) provParams++;

            return provParams == 2;
        }

        return false;
    }

    private void calcDiffPay(double principal, int periods, double monthlyInterest) {
        double totalPaid = 0;
        double basePay = principal / periods;

        for (int month = 1; month <= periods; month++) {
            double diffPay = basePay + monthlyInterest *
                    (principal - basePay * (month - 1));
            diffPay = Math.ceil(diffPay);
            totalPaid += diffPay;

            System.out.println("Month " + month + ": payment is " + (int)diffPay);
        }

        double overpay = totalPaid - principal;
        System.out.println("Overpayment = " + Math.round(overpay));
    }

    private void calcuAnnuity(double principal, int periods, double monthlyInterest, double payment) {
        if (principal == 0) {

            double annuityPay = (monthlyInterest * Math.pow(1 + monthlyInterest, periods)) /
                    (Math.pow(1 + monthlyInterest, periods) - 1);

            double loanPrincipal = payment / annuityPay;
            System.out.println("Your loan principal = " + Math.round(loanPrincipal) + "!");

        } else if (periods == 0) {
            double n = Math.log(payment / (payment - monthlyInterest * principal)) /
                    Math.log(1 + monthlyInterest);
            int months = (int) Math.ceil(n);
            displayAsYear(months);


            double overpayment = payment * months - principal;
            System.out.println("Overpayment = " + Math.round(overpayment));

        } else if (payment == 0) {
            double annuityPay = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, periods)) /
                    (Math.pow(1 + monthlyInterest, periods) - 1);

            annuityPay = Math.ceil(annuityPay);
            int roundPay = (int) annuityPay;
            if (roundPay < annuityPay) {
                roundPay = (int) Math.ceil(annuityPay);
            }


            System.out.println("Your annuity payment = " + roundPay + "!");


            double overpayment = roundPay * periods - principal;
            System.out.println("Overpayment = " + Math.round(overpayment));
        }
    }

    private void displayAsYear(int totalMonths) {
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