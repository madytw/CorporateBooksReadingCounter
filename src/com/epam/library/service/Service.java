package com.epam.library.service;

import com.epam.library.DAO.BookDAO;
import com.epam.library.DAO.EmployeeDAO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.exception.DAOException;
import com.epam.library.printer.InfoPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 15.03.2017.
 */
public class Service {
    private static final Logger LOG = LogManager.getLogger();
    private InfoPrinter printer = new InfoPrinter();

    public void findBook(String request) {
        BookDAO bookDAO = new BookDAO();
        try {
            printer.showBooks(bookDAO.findBook(request));
        }catch (DAOException e){
            LOG.error(e);
        }finally {
            bookDAO.closeConnection();
        }
    }

    public void findEmployeesMore(int bookNumber) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            Map<Employee, Integer> map = employeeDAO.findEmployeesWithNumberOfBooksMoreThan(bookNumber);
            printer.showEmployeesMore(map);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            employeeDAO.closeConnection();
        }
    }

    public void findEmployeesLess(int bookNumber) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            Map<Employee, Integer> map = employeeDAO.findEmployeesWithNumberOfBooksLessEqThan(bookNumber);
            printer.showEmployeesLess(map);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            employeeDAO.closeConnection();
        }
    }

    public boolean renameBook(String oldTitle, String newTitle) {
        boolean isRenamed = false;
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = bookDAO.findBookByTitle(oldTitle);
            book.setTitle(newTitle);
            isRenamed = bookDAO.update(book);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            bookDAO.closeConnection();
        }
        return isRenamed;
    }


    public void createBook(String title, String author, String brief, int dateOfPublishing) {
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = new Book(title, author, brief, dateOfPublishing);
            bookDAO.create(book);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            bookDAO.closeConnection();
        }
    }

    public void showBook(int id) {
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = bookDAO.findEntityById(id);
            System.out.println(book);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            bookDAO.closeConnection();
        }
    }

    public void updateBook(int id, String newTitle, String newAuthor, String newBrief, String newDateOfPublishing) {
        BookDAO bookDAO = new BookDAO();
        try {
            Book book = bookDAO.findEntityById(id);
            int dateOfPublishing = 0;
            if (newTitle.isEmpty()) {
                newTitle = book.getTitle();
            }
            if (newAuthor.isEmpty()) {
                newAuthor = book.getAuthor();
            }
            if (newBrief.isEmpty()) {
                newBrief = book.getBrief();
            }
            if (newDateOfPublishing.isEmpty()) {
                dateOfPublishing = book.getDateOfPublishing();
            } else {
                try {
                    dateOfPublishing = Integer.parseInt(newDateOfPublishing);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            book = new Book(newTitle, newAuthor, newBrief, dateOfPublishing);
            book.setBookId(id);
            bookDAO.update(book);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            bookDAO.closeConnection();
        }
    }

    public void deleteBook(int id) {
        BookDAO bookDAO = new BookDAO();
        try {
            bookDAO.delete(id);
        } catch (DAOException e) {
            LOG.error(e);
        } finally {
            bookDAO.closeConnection();
        }
    }
}
