//package com.yidong.common;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.FutureTask;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class SQLUtil {
//	
//	private static int batchHandleNum = 1;//一次批量导入的条数,批量导入可以大幅度提升性能
//
//	//异常日志记录 TODO String泛型可以改成 日志记录表的对象
//	private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
//
//	private static AtomicInteger line = new AtomicInteger(0);
//	private  static int threadNum = 10;//配置需要执行的线程数
//
//	//private static ArrayList<String> sqlList = new ArrayList<>();
//
//	static ThreadPoolExecutor pool = new ThreadPoolExecutor(threadNum, 320, 1L, TimeUnit.MILLISECONDS,
//			new LinkedBlockingDeque<Runnable>(100),
//			Executors.defaultThreadFactory(),
//			new java.util.concurrent.ThreadPoolExecutor.AbortPolicy());
//	static String[] inSqlArr = new String[31];//测试数组用
//	
//	
//	
//	//获取连接
//	public static Connection getConnect() {
//		Connection conn = null;
//		ResultSet rs = null;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testsql?serverTimezone=GMT%2B8&characterEncoding=utf8", "root", "root");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
//
//	public static int getLine() {
//		return line.addAndGet(1);//返回相加后值.AtomicInteger防止并发执行的造成num数据被重复读和脏读
//	}
//	
//	//获取每个线程执行的要执行的sql
//	public static ArrayList<String> getSqlData(int num) {
//		//获取本次执行的线程数
//		int piece = inSqlArr.length / threadNum;//每个线程应该分派的任务数量
//		//每个线程应该取的范围集合
//		int res = num * piece;
//		int start = piece * (num - 1);
//		ArrayList<String> tempSql = new ArrayList<>();
//		if (num == threadNum) {
//			System.out.println("第" + Thread.currentThread().getName()+":"+ num + "个线程处理数据范围为：[" + start + "," + inSqlArr.length + ")");
//			//for (int i = start; i < sqlList.size(); i++) {
//			for (int i = start; i < inSqlArr.length; i++) {
//				//tempSql.add(sqlList.get(i));
//				tempSql.add(inSqlArr[i]);
//			}
//		} else {
//			System.out.println("第" + Thread.currentThread().getName()+":"+ num + "个线程处理数据范围为：[" + start + "," + res + ")");
//			for (int i = start; i < res; i++) {
//				//tempSql.add(sqlList.get(i));
//				tempSql.add(inSqlArr[i]);
//			}
//		}
//		return tempSql;
//	}
//
//	//运行sql
//	public static int runSql(ArrayList<String> sqlData) {
//		long singleTimeStart = System.currentTimeMillis();
//		Connection conn = null;
//		Statement st = null;
//		int num1 = 0;
//		int num2 = 0;
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testsql?serverTimezone=GMT%2B8&characterEncoding=utf8", "root", "root");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			st = conn.createStatement();
//			int i ;
//			for (i = 0; i < sqlData.size(); i++) {
//				st.addBatch(sqlData.get(i));
//			}
//			if (i == sqlData.size()) {//剩余不足batchHandleNum条数的批量执行
//				num1 = dealDate(st);//调用执行
//			}
//			if (i % batchHandleNum == 0) {//满batchHandleNum批量执行
//				num2 = dealDate(st);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try{
//				if(st != null)
//					st.close();
//			}catch(Exception ex){
//			}
//			try{
//				if(conn != null)
//					conn.close();
//			}catch(Exception ex){
//			}
//		}
//		int num3 = num1 + num2;
//		System.out.println("单线程时间："+Thread.currentThread().getName()+":"+(System.currentTimeMillis()-singleTimeStart));
//		return num3;
//		
//	}
//
//	//批量sql执行
//	private static int dealDate(Statement st) {
//		int length = 0;
//		try {
//			int[] rs = st.executeBatch();
//			length = rs.length;
//			//sb.setLength(0);//清空
//		} catch (Exception e) {
//			System.out.println("执行异常：异常原因：" + e.getMessage());
//			//记录失败sql
//			//logs(sb.toString());
//			//TODO 可使用队列,mq，kafka，异步处理
//			//queue.add(sb.toString());
//			//sb.setLength(0);//清空
//		}
//		return length;
//	}
//
//
//	public static void main(String[] args) {
//		//TODO 
//		long s = System.currentTimeMillis();
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
//		inSqlArr[14]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[15]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[16]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[17]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[18]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[19]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[20]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[21]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[22]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[23]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[24]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[25]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[26]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[27]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[28]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[29]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		inSqlArr[30]= "insert into t_c_count(num) select count(1) from t_c_test_bigtable_p where num between 65000000 and 70000000-1";
//		
//		
//		//要处理的sql
//		//System.out.println("解析到待处理sql数量：" + sqlList.size());
//		
//		//每个线程只取自己处理的那部分数据
//		for (int i = 0; i < threadNum; i++) {
//			
//			//Thread thread = new MyThread();
//			//pool.execute(thread);
//			//线程池执行,可能会有RejectedExecutionException 但是队列设置为最大值Integer.MAX_VALUE或合理的队列长度,(同时pool的拒绝策略也是一个问题)
//			pool.execute(()->{
//				int num = getLine();
//				ArrayList<String> sqlData = getSqlData(num);//获取每个线程需处理的数据
//				int countAll = runSql(sqlData);
//				System.out.println("插入操作的条数："+countAll);
//			});
//			
//		}
//			pool.shutdown();
//		try {
//			if (pool.awaitTermination(10L, TimeUnit.SECONDS)) {
//				long end = System.currentTimeMillis();
//				System.out.println("线程耗时："+Thread.currentThread().getName()+(end-s));
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//	}
//}
//
//////获得线程
////class MyThread extends Thread {
////	@Override
////	public void run() {
////		int num = SQLUtil.getLine();
////		ArrayList<String> sqlData = SQLUtil.getSqlData(num);//获取每个线程需处理的数据
////		/*for (String sqlDatum : sqlData) {
////            System.out.println("第"+num+"个线程取到的数据："+sqlDatum);
////        }*/
////		SQLUtil.runSql(sqlData);
////	}
////}
//
//
///************************************************************************************************/
//
////class MyThread2 implements Callable<Integer>{
////	public MyThread2() {
////	}
////	
////    @Override
////    public Integer call() throws Exception {
////    	int num = SQLUtil.getLine();
////		ArrayList<String> sqlData = SQLUtil.getSqlData(num);//获取每个线程需处理的数据
////		/*for (String sqlDatum : sqlData) {
////            System.out.println("第"+num+"个线程取到的数据："+sqlDatum);
////        }*/
////		SQLUtil.runSql(sqlData);
////        return 1;
////    }
////    
////    public static void main(String[] args) throws Exception {
////        //一个主线程,一个AA FuturaTask线程
////        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
////        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());
////
////        new Thread(futureTask,"AA").start();
////        new Thread(futureTask,"AAB").start();
////        new Thread(futureTask2,"BB").start();
////
////        int result01 = 100;
////        while (!futureTask.isDone()){
////            System.out.println(Thread.currentThread().getName()+"线程");
////        }
////        int result02 =futureTask.get();//建议放在最后,获得Callable接口.如果没有计算完成,则会导致阻塞,直到计算完成
////        System.out.println("*********"+(result01+result02));
////    }
////}
//
//
//
