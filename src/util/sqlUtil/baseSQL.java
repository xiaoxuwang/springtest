package util.sqlUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class baseSQL {

	
	/*
     * 功能：根据HashMap和表名生成插入SQL
     * 根据HashMap的key和value生成SQL语句
     */  
	public static String createInsertSQLFromHashMap(HashMap hm,String tableName){
		Iterator cols = hm.entrySet().iterator();
		StringBuffer colmnBuffer=(new StringBuffer("insert into ")).append(tableName).append("(");
		StringBuffer valueBuffer=new StringBuffer("values(");
		int num=0;
		while(cols.hasNext()){
			Map.Entry map=(Map.Entry) cols.next();
			if(num++!=0){
				colmnBuffer.append(",");
				valueBuffer.append(",");
			}
			String key=(String)map.getKey();
			String value=map.getValue().toString();
			
			if(value==null){
				continue;
			}
			colmnBuffer.append(key);
			if(value.indexOf("nextval")>-1){
				valueBuffer.append(value);			
			}else{
				valueBuffer.append("'").append(value).append("'");
			}
		}
		colmnBuffer.append(")");
		valueBuffer.append(")");
		colmnBuffer.append(valueBuffer);
		return colmnBuffer.toString();
	}
	
}
