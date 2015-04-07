<title>workload</title>
<%
	request.setAttribute("ctp", request.getContextPath());
%>
<link rel="stylesheet" href="${ctp}/res/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctp}/res/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctp}/res/assets/css/ui.jqgrid.css" />
<%@include file="../../res/common/importAll.jsp"%>

<!-- ajax layout which only needs content area -->
<div class="page-header"></div>
<!-- /.page-header -->

<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<table id="grid-table"></table>

		<div id="grid-pager"></div>

		<script type="text/javascript">
			var $path_base = "../..";//in Ace demo this will be used for editurl parameter
		</script>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
	var scripts = [ null, null ]
	ace.load_ajax_scripts(scripts, function() {
		//inline scripts related to this page
	});
</script>