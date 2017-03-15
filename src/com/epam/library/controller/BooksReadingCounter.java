package com.epam.library.controller;

import com.epam.library.connector.DBConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Created by Alexandra_Lavrenteva on 3/15/2017.
 */
public class BooksReadingCounter {

    private static final Logger LOG = LogManager.getLogger();
    private static Scanner scanner;
    private static Controller controller;

    public static void main(String[] args) {
        initApp();
        String command;
        LOG.info("Application started!");
        do {
            System.out.println("Enter command (find more | find less | rename | search):");
            command = scanner.nextLine();
            controller.defineService(command);
        } while (!command.equals("end"));
        LOG.info("Application shut down!");
    }

    private static void initApp() {
        DBConnector.registerDB();
        scanner = new Scanner(System.in);
        controller = new Controller();
    }
}