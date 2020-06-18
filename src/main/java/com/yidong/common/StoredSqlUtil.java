//package com.yidong.common;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.TimeUnit;
//
//import org.apache.tomcat.jni.Pool;
//import org.apache.tomcat.util.threads.ThreadPoolExecutor;
//
//
//public class StoredSqlUtil {
//
//	private static Connection conn = null;
//	private static Statement ste = null;
//	private static ResultSet rs = null;
//	public static double singeThreadTime;
//	
//	public static void main(String[] args) {
//		
//		long s = System.currentTimeMillis();
//		
//		List<Map<String, String>> list = null;
//		
//		String[] inSqlArr = new String[14];
////		inSqlArr[0] = " select count(1) from t_c_test_bigtable_p where num between 1 and        5000000-1";
////		inSqlArr[1] = " select count(1) from t_c_test_bigtable_p where num between 10000000 and 15000000-1";
////		inSqlArr[2] = " select count(1) from t_c_test_bigtable_p where num between 15000000 and 20000000-1";
////		inSqlArr[3] = " select count(1) from t_c_test_bigtable_p where num between 20000000 and 25000000-1";
////		inSqlArr[4] = " select count(1) from t_c_test_bigtable_p where num between 25000000 and 30000000-1";
////		inSqlArr[5] = " select count(1) from t_c_test_bigtable_p where num between 30000000 and 35000000-1";
////		inSqlArr[6] = " select count(1) from t_c_test_bigtable_p where num between 35000000 and 40000000-1";
//
//		inSqlArr[0] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 1 and        5000000-1";
//		inSqlArr[1] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between  5000000 and 10000000-1";
//		inSqlArr[2] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 10000000 and 15000000-1";
//		inSqlArr[3] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 15000000 and 20000000-1";
//		inSqlArr[4] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 20000000 and 25000000-1";
//		inSqlArr[5] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 25000000 and 30000000-1";
//		inSqlArr[6] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 30000000 and 35000000-1";
//		inSqlArr[7] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 35000000 and 40000000-1";
//		inSqlArr[8] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 40000000 and 45000000-1";
//		inSqlArr[9] = "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 45000000 and 50000000-1";
//		inSqlArr[10]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 50000000 and 55000000-1";
//		inSqlArr[11]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 55000000 and 60000000-1";
//		inSqlArr[12]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 60000000 and 65000000-1";
//		inSqlArr[13]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		
//		
//		String outSql = "SELECT SUM(num) FROM(SELECT num FROM t_c_count UNION ALL SELECT num FROM t_c_count1 UNION ALL SELECT num FROM t_c_count2 UNION ALL SELECT num FROM t_c_count3 UNION ALL SELECT num FROM t_c_count4 UNION ALL SELECT num FROM t_c_count5 UNION ALL SELECT num FROM t_c_count6 UNION ALL SELECT num FROM t_c_count7 UNION ALL SELECT num FROM t_c_count8 UNION ALL SELECT num FROM t_c_count9 UNION ALL SELECT num FROM t_c_count10) t";
//		//多线程执行
//		list = StoredSqlUtil.multithreadExc(inSqlArr, outSql, true,1500);
//		System.out.println("主线程耗时:" +Thread.currentThread().getName()+ (System.currentTimeMillis()-s));
//		System.out.println("单线程:" + (singeThreadTime/14));
//		
//	}
//	
//	/**
//	 * 执行sql
//	 * @param sql
//	 * @return
//	 */
//	public static List<Map<String,String>> excSql(String sql){
//		List<Map<String , String>> list = new ArrayList<>();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testsql?serverTimezone=GMT%2B8&characterEncoding=utf8", "root", "root");
//			
//			ste = conn.createStatement();
//			rs = ste.executeQuery(sql);
//			int columnCount = rs.getMetaData().getColumnCount();
//			while (rs.next()) {
//				Map<String, String> map = new HashMap<String, String>();
//				for(int i = 0; i < columnCount; i++){ 
//					map.put(rs.getMetaData().getColumnName(i+1).toUpperCase(), rs.getString(i+1));
//				}
//				list.add(map);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			try{
//				if(rs != null)
//				rs.close();
//			}catch(Exception ex){
//			}
//			try{
//				if(ste != null)
//				ste.close();
//			}catch(Exception ex){
//			}
//
//			try{
//				if(conn != null)
//				conn.close();
//			}catch(Exception ex){
//			}
//		}
//		return list;
//
//	}
//
//	/**
//	 * 线程池执行sql
//	 * @param inSqlArr
//	 * @param outSql
//	 * @param isCleanTmpTable
//	 * @return
//	 */
//	public static List<Map<String, String>> multithreadExc(String[] inSqlArr, String outSql, boolean isCleanTmpTable, int interval){
//
//		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
//
//		List<Future<String>> results = new ArrayList<Future<String>>();
//		ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 320, 1L, TimeUnit.SECONDS,
//				new LinkedBlockingDeque<>(3),
//				Executors.defaultThreadFactory(),
//				new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
//		
//		for(int i = 0; i < inSqlArr.length; i++){
//			results.add(pool.submit(new Task(inSqlArr[i])));
//			/*************************************************************/
//			try {
//				if (pool.awaitTermination(1L, TimeUnit.MILLISECONDS)) {
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		for(Future<String> result : results){
//			try {
//				String string = result.get();
//			} catch (Exception e) {e.printStackTrace();}
//		}
//
//		list = StoredSqlUtil.excSql(outSql);
//		System.out.println("返回值list："+list);
//		return list;
//	}
//
//	private static int exec(String sql) {
//		int rs2 = -1;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testsql?serverTimezone=GMT%2B8&characterEncoding=utf8", "root", "root");
//			
//			ste = conn.createStatement();
//			long sqlStart = System.currentTimeMillis();
//			rs2 = ste.executeUpdate(sql);
//			long sqlEnd = System.currentTimeMillis();
//			System.out.println("耗时: " + (sqlEnd-sqlStart));
//			singeThreadTime = singeThreadTime + (sqlEnd-sqlStart);
//		} catch (Exception e) {e.printStackTrace();
//		}finally{
//			try{
//				if(ste != null)
//					ste.close();
//			}catch(Exception e){e.printStackTrace();
//			}
//			try{
//				if(conn != null)
//					conn.close();
//			}catch(Exception e){e.printStackTrace();
//			}
//		}
//		return rs2;
//	}
//
//
//	public static class Task implements Callable<String>{
//		String sql = "";
//		public Task(String sql){
//			this.sql = sql;
//		}
//		@Override
//		public String call() throws Exception {
//			int rs = StoredSqlUtil.exec(this.sql);
//			if(rs > 0){
//				return "yes";
//			}else{
//				return "no";
//			}
//		}
//	}
//}
//
//
//
//
//
//
