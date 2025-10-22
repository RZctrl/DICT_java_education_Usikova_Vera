package CoffeeMachine;

import java.util.*;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many ml of water the coffee machine has?");
        int water = scanner.nextInt();
        System.out.println("How many ml of milk the coffee machine has?");
        int milk = scanner.nextInt();
        System.out.println("How many grams of coffee beans the coffee machine has?");
        int beans = scanner.nextInt();
        System.out.println("How many cups of coffee you will need?");
        int cupsNeed = scanner.nextInt();

        int maxCupsWater = water / 200;
        int maxCupsMilk = milk / 50;
        int maxCupsBeans = beans / 15;

        int maxCups = Math.min(Math.min(maxCupsWater, maxCupsMilk), maxCupsBeans);

        if (maxCups == cupsNeed) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (maxCups > cupsNeed) {
            int extraCups = maxCups - cupsNeed;
            System.out.println("Yes, I can make that amount of coffee (and even " + extraCups + " more than that)");
        } else {
            System.out.println("No, I can make only " + maxCups + " cups of coffee");
        }

        scanner.close();
    }
}
