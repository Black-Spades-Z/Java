/*
 * Name : Azizbek Muminjonov
 * ID   : U2110207
 * Lab  : Lab assignment 2
 * */

import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class U2110207
{
    // Function to check whether number is Prime or not for Task 1
    public static boolean PrimeChecker(int n)
    {
        if (n <= 1)
        {
            return false;
        }
        else
        {
            for (int i = 2; i < n; i++ )
            {
                if(n%i==0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to find largest among 3 numbers for Task 2

    public static int LargestNumber(int number1, int number2, int number3)
    {
        if(number1 > number2)
        {
            if(number1 > number3)
            {
                return number1;
            }
        }
        else if (number2 > number3)
        {
            return number2;
        }
        return number3;
    }
    //Function to check whether given number is Odd or Even for Task 3

    public static void EvenOrOdd(int number)
    {
        if(number%2==0)
        {
            System.out.println("Number " + number + " is Even");
        }
        else
        {
            System.out.println("Number " + number + " is Odd");
        }
    }
    // Function to find Factorial of given number for Task 4

    public static int Factorial(int number)
    {
        int output = 1;

        for(int i = 1; i <= number; i++)
        {
            output *=i;
        }
        return output;
    }

    // Function to power the given number for Task 5

    public static int Power(int number, int degree)
    {
        int powered = number;
        for(int i = 1; i < degree; i++ )
        {
            powered = powered * number;
        }
        return powered;
    }




    //Function that Clears Screen for UI

    public static void clrscr(){

        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public static void main(String[] args) {
        // Creating basic variables 
        int choice, number1, number2, number3;
        boolean condition = true;
        
        // Creating an object of Scanner library to take inputs . . . 
        
        Scanner take = new Scanner(System.in);

        while(condition) {
            // Clearing screen
            clrscr();
            System.out.println("ID : U2110207 \n");
            System.out.println("1. Prime checker");
            System.out.println("2. Largest among 3");
            System.out.println("3. Even or Odd");
            System.out.println("4. Factorial");
            System.out.println("5. Power");
            System.out.println("0. Exit");
            System.out.print("Choose operation : ");

            choice = take.nextInt();

            switch (choice) {
                case 0:
                    // Exits loop
                    condition = false;
                    break;
                case 1:
                    // Task 1

                    System.out.print("Enter the number : ");
                    number1 = take.nextInt();
                    // Clearing screen
                    clrscr();
                    System.out.println("The given number " + number1 + " is prime : " + PrimeChecker(number1));

                    // Thread to pause the output before it is cleaned by clear screen function . . .
                    System.out.println("\n\nWaiting 3 seconds . . . before cleaning");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    // Clearing screen
                    clrscr();
                    break;
                case 2:
                    // Task 2

                    System.out.print("Enter the number 1 : ");
                    number1 = take.nextInt();
                    System.out.print("Enter the number 2 : ");
                    number2 = take.nextInt();
                    System.out.print("Enter the number 3 : ");
                    number3 = take.nextInt();
                    // Clearing screen
                    clrscr();
                    System.out.println("The given numbers : " +number1 +", " + number2+ ", "+ number3);
                    System.out.println("The largest is " + LargestNumber(number1, number2, number3));
                    // Thread to pause the output before it is cleaned by clear screen function . . .
                    System.out.println("\n\nWaiting 3 seconds . . . before cleaning");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    // Clearing screen
                    clrscr();
                    break;
                case 3:
                    // Task 3

                    System.out.print("Enter the number : ");
                    number1 = take.nextInt();
                    // Clearing screen
                    clrscr();
                    System.out.println("The given number " + number1);
                    EvenOrOdd(number1);
                    // Thread to pause the output before it is cleaned by clear screen function . . .
                    System.out.println("\n\nWaiting 3 seconds . . . before cleaning");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    // Clearing screen
                    clrscr();
                    break;
                case 4:
                    // Task 4

                    System.out.print("Enter the number : ");
                    number1 = take.nextInt();
                    // Clearing screen
                    clrscr();
                    System.out.println("The given number " + number1 + " it's Factorial : " + Factorial(number1));

                    // Thread to pause the output before it is cleaned by clear screen function . . .
                    System.out.println("\n\nWaiting 3 seconds . . . before cleaning");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    // Clearing screen
                    clrscr();
                    break;
                case 5:
                    // Task 5

                    System.out.print("Enter the number : ");
                    number1 = take.nextInt();
                    System.out.print("Enter the power : ");
                    number2 = take.nextInt();
                    // Clearing screen
                    clrscr();
                    System.out.println("The given number " + number1 + " to power "+ number2 +" is "+ Power(number1, number2));

                    // Thread to pause the output before it is cleaned by clear screen function . .
                    System.out.println("\n\nWaiting 3 seconds . . . before cleaning");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    // Clearing screen
                    clrscr();
                    break;
                default:
                    System.out.println("Wrong input try again. . . T_T");
                    // Thread to pause the output before it is cleaned by clear screen function . . .
                    System.out.println("\n\nWaiting 3 seconds . . . before cleaning");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    break;

            }
        }
    }

}
