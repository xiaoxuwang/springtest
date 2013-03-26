//动态表格
var win;
var addform;
Ext.onReady(function(){
	//stemp1
	//create data Store	
	var store=new Ext.data.JsonStore({
		
		url:'../login.do?method=getEmp',
		root:'data',
		fields:['id','ename','dname','sex','phone','createDate']
		//autoLoad:true //自动加载数据
		
	});
	   store.load({
    // 从第0个数据开始.每页10条数据.
  	params : {
  	start : 0,
   	limit : 10
  }
 });
	var store3=new Ext.data.JsonStore({
		url:'../login.do?method=getCombo&outkeyname=dept',
		fields:['key','value'],
		autoLoad:false //自动加载数据默认为false可以省略不写
	});
	store3.load();
		 //读入数据信息,放置位置决定其何时加载.如最后:表格显示之后,在加载(必选项).

	var cm=new Ext.grid.ColumnModel([
		{header:'编号',dataIndex:'id',renderer:function(value){var str="";str='<font color="green">'+value+'</font>';return str;}},
		{header:'姓名',dataIndex:'ename'},
		{header:'性别',dataIndex:'sex',renderer:function(value){value=value=="F"?"男":"女";return value;}},
		{header:'部门',dataIndex:'dname',renderer:function(value){var index=store3.find('value',value);value=store3.getAt(index).get('key');return value;}},
		{header:'联系电话',dataIndex:'phone'},
		{header:'创建日期',dataIndex:'createDate'}
	]);
	
	//stemp2
	//create the Grid
	var grid=new Ext.grid.GridPanel({
		autoHeight:true,
		autoWidth:true,
		//width:800,
		renderTo:'grid',
		store:store,
		cm:cm,
		viewConfig:{forceFit:true},
		loadMask : {
   			msg : '正在加载数据，请稍侯……'
  		}, // 数据读入等待显示界面.
		bbar: new Ext.PagingToolbar({
            store: store,
            pageSize: 10,
            displayInfo : true,
   			displayMsg : '当前显示 {0} - {1}条记录 /共 {2}条记录',
   			emptyMsg : " "
            //plugins: [filters]
        })

	});

/*
var data=[['F','男'],['M','女']];
var store2=new Ext.data.SimpleStore({
	fields:['value','text'],
	data:data
});

var store4=new Ext.data.Store({
    proxy:new Ext.data.HttpProxy({url:'../login.do?method=getCombo&tablename=dept&key=deptid&value=dname'}),
    reader:new Ext.data.ArrayReader([{name:'key'},{name:'value'}]),
    autoLoad:true
});
*/


//create FormPanel
addform=new Ext.form.FormPanel({
	border:false,
	labelAlign:'right',
	LabelWidth:60,
	LabelHeight:20,
	frame:true,
	//defaultType:'textfield',
	buttonAlign:'center',
	url:'../login.do?method=addEmp',
	/*
	onSubmit:Ext.emptyFn(),
	submit:function(){
		this.getEl().dom.action='login.do?method=addemp';
		this.getEl().dom.submit();
	},*/
	items:[
		new Ext.form.TextField({
			fieldLabel:'姓名',
			name:'ename'
		}),new Ext.form.RadioGroup({
			id:'inputRadio',
			fieldLabel:'性别',
			column:1,
			vertical:true,
			items:[{
				boxLabel:'男',name:'sex',inputValue:'F'
			},{
				boxLabel:'女',name:'sex',inputValue:'M'
			}]
		}),
		new Ext.form.TextField({
			fieldLabel:'联系电话',
			name:'phone'
		}),
		new Ext.form.ComboBox({
			
			fieldLabel:'部门',
			width:140,
			hiddenName:'deptid',
			triggerAction:'all',//显示所有下拉列数，必须指定为'all'
			emptyText:'请选择',
			valueField:'value',	//值 
			displayField:'key',//显示内容
			mode:'remote',	//指定数据是远程的
			store:store3	//填充的数据
	})
	],
	buttons:[{
		text:'保存',
		handler:function(){
			addform.getForm().submit({
				  	success:function(form, action){
				  		win.hide();//隐藏添加窗体
                        Ext.Msg.alert('信息', action.result.msg);
                    },
                    failure:function(){
                        Ext.Msg.alert('错误', '操作失败！');
                    }
			});
		}
	},{
		text:'取消',
		handler:function(){addform.form.reset();}
	}]
	
});

//create window for addinfo
win=new Ext.Window({
	title:'添加数据',
	x:100,
	y:20,
	layout:'fit',
	height:200,
	width:400,
	closeAction:'hide',
	closeable:false,
	items:[addform]
	
});

});

function addinfo(){
	addform.form.reset();
	win.show();
}


