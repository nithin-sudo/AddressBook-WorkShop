package com.bridgelabz.addressbookworkshop;

import java.util.Scanner;

public class AddressBookMain {
    static Service service = new Service();

    public static void main(String[] args) {
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter options 1.Add. 2.Display Contact. 3.Exit.");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    service.addNewContact();
                    break;
                case 2:
                    service.displayList();
                    break;
                case 3:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }
}
