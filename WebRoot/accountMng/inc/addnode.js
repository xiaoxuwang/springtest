Ext.onReady(function(){

	
	var data=[['0', '目录'],['1', '叶子节点']];
	
	var store1=new Ext.data.SimpleStore({
			fields: ['value', 'key'],
            data: data
		});
	var store2=new Ext.data.JsonStore({
		url:'../login.do?method=getCombo&outkeyname=parentnode',
		fields:['key','value']
	});	
	// comosql
	var form=new Ext.form.FormPanel({
	        width:'300',	
	        title: '添加菜单节点',
	        labelWidth: 100,
	        labelAlign: 'right',
	        buttonAlign: 'center',
	        frame:true,
	        url: '../login.do?method=addNode',
	        //autoWidth: true,
	        renderTo:'form1',
			items:[{
					xtype:'textfield',
					fieldLabel:'节点名称',
					name:'text'
				},{
		            xtype: 'combo',
		            emptyText:'请选择',
		            width:120,
		            fieldLabel: '节点类型',
		            hiddenName:'leaf',
		            mode:'local',//store数据是本地的，mode:'remote'远程服务器数据
					store:store1,
					triggerAction: 'all',
		            valueField: 'value',
		            displayField: 'key'
				},{
		            xtype: 'textfield',
		            fieldLabel: '节点路径',
		            name:'href'
				},{
		            xtype: 'combo',
		            emptyText:'请选择',//无数据时显示的文本
		            width:120,
		            fieldLabel: '父节点',//节点名称
		            hiddenName:'parentid',
					store:store2,
					triggerAction: 'all',
		            valueField: 'value',
		            displayField: 'key'
			}],
			buttons:[{	
					text:'添加',
					handler:function(){
						form.getForm().submit({
							success:function(form,action){
								Ext.Msg.alert('提示信息', action.result.msg);
							},
		                    failure:function(){
		                        Ext.Msg.alert('提示信息', '操作失败！');
		                    }
						});
					}
					}]
		});
	
		//form.render("form1");
		//
});