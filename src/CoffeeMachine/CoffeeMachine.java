package CoffeeMachine;

import java.util.*;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int dispCups;
    private int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.dispCups = 9;
        this.money = 550;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();

            switch (action) {
                case "buy":
                    buyCoffeeInteractive(scanner);
                    break;
                case "fill":
                    fillInteractive(scanner);
                    break;
                case "take":
                    takeMoneyInteractive();
                    break;
                case "remaining":
                    displayStatus();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown action!");
            }
        }
        scanner.close();
    }

    private void buyCoffeeInteractive(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back -- to main menu:");
        String choice = scanner.next();

        if ("back".equals(choice)) {
            return;
        }

        try {
            int coffeeType = Integer.parseInt(choice);
            buyCoffee(coffeeType);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice!");
        }
    }

    private void buyCoffee(int coffeeType) {
        switch (coffeeType) {
            case 1: // espresso
                if (canMakeCoffee(250, 0, 16)) {
                    water -= 250;
                    beans -= 16;
                    dispCups -= 1;
                    money += 4;
                    System.out.println("I've enough resources, making you a coffee!");
                }
                break;
            case 2: // latte
                if (canMakeCoffee(350, 75, 20)) {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    dispCups -= 1;
                    money += 7;
                    System.out.println("I've enough resources, making you a coffee!");
                }
                break;
            case 3: // cappuccino
                if (canMakeCoffee(200, 100, 12)) {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    dispCups -= 1;
                    money += 6;
                    System.out.println("I've enough resources, making you a coffee!");
                }
                break;
            default:
                System.out.println("Invalid coffee type!");
        }
    }

    private boolean canMakeCoffee(int waterNeed, int milkNeed, int coffeeNeed) {
        if (water < waterNeed) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk < milkNeed) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (beans < coffeeNeed) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (dispCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    private void fillInteractive(Scanner scanner) {
        System.out.println("Write how many ml of water do you want to add:");
        int waterAdd = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milkAdd = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int coffeeAdd = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cupsAdd = scanner.nextInt();

        fillSupplies(waterAdd, milkAdd, coffeeAdd, cupsAdd);
        System.out.println("Supplies filled successfully!");
    }

    private void fillSupplies(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.dispCups += cups;
    }

    private void takeMoneyInteractive() {
        int takenMoney = money;
        money = 0;
        System.out.println("I gave you $" + takenMoney);
    }

    private void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(dispCups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

}