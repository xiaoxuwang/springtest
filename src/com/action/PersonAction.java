package com.action;


import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import util.sqlUtil.DBUtil;

/**
 * Author:xiaoxu.wang
 * Date:2010-10-29
 * Des:q
 */
@Controller
@RequestMapping("/work.do")
public class PersonAction {

	//@Autowired(required=true)
	//private actionDao actiondao;
	
	@RequestMapping(params="method=addimage")
	public void addimage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DBUtil dbUtil=null;
		//DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload fu=new ServletFileUpload();
		boolean isMultipart= fu.isMultipartContent(request);// 判断表单是否为multipart类型
		System.out.println(isMultipart);
		FileItemIterator iter=fu.getItemIterator(request);// 取得表单域迭代器
		while(iter.hasNext()){
			// 取得每一个表单域   
			FileItemStream item = iter.next();
			// 取得表单域的名字   
		    String name = item.getFieldName();   
			// 取得表单域对应的数据流   
		    InputStream stream = item.openStream();   
		    if (item.isFormField()) {   
		        // 一般表单域的处理   
		        // 将数据流转成字符串，如：   
		        String value = Streams.asString(stream, "UTF-8");   
		    } else {   
		        // 文件表单域的处理   
		        // 取得文件表单域的ContentType，如：   
		        String fileContentType = item.getContentType();   
		        // 取得文件名，如：   
		        String filename = item.getName();   
		        // 以任意方式操作数据流stream   
		        // 调用方式 略   
		    }   
		}
		/*
		String filename= request.getParameter("image");
		String desc= request.getParameter("desc");
		System.out.println("filename="+filename);
		System.out.println("desc="+desc);
		System.out.println("***********addimage*************");
		FileInputStream str=new FileInputStream(filename);
		String sql="insert  into image (desc,image) values(?,?)";
		try {
			dbUtil=new DBUtil();
			PreparedStatement ps=dbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, desc);
			ps.setBinaryStream(2, str,str.available());
			ps.execute();
			System.out.println("Success,You Have Insert an Image Successfully");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}*/
	}
	@RequestMapping(params="method=showimage")
	public void showimage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id=Integer.parseInt( request.getParameter("id"));
		System.out.println("id="+id);
		System.out.println("***********showimage*************");
		DBUtil dbUtil=null;
		ResultSet rs=null;
		String sql="select image from image where id="+1;
		try {
			dbUtil=new DBUtil();
			rs=dbUtil.Query(sql);
			while(rs.next()){
				ServletOutputStream sout=response.getOutputStream();
				InputStream in=rs.getBinaryStream(1);
				byte b[]=new byte[0x7a120];
				for(int i = in.read(b); i != -1;){ 
					sout.write(b);
					//将缓冲区的输入输出到页面
					in.read(b);
				}
				sout.flush();
				sout.close();
			}
			System.out.println("Success,You Have output an Image Successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{dbUtil.close();}
	}
}


