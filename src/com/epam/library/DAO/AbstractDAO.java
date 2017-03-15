package com.epam.library.DAO;

import com.epam.library.domain.Entity;
import com.epam.library.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Roman on 14.03.2017.
 */
public abstract class AbstractDAO<K, T extends Entity> {
    private static final Logger LOG = LogManager.getLogger();
    protected Connection connection;

    public AbstractDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF-8&useSSL=true", "root", "pass");
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    public abstract T findEntityById(K id) throws DAOException;

    public abstract boolean create(T entity) throws DAOException;

    public abstract boolean delete(K id) throws DAOException;

    public abstract boolean update(T entity) throws DAOException;

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    public void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
}
