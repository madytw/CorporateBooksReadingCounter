package com.epam.library.printer;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created by Roman on 15.03.2017.
 */
public class InfoPrinter {

    public void showEmployeesMore(Map<Employee, Integer> map) {
        for (Map.Entry<Employee, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey().getName() + " : " + entry.getValue());
        }
    }

    public void showEmployeesLess(Map<Employee, Integer> map) {
        for (Map.Entry<Employee, Integer> entry : map.entrySet()) {
            Employee employee = entry.getKey();
            int amountOfBooks = entry.getValue();
            System.out.println(employee.getName() + "," + employee.getDateOfBirth() + " : " + amountOfBooks);
        }
    }

    public void showBooks(List<Book> bookList) {
        bookList.forEach(System.out::println);
    }

}
