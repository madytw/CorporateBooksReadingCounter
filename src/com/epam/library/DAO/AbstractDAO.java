package com.epam.library.DAO;

import com.epam.library.domain.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Roman on 14.03.2017.
 */
public abstract class AbstractDAO<K, T extends Entity> {
    protected Connection connection;
    public AbstractDAO() {
        try {
            connection = DriverManager.getConnection("", "", "");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public abstract T findEntityById(K id);
    public abstract boolean create(T entity);
    public abstract boolean delete(K id);
    public abstract boolean update(T entity);
    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closeStatement(Statement st){
        try{
            if(st != null){
                st.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
