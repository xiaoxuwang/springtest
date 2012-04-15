package com;

import java.lang.reflect.Field;

/**
 * Author:xiaoxu.wang
 * Date:2010-10-9
 * Des:test
 */
public class TestRefection {

	/**
	 * @param args
	 */
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class c;
		try {
			c = Refection.class;
			Field fs1[]=c.getDeclaredFields();
			for(int i=0;i<fs1.length;i++){
				System.out.println(fs1[i].toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}


