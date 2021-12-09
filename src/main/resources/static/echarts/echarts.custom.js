
var Custom = {    
		divId:'',
		title:'',
		bindData:'',
		generateCommonPieChart:function (divId,title,bindData){
	   // 使用刚指定的配置项和数据显示图表。
       var zdyDiv = echarts.init(document.getElementById(divId));      
 	    // 指定图表的配置项和数据
 	   var  zdy_option = {
 	    title : {
 	        text: title,
 	        /*subtext: '纯属虚构',*/
 	        x:'center'
 	    },
 	    tooltip : {
 	        trigger: 'item',
 	        formatter: "{a} <br/>{b} : {c} ({d}%)"
 	    },
 	    /*legend: {
 	        orient: 'vertical',
 	        left: 'left',
 	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
 	    },*/
 	    series : [
 	        {
 	            name: '访问来源',
 	            type: 'pie',
 	            radius : '55%',
 	            center: ['50%', '60%'],
 	            data:bindData,
 	            itemStyle: {
 	                emphasis: {
 	                    shadowBlur: 10,
 	                    shadowOffsetX: 0,
 	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
 	                }
 	            }
 	        }
 	    ]
 	};
 	// 使用刚指定的配置项和数据显示图表。
 	zdyDiv.setOption(zdy_option); 
   },
   _generateCommonPieChart_NP:function (){
	  this.generateCommonPieChart(this.divId,this.title,this.bindData);
 	//};
 	// 使用刚指定的配置项和数据显示图表。
 	//zdyDiv.setOption(zdy_option); 
   }
}




//定义一个函数，同时也定义了一个Person类
function Drawing(divId,title,bindData){
	this.divId = divId;
	this.title = title;
	this.bindData = bindData;

	this._generateCommonPieChart_NP = function (){
		   // 使用刚指定的配置项和数据显示图表。
	       var zdyDiv = echarts.init(document.getElementById(this.divId));      
	 	    // 指定图表的配置项和数据
	 	   var  zdy_option = {
	 	    title : {
	 	        text: this.title,
	 	        /*subtext: '纯属虚构',*/
	 	        x:'center'
	 	    },
	 	    tooltip : {
	 	        trigger: 'item',
	 	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	 	    },
	 	    /*legend: {
	 	        orient: 'vertical',
	 	        left: 'left',
	 	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	 	    },*/
	 	    series : [
	 	        {
	 	            name: '访问来源',
	 	            type: 'pie',
	 	            radius : '55%',
	 	            center: ['50%', '60%'],
	 	            data:this.bindData,
	 	            itemStyle: {
	 	                emphasis: {
	 	                    shadowBlur: 10,
	 	                    shadowOffsetX: 0,
	 	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	 	                }
	 	            }
	 	        }
	 	    ]
	 	};
	 	// 使用刚指定的配置项和数据显示图表。
	 	zdyDiv.setOption(zdy_option); 
	   }
	
	this._generateCommonSingleHistogramChart_NP = function (){
		 var zdyDiv = echarts.init(document.getElementById(this.divId));      
         // app.title = '坐标轴刻度与标签对齐';
		 var zdy_option = {
              color: ['#3398DB'],
              tooltip : {
                  trigger: 'axis',
                  axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                      type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                  }
              },
              grid: {
                  left: '3%',
                  right: '4%',
                  bottom: '3%',
                  containLabel: true
              },
              xAxis : [
                  {
                      type : 'category',
                      data : this.title,
                      axisTick: {
                          alignWithLabel: true
                      }
                  }
              ],
              yAxis : [
                  {
                      type : 'value'
                  }
              ],
              series : [
                  {
                      name:'直接访问',
                      type:'bar',
                      barWidth: '60%',
                      data:bindData
                  }
              ]
          };
  	 	// 使用刚指定的配置项和数据显示图表。
  	 	zdyDiv.setOption(zdy_option); 
	}
	
	
	//return this;
	
}




