package com.epam.library.controller;

import com.epam.library.service.Service;

import java.util.Scanner;

/**
 * Created by 123 on 14.03.2017.
 */
public class Controller {
    private Service service;
    private Scanner scanner;

    public Controller() {
        service = new Service();
        scanner = new Scanner(System.in);
    }

    public void defineService(String command) {
        switch (command) {
            case "find more":         //find (then accept book number parameter)
                System.out.println("Enter book number: ");
                int bookNumberMore = scanner.nextInt();
                service.findEmployeesMore(bookNumberMore);
                break;
            case "find less":         //find (then accept book number parameter)
                System.out.println("Enter book number: ");
                int bookNumberLess = scanner.nextInt();
                service.findEmployeesLess(bookNumberLess);
                break;
            case "search":         //search book by name
                System.out.println("Enter key words: ");
                String request = scanner.nextLine();
                service.findBook(request);
                break;
            case "rename":          //rename Book
                System.out.println("Enter old title: ");
                String oldTitle = scanner.nextLine();
                System.out.println("Enter new title: ");
                String newTitle = scanner.nextLine();
                service.renameBook(oldTitle, newTitle);
                break;
            case "end":
                System.out.println("End application");
                break;
            default:
                System.out.printf("There is no such command");
                break;
        }
    }
}
