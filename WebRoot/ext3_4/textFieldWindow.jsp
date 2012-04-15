<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/ext3_2.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>textFieldWindow</title>
<script type="text/javascript">

	//创建一个Panel对象
	var win;

	
	var set=new Ext.form.FieldSet({
			title:'Query1',
			height:60,
			columnWidth:1,
			layout:'column',
			border:true,
			anchor:'100%',
			labelWidth:40,
			buttonAlign:'center',
			items:[{
				columnWidth:.4,
				layout:'form',
				border:false,
				items: [{
	                xtype:'textfield',
	                fieldLabel: 'id',
	                name: 'aaa',
	                anchor: '95%'
	            }]
			},{
				columnWidth:.4,
	            layout: 'form',
	            border:false,
	            items: [{
	                xtype:'textfield',
	                fieldLabel: 'name',
	                name: 'bbb',
	                anchor:'95%'
	         	}]
			},{
				columnWidth:.2,
				layout:'form',
				border:false,
				items:[{
					xtype:'button',
					text:'query',
					handler:function(){
						Ext.Msg.alert('Message','aaaa');
					},
					scope:this
					}]
		       }]
		});
	var set1= new Ext.form.FieldSet({
			title:'Query2',
			height:60,
			columnWidth:1,
			layout:'column',
			border:true,
			anchor:'100%',
			labelWidth:40,
			buttonAlign:'center',
			items:[{
				columnWidth:.4,
				layout:'form',
				border:false,
				items: [{
	                xtype:'textfield',
	                fieldLabel: 'id',
	                name: 'aaa',
	                anchor: '95%'
	            }]
			},{
				columnWidth:.4,
	            layout: 'form',
	            border:false,
	            items: [{
	                xtype:'textfield',
	                fieldLabel: 'name',
	                name: 'bbb',
	                anchor:'95%'
	         	}]
			},{
				columnWidth:.2,
				layout:'form',
				border:false,
				items:[{
					xtype:'button',
					text:'query',
					handler:function(){
						Ext.Msg.alert('Message','aaaa');
					},
					scope:this
					}]
		       }]
		});

	
	var form=new Ext.form.FormPanel({
			height:80,
			border:false,
			labelAlign:'right',
			labelWidth:80,
			frame:true,
			items:[{
				layout:'form',
				items:[set,set1]
				}]
			
		});

	win=new Ext.Window({
			title:'fieldSet',
			height:300,
			width:500,
			layout:'fit',
			closeAction:'hide',
			items:[form]
		});
	Ext.onReady(function(){
		var data=[['0','text1'],['1','text2'],['2','text3'],['3','text4'],['4','text5']];
      	
	    var store = new Ext.data.SimpleStore({
	        fields: ['value', 'text'],
	        data: data
	    });
		//用于自动提示输入 类似百度google上输入的提示效果
	    var combo = new Ext.form.ComboBox({
	        store: store,
	        emptyText: '请选择',
	        mode: 'local',
	        triggerAction: 'query',
	        valueField: 'value',
	        displayField: 'text',
	        applyTo: 'combo' 
	    });
		
});
function show(){

	win.show();
}
</script>
  </head>
  <body>
    <input type="button" value="shwoWindow" onclick="show();"/>
	<br /><br />
    <input id="combo" type="text"/>
  </body>
</html>
