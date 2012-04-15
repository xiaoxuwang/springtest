package com;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.codehaus.jackson.node.JsonNodeFactory;

import com.formBean.Menu;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONBuilder;

/**
 * Author:xiaoxu.wang
 * Date:2010-9-2
 * Des:test
 */
public class test {

	/**
	 * @param args
	 */
	//测试你的正则表达式
	public static void testRegx(String regx,String str){
		
		boolean result=Pattern.compile("^[0-9]{11}$").matcher("13429161770").find();
		System.out.println(result);
	}
	/*
	 * 根据对像创建Json格式的数据
	 * */
	public void createJson(){
		  
		    
		Menu me=new Menu();
		me.setText("tttt");
		me.setId("22222");
		//me.setLeaf(true);
		Menu me2=new Menu();
		me2.setText("tttt");
		me2.setId("22222");
		//me2.setLeaf(true);
		List<Menu> list=new ArrayList<Menu>();
		list.add(me);
		list.add(me2);
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray); 
	}
	/*
	 * 获取（0~9）随机数字或者是（a~z）随机字母
	 * */
	public void testspeed(){
		Long start=System.currentTimeMillis();
		//System.out.println(start);
		Random rd=new Random();
		int ret;
		int ret2;
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String n="";
			String c="";
			do{
				ret=Math.abs(rd.nextInt())%10+48;//产生48到57的随机数(0-9的键位值)
				ret2=Math.abs(rd.nextInt())%26+97;//产生97到122的随机数(a-z的键位值)
				char ch=(char)ret;
				n+=Character.toString(ch);
			}while(n.length()<5);
			System.out.println(n);
		}
		Long end=System.currentTimeMillis();	
		System.out.println((end-start)+"ms");
	}
	/**
	 * String的"+="操做,是原来的String对象不能继续使用，而且又要产生新的对象因此占用较高的内存
	 * StringBuffer来实现同样循环19次占用内存为33M,String的"+="操做需要占用内存为40M 相差7M
	 * StringBuilder是StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候（这种情况很普遍）。如果可能，建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。
	 * */
	public void MemoryTest(){
		
		//打印系统所有参数设置
		//System.getProperties().list(System.out);
		//String sb="abcdefghigklmn";
		StringBuffer sb=new StringBuffer("abcdefghigklmn");
		System.out.print("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"M");
		System.out.print("totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		int a=0;
		while(true){
			try{
				//sb+=sb;
				sb.append(sb);
				a++;
				System.out.println("第"+a+"次循环:"+Runtime.getRuntime().totalMemory()/1024/1024+"M");
			}catch(Error e){
				System.out.println("最大循环次数:"+a);
				System.out.println("实际字节数:"+sb.length()/1024/1024+"M");
				System.out.print("已使用内存totalMemory:");
				System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
				System.out.println("错误原因"+e);
				break;
			}
		}
	}
	/**
	 *数组透析
	 * 一下的例子说明二维数组要比一维数组占用更多的内存，所以在编写程序的时候尽量少用二维数组
	 * 
	 * */
	public void TestArray(){
		
		try{
//			byte[] bt=new byte[1024*1024*2];//初始化一个2M的数组
//			for(int i=0;i<bt.length;i++){
//				bt[i]=(byte)i;
//			}
//			System.out.print("已使用内存:");//已使用内存:4M
//			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
			byte[][] bt2=new byte[1024*1024][2];
			for(int i=0;i<bt2.length;i++){
				bt2[i][0]=(byte)i;
				bt2[i][1]=(byte)i;
			}
			System.out.print("已使用内存:");//已使用内存:26M
			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
			
		}catch(Exception e){
			
		}
		
	}
/* 
	使用数组赋值取值 使用HashMap赋值取值 比较
	HashMap的检索速度超快0ms,建议在简单存储数据时优先考虑数组因为他比较节省内存
	 如果你频繁查询数据的时候建议使用HashMap查询效率比较高
	 一下两个例子可以说明
	 TestHashMap()方法 使用数组赋值取值
	maxMemory:1016M
	已使用内存:43M
	取值结果：1048575
	赋值循环时间：474ms
	取值循环时间：8ms
	已使用内存:43M
	************************
	TestHashMap2()方法 使用HashMap赋值取值
	maxMemory:1016M
	已使用内存:326M
	取值结果：2097151
	赋值循环时间：3912ms
	取值循环时间：0ms
	已使用内存:326M
*/
	
	public void TestHashMap(){
		System.out.print("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"M");
		long start=System.currentTimeMillis();
		int len=1024*1024;
		int[][] in=new int[len][2];
		for(int i=0;i<in.length;i++){
			in[i][0]=i;
			in[1][1]=i+1;
		}
		System.out.print("已使用内存:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		long get=System.currentTimeMillis();
		for(int i=0;i<in.length;i++){
			if(in[i][0]==(1024*1024-1)){
				System.out.println("取值结果："+in[i][0]);
			}
		}
		long end=System.currentTimeMillis();
		System.out.println("赋值循环时间："+(get-start)+"ms");
		System.out.println("取值循环时间："+(end-get)+"ms");
		System.out.print("已使用内存:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		
	}
	public void TestHashMap2(){
		System.out.print("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"M");
		long start=System.currentTimeMillis();
		int len=1000;
		HashMap ma=new HashMap();
		for(int i=0;i<len;i++){
			ma.put("ma"+i, i);
		}
		System.out.print("已使用内存:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		long get=System.currentTimeMillis();
		System.out.println("取值结果："+ma.get("ma"+(len)));
		long end=System.currentTimeMillis();
		System.out.println("赋值循环时间："+(get-start)+"ms");
		System.out.println("取值循环时间："+(end-get)+"ms");
		System.out.print("已使用内存:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		
	}
	/*
	 * 获取计算机的一些参数
	 * 
	 * 
	 */
	public void getcanshu(){
		//时间
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd HHmmss");
		System.out.println("时间:"+format.format(date.getTime()));
		//jvm内存
		System.out.println("jvm内存:"+Runtime.getRuntime().totalMemory()/1024+"k");
		System.out.println("操作系统名称:"+System.getProperty("os.name"));//得到操作系统名称
		System.out.println("操作系统版本:"+System.getProperty("sun.os.patch.level"));//得到操作系统版本 
		System.out.println("CPU:"+System.getProperty("sun.cpu.isalist"));
	}
	//字符转型
	public void changeByte(){
		String str="nidnfddd";
		try {
			str=new String(str.getBytes("iso-8858-1"),"gbk");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//test you method
		test ss=new test();
		//ss.createJson();
		//随机数
		ss.testspeed();
		//ss.MemoryTest();
		//ss.TestArray();
		/*
		ss.TestHashMap();
		System.out.println("************************");
		*/
		//ss.TestHashMap2();
		
		
		//ss.getcanshu();
	}	

	
	
	
	
	
}


