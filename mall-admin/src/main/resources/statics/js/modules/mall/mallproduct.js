$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'mall/mallproduct/list',
        datatype: "json",
        colModel: [			
			{ label: 'productId', name: 'productId', index: 'product_id', width: 50, key: true },
			{ label: '商品名称', name: 'productName', index: 'product_name', width: 80 }, 			
			{ label: '商品类型', name: 'productType', index: 'product_type', width: 80 }, 			
			{ label: '摘要', name: 'productTitle', index: 'product_title', width: 80 }, 			
			{ label: '图片', name: 'image', index: 'image', width: 80 }, 			
			{ label: '详细描述', name: 'productDesc', index: 'product_desc', width: 80 }, 			
			{ label: '价格', name: 'price', index: 'price', width: 80 }, 			
			{ label: '状态，0-无效,1-有效', name: 'status', index: 'status', width: 80 }, 			
			{ label: '起始日期，yyyyMMdd', name: 'startDate', index: 'start_date', width: 80 }, 			
			{ label: '结束日期，yyyyMMdd', name: 'endDate', index: 'end_date', width: 80 }, 			
			{ label: '排序,默认9999', name: 'orderNum', index: 'order_num', width: 80 }, 			
			{ label: '创建人', name: 'createUser', index: 'create_user', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新人', name: 'updateUser', index: 'update_user', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		mallProduct: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.mallProduct = {};
		},
		update: function (event) {
			var productId = getSelectedRow();
			if(productId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(productId)
		},
		saveOrUpdate: function (event) {
			var url = vm.mallProduct.productId == null ? "mall/mallproduct/save" : "mall/mallproduct/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.mallProduct),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var productIds = getSelectedRows();
			if(productIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "mall/mallproduct/delete",
                    contentType: "application/json",
				    data: JSON.stringify(productIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(productId){
			$.get(baseURL + "mall/mallproduct/info/"+productId, function(r){
                vm.mallProduct = r.mallProduct;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});