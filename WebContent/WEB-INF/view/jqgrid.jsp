<title>template</title>
<%
	request.setAttribute("ctp", request.getContextPath());
%>
<!-- ajax layout which only needs content area -->
<div class="page-header">
	<h1>
		template
	</h1>
</div><!-- /.page-header -->

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
	var scripts = [null,"${ctp}/res/assets/js/date-time/bootstrap-datepicker.min.js","${ctp}/res/assets/js/jqGrid/jquery.jqGrid.min.js","${ctp}/res/assets/js/jqGrid/i18n/grid.locale-en.js", null]
	ace.load_ajax_scripts(scripts, function() {
		jQuery(function($) {
			var grid_selector = "#grid-table";
			var pager_selector = "#grid-pager";
			
			//resize to fit page size
			$(window).on('resize.jqGrid', function () {
				$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
		    })
			//resize on sidebar collapse/expand
			var parent_column = $(grid_selector).closest('[class*="col-"]');
			$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
				if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
					//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
					setTimeout(function() {
						$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
					}, 0);
				}
		    })
		    
		    jQuery(grid_selector).jqGrid({
		    	ajaxGridOptions : {
					timeout : 500000  //设置ajax加载超时时间
				},
				url : "${ctp}/workload/list", //这是Action的请求地址
				datatype : "json", //将这里改为使用JSON数据   
				mtype : "POST", //提交类型
				height: 250,
				caption:"Workload",//表格标题
				rowNum: 20,
				rowList: [10,20,30],
				colNames:['Date','Project','Phase','Mon.', 'Tue.','Wed ','Thu','Fri','Sat','Sun','Status','Operation'],
				colModel:[
					{name:'dateRange',index:'dateRange', width:60, sorttype:"int", editable: true},
					{name:'projectName',index:'projectName',width:90, editable:true, sorttype:"date",unformat: pickDate},
					{name:'phaseCode',index:'phaseCode', width:150,editable: true,editoptions:{size:"20",maxlength:"30"}},
					{name:'stock',index:'stock', width:70, editable: true,edittype:"checkbox",editoptions: {value:"Yes:No"},unformat: aceSwitch},
					{name:'ship',index:'ship', width:90, editable: true,edittype:"select",editoptions:{value:"FE:FedEx;IN:InTime;TN:TNT;AR:ARAMEX"}},
					{name:'note',index:'note', width:150, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"10"}},
					{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
						formatter:'actions', 
						formatoptions:{ 
							keys:true,
							//delbutton: false,//disable delete button
							
							delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
							//editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
						}
					},
				],
				
		    });
			$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
			
		});
	});
</script>
