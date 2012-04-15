package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Author:xiaoxu.wang
 * Date:2010-11-25
 * Des:
 */
public class tomtest {

	/**
	 * @param args
	 * 
	 */
	//create static method
	public static void changeStr(String str){
		str="changeStr";
		System.out.println("changestr="+str);
	}
	static boolean foo(char c){
		System.out.println(c);
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i=0;
		for(foo('A');foo('B')&&i<2;foo('C')){
			i++;
			foo('D');
		}
//		InetAddress addr;
//		try {
//			addr = InetAddress.getLocalHost();
//			String ip=addr.getHostAddress().toString();//获得本机IP
//			System.out.println(ip);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}


