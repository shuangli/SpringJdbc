package com.xyz.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.xyz.domain.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private MyRowMapper rowMapper = new MyRowMapper();

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteInserted() {
		jdbcTemplate.execute("DELETE FROM user WHERE id < 1000000");
	}
	
	public void insertUser(User user) {
		/**
		 * Specify the statement
		 */
		String query = "INSERT INTO user (id,firstname,lastname,address,city,state,zipcode,col1,col2,col3,col4,col5,col6,col7,col8,col9) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		/**
		 * Specify the values 
		 */
		jdbcTemplate.update(query, new Object[] {user.getId(), user.getFirstname(),  user.getLastname(), user.getAddress(),
				user.getCity(), user.getState(), user.getZipcode(), user.getCol1(), user.getCol2(), user.getCol3(),
				user.getCol4(), user.getCol5(), user.getCol6(), user.getCol7(), user.getCol8(), user.getCol9()});
	}
	
	public User selectUser(int userId) {
		/**
		 * Specify the statement
		 */
		String query = "SELECT * FROM user WHERE id=?";
		/**
		 * Implement the RowMapper callback interface
		 */
		return (User) jdbcTemplate.queryForObject(query, new Object[] { Integer.valueOf(userId) }, rowMapper);
	}
	
	static class MyRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			User user = new User(resultSet.getInt("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("address"), resultSet.getString("city"),
					resultSet.getString("state"), resultSet.getInt("zipcode"), resultSet.getString("col1"), resultSet.getString("col2"), resultSet.getString("col3"),
					resultSet.getString("col4"), resultSet.getString("col5"), resultSet.getString("col6"), resultSet.getString("col7"), resultSet.getString("col8"), resultSet.getString("col9"));
			return user;
		}
	}
}
