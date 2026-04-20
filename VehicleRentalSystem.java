/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vehiclerentalsytem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author air
 */
public class VehicleRentalSystem {

    public static void main(String[] args) {
        
 
        Scanner input = new Scanner(System.in);
        
        // Settings
        final double DISCOUNT_RATE = 0.15;
        final String PROMO_CODE = "SAVE15";
        
        // Fleet Setup
        Car[] fleet = {
            new Car("Toyota Corolla", 500.0),
            new Car("BMW M3", 800.0),
            new Car("VW Polo", 400.0)
        };

        System.out.println("=== SOUTH COAST CAR RENTALS ===");
        System.out.println("Available Cars:");
        for (int i = 0; i < fleet.length; i++) {
            System.out.printf("%d. %s - R%.2f/day%n", (i + 1), fleet[i].getName(), fleet[i].getPricePerDay());
        }

        // User Choices
        System.out.print("\nSelect a car (1-3): ");
        int choice = input.nextInt();
        
        if (choice < 1 || choice > fleet.length) {
            System.out.println("Invalid selection. System exiting.");
            return;
        }
        
        Car selectedCar = fleet[choice - 1];

        System.out.print("How many days will you rent? ");
        int days = input.nextInt();

        // Promo Logic
        System.out.print("Enter Promo Code (or type 'none'): ");
        String enteredCode = input.next();
        
        double baseTotal = selectedCar.getPricePerDay() * days;
        double savings = 0;

        if (enteredCode.equalsIgnoreCase(PROMO_CODE)) {
            savings = baseTotal * DISCOUNT_RATE;
        }
        
        double finalTotal = baseTotal - savings;

        // Date Logic
        LocalDate pickupDate = LocalDate.now();
        LocalDate returnDate = pickupDate.plusDays(days);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        // Format the Receipt Content
        String receiptHeader = "\n===============================\n" +
                               "       RENTAL RECEIPT          \n" +
                               "===============================";
        
        String receiptBody = String.format(
            "\nVehicle      : %s" +
            "\nDuration     : %d Days" +
            "\nPickup Date  : %s" +
            "\nReturn Date  : %s" +
            "\n-------------------------------" +
            "\nBase Price   : R%.2f", 
            selectedCar.getName(), days, pickupDate.format(dtf), returnDate.format(dtf), baseTotal);

        // --- 1. PRINT TO CONSOLE ---
        System.out.println(receiptHeader);
        System.out.println(receiptBody);
        if (savings > 0) {
            System.out.printf("Discount     : -R%.2f (15%% Off)%n", savings);
        }
        System.out.println("-------------------------------");
        System.out.printf("TOTAL DUE    : R%.2f%n", finalTotal);
        System.out.println("===============================");
        System.out.println("   Thank you for driving with us!  ");

        // --- 2. SAVE TO FILE ---
        try (PrintWriter writer = new PrintWriter(new FileWriter("Receipt.txt"))) {
            writer.println(receiptHeader);
            writer.println(receiptBody);
            if (savings > 0) {
                writer.printf("Discount     : -R%.2f (15%% Off)%n", savings);
            }
            writer.println("-------------------------------");
            writer.printf("TOTAL DUE    : R%.2f%n", finalTotal);
            writer.println("===============================");
            writer.println("   Thank you for driving with us!  ");
            
            System.out.println("\n[SUCCESS] A copy of this receipt was saved to Receipt.txt");
            
        } catch (IOException e) {
            System.out.println("\n[ERROR] Could not save receipt file: " + e.getMessage());
        }

        input.close();
    }
}

    
        

        
    

