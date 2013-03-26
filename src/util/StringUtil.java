package util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
//@������
import java.util.*;

import exception.AppException;
//@ҵ�������


public class StringUtil {
  public StringUtil() {
  }
//����:�ָ�����ַ���ת��Ϊ����
  public static String[] split(String as_str, String as_separator) {//input example as_str="a,,b,c,,d", ",",it can return string array splited by as_separator ','
    if(as_str == null)
            throw new NullPointerException("�ַ�������Ϊ��!");
    if(as_separator == null)
            throw new NullPointerException("�ָ�������Ϊ��!");
    if(as_separator.length() == 0)
            throw new IllegalArgumentException("�ָ�������Ϊ��!");

    ArrayList lal_tmp = new ArrayList();
    int li_spos = 0;
    int li_separatorLen = as_separator.length();
    int li_epos = as_str.indexOf(as_separator);
    while(li_epos != -1) {
      lal_tmp.add(as_str.substring(li_spos, li_epos));
      li_spos = li_epos + li_separatorLen;
      li_epos = as_str.indexOf(as_separator, li_spos);
    }
    lal_tmp.add(as_str.substring(li_spos,as_str.length()));
    String[] ls_result = new String[lal_tmp.size()];
    lal_tmp.toArray(ls_result);
    return ls_result;
}
//about call:
//	String[] result = split("a,,b,c,,d", ",");
//	for(int i=0; i<result.length; i++)	System.out.println(result[i]);

  public static String pad(String as_pad,int ai_num){//repeat counted ai_num the string named as_pad,and return summar the value;
    String ls_result= new String();

    for(int i=1;i<=ai_num;i++){
      ls_result += as_pad;
    }
    return ls_result;
  }
//����:����,������sql��׼����in���� ���as_InStrsΪ�ַ���,��ʽΪ����"aa|bb"�а���"aa"������,yan_add_20060711
  public static boolean in(String as_SrcStr,String as_InStrs){
    boolean lb=false;
    String[] ls_InStrs=null;
    //��Ϊ�մ�,����false
    if( as_SrcStr.equals("")|| as_InStrs.equals("") )  return false;

    ls_InStrs = split( as_InStrs,"|");

    for (int i=0;i<ls_InStrs.length;i++){
      if( as_SrcStr.indexOf(ls_InStrs[i]) >=0 ){
        lb=true;
        break;
      }
    }

    return lb;
  }
//����:��ֵ�滻,������oracle��nvl����
  public static String nvl(String as_str,String as_val){//equal nvl function in oracle
    if(as_str==null) return as_val;
    if ( (as_str.trim()).length()==0 || as_str.equals("") ){

    }else{
      as_val = as_str;
    }
    return as_val;
  }//nvl(String as_str,String as_val)
//��������ĸ��д���ִ�
 public static String FirstCharToUpper(String as_str){
   String ls_rtnstr="";
   int li_len=0;
   li_len=as_str.length();
   ls_rtnstr = as_str.substring(0,1).toUpperCase()+as_str.substring(1,li_len);

   return ls_rtnstr;
 }

  public static String nvl(Object ao_obj1,String as_val1){//equal nvl function in oracle
    if(ao_obj1==null) {
      return as_val1;
    }else{
      return ao_obj1.toString().trim();
    }
  }//nvl(String as_str,String as_val)
//���ܣ��ж��ַ����Ƿ�Ϊ��
  public static boolean isnull(String as_str){
    if ( as_str==null){//yan_modi_20061229 as_str==null
      return true;
    }
    if ( (as_str.trim()).length()==0 || as_str.equals("") ){
      return true;
    }
    return false;
  }

//@���ܣ��ַ����滻��
  public static String replace(String oldStr, String searchStr, String replaceStr){
    String outStr = "";
    oldStr = nvl(oldStr, "");
    searchStr = nvl(searchStr, "");

    int iPos = 0;
    int iLen = searchStr.length();

    if (oldStr.equals("") || searchStr.equals("") || replaceStr == null)
      return oldStr;

    iPos = oldStr.indexOf(searchStr);

    while (iPos != -1) {
      outStr += oldStr.substring(0, iPos) + replaceStr;
      iPos += iLen;
      if (oldStr.length() > iPos) {
        oldStr = oldStr.substring(iPos);
        iPos = oldStr.indexOf(searchStr);
      } else {
        oldStr = "";
        iPos = -1;
      }
    }

    outStr += oldStr;
    return outStr;
  }
//@���ܣ��ж��ַ����Ƿ����ַ�������
  public static boolean isStrInArray(String as_Str,String[] strArr){
    boolean bl=false;
    for(int i=0;i<strArr.length;i++){
      if(as_Str.equals( strArr[i])) {
        bl = true;   return bl;
      }
    }
    return bl;
  }
//@���ܣ���sql�ַ������룬���а��������ŵĽ���ת��
  public static String sqlEncode(String str){
    if (StringUtil.nvl(str,"").equals("")){
       return "";
    }else{
      str = StringUtil.replace(str, "'", "''");
    }
    return str;
  }
//xml�ַ������룬�����а���˫���š�<��>...�Ľ���ת��
  public static String xmlEncode(String input) {
    StringBuffer filtered = null;
    input = nvl(input,"");
    filtered = new StringBuffer(input.length());

    char c;
    for(int i=0; i<input.length(); i++) {
      c = input.charAt(i);
      if (c == '<') {
        filtered.append("&lt;");
      } else if (c == '>') {
        filtered.append("&gt;");
      } else if (c == '"') {
        filtered.append("&quot;");
      } else if (c == '&') {
        filtered.append("&amp;");
      } else {
        filtered.append(c);
      }
    }
    return(filtered.toString());
  }
//�ַ������룬���ݿ��ѯȡ���ַ�������Ϊ����GB2312�ַ���
  public static String dbstringEncode(String as_srcStr,String as_dbtype,int flag)  throws AppException {
//          if (uiEngine.Const.SYSTEM_DATABASE_TYPE == uiEngine.Const.SECTION_DATABASE_ORACLE) {
//                  return sourceString;
//          } else if (uiEngine.Const.SYSTEM_DATABASE_TYPE == uiEngine.Const.SECTION_DATABASE_INFORMIX) {
   try{
     if(flag==1){//0�������ݿ���ַ�����1�ύ�����ݿ���ַ���
       if(as_dbtype.equals("Informix")){//Informix���ݿ���Ҫ�ַ���ת��
         return new String(as_srcStr.getBytes("GB2312"), "ISO8859-1");
       }
     }
     if(as_dbtype.equals("Oracle")){
       return as_srcStr;
     }else if(as_dbtype.equals("Informix")){
       return new String(as_srcStr.getBytes("ISO8859-1"), "GB2312");
     }else{
       return new String(as_srcStr.getBytes("ISO8859-1"), "GB2312");
     }

   } catch (Exception ex) {
     throw new AppException("[����ת������]:"+ex.getMessage(),"");
   }
//          } else
//                  return new String(sourceString.getBytes("ISO8859-1"), "GB2312");
  }//end


  public static String RightTag(String as_str,String as_tag){
    String ls_rightTag="";
    int li_pos= as_str.indexOf(as_tag);
    int li_taglen= as_tag.length();
    ls_rightTag= as_str.substring(li_pos+li_taglen,as_str.length());
    return ls_rightTag;
  }
  public static String LeftTag(String as_str,String as_tag){
    String ls_leftTag="";
    int li_pos= as_str.indexOf(as_tag);
    ls_leftTag= as_str.substring(0,li_pos);
    return ls_leftTag;
  }

  public static void main(String[] args)  {
    StringUtil sStringUtil=new StringUtil();
//    System.out.println( sStringUtil.xmlEncode("sdfsdfsd\"") );
//   System.out.println( sStringUtil.FirstCharToUpper("asdfFds") );
    String ls_tmp="na_stud_depcode";
    String ls_resultName_pre = ls_tmp.substring(0,ls_tmp.lastIndexOf("_"));
    String ls_colName = ls_tmp.substring( ls_tmp.lastIndexOf("_")+1,ls_tmp.length());
    System.out.println( ls_resultName_pre );
    System.out.println( ls_colName );
  }

}
