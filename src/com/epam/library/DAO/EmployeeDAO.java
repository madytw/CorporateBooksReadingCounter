package com.epam.library.DAO;

import com.epam.library.domain.Employee;
import com.epam.library.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roman on 15.03.2017.
 */

public class EmployeeDAO extends AbstractDAO<Integer, Employee> {
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id=?";
    private static final String SELECT_MORE_THAN_STATISTICS = "SELECT id, `name`, date_of_birth, email , COUNT(book_id) as amount  FROM employee JOIN " +
            "book_has_employee ON id=employee_id GROUP BY id HAVING amount>? ORDER BY amount ASC";
    private static final String INSERT_NEW_EMPLOYEE = "INSERT INTO employee (id, `name`, date_of_birth, email) VALUES (?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE_BY_ID = "DELETE FROM employee WHERE id=?";
    private static final String UPDATE_EMPLOYEE = "UPDATE employee SET `name`=?, date_of_birth=?, email=? WHERE id=?";
    private static final String SELECT_LESS_EQUAL_STATISTICS = "SELECT id, `name`, date_of_birth, email , COUNT(book_id) as amount  FROM employee JOIN " +
            "book_has_employee ON id=employee_id GROUP BY id HAVING amount<=? ORDER BY amount ASC";
    @Override
    public Employee findEntityById(Integer id) throws DAOException {
        Employee employee = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            employee = takeEmployees(rs).get(0);
        }catch (SQLException e){
            throw new DAOException("DAOException: " + e);
        }finally {
            closeStatement(ps);
        }
        return employee;
    }

    @Override
    public boolean create(Employee entity) throws DAOException{

        boolean isCreated = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_NEW_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, Types.INTEGER);
            ps.setString(2, entity.getName());
            ps.setDate(3, Date.valueOf(entity.getDateOfBirth()));
            ps.setString(4, entity.getEmail());
            isCreated = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                entity.setEmployeeId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException("DAOException: " + e);
        }finally {
            closeStatement(ps);
        }
        return isCreated;
    }

    @Override
    public boolean delete(Integer id) throws DAOException{
        boolean isDeleted = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE_EMPLOYEE_BY_ID);
            ps.setInt(1, id);
            isDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("DAOException: " + e);
        } finally {
            closeStatement(ps);
        }
        return isDeleted;
    }

    @Override
    public boolean update(Employee entity) throws DAOException{
        boolean isUpdated = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(UPDATE_EMPLOYEE);
            ps.setString(1, entity.getName());
            ps.setDate(2, Date.valueOf(entity.getDateOfBirth()));
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getEmployeeId());
            isUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("DAOException: " + e);
        } finally {
            closeStatement(ps);
        }
        return isUpdated;
    }

    public Map<Employee, Integer> findEmployeesWithNumberOfBooksMoreThan(int minAmountOfBooks) throws DAOException{
        Map<Employee, Integer> report = new LinkedHashMap<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_MORE_THAN_STATISTICS);
            ps.setInt(1, minAmountOfBooks);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                employee.setEmail(rs.getString("email"));
                report.put(employee, rs.getInt("amount"));
            }
        }catch (SQLException e){
            throw new DAOException("DAOException: " + e);
        }finally {
            closeStatement(ps);
        }
        return report;
    }

    public Map<Employee, Integer> findEmployeesWithNumberOfBooksLessEqThan(int maxAmountOfBooks) throws DAOException{
        Map<Employee, Integer> report = new LinkedHashMap<>();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_LESS_EQUAL_STATISTICS);
            ps.setInt(1, maxAmountOfBooks);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                employee.setEmail(rs.getString("email"));
                report.put(employee, rs.getInt("amount"));
            }
        }catch (SQLException e){
            throw new DAOException("DAOException: " + e);
        }finally {
            closeStatement(ps);
        }
        return report;
    }


    private List<Employee> takeEmployees(ResultSet rs) throws DAOException{
        List<Employee> employees = new ArrayList<>();
        try {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                employee.setEmail(rs.getString("email"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new DAOException("DAOException: " + e);
        }
        return employees;
    }

}
