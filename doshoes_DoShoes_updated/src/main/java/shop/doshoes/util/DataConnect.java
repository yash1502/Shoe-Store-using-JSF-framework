//package shop.doshoes.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class DataConnect {
//
//	public static Connection getConnection() {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection(
//					"jdbc:mysql://doshoes-mysql.cmeowmpzwpwg.us-east-2.rds.amazonaws.com:3306/doshoes?useSSL=false", "doshoes", "doshoes123");
//			return con;
//		} catch (Exception e) {
//			System.out.println("Database.getConnection() Error -->"
//					+ e.getMessage());
//			return null;
//		}
//	}
//
//	public static void close(Connection con) {
//		try {
//			con.close();
//		} catch (Exception e) {
//		}
//	}
//}