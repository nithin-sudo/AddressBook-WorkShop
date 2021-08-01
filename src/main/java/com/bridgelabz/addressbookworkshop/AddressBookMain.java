package com.bridgelabz.addressbookworkshop;

import java.util.Scanner;

public class AddressBookMain {
    static Service service = new Service();

    public static void main(String[] args) {
        boolean isExit = false;
        while (!isExit)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter options 1.Add. 2.edit contact 3.Display Contact. 4.Exit.");
            int userInput = scanner.nextInt();
            switch (userInput)
            {
                case 1:
                    service.addNewContact();
                    break;
                case 2:
                    service.editContact();
                    break;
                case 3:
                    service.displayList();
                    break;
                case 4:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }
}
