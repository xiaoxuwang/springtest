package util;

import java.sql.*;

import java.text.*;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.GregorianCalendar;

import exception.AppException;


/**
 * <p>Title: 获取时间</p>
 * <p>Description: 采用统一的时间获取类，目的在于将来将时间进行统一</p>
 */
public class DateUtil {
  public DateUtil() {
  }

//功能：取应用服务器日期
  public static String getDateTime() {
	  return getCurrentDateByFormat("yyyy-MM-dd HH:mm:ss");
  }
  
  public static String getCurrentDateByFormat(String formatStr){
	    long currentTime = System.currentTimeMillis();
	    java.util.Date date = new java.util.Date(currentTime);
	    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(formatStr);
	    return formatter.format(date);  
  }
  
//功能：转换long型为日期型字串 yan_add－20061212
  public static String getDateTime(long al_datetime) {
    java.util.Date date = new java.util.Date(al_datetime);
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    return formatter.format(date);
  }

  public static String getDateString(java.util.Date inDate) {
    return inDate.toString();
  }

  public static java.util.Date getDateNDays(java.util.Date date, int days) {//把给定日期与给定天数进行加减运算,返回一个新日期
    if (days > 36500 || days < -36500) {
      System.out.println("请把日期限制在100年内");
      return null;
    }
    long l1 = 24, l2 = 60, l3 = 1000, l4 = days;
    long lDays = l1 * l2 * l2 * l3 * l4; //所改变天数的毫秒数
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    long lCurrentDate = calendar.getTimeInMillis(); //给定日期的毫秒日期
    long lUpdatedDate = lCurrentDate + lDays; //给定日期与给定天数运算后的毫秒日期
    java.util.Date dateNew = new java.util.Date(lUpdatedDate); //结果日期
    return dateNew;
  }
  /**
   * 把给定日期与给定月数进行运算,月数可以是负数.返回给定日期与给定日期的差或和
   * @param as_date:格式是yyyymm的日期,现支持yyyy-mm-dd型日期
   * @param months
   * @return
   * @throws AppException
   */
  public static Date getDateNMonths(String as_date, int months)throws AppException{
    String as_dateTem="";
    //yan_modi_20060708
    if( as_date.length()==10 ){//yyyy-mm-dd型日期
      as_dateTem= as_date.substring(0,7)+"-01";
    }else{//yyyymm型日期
      as_dateTem = as_date.substring(0,4) + "-" + as_date.substring(4,as_date.length()) + "-01"; //把yyyymm型的年月日期转换为yyyy-mm型的日期,在后面和-01相加,组成一个合法日期
    }
    Date ad_date = null;
    try {
      ad_date = getDate(as_dateTem);
    }  catch (AppException ex) {
      ex.setClientMessage("日期处理出错！");
      ex.setLogMessage("getDateNMonths(String as_date, int months) 出错！");
      throw ex;
    }
    ad_date=getDateNMonths(ad_date,months);

    //入参字符串是格式为yyyymm的日期
    return ad_date;
  }

  //把给定日期与给定月数进行运算,月数可以是负数.返回给定日期与给定日期的差或和
  //若形成的新日期非法,则自动对新日期进行调整,例如:2004年1月31日加1个月为2004年2月31日,系统自动调整为2004年2月29日
  public static Date getDateNMonths(java.util.Date date, int months) {
    if (months == 0) { //月数为零,直接返回给定日期
      return date;
    }
    if (months > 1200 || months < -1200) { //日期限制在100年以内
      System.out.println("请把日期限制在100年内");
      return null;
    }
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(date);
    gc.add(Calendar.MONTH, months);
    java.util.Date date2 = gc.getTime();
    return date2;
  }

//功能：取得数据库系统日期，数据库类型可选，如为Oracle
  public static Date getDateTime(Connection inConnection,String as_DBType) throws  AppException {//取数据库系统日期
//    String sql = "select TO_CHAR(SYSDATE,'YYYY-MM-DD') d from dual ";//oracle
    String sql = "";
    System.out.println("DateUtil getDateTime the db type is : " + as_DBType);
    if (as_DBType.equalsIgnoreCase("Oracle")){
      sql = "SELECT sysdate from dual";
    }else if (as_DBType.equalsIgnoreCase("Sybase")){
      sql="SELECT GetDate()";//sybase
    }else if (as_DBType.equalsIgnoreCase("Informix")){
      sql="SELECT GetDate()";//Informix
      Calendar date = Calendar.getInstance();
      Date sysDate = date.getTime();
      return sysDate;
    }

    Statement statement = null;
    ResultSet rs = null;
    try {
      statement = inConnection.createStatement();
      rs = statement.executeQuery(sql);

      //while (rs.next()) {
      if(rs.next()) {
        String date = rs.getString(1);      //add by wang_xm
        System.out.println("the date is :" + date);
        System.out.println("the getDate is :" +rs.getDate(1));
//        try {
//          java.util.Date d = DateFormat.getDateInstance().parse(date);
//          return d;
//        } catch (ParseException e) {
//          throw new AppException(e, "日期数据转换失败");
//        }
        if(date.indexOf(".") >0){
        	date = date.substring(0, date.indexOf("."));
        }
        System.out.println("the date2 is :" + date);
        java.util.Date d = getDate(date);
        //System.out.println("the date is not null..");
        return d;
        
      }
      System.out.println("the date is null..");
      return null;
    } catch (SQLException e) {
      System.out.println("query sysdate error..........");
      e.printStackTrace();
      throw new AppException(e, "取数据库服务器日期错", "取数据库服务器日期错");
    } finally {
       try {
         if (rs != null)  rs.close();
         if (statement != null) statement.close();
       } catch (Exception e) {
          e.printStackTrace();
       }
       rs = null;
       statement = null;
    }
  }

  /*
   * 对日期进行格式化
   * @适应PB客户端编程调用
   * @wang_xm
   */
  public static String formatDate(Date date,String format){
    if (date == null){
      return "";
    }
    if (format == null || format.trim().equals("")){
      format = "yyyy-MM-dd HH:mm:ss";
    }
    Locale locale = new Locale("en","US");
    SimpleDateFormat formatter = new SimpleDateFormat(format.trim(),locale);
    return formatter.format(date);
  }

  /**
   * 得到当前日期(java.sql.Date类型)，注意：没有时间，只有日期
   * @return 当前日期
   */
  public static java.sql.Date getDate() {
    Calendar oneCalendar = Calendar.getInstance();
    return getDate(oneCalendar.get(Calendar.YEAR),
                   oneCalendar.get(Calendar.MONTH) + 1,
                   oneCalendar.get(Calendar.DATE));
  }

  public static int getIntervalDay(java.sql.Date startDate,java.sql.Date endDate) {//根据所给的起始,终止时间来计算间隔天数
    long startdate = startDate.getTime();    long enddate = endDate.getTime();
    long interval = enddate - startdate;
    int intervalday = (int) interval / (1000 * 60 * 60 * 24);
    return intervalday;
  }

  public static java.sql.Date getDate(int yyyy, int MM, int dd) {
   //根据所给年、月、日，得到日期(java.sql.Date类型)，注意：没有时间，只有日期。
   //年、月、日不合法，会抛IllegalArgumentException(不需要catch)
    if (!verityDate(yyyy, MM, dd)) {
      throw new IllegalArgumentException("This is illegimate date!");
    }

    Calendar oneCalendar = Calendar.getInstance();
    oneCalendar.clear();
    oneCalendar.set(yyyy, MM - 1, dd);
    return new java.sql.Date(oneCalendar.getTime().getTime());
  }

  public static boolean verityDate(int yyyy, int MM, int dd) {//根据所给年、月、日，检验是否为合法日期。
    boolean flag = false;

    if (MM >= 1 && MM <= 12 && dd >= 1 && dd <= 31) {
      if (MM == 4 || MM == 6 || MM == 9 || MM == 11) {
        if (dd <= 30) {
          flag = true;
        }
      }  else if (MM == 2) {
        if (yyyy % 100 != 0 && yyyy % 4 == 0 || yyyy % 400 == 0) {
          if (dd <= 29) {
            flag = true;
          }
        } else if (dd <= 28) {
          flag = true;
        }
      } else {
        flag = true;
      }
    }
    return flag;
  }

 //功能：根据所给的起始,终止时间来计算间隔月数
 //入参的格式是：yyyymm或yyyy-mm-dd
  public static int getIntervalMonth(String as_startDate, String as_endDate) throws AppException{
    String ls_startD="",ls_endD="";
    Date ld_start = null; Date ld_end = null;
    if (as_startDate.length()==6){//yyyymm型
      ls_startD = as_startDate.substring(0,4) + "-" + as_startDate.substring(4,as_startDate.length()) + "-01"; //把yyyymm型的年月日期转换为yyyy-mm型的日期,在后面和-01相加,组成一个合法日期
      ls_endD = as_endDate.substring(0,4) + "-" + as_endDate.substring(4,as_endDate.length()) + "-01"; //把yyyymm型的年月日期转换为yyyy-mm型的日期,在后面和-01相加,组成一个合法日期
    }else{
      ls_startD = as_startDate;
      ls_endD = as_endDate;
    }
    //System.out.println("as_startD:"+as_startD);    //System.out.println("as_endD:"+as_endD);

    try {
      ld_start = getDate(ls_startD);
      ld_end = getDate(ls_endD);
    }catch (AppException ex) {
      throw new AppException(ex,"getIntervalMonth(String as_startDate, String as_endDate) 出错！","日期处理出错！");
    }
    int interval=getIntervalMonth(ld_start,ld_end);
    return interval;
  }
//功能：根据所给的起始,终止时间来计算间隔月数
  public static int getIntervalMonth(java.util.Date startDate, java.util.Date endDate) {
    java.text.SimpleDateFormat mmformatter = new java.text.SimpleDateFormat("MM");
    int monthstart = Integer.parseInt(mmformatter.format(startDate));
    int monthend = Integer.parseInt(mmformatter.format(endDate));
    java.text.SimpleDateFormat yyyyformatter = new java.text.SimpleDateFormat("yyyy");
    int yearstart = Integer.parseInt(yyyyformatter.format(startDate));
    int yearend = Integer.parseInt(yyyyformatter.format(endDate));
    return (yearend - yearstart) * 12 + (monthend - monthstart);
  }

  public static java.util.Date getDate(String strdate) throws AppException {
    int yyyy = Integer.parseInt(strdate.substring(0, 4));
    String temp = strdate.substring(5, strdate.length());
    int MM = Integer.parseInt(temp.substring(0, temp.indexOf("-")));
    temp = temp.substring(temp.indexOf("-") + 1, temp.length());
    int dd;
    if (temp.indexOf(" ") > 0) {
      dd = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));
    } else {
      dd = Integer.parseInt(temp);
    }
    if (!verityDate(yyyy, MM, dd)) {
      throw new AppException("非法日期数据");
    }

    java.util.Date d;
    try {
      if (strdate.length() > 10) {
        d = DateFormat.getDateTimeInstance().parse(strdate);
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

      } else {
        d = DateFormat.getDateInstance().parse(strdate);
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      }
//      System.out.println(formatter.format(d));
      return d;
    } catch (ParseException e) {
   //   System.out.println("date convert error...");
      e.printStackTrace();
      java.util.Date rtdate;
      rtdate = new java.util.Date();
      return rtdate;
      //throw new AppException("日期数据转换错");
    }
  }

  public static void main(String[] args) throws AppException {
    DateUtil dateUtil=new DateUtil();
    Calendar oneCalendar = Calendar.getInstance();
    String startDate="197401";//1974-01-13
    String endDate="200608";//2006-08-12

    System.out.println( dateUtil.getIntervalMonth(startDate,endDate) );
//    System.out.println(oneCalendar.getTime());
//    System.out.println(getDateTime());
//    Date dt= getDateTime();
//    System.out.println( dateUtil.formatDate(getDate("2004-09-02 10:20:17"),"") );

//    java.util.Date i=null;
//    String start="200002",end="200003";
//    try {
//      i = getDateNMonths("198802", 18);
//    }catch (AppException ex) {
//    }
//    System.out.println(i);
//    java.util.ArrayList al=new java.util.ArrayList();
//    al.add("aa");
//    al.add("bb");
//    al.add("cc");
//    for(int i=0;i<al.size();i++){
//      System.out.println( al.get(i) );
//    }
    java.util.Date ldt_dbDate = new java.util.Date();
   // System.out.println("the dt is : " + ldt_dbDate);
    String ls_dbDate = DateUtil.formatDate(ldt_dbDate,"");//系统当前日期
   // System.out.println("the ls_dbDate is : " + ls_dbDate);
   }
  
  
  
  /*得到了系统当前日期时间*/
  public static String getSysDate()
   {
       SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
       String s = simpledateformat.format(Calendar.getInstance().getTime());
       return s;
   }
  /*得到系统当前时间*/
/* public static String getSysTime()
   {
       SimpleDateFormat simpledateformat = new SimpleDateFormat("hhmmss");
       String s = simpledateformat.format(Calendar.getInstance().getTime());
       return s;
   }*/
}
