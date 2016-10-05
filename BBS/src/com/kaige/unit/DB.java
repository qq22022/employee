package com.kaige.unit;

import java.sql.*;

public class DB {
	private static DB db;
	
	static
	{
		db = new DB();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private DB()
	{
		
	}
	
	public static Connection getConn()
	{
		Connection conn = null;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://192.168.73.1:3306/bbs","root","123456");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static Statement createStmt(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static void closeConn(Connection conn)
	{
		try
		{
			if(conn != null)
			{
				conn.close();
				conn = null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
			
		
	}
	
	public static Statement getStmt(Connection conn)
	{
		Statement stmt = null;
		
		try
		{
			stmt = conn.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static PreparedStatement getPStmt(Connection conn, String sql)
	{
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(sql);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pStmt;
	}
	
	public static PreparedStatement getPStmt(Connection conn, String sql, boolean generatedKey)
	{
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pStmt;
	}
	
	public static PreparedStatement getPStmt(Connection conn, String sql, int autoGeneratedKey)
	{
		PreparedStatement pStmt = null;
		try {
			pStmt = conn.prepareStatement(sql, autoGeneratedKey);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pStmt;
	}
	
	
	public static void closeStmt(Statement stmt)
	{
		try
		{
			if(stmt != null)
			{
				stmt.close();
				stmt = null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}			
	}
	
	public static ResultSet excuteQuery(Statement stmt,String sql)
	{
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static void closeRs(ResultSet rs)
	{
		try
		{
			if(rs != null)
			{
				rs.close();
				rs = null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}			
	}
	
	
	
	public static ResultSet executeQuery(Connection conn,String sql)
	{
		ResultSet rs = null;
		
		try {
			rs = conn.createStatement().executeQuery(sql);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public static void executeUpdate(Statement stmt, String sql) {
		try {
			if(stmt != null) {
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void executeUpdate(Connection conn, String sql) {
		try {
			if(conn.createStatement() != null) {
				conn.createStatement().executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
