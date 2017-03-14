package com.epam.library.DAO;

import com.epam.library.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Roman on 14.03.2017.
 */
public class BookDAO extends AbstractDAO<Integer, Book> {
    private static final String SELECT_BOOK_BY_ID = "SELECT FROM book WHERE id=?";
    private static final String INSERT_NEW_BOOK = "INSERT INTO book (id, title, brief, publish_year, author)" +
            " VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM book WHERE id=?";
    private static final String UPDATE_BOOK = "UPDATE book SET title=?, brief=?, publish_year=?, author=? WHERE id=?";
    private static final String SELECT_BOOK_BY_TITLE = "SELECT FROM book WHERE title=?";

    @Override
    public Book findEntityById(Integer id) {
        Book book = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BOOK_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            book = takeBooks(rs).get(0);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeStatement(ps);
        }
        return book;
    }

    @Override
    public boolean create(Book entity) {
        boolean isCreated = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            ps.setNull(1, Types.INTEGER);
            ps.setString(2, entity.getTitle());
            ps.setString(3, entity.getBrief());
            ps.setInt(4, entity.getDateOfPublishing());
            ps.setString(5, entity.getAuthor());
            isCreated = ps.executeUpdate() > 0;
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                entity.setBookId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeStatement(ps);
        }
        return isCreated;
    }

    @Override
    public boolean delete(Integer id) {
        boolean isDeleted = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(DELETE_BOOK_BY_ID);
            ps.setInt(1, id);
            isDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
        }
        return isDeleted;
    }

    @Override
    public boolean update(Book entity) {
        boolean isUpdated = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(UPDATE_BOOK);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getBrief());
            ps.setInt(3, entity.getDateOfPublishing());
            ps.setString(4, entity.getAuthor());
            ps.setInt(5, entity.getBookId());
            isUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(ps);
        }
        return isUpdated;
    }

    public Book findBookByTitle(String title) {
        Book book = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BOOK_BY_TITLE);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            book = takeBooks(rs).get(0);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeStatement(ps);
        }
        return book;
    }


    private List<Book> takeBooks(ResultSet rs) {
        List<Book> books = new ArrayList<>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setBrief(rs.getString("brief"));
                book.setDateOfPublishing(rs.getInt("publish_year"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
