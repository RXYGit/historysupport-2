//package com.yidong.controller.procedure;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// *  调用oracle 存储过程
// */
//public class OracleStoredProcedure {
//	private  CallableStatement call;
//	private  Connection conn;
//	
//	 Map<String, Object> map = new HashMap<>();
//	/**
//	 * 建立连接
//	 */
//	private  Connection getConn() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@//10.19.190.113:4406/ora92", "icdwf", "sdyd60_wf");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	/**
//	 * 执行语句对象
//	 */
//	private  Map<String,Object> preparedCall(String staffid,String type,String beginpage,String pagelength) {
//			try {
//				String sql = "{p_c_pbh_qryprocessxzg51(?,?,?,?,?)}";
//				call = conn.prepareCall(sql);
//				//4.设置输入参数
//				call.setObject(1, staffid);
//				call.setString(2, type);
//				call.setString(3, beginpage);
//				call.setString(4, pagelength);
//				//注册输出参数
//				call.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
//				//执行存储过程
//				call.execute();
//				//ResultSet executeQueryRS = call.executeQuery();
//				//获取输出参数
//				String result = String.valueOf(call.getObject(5));
//				map.put("result", result);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					if (call != null) {
//						call.close();
//					}
//				} catch (Exception e2) {
//				}
//				try {
//					if (conn != null) {
//						conn.close();
//					}
//				} catch (Exception e2) {
//				}
//			}
//			return map;
//		}
//	
//	public  Map<String,Object> getParams(HttpServletRequest request,HttpServletResponse response) {
//		Map<String,Object> result = null;
//		try {
//			request.setCharacterEncoding("UTF-8");
//			response.setCharacterEncoding("UTF-8");
//			getConn();
//			String staffid = request.getParameter("staffid");
//			String type = request.getParameter("type");
//			String beginpage = request.getParameter("beginpage");
//			String pagelength = request.getParameter("pagelength");
//			result = preparedCall(staffid,type,beginpage,pagelength);
//			
//		} catch (Exception e) {
//		}
//		return result;
//		
//	}
//
//}
//
//
//
//
//
