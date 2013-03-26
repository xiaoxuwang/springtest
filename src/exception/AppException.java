package exception;
/**
 * <p>Title: �쳣��</p>
 * <p>Messageription: </p>
 * @author yan_dk
 * @date 2004-05
 * @version 1.5
 */

import java.io.Serializable;


import util.DateUtil;
import util.StringUtil;



public class AppException extends Exception implements Serializable {
  /**
   * �쳣����.
   * ��2�֣�<p>
   * ConstantValue.getReturnTypeLogicalError() = "001" ҵ���쳣<p>
   * ConstantValue.getReturnTypeSystemError() = "999"ϵͳ�쳣<p>
   */
  /**��־����
   */
  private String logType;	/**��־��ʱ�� */
  private String logDateTime;/**�����쳣��ʱ�� */
  private String logMessage;	/**��־������Ϣ */
  private Exception exception;/**ԭʼ�쳣�� */
  private String clientMessage;/**���ظ��ͻ������� */
  private String clientType;/**���ظ��ͻ���Ϣ���� */
//  private String errorNo ;/**Ӧ�ö���Ĵ����yan_add */

  public AppException(){
    this.logDateTime = DateUtil.getDateTime() ;
    logMessage="";
    clientMessage="";
  }

  public String getLogType(){
    return logType;
  }
  public String getLogDateTime(){
    return logDateTime;
  }
  public String getLogMessage(){
    return logMessage;
  }
  public Exception getException(){
    return exception;
  }
  public String getClientMessage(){
    return clientMessage;
  }
  public String getClientType(){
    return clientType;
  }
  public void setClientMessage(String clientMessage) {
    this.clientMessage = StringUtil.nvl(clientMessage,"");//yan_modi
  }
  public void setException(Exception exception) {
    this.exception = exception;
  }
  public void setLogMessage(String logMessage) {
    this.logMessage = StringUtil.nvl(logMessage,"");//yan_modi
  }
  public void setLogType(String logType) {
    this.logType = logType;
  }
  public void setLogDateTime(String logDateTime) {
    this.logDateTime = logDateTime;
  }
  public void setClientType(String clientType) {
    this.clientType = clientType;
  }

//  public String getErrorNo() {
//    return errorNo;
//  }
//  public void setErrorNo(String errorNo) {
//    this.errorNo = errorNo;
//  }

  public String toString(){
    if(null == exception ){
      return "logType="+logType+" logDateTime="+logDateTime+" logMessage="+logMessage+" clientDest="+clientMessage+" clientType="+clientType;
    }else{
      return "logType="+logType+" logDateTime="+logDateTime+" logMessage="+logMessage+" Exception="+exception.getMessage()+" clientDest="+clientMessage+" clientType="+clientType;
    }
  }

  public AppException( String inMessage) {//yan_add //    setClientType( ConstantValue.getReturnTypeLogicalError() );
    setClientMessage( inMessage );//      setLogType(ConstantValue.getLogTypeLogicalError());
    setLogMessage(inMessage);
  }

  public AppException( String inLogMessage,String inClientMessage ) {//yan_add //    setClientType( ConstantValue.getReturnTypeLogicalError() );
    setClientMessage( inClientMessage );//    setLogType(ConstantValue.getLogTypeLogicalError());
    setLogMessage(inLogMessage);
  }

  public AppException( Exception inException,String inLogMessage,String inClientMessage ) {//yan_add
    this.logDateTime = DateUtil.getDateTime() ;// setClientType( ConstantValue.getReturnTypeSystemError() );
    setClientMessage( inClientMessage );// setLogType(ConstantValue.getLogTypeSystemError());
    setLogMessage(inLogMessage);
    setException(inException);
  }
  public AppException( Exception inException,String inMessage) {//yan_add
    this.logDateTime = DateUtil.getDateTime() ;  //setClientType( ConstantValue.getReturnTypeSystemError() );
    setClientMessage( inMessage );  //setLogType(ConstantValue.getLogTypeSystemError());
    setLogMessage(inMessage);
    setException(inException);
  }

  public static void main(String args[]){
    AppException ae=new AppException();
    System.out.println(ae.toString()  ) ;
  }

}
