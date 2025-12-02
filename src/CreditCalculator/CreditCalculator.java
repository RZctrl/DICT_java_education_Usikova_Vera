package CreditCalculator;

public class CreditCalculator {
    public static void main(String[] args) {
        CalculatorLogic calculatorLogic = new CalculatorLogic();

        String type = System.getProperty("type");
        String principalStr = System.getProperty("principal");
        String periodsStr = System.getProperty("periods");
        String interestStr = System.getProperty("interest");
        String paymentStr = System.getProperty("payment");

        calculatorLogic.dataAnalyze(type, principalStr, periodsStr, interestStr, paymentStr);
    }
}