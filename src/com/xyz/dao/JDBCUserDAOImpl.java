package com.xyz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.xyz.domain.User;

public class JDBCUserDAOImpl implements UserDAO {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void deleteInserted() {
		new JdbcTemplate(dataSource).execute("DELETE FROM user WHERE id < 1000000");
	}
	
	public void insertUser(User user) {
		/**
		 * Specify the statement
		 */
		String query = "INSERT INTO user (id,firstname,lastname,address,city,state,zipcode,col1,col2,col3,col4,col5,col6,col7,col8,col9) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		/**
		 * Define the connection and preparedStatement parameters
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			/**
			 * Open the connection
			 */
			connection = dataSource.getConnection();
			/**
			 * Prepare the statement
			 */
			preparedStatement = connection.prepareStatement(query);
			/**
			 * Bind the parameters to the PreparedStatement
			 */
			preparedStatement.setInt(1,     user.getId());
			preparedStatement.setString(2,  user.getFirstname());
			preparedStatement.setString(3,  user.getLastname());
			preparedStatement.setString(4,  user.getAddress());
			preparedStatement.setString(5,  user.getCity());
			preparedStatement.setString(6,  user.getState());
			preparedStatement.setInt(7,     user.getZipcode());
			preparedStatement.setString(8,  user.getCol1());
			preparedStatement.setString(9,  user.getCol2());
			preparedStatement.setString(10, user.getCol3());
			preparedStatement.setString(11, user.getCol4());
			preparedStatement.setString(12, user.getCol5());
			preparedStatement.setString(13, user.getCol6());
			preparedStatement.setString(14, user.getCol7());
			preparedStatement.setString(15, user.getCol8());
			preparedStatement.setString(16, user.getCol9());
			/**
			 * Execute the statement
			 */
			preparedStatement.execute();
		}
		catch (SQLException e) {
			/**
			 * Handle any exception
			 */
			e.printStackTrace();
		}
		finally {
			try {
				/**
				 * Close the preparedStatement
				 */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (SQLException e) {
				/**
				 * Handle any exception
				 */
				e.printStackTrace();
			}
			try {
				/**
				 * Close the connection
				 */
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				/**
				 * Handle any exception
				 */
				e.printStackTrace();
			}
		}
	}
	
	public User selectUser(int userId) {
		/**
		 * Specify the statement
		 */
		String query = "SELECT * FROM user WHERE id=?";
		/**
		 * Define the connection, preparedStatement and resultSet parameters
		 */
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			/**
			 * Open the connection
			 */
			connection = dataSource.getConnection();
			/**
			 * Prepare the statement
			 */
			preparedStatement = connection.prepareStatement(query);
			/**
			 * Bind the parameters to the PreparedStatement
			 */
			preparedStatement.setInt(1, userId);
			/**
			 * Execute the statement
			 */
			resultSet = preparedStatement.executeQuery();
			User user = null;
			/**
			 * Extract data from the result set
			 */
			if (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("address"), resultSet.getString("city"),
						resultSet.getString("state"), resultSet.getInt("zipcode"), resultSet.getString("col1"), resultSet.getString("col2"), resultSet.getString("col3"),
						resultSet.getString("col4"), resultSet.getString("col5"), resultSet.getString("col6"), resultSet.getString("col7"), resultSet.getString("col8"), resultSet.getString("col9"));
			}
			return user;
		}
		catch (SQLException e) {
			/**
			 * Handle any exception
			 */
			e.printStackTrace();
		}
		finally {
			try {
				/**
				 * Close the resultSet
				 */
				if (resultSet != null) {
					resultSet.close();
				}
			}
			catch (SQLException e) {
				/**
				 * Handle any exception
				 */
				e.printStackTrace();
			}
			try {
				/**
				 * Close the preparedStatement
				 */
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			}
			catch (SQLException e) {
				/**
				 * Handle any exception
				 */
				e.printStackTrace();
			}
			try {
				/**
				 * Close the connection
				 */
				if (connection != null) {
					connection.close();
				}
			}
			catch (SQLException e) {
				/**
				 * Handle any exception
				 */
				e.printStackTrace();
			}
		}
		return null;
	}
}
