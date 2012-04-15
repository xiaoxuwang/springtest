package util.charFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 字符编码处理
 * */
public class charFilter implements Filter {

	String charset="";
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
			HttpServletRequest request=(HttpServletRequest)arg0;
			HttpServletResponse response=(HttpServletResponse)arg1;
			request.setCharacterEncoding(charset);
			response.setCharacterEncoding(charset);
			arg2.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		 
		this.charset= arg0.getInitParameter("charset");//获取web.xml配置参数

	}

}
