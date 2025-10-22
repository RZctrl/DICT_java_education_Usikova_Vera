package CoffeeMachine;

import java.util.*;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int dispCups;
    private int money;
    private MachineState currentState;

    public enum MachineState {
        CHOOSING_ACTION,
        CHOOSING_COFFEE,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_COFFEE,
        FILLING_CUPS
    }


    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.dispCups = 9;
        this.money = 550;
        this.currentState = MachineState.CHOOSING_ACTION;
    }

    public void processInput(String input) {
        switch (currentState) {
            case CHOOSING_ACTION:
                handleAction(input);
                break;
            case CHOOSING_COFFEE:
                handleCoffeeChoice(input);
                break;
            case FILLING_WATER:
                handleFillingWater(input);
                break;
            case FILLING_MILK:
                handleFillingMilk(input);
                break;
            case FILLING_COFFEE:
                handleFillingCoffee(input);
                break;
            case FILLING_CUPS:
                handleFillingCups(input);
                break;
        }
    }

    private void handleAction(String input) {
        switch (input) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back -- to main menu:");
                currentState = MachineState.CHOOSING_COFFEE;
                break;
            case "fill":
                System.out.println("Write how many ml of water do you want to add:");
                currentState = MachineState.FILLING_WATER;
                break;
            case "take":
                takeMoney();
                promptForAction();
                break;
            case "remaining":
                displayStatus();
                promptForAction();
                break;
            case "exit":
                break;
            default:
                System.out.println("Unknown action!");
                promptForAction();
        }
    }

    private void handleCoffeeChoice(String input) {
        if ("back".equals(input)) {
            promptForAction();
            return;
        }

        try {
            int coffeeType = Integer.parseInt(input);
            buyCoffee(coffeeType);
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice/");
        }
        promptForAction();
    }

    private void handleFillingWater(String input) {
        try {
            int waterAdd = Integer.parseInt(input);
            if (waterAdd < 0) {
                System.out.println("Enter a positive number.");
                return;
            }
            this.water += waterAdd;
            System.out.println("Write how many ml of milk do you want to add:");
            currentState = MachineState.FILLING_MILK;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
    }

    private void handleFillingMilk(String input) {
        try {
            int milkAdd = Integer.parseInt(input);
            if (milkAdd < 0) {
                System.out.println("Enter a positive number!");
                return;
            }
            this.milk += milkAdd;
            System.out.println("Write how many grams of coffee beans do you want to add:");
            currentState = MachineState.FILLING_COFFEE;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
    }

    private void handleFillingCoffee(String input) {
        try {
            int coffeeAdd = Integer.parseInt(input);
            if (coffeeAdd < 0) {
                System.out.println("Enter a positive number!");
                return;
            }
            this.beans += coffeeAdd;
            System.out.println("Write how many disposable cups of coffee do you want to add:");
            currentState = MachineState.FILLING_CUPS;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
    }

    private void handleFillingCups(String input) {
        try {
            int cupsAdd = Integer.parseInt(input);
            if (cupsAdd < 0) {
                System.out.println("Enter a positive number!");
                return;
            }
            this.dispCups += cupsAdd;
            System.out.println("Supplies filled successfully!");
            promptForAction();
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number!");
        }
    }

    private void promptForAction() {
        currentState = MachineState.CHOOSING_ACTION;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
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
                System.out.println("Invalid coffee type.");
        }
    }

    private boolean canMakeCoffee(int waterNeed, int milkNeed, int coffeeNeed) {
        if (water < waterNeed) {
            System.out.println("Not enough water.");
            return false;
        }
        if (milk < milkNeed) {
            System.out.println("Not enough milk.");
            return false;
        }
        if (beans < coffeeNeed) {
            System.out.println("Not enough coffee beans.");
            return false;
        }
        if (dispCups < 1) {
            System.out.println("Not enough disposable cups.");
            return false;
        }
        return true;
    }

    private void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(dispCups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public MachineState getCurrentState() {
        return currentState;
    }

}