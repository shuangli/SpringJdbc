package com.xyz.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.xyz.domain.User;

public class UserDAOImpl2 implements UserDAO {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public void deleteInserted() {
		jdbcTemplate.getJdbcOperations().execute("DELETE FROM user WHERE id < 1000000");
	}
	
	public void insertUser(User user) {
		/**
		 * Specify the statement
		 */
		String query = "INSERT INTO user (id,firstname,lastname,address,city,state,zipcode,col1,col2,col3,col4,col5,col6,col7,col8,col9) VALUES (:id,:firstname,:lastname,:address,:city,:state,:zipcode,:col1,:col2,:col3,:col4,:col5,:col6,:col7,:col8,:col9)";
		/**
		 * Specify the values 
		 */
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update(query, namedParameters);
	}
	
	public User selectUser(int userId) {
		/**
		 * Specify the statement
		 */
		String query = "SELECT * FROM user WHERE id=?";
		/**
		 * Implement the RowMapper callback interface
		 */
		return (User) jdbcTemplate.getJdbcOperations().queryForObject(query, new Object[] { userId }, rowMapper);
	}
}
