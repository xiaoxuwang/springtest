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
		boolean isMultipart= fu.isMultipartContent(request);// �жϱ��Ƿ�Ϊmultipart����
		System.out.println(isMultipart);
		FileItemIterator iter=fu.getItemIterator(request);// ȡ�ñ��������
		while(iter.hasNext()){
			// ȡ��ÿһ������   
			FileItemStream item = iter.next();
			// ȡ�ñ��������   
		    String name = item.getFieldName();   
			// ȡ�ñ����Ӧ��������   
		    InputStream stream = item.openStream();   
		    if (item.isFormField()) {   
		        // һ�����Ĵ���   
		        // ��������ת���ַ������磺   
		        String value = Streams.asString(stream, "UTF-8");   
		    } else {   
		        // �ļ�����Ĵ���   
		        // ȡ���ļ������ContentType���磺   
		        String fileContentType = item.getContentType();   
		        // ȡ���ļ������磺   
		        String filename = item.getName();   
		        // �����ⷽʽ����������stream   
		        // ���÷�ʽ ��   
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
					//�������������������ҳ��
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


