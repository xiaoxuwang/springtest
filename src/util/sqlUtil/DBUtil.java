package util.sqlUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import util.connUtil.ConnectionSource;



/**
 * Author:xiaoxu.wang
 * Date:2010-8-24
 * 
 */
public class DBUtil {

	static Logger logger=Logger.getLogger(DBUtil.class);
	
	private Connection conn=null;
	private ResultSet rs=null;
	private Statement qryStmt=null;
	private Statement uptStmt=null;
	
	public DBUtil() throws ClassNotFoundException, SQLException{
		createConn();
	}
	
	private synchronized void  createConn()throws ClassNotFoundException,SQLException{
		//System.out.println("OOOOOOOOOOOKKKKKKKKK");
		conn=ConnectionSource.getConnection();
	}
	  /**
	   * 功能：获得已经创建好的数据库链接
	   * @param     
	   * @return    Connection
	   * @exception 获取数据源错
	   */
	public Connection getConnection(){
		return conn;
	}
	//query
	public ResultSet Query(String sql) throws SQLException{   
		 closeResultSet();
		 setQryStmt();
		 rs=qryStmt.executeQuery(sql);
		 return rs;
	}
	public String[][] QueryArray(String sql)throws SQLException{
		ResultSetMetaData rsmd=null;
		rs=Query(sql);
		rsmd=rs.getMetaData();
		rs.last();
		String[][] str=new String[rs.getRow()][rsmd.getColumnCount()];
		rs.beforeFirst();
		while(rs.next()){
			int row=rs.getRow();
			for(int i=0;i<rsmd.getColumnCount()-1;i++){
				str[rs.getRow()-1][i]=rs.getString(i+1);
			}
		}
		return str;
		
	}
	
    public int Update(String sql)throws SQLException{
    	return Update(sql,0);
    }
    
    public int Update(String sql,int code) throws SQLException{
    	int count=0;
    	setUptStmt();
		try{
			count = uptStmt.executeUpdate(sql);
		}catch(SQLException e){
			rollback();
			if(code==1){
				count=0;
			}else{
				throw new SQLException(e.toString());
			}
			
		}
		if(count!=0){
			commit();
		}else{
			rollback();
		}
		
    	return count;
    }
    public void addBatch(String sql) throws SQLException{
    	setUptStmt();
    	uptStmt.addBatch(sql);
    }
    
    
    public void addBatch(ArrayList<String> list) throws SQLException{
    	setUptStmt();
    	for(int i=0;i<list.size();i++){
    		uptStmt.addBatch(list.get(i));
    	}
    }
    
    public int executeBatch() throws SQLException{
		return  executeBatch(0);
    }
    /*
     * code 0:判断所有的sql是否成功
     *      1:执行过程中不出错就可以
     */
    public int executeBatch(int code) throws SQLException{
    	int[] re=null;
    	int ri=0;
    	setUptStmt();
    	try{   		
    		re=uptStmt.executeBatch();
		}catch(SQLException e){
			rollback();
			e.printStackTrace();
			throw new SQLException(e.toString());
		}
		if(re!=null){
			boolean bool=false;
			if(code==0){
				for(int i=0;i<re.length;i++){
					if(re[i]==0){
						rollback();
						bool=true;
						break;
					}
				}
				if(!bool){
					commit();
					ri=1;
				}
			}else if(code==1){
				commit();
				ri=1;
			}else{
				commit();
				ri=1;
			}
		}
		return  ri;
    }
    
	@SuppressWarnings("unused")
	private void closeResultSet() throws SQLException{

		    if (rs != null) {

		      rs.close();      rs = null;
		    }
	}
	  /**
	   * 功能：获得已经创建好的数据库链接
	   * @param     
	   * @return    Statement
	   */
	public Statement getQryStatement(){
		return qryStmt;
	}

	public Statement getUptStatement() throws SQLException{
		return uptStmt;
	}
	@SuppressWarnings("unused")
	private  void closeStatement(Statement stmt) throws SQLException{

		    if (stmt != null) {

		      stmt.close();      stmt = null;
		    }
	}
	private void setQryStmt() throws SQLException{
		if(qryStmt==null){
			conn.setAutoCommit(true);
			qryStmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
	}
	private void setUptStmt() throws SQLException{
		if(uptStmt==null){
			conn.setAutoCommit(false);
			uptStmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
	}
    public void commit() throws SQLException{
    	conn.commit();
    }
    
    public void rollback() throws SQLException{
    	conn.rollback();
    }
    
    public synchronized void close() {
    	try{
    		if(rs!=null){
        	    closeResultSet();      
        	}
        	if(uptStmt!=null) {
        		uptStmt.close();      
        	}
        	if(qryStmt!=null) {
        		qryStmt.close();      
        	}
        	if(conn!=null){
        		conn.close();
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

}









