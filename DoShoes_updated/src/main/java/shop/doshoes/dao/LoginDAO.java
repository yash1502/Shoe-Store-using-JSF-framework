//package shop.doshoes.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import shop.doshoes.util.DataConnect;
//
//public class LoginDAO {
//
//	public static boolean validate(String email, String password) {
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		try {
//			con = DataConnect.getConnection();
//			ps = con.prepareStatement("SELECT * FROM doshoes.customer WHERE email = ? AND password = ?");
//			ps.setString(1, email);
//			ps.setString(2, password);
//
//			ResultSet rs = ps.executeQuery();
//
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException ex) {
//			System.out.println("Login error -->" + ex.getMessage());
//			return false;
//		} finally {
//			DataConnect.close(con);
//		}
//		return false;
//	}
//}