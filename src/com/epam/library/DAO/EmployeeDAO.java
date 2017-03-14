package com.epam.library.DAO;

import com.epam.library.domain.Employee;

import java.util.Map;

/**
 * Created by Roman on 15.03.2017.
 */

//????????????????????///
public class EmployeeDAO extends AbstractDAO<Integer, Employee> {
    private static final String SELECT_MORE_THAN_STATISTICS = "SELECT *, c  FROM employee WHERE (SELECT COUNT(*) FROM book_has_employee WHERE employee_id=id) as c > ?";
    @Override
    public Employee findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(Employee entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(Employee entity) {
        return false;
    }

//    public Map<Employee, Integer> findEmployeesWithNumberOfBooksMoreThan(int minAmountOfBooks){
//
//    }

}
