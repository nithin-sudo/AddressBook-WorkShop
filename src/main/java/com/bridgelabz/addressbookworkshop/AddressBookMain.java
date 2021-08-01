package com.bridgelabz.addressbookworkshop;

import java.util.Scanner;

public class AddressBookMain {
    static Service service = new Service();

    public static void main(String[] args) {
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter options \n1.Add. \n2.edit contact \n3.delete contact \n4.Display Contact.\n5.search person in a city \n6.view person \n7.Exit.");
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    service.addNewContact();
                    break;
                case 2:
                    System.out.println("Enter the Address Book name");
                    String bookName = scanner.next();
                    service.editContact(bookName);
                    break;
                case 3:
                    System.out.println("Enter the Address Book name");
                    String bookNameToDelete = scanner.next();
                    System.out.println("Enter the Name of the person do you wants to delete:");
                    String personName = scanner.next();
                    service.deleteContact(personName, bookNameToDelete);
                    break;
                case 4:
                    service.displayList();
                    break;
                case 5:
                    System.out.println("Enter the city to be searched:");
                    String searchCity = scanner.next();
                    service.searchPersonInACity(searchCity);
                    break;
                case 6:
                    System.out.println("enter the city of a person to be viewed:");
                    String viewCity = scanner.next();
                    service.viewPersonInACity(viewCity);
                    break;
                case 7:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }
}
