package com.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.dao.actionDao;
import com.formBean.Emp;
import com.formBean.Menu;


@Controller
@RequestMapping("/login.do")
public class LoginAction{

	@Autowired(required=true)//自动注入属性actionDao
	private actionDao actiondao;
	
	//注解方式实现
	@RequestMapping(params="method=login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username= request.getParameter("username");
		String password=request.getParameter("password");
		request.setAttribute("username", username);
		request.setAttribute("password", password);
		System.out.println(username+"\t"+username);
		//HttpSession session=request.getSession();
		boolean result= actiondao.checkLogin(username, password);
		if(result){
			System.out.println(username+"登录系统成功");
			return new ModelAndView("index");
		} 
		//response.setCharacterEncoding("utf-8");
		//response.getWriter().print("{success:true,msg:'成功'}");
		//new ModelAndView("loginok")
		return new ModelAndView("error");
	}
	@RequestMapping(params ="method=TreeMenu")
	public void TreeMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("************加载treeMenu*************");
		int parentid=Integer.parseInt(request.getParameter("nodeid"));
		///System.out.println(parentid);
		//actionDao dao=new actionDao();
		String result=actiondao.createTree(parentid);
		response.setCharacterEncoding("utf-8");
		//System.out.println(result);
		response.getWriter().print(result);
	}
	@RequestMapping(params="method=getEmp")
	public void getEmp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("***********加载表格数据*************");
		int start=Integer.parseInt(request.getParameter("start"));
		int limit=Integer.parseInt(request.getParameter("limit"));
		System.out.println("start="+start+"\tlimit="+limit);
		String result=actiondao.empInfo(start,limit);
		response.setCharacterEncoding("utf-8");
		System.out.println("getEmp"+result);
		response.getWriter().print(result);
	}
	@RequestMapping(params="method=getCombo")
	public void getCombo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("***********加载部门*************");
		String outkeyname=request.getParameter("outkeyname");
		String result=actiondao.combo_list(outkeyname);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(result);
	}
	@RequestMapping(params="method=addEmp")
	public void addEmp(HttpServletRequest request,
			HttpServletResponse response,Emp e) throws Exception {
		System.out.println("e.getName:"+e.getEname());
		System.out.println("***********添加员工信息*************");
		String ename=request.getParameter("ename");
		String sex=request.getParameter("sex");
		String dname=request.getParameter("deptid");
		String phone=request.getParameter("phone");
		System.out.println(ename+"\t"+sex+"\t"+dname+"\t"+phone);
		Emp emp=new Emp();
		emp.setEname( ename);
		emp.setSex(sex);
		emp.setPhone(phone);
		emp.setDname(dname);
		response.setCharacterEncoding("utf-8");
		if(actiondao.addEmp(emp)){
			response.getWriter().print("{success:true,msg:'成功'}");
		}
	}
	@RequestMapping(params="method=addNode")
	public void addNode(HttpServletRequest request,
			HttpServletResponse response,Menu me) throws Exception {
		System.out.println("***********添加节点信息*************");
		response.setCharacterEncoding("utf-8");
		if(actiondao.addNode(me)){
			response.getWriter().print("{success:true,msg:'成功'}");
		}
	}
	@RequestMapping(params="method=cheUsername")
	public void cheUsername(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username= request.getParameter("username");
		System.out.println("***********checkUsername*************");
		response.setCharacterEncoding("utf-8");
		if(actiondao.cheUsername(username)){
			response.getWriter().print("{success:true}");
		}else{
			response.getWriter().print("{success:false}");
		}
	}
	@RequestMapping(params="method=addaccount")
	public void addaccount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		System.out.println("***********addaccount*************");
		response.setCharacterEncoding("utf-8");
		if(actiondao.addaccount(username,password)){
			response.getWriter().print("{success:true,msg:'帐户添加成功！'}");
		}else{
			response.getWriter().print("{success:false}");
		}
	}
}