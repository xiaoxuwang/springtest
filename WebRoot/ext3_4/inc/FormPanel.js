Ext.onReady(function(){
	
	var data=[['1', 'text1'],['2', 'text2'],['3', 'text3'],['4', 'text4'],['5', 'text5']];
	
	var store1=new Ext.data.SimpleStore({
			fields: ['value', 'text'],
            data: data
		});
	
	var form=new Ext.form.FormPanel({
	        labelAlign: 'right',
	        title: 'form',
	        labelWidth: 50,
	        buttonAlign: 'center',
	        frame:true,
	        //url: 'form2.jsp',
	        width: 280,
			items:[{
					xtype:'textfield',
					fieldLabel:'文本',
					name:'text'
				},{
		            xtype: 'numberfield',
		            fieldLabel: '数字',
		            name: 'number'
				},{
		            xtype: 'datefield',
		            fieldLabel: '日期',
		            format:'Ymd',
		            name: 'date'
				},{
		            xtype: 'combo',
		            fieldLabel: '下拉',
		            name: 'combo',
					store:store1,
					triggerAction: 'all',
		            valueField: 'value',
		            displayField: 'text'
			}],
			buttons:[{	
					text:'按钮'
					}]
		});
	
		form.render("form");
		//
});