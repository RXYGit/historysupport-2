//package com.yidong.common;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.Callable;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//public class SQLUtil2 {
//	private static final List<String> uuidList = new ArrayList<>();
//	private static final Map<String, Future<List<Map<String, Object>>>> resultList = new HashMap<>();//结果
//
//	static ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 320, 1L, TimeUnit.SECONDS,
//			new LinkedBlockingDeque<>(10000),
//			Executors.defaultThreadFactory(),
//			new ThreadPoolExecutor.AbortPolicy());
//
//
//	//1开始调用
//	public static void main(String[] args) {
//		String [] exesql = new String[10];
//		exesql[0] = "select * from t_c_test_bigtable_p where num between 1 and 5000000-1";
//		exesql[1] = "select count(1) from t_c_test_bigtable_p where num between 1 and 10000000-1";
//		exesql[2] = "select count(1) from t_c_test_bigtable_p where num between 1 and 15000000-1";
//		exesql[3] = "select count(1) from t_c_test_bigtable_p where num between 1 and 20000000-1";
//		exesql[4] = "select count(1) from t_c_test_bigtable_p where num between 1 and 25000000-1";
//		exesql[5] = "select count(1) from t_c_test_bigtable_p where num between 1 and 30000000-1";
//		exesql[6] = "select count(1) from t_c_test_bigtable_p where num between 1 and 35000000-1";
//		exesql[7] = "select count(1) from t_c_test_bigtable_p where num between 1 and 40000000-1";
//		exesql[8] = "select count(1) from t_c_test_bigtable_p where num between 1 and 45000000-1";
//		exesql[9] = "select count(1) from t_c_test_bigtable_p where num between 1 and 50000000-1";
//		submitSql(exesql);//执行
//		pool.shutdown();
//		try {
//			boolean flag;
//			do {flag = !pool.awaitTermination(100L, TimeUnit.MILLISECONDS);
//			} while (flag);
//		} catch (InterruptedException e) {e.printStackTrace();}
//		for (int i = 0; i < uuidList.size(); i++) {
//			System.out.println("对应uuid的结果:"+SqlTask.getResult(uuidList.get(i)));
//		}
//	}
//
//	//2.获取数据库连接
//	public static Connection getConnect() {
//		Connection conn = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testsql?serverTimezone=GMT%2B8&characterEncoding=utf8", "root","root");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	//3线程池提交任务,并执行,把结果放入List中,然后放入map
//	public static void submitSql(String[] exesql){
//		for (int i = 0; i < exesql.length; i++) {
//			Future<List<Map<String, Object>>> result = pool.submit(new SqlTask(exesql[i]));
//			String uuid = UUID.randomUUID().toString();
//			uuidList.add(uuid);
//			resultList.put(uuid, result);
//		}
//	}
//
//
//
//	//实现Callable方法,执行数据库操作
//	public static class SqlTask implements Callable<List<Map<String, Object>>> {
//		private String sql;
//		// private DataMapper dataMapper;
//
//		public SqlTask(String sql) {
//			this.sql=sql;
//		}
//
//		@Override
//		public List<Map<String, Object>> call() throws Exception{
//			//List<LinkedHashMap<String,Object>> res=dataMapper.getPublicItems(sql);//执行数据库操作
//			List<Map<String, Object>> res = new ArrayList<>();
//			long singleThreadTimeStart = System.currentTimeMillis();
//			Connection conn = null;
//			Statement st = null;
//			ResultSet rs = null;
//			try {
//				conn = getConnect();
//				if (conn != null) {
//					st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
//					rs = st.executeQuery(this.sql);
//					while (rs.next()) {
//						int columnCount = rs.getMetaData().getColumnCount();
//						Map<String, Object> map = new HashMap<>();
//						for(int i = 0; i < columnCount; i++){
//							map.put(rs.getMetaData().getColumnName(i+1).toUpperCase(), rs.getString(i+1));
//						}
//						res.add(map);
//					}
//					System.out.println("处理sql:"+Thread.currentThread().getName()+":"+sql);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				try {
//					if (rs!=null) {rs.close();}
//				} catch (Exception e2) {}
//				try {
//					if (st!=null) {st.close();}
//				} catch (Exception e2) {}
//				try {
//					if (conn!=null) {conn.close();}
//				} catch (Exception e2) {}
//			}
//			System.out.println("单线程处理时间："+Thread.currentThread().getName()+"****"+(System.currentTimeMillis()-singleThreadTimeStart));
//			return res;
//		}
//
//
//		//从结果中取值
//		public static Object getResult(String uuid)  {
//			Future f = resultList.get(uuid);
//			if(f!=null){
//				List<Map<String, Object>> r;
//				try {
//					while(!f.isDone()){
//						Thread.sleep(100);
//						//System.out.println("还没有执行完....");
//					}
//					//System.out.println("执行完毕");
//					r = (List<Map<String, Object>>) f.get();
//					resultList.remove(uuid);
//				} catch (Exception e) {
//				 return e.getMessage();
//				}
//				return r;
//			}else{
//				System.out.println("任务不存在");
//			}
//			return null;
//		}
//	}
//}
//
