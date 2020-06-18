package com.yidong.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Test {
	
	@org.junit.jupiter.api.Test
	public void test1() throws Exception {
		LocalDateTime now = LocalDateTime.now();
		String cretime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(cretime);
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime time = now;
		String localTime = df.format(time);
		System.out.println("转化后现在时间"+localTime);
		
		
		
		//LocalDateTime时间的加减
		System.out.println("现在时间L:"+now);
		LocalDateTime minusMonths = now.minusMonths(1);
		String format = minusMonths.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println("减少一个月时间"+format);
		
	}
	
	@org.junit.jupiter.api.Test
	public void testNull() {
		String a = null;
		String.valueOf(a);
		System.out.println(a);
	}
}
 