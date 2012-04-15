Ext.chart.Chart.CHART_URL = '../resources/charts.swf';//是为了使用当前服务器的文件，如果没有这句话，默认会去adobe的站点取。

Ext.onReady(function(){
//饼状统计图
	var store = new Ext.data.JsonStore({
        fields: ['season', 'total'],
        data: [{
            season: 'Summer',
            total: 150
        },{
            season: 'Fall',
            total: 245
        },{
            season: 'Winter',
            total: 117
        },{
            season: 'Spring',
            total: 184
        }]
    });
    
    new Ext.Panel({
    	iconCls:'chart',
        width: 400,
        height: 400,
        title: '饼状统计图',
        renderTo: 'pieChart',
        items: {
            store: store,
            xtype: 'piechart',
            dataField: 'total',
            categoryField: 'season',
            //extra styles get applied to the chart defaults
            extraStyle:
            {
                legend:
                {
                    display: 'bottom',
                    padding: 5,
                    font:
                    {
                        family: 'Tahoma',
                        size: 13
                    }
                }
            }
        }
    });

});