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
	//�������������ʽ
	public static void testRegx(String regx,String str){
		
		boolean result=Pattern.compile("^[0-9]{11}$").matcher("13429161770").find();
		System.out.println(result);
	}
	/*
	 * ���ݶ��񴴽�Json��ʽ������
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
	 * ��ȡ��0~9��������ֻ����ǣ�a~z�������ĸ
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
				ret=Math.abs(rd.nextInt())%10+48;//����48��57�������(0-9�ļ�λֵ)
				ret2=Math.abs(rd.nextInt())%26+97;//����97��122�������(a-z�ļ�λֵ)
				char ch=(char)ret;
				n+=Character.toString(ch);
			}while(n.length()<5);
			System.out.println(n);
		}
		Long end=System.currentTimeMillis();	
		System.out.println((end-start)+"ms");
	}
	/**
	 * String��"+="����,��ԭ����String�����ܼ���ʹ�ã�������Ҫ�����µĶ������ռ�ýϸߵ��ڴ�
	 * StringBuffer��ʵ��ͬ��ѭ��19��ռ���ڴ�Ϊ33M,String��"+="������Ҫռ���ڴ�Ϊ40M ���7M
	 * StringBuilder��StringBuffer ��һ�������滻�������ַ����������������߳�ʹ�õ�ʱ������������ձ飩��������ܣ��������Ȳ��ø��࣬��Ϊ�ڴ����ʵ���У����� StringBuffer Ҫ�졣
	 * */
	public void MemoryTest(){
		
		//��ӡϵͳ���в�������
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
				System.out.println("��"+a+"��ѭ��:"+Runtime.getRuntime().totalMemory()/1024/1024+"M");
			}catch(Error e){
				System.out.println("���ѭ������:"+a);
				System.out.println("ʵ���ֽ���:"+sb.length()/1024/1024+"M");
				System.out.print("��ʹ���ڴ�totalMemory:");
				System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
				System.out.println("����ԭ��"+e);
				break;
			}
		}
	}
	/**
	 *����͸��
	 * һ�µ�����˵����ά����Ҫ��һά����ռ�ø�����ڴ棬�����ڱ�д�����ʱ�������ö�ά����
	 * 
	 * */
	public void TestArray(){
		
		try{
//			byte[] bt=new byte[1024*1024*2];//��ʼ��һ��2M������
//			for(int i=0;i<bt.length;i++){
//				bt[i]=(byte)i;
//			}
//			System.out.print("��ʹ���ڴ�:");//��ʹ���ڴ�:4M
//			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
			byte[][] bt2=new byte[1024*1024][2];
			for(int i=0;i<bt2.length;i++){
				bt2[i][0]=(byte)i;
				bt2[i][1]=(byte)i;
			}
			System.out.print("��ʹ���ڴ�:");//��ʹ���ڴ�:26M
			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
			
		}catch(Exception e){
			
		}
		
	}
/* 
	ʹ�����鸳ֵȡֵ ʹ��HashMap��ֵȡֵ �Ƚ�
	HashMap�ļ����ٶȳ���0ms,�����ڼ򵥴洢����ʱ���ȿ���������Ϊ���ȽϽ�ʡ�ڴ�
	 �����Ƶ����ѯ���ݵ�ʱ����ʹ��HashMap��ѯЧ�ʱȽϸ�
	 һ���������ӿ���˵��
	 TestHashMap()���� ʹ�����鸳ֵȡֵ
	maxMemory:1016M
	��ʹ���ڴ�:43M
	ȡֵ�����1048575
	��ֵѭ��ʱ�䣺474ms
	ȡֵѭ��ʱ�䣺8ms
	��ʹ���ڴ�:43M
	************************
	TestHashMap2()���� ʹ��HashMap��ֵȡֵ
	maxMemory:1016M
	��ʹ���ڴ�:326M
	ȡֵ�����2097151
	��ֵѭ��ʱ�䣺3912ms
	ȡֵѭ��ʱ�䣺0ms
	��ʹ���ڴ�:326M
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
		System.out.print("��ʹ���ڴ�:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		long get=System.currentTimeMillis();
		for(int i=0;i<in.length;i++){
			if(in[i][0]==(1024*1024-1)){
				System.out.println("ȡֵ�����"+in[i][0]);
			}
		}
		long end=System.currentTimeMillis();
		System.out.println("��ֵѭ��ʱ�䣺"+(get-start)+"ms");
		System.out.println("ȡֵѭ��ʱ�䣺"+(end-get)+"ms");
		System.out.print("��ʹ���ڴ�:");
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
		System.out.print("��ʹ���ڴ�:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		long get=System.currentTimeMillis();
		System.out.println("ȡֵ�����"+ma.get("ma"+(len)));
		long end=System.currentTimeMillis();
		System.out.println("��ֵѭ��ʱ�䣺"+(get-start)+"ms");
		System.out.println("ȡֵѭ��ʱ�䣺"+(end-get)+"ms");
		System.out.print("��ʹ���ڴ�:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		
	}
	/*
	 * ��ȡ�������һЩ����
	 * 
	 * 
	 */
	public void getcanshu(){
		//ʱ��
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd HHmmss");
		System.out.println("ʱ��:"+format.format(date.getTime()));
		//jvm�ڴ�
		System.out.println("jvm�ڴ�:"+Runtime.getRuntime().totalMemory()/1024+"k");
		System.out.println("����ϵͳ����:"+System.getProperty("os.name"));//�õ�����ϵͳ����
		System.out.println("����ϵͳ�汾:"+System.getProperty("sun.os.patch.level"));//�õ�����ϵͳ�汾 
		System.out.println("CPU:"+System.getProperty("sun.cpu.isalist"));
	}
	//�ַ�ת��
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
		//�����
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


