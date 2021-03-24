//初始化echarts实例
var myChart = echarts.init(document.getElementById('myEcharts'));
// 指定图表的配置项和数据
option = {
	title: {
		text: '新冠疫情-国内累计数据',
		left: 'center'
	},
	tooltip: {
		trigger: 'item'
	},
	legend: {
		orient: 'vertical',
		left: 'left',
		data: ['新冠疫情-国内累计数据']
	},
	visualMap: {
		type: 'piecewise',
		/*pieces: [
            { min: 1000, max: 1000000, label: '大于等于1000人', color: '#372a28' },
            { min: 500, max: 999, label: '确诊500-999人', color: '#4e160f' },
            { min: 100, max: 499, label: '确诊100-499人', color: '#974236' },
            { min: 10, max: 99, label: '确诊10-99人', color: '#ee7263' },
            { min: 1, max: 9, label: '确诊1-9人', color: '#f5bba7' },
        ],*/
		pieces: [
			{gt: 2000, color: 'darkred'},                        // (1500, Infinity]
			{gt: 1300, lte: 2000, color: 'red', colorAlpha: 1},  // (1000, 1500]
			{gt: 800, lte: 1300, color: 'red', colorAlpha: 0.8},
			{gt: 500, lte: 800, color: 'red', colorAlpha: 0.6},
			{gt: 200, lte: 500, color: 'red', colorAlpha: 0.4},
			{gt: 50, lte: 200, color: 'red', colorAlpha: 0.3},
			{lt: 50, color: 'red', colorAlpha: 0.2}          // (-Infinity, 100)
		],
		color: ['#E0022B', '#E09107', '#A3E00B']
	},
	/*toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },*/
	roamController: {
		show: true,
		left: 'right',
		mapTypeControl: {
			'china': true
		}
	},
	series: [
		{
			name: '确诊数',
			type: 'map',
			mapType: 'china',
			roam: false,
			label: {
				show: true,
				color: 'rgb(249, 249, 249)'
			},
			data: []
		}
	]
};

//使用指定的配置项和数据显示图表
myChart.setOption(option);

//获取数据
function getData() {
	$.ajax({
		url: "https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5",
		dataType: "jsonp",
		success: function (data) {
			//  console.log(data.data)
			var res = data.data || "";
			res = JSON.parse(res);
			var newArr = [];
			//newArr的数据格式为：
			// [{
			//   name: '北京11',
			//   value: 212
			// }, {
			//   name: '天津',
			//   value: 60
			// }]
			if (res) {
				//获取到各个省份的数据
				var province = res.areaTree[0].children;
				for (var i = 0; i < province.length; i++) {
					var json = {
						name: province[i].name,
						value: province[i].total.confirm
					}
					newArr.push(json)
				}
				console.log(newArr)
				console.log(JSON.stringify(newArr))
				//使用指定的配置项和数据显示图表
				myChart.setOption({
					series: [
						{
							name: '确诊数',
							type: 'map',
							mapType: 'china',
							roam: false,
							label: {
								show: true,
								color: 'rgb(249, 249, 249)'
							},
							data: newArr
						}
					]
				});
			}
		}

	})
}
getData();