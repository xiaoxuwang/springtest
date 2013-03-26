package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;

import org.apache.log4j.Logger;

import com.formBean.Dept;
import com.formBean.Emp;
import com.formBean.Menu;

import util.sqlUtil.DBUtil;

/**
 * Author:xiaoxu.wang
 * Date:2010-8-30 杨高南路  浦三路 
 * Des:rr
 */
public class actionDao {
	
	DBUtil dbUtil;
	ResultSet rs;
	private static Logger logger=Logger.getLogger(actionDao.class);
	public boolean checkLogin(String uname,String pwd){
		try {
			dbUtil=new DBUtil();
			String sql="select username from account where status='1' and username='"+uname+"' and password='"+pwd+"'";
			logger.debug(sql);
			rs=dbUtil.Query(sql);
			if(rs.next())return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{dbUtil.close();}
		return false;
	}
	public boolean cheUsername(String uname){
		try {
			dbUtil=new DBUtil();
			String sql="select username from account where username='"+uname+"'";
			logger.debug(sql);
			rs=dbUtil.Query(sql);
			if(rs.next())return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{dbUtil.close();}
		return false;
	}
	//添加帐户
	public boolean addaccount(String username,String password){
		
		String sql="insert  into account (username,password,status) values( '"+username+"','"+password+"','1')";
		try {
			dbUtil=new DBUtil();
			int aa=dbUtil.Update(sql);
			if(aa>0)return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}
		return false;
	} 
	
	/*
	 *[{'text':'ExtWindow','id':'1-','leaf':false},{'text':'ExtData','id':'2-','leaf':false}]
	 * */
	public String createTree(int parentid){
		StringBuilder treeNode=new StringBuilder();
		try {
			dbUtil=new DBUtil();
			String sql="select nodeid,text,leaf,href from treenode where parentid="+parentid;
			rs=dbUtil.Query(sql);
			boolean bool=false;
			treeNode.append("[");
			while(rs.next()){
				treeNode.append("{'text':'"+rs.getString("text")+"',");
				treeNode.append("'id':'"+rs.getString("nodeid")+"-"+rs.getString("href")+"',");
				bool=rs.getString("leaf").equals("1")?true:false;
				treeNode.append("'leaf':"+bool+"},");
			}
			treeNode=treeNode.delete(treeNode.length()-1,treeNode.length());
			treeNode.append("]");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{dbUtil.close();}
		//System.out.println(treeNode);
		return treeNode.toString();
	}
	public String empInfo(int start,int limit){
		String result="";
		int count=getcountNum("emp");
		ArrayList<Emp> list=new ArrayList<Emp>();
		Map map=new HashMap();
		try{
			dbUtil=new DBUtil();
			String sql="select empid,ename,dname,sex,phone,createdate from emp limit "+start+","+limit;
			rs=dbUtil.Query(sql);
			while(rs.next()){
			 Emp me=new Emp();
			 me.setId( rs.getString("empid"));
			 me.setEname(rs.getString("ename"));
			 me.setSex( rs.getString("sex"));
			 me.setDname(rs.getString("dname"));
			 me.setPhone(rs.getString("phone"));
			 me.setCreateDate(rs.getString("createdate"));
			 list.add(me);
			}
		map.put("total",count);
		map.put("data", list);
		//JSONArray json=JSONArray.fromObject(list);
		JSONObject json=JSONObject.fromObject(map);
		result=json.toString();
		//System.out.println(result);
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{dbUtil.close();}
		return result;
	}
	/**
	 * 获取数据的总条数
	 * param:tablename
	 * return:int
	 * */
	public int getcountNum(String tablename){
		int count=0;
		String sql="select count(empid) from "+tablename;
			try {
				dbUtil=new DBUtil();
				rs=dbUtil.Query(sql);
				if(rs.next()){
					count=rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{dbUtil.close();}
		return count;
	}
	
	public String combo_list(String outkeyname){
		String sql=getOutkeysql(outkeyname);
		List list=new ArrayList();
		JSONArray json=null;
		try {
			dbUtil=new DBUtil();
			rs=dbUtil.Query(sql);
			while(rs.next()){
				HashMap map=new HashMap();
				map.put("value", rs.getString(1));
				map.put("key", rs.getString(2));
				list.add(map);
			}
			
			json=JSONArray.fromObject(list);
			System.out.println(json.toString());
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}
		
		return json.toString();
	}
	public String getOutkeysql(String outkeyname){
		String result="";
		String sql="select outkeysql from outkeytb where outkeyname='"+outkeyname+"'";
		try {
			dbUtil=new DBUtil();
			rs=dbUtil.Query(sql);
			if(rs.next()){
				result=rs.getString("outkeysql");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}
		
		return result;
	}
	public boolean addEmp(Emp emp){
		int dname=Integer.parseInt(emp.getDname());
		String sql="insert  into emp (ename,sex,dname,phone,createdate) values( '"+emp.getEname()+"','"+emp.getSex()+"','"+dname+"', '"+emp.getPhone()+"','"+emp.getCreateDate()+"')";
		try {
			dbUtil=new DBUtil();
			int aa=dbUtil.Update(sql);
			if(aa>0)return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}
		return false;
	}
	//添加节点
	public boolean addNode(Menu m){
		
		String sql="insert  into treenode (text,leaf,href,parentid) values( '"+m.getText()+"','"+m.getLeaf()+"','"+m.getHref()+"', "+m.getParentid()+")";
		try {
			dbUtil=new DBUtil();
			int aa=dbUtil.Update(sql);
			if(aa>0)return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}
		return false;
	}
	
	/**
	 * 把对象转换为Json格式
	 * return:[{"cls":"","id":"1","leaf":false,"nodeid":0,"parentid":0,"text":"ExtWindow"}]
	 * 
	 * */
	
	public  String jsonTodata(){
		String menuString="";
		ArrayList<Menu> list=new ArrayList<Menu>();
		boolean bool=false;
		try{
			dbUtil=new DBUtil();
			String sql="select nodeid,text,leaf,href from treenode";
			rs=dbUtil.Query(sql);
			
			while(rs.next()){
			 Menu me=new Menu();
			 me.setText( rs.getString("text"));
			 me.setId(rs.getString("nodeid"));
			 bool=rs.getString("leaf").equals("1")?true:false;
			 //me.setLeaf( bool);
			  list.add(me);
			}
		//------------------------------------------
		//Map map=new HashMap();
		//map.put("Menu", list);
		//JSONObject json=JSONObject.fromObject(map);
		//{"Menus":[{"cls":"","id":"2","leaf":false,"nodeid":0,"parentid":0,"text":"ExtData"},{"cls":"","id":"5","leaf":true,"nodeid":0,"parentid":0,"text":"text5"}]}
		//------------------------------------------
		JSONArray json=JSONArray.fromObject(list);
		//[{"cls":"","id":"1","leaf":false,"nodeid":0,"parentid":0,"text":"ExtWindow"}]
		String result=json.toString();
		System.out.println(result);
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{
			dbUtil.close();
		}
		
		return menuString;
	}
	
	//程序模拟自动添加数据
	public void testadd(){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		//System.out.println("时间:"+format.format(date.getTime()));
		Long start=System.currentTimeMillis();
		System.out.print("start>totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		try {
			
			dbUtil=new DBUtil();
			for(int i=0;i<1;i++){
				for(int j=0;j<100000;j++){
					String de=format.format(date.getTime());
					int a=Integer.parseInt(get0_9(1));
					String s=a%2==0?"F":"M";
					int dname=(Integer.parseInt(get0_9(1))+1);
					String sql="insert  into emp (ename,sex,dname,phone,createdate) values( '"+geta_z(4)+get0_9(3)+"','"+s+"','"+dname+"', '133"+get0_9(8)+"','"+de+"')";
					dbUtil.addBatch(sql);
				}
				int a=dbUtil.executeBatch();
				if(a>0)System.out.println("OK");
				//System.out.print("update<"+i+">totalMemory:");
				//System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
			}
		//插入10000条数据2047ms JVM最大内存64M
		//插入10万条数据15271ms JVM最大内存64M
		//插入100万条数据耗时：157879ms JVM最大内存64M 实际56
		//插入100万条数据耗时：157475ms JVM最大内存1024M 实际423M
		Long end=System.currentTimeMillis();	
		//System.out.println("插入100万条数据耗时："+(end-start)+"ms");
		System.out.print("update<end>totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.print("END>totalMemory:");
			System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
			e1.printStackTrace();
		}finally{
			dbUtil.close();
		}
		//System.out.println(e.getEname()+"\t"+e.getSex()+"\t"+e.getDname()+"\t"+e.getPhone());
	}
	public String geta_z(int n){
		String result="";
		Random rd=new Random();
		int ret;
		do{
			ret=Math.abs(rd.nextInt())%26+97;
			char ch=(char)ret;
			result+=Character.toString(ch);
		}while(result.length()<n);
		return result;
	}
	public String get0_9(int n){
		String result="";
		Random rd=new Random();
		int ret;
		do{
			ret=Math.abs(rd.nextInt())%10+48;
			char ch=(char)ret;
			result+=Character.toString(ch);
		}while(result.length()<n);
		return result;
	}
	/** Method:test_getemp
	 * 比较索引字段和非索引字段查询效率
	 * 测试数据量为200万条
	 * 测试表emp 主键empid:2217355 phone:'13315325076'
	 * 测试结果： 
	 * 	   主键索引查询耗时:360ms
	 *   手机号码查询耗时:3920ms
	 * 差距会随着数据的增多越来越大
	 * */
	public void test_getemp(){
		Long start=System.currentTimeMillis();
		//System.out.println(start);
		try{
			dbUtil=new DBUtil();
			String sql="select empid,ename,dname,sex,phone from emp where empid=2217355";
			String sql2="select empid,ename,dname,sex,phone from emp where phone='13315325076'";
			rs=dbUtil.Query(sql);
			if(rs.next()){
			 Long end=System.currentTimeMillis();
			 System.out.println("主键索引time:"+(end-start)+"ms");
			 System.out.print( rs.getString("empid")+"\t");
			 System.out.print(rs.getString("ename")+"\t");
			 System.out.print( rs.getString("sex")+"\t");
			 System.out.print(rs.getString("dname")+"\t");
			 System.out.println(rs.getString("phone")+"\t");
			}
			start=System.currentTimeMillis();
			rs=dbUtil.Query(sql2);
			if(rs.next()){
				 Long end=System.currentTimeMillis();
				 System.out.println("手机号码time:"+(end-start)+"ms");
				 System.out.print( rs.getString("empid")+"\t");
				 System.out.print(rs.getString("ename")+"\t");
				 System.out.print( rs.getString("sex")+"\t");
				 System.out.print(rs.getString("dname")+"\t");
				 System.out.println(rs.getString("phone")+"\t");
				}
		}catch(Exception e){ 
			e.printStackTrace();
		}finally{dbUtil.close();}
		
	}
	public static void main(String[] args) {
		System.out.print("maxMemory:");
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"M");
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		System.out.println("开始时间:"+format.format(date.getTime()));
		actionDao dao=new actionDao();
		//dao.empInfo(0,5);
		//匹配字符是否为11位数字   是:true 否 :false
		//
		//dao.combo_list("dept");
		//dao.testadd();
		dao.empInfo(0, 10);
		//dao.jsonTodata();
		//for(int i=0;i<100;i++){
			
			//System.out.println(dao.get0_9(1));
		//}
		//dao.test_getemp();
		Date date2=new Date();
		System.out.println("结束时间:"+format.format(date2.getTime()));
		System.out.print("totalMemory:");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
	}
	
	
}


