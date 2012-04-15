<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>checkBox</title>
<script type="text/javascript">

	//创建一个Panel对象
	var fieldset1=new Ext.form.FieldSet({
		xtype:'fieldset',
		title:'多选',
		//layout:'form',//默认form布局
		width:250,
		autoHeight:true,
		defaultType:'checkbox',
		hideLabels:true,
		items:[{boxLabel:'上网',inputValue:'1',checked:true},
			{boxLabel:'打牌',inputValue:'2'},
			{boxLabel:'睡觉',inputValue:'3'},
			{boxLabel:'做梦',inputValue:'4'}
			]
		});
	var fieldset2=new Ext.form.FieldSet({
		xtype:'fieldset',
		title:'单选',
		//layout:'form',//默认form布局
		width:250,
		autoHeight:true,
		defaultType:'radio',
		hideLabels:true,
		items:[{boxLabel:'上网',name:'radio',inputValue:'1',checked:true},
			{boxLabel:'打牌',name:'radio',inputValue:'2'},
			{boxLabel:'睡觉',name:'radio',inputValue:'3'},
			{boxLabel:'做梦',name:'radio',inputValue:'4'}
			]
		});
	var form=new Ext.form.FormPanel({
			title:'form',
			labelWidth:50,
			//layout:'fit',
			buttonAlign:'center',
			frame:true,
			width:600,
			items:[fieldset1,fieldset2],
			buttons:[{
					text:'按钮'
					}]
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
