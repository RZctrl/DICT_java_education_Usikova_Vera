package CoffeeMachine;

import java.util.*;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many cups of coffee you'll need?");
        int cups = scanner.nextInt();

        int water = cups * 200;
        int milk = cups * 50;
        int beans = cups * 15;

        System.out.println("For " + cups + " cups of coffee you'll need:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");

        scanner.close();
    }
}
