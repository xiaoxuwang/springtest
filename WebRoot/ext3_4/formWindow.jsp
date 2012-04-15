<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ext3_4table.jsp' form</title>
<script type="text/javascript">

	//创建一个Panel对象
	
	Ext.QuickTips.init();//THML 编辑器
	
	var form=new Ext.form.FormPanel({
			labelAlign:'right',
			title:'form',
			labelWidth:50,
			frame:true,
			width:600,
			items:[{
				layout:'column',
				items:[{
					columnWidth:7,
					xtype:'fieldset',
					checkboxToggle:true,
					title:'单纯输入',
					autoHeight:true,
					defaults:{width:300},
					defaultType:'textfield',
					items:[
							{fieldLabel:'文本',name:'text',},
							{xtype:'numberfield',fieldLable:'数字',name:'name'},
							{xtype:'combo',fieldLabel:'选择',name:'combo',
							 	store:new Ext.data.SimpleStore({fields:['value','text'],data:[['value1','text1'],['value2','text2']]}),
								displayField:'text',
								vlaueField:'value',
								mode:'local',
								emptyText:'请选择'},
							{xtype:'datefield',fieldLabel:'日期',name:'date'},
							{xtype:'timefield',fieldLabel:'时间',name:'time'},
							{xtype:'textarea',fieldLabel:'多行',name:'textarea'},
							{xtype:'hidden',name:'hidden'}]
					},{
						columnWidth:3,
						layout:'form',
						items:[{
							xtype:'fieldset',
							checkboxToggle:true,
							title:'多选',
							autoHeight:true,
							defaultType:'checkbox',
							hiddenLabel:true,
							style:'margin-left:10px',
							bodyStyle:'margin-left:20px',
							items:[{
									boxLabel:'首先要穿暖',
									name:'check',
									value:'1',
									checked:true,
									width:'auto'
								},{
									boxLabel:'然后吃饱',
									name:'check',
									value:'2',
									checked:true,
									width:'auto'
								},{
									boxLabel:'房子遮风避雨',
									name:'check',
									value:'3',
									checked:true,
									width:'auto'
								},{
									boxLabel:'行路方便',
									name:'check',
									value:'4',
									checked:true,
									width:'auto'
								}]
							},{
								xtype:'fieldset',
							}
						]}
							
				],
				}],
		});
	Ext.onReady(function(){
		form.render("form");
		
});
</script>
  </head>
  <body>
    <div id="form" style="margin:100px;" ></div>
  </body>
</html>
