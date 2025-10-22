package CoffeeMachine;

import java.util.*;

public class CoffeeMachine {
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int dispCups = 9;
    static int money = 550;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printState();

        System.out.println("Write action (buy, fill, take):");
        String action = scanner.next();

        switch (action) {
            case "buy":
                buyCoffee(scanner);
                break;
            case "fill":
                fillSupplies(scanner);
                break;
            case "take":
                takeMoney();
                break;
        }

        printState();
        scanner.close();
    }

    public static void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(dispCups + " of disposable cups");
        System.out.println(money + " of money");
        System.out.println();
    }

    public static void buyCoffee(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1: // espresso
                if (water >= 250 && beans >= 16 && dispCups >= 1) {
                    water -= 250;
                    beans -= 16;
                    dispCups -= 1;
                    money += 4;
                    System.out.println("I've enough resources, making you a coffee!");
                } else {
                    System.out.println("Not enough resources.");
                }
                break;
            case 2: // latte
                if (water >= 350 && milk >= 75 && beans >= 20 && dispCups >= 1) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    dispCups -= 1;
                    money += 7;
                    System.out.println("I've enough resources, making you a coffee!");
                } else {
                    System.out.println("Not enough resources.");
                }
                break;
            case 3: // cappuccino
                if (water >= 200 && milk >= 100 && beans >= 12 && dispCups >= 1) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    dispCups -= 1;
                    money += 6;
                    System.out.println("I've enough resources, making you a coffee!");
                } else {
                    System.out.println("Not enough resources.");
                }
                break;
        }
        System.out.println();
    }

    public static void fillSupplies(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable coffee cups you want to add:");
        dispCups += scanner.nextInt();
        System.out.println();
    }

    public static void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
        System.out.println();
    }
}
