<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_4table.jsp' form</title>
<script type="text/javascript">
	Ext.QuickTips.init();
	//创建一个Panel对象
	var simple=new Ext.form.FormPanel({
			title:'form',
			labelWidth:60,
			labelAlign:'right',
			buttonAlign:'center',
			frame:true,
			width:500,
			items:[{
				layout:'column',
				items:[{
					columnWidth:.5,
					layout:'form',
					xtype:'fieldset',
					title:'fieldset1',
					autoHeight:true,
					defaultType:'textfield',
					items:[
		                    {fieldLabel: '数字',xtype: 'numberfield',allowNegative: false,allowDecimals: false,maskRe: /\d/},
		                    {fieldLabel: '文本',regex: /^[\u4E00-\u9FA5]+$/,regexText: '只能输入汉字'},
		                    {fieldLabel: 'Email'}
		                ]
					},{
						columnWidth:.5,
		                layout: 'form',
		                xtype: 'fieldset',
		                title: 'fieldset2',
		                autoHeight: true,
		                style: 'margin-left: 20px;',
		                defaultType: 'textfield',
		                items:[
		                    {fieldLabel: '电话'},
		                    {fieldLabel: '手机'},
		                    {fieldLabel: '身份证'}
		                ]
					}]
				
				},{
					xtype: 'fieldset',
		            title: 'fieldset3',
		            autoHeight: true,
		            items: [{
		                width: 345,
		                height: 100,
		                xtype: 'textarea',
		                fieldLabel: '四个汉字'
		            }]
				}],
			buttons:[{text:'确定'},
					{text:'取消'}]
		});
	Ext.onReady(function(){
		simple.render("form");
		
});
</script>
  </head>
  <body>
    <div id="form" style="margin:100px;" ></div>
  </body>
</html>
