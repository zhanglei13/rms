<%request.setAttribute("ctp", request.getContextPath());%>
<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctp}/res/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctp}/res/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${ctp}/res/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${ctp}/res/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="${ctp}/res/assets/js/bootbox.min.js"></script>
<!-- jqgrid -->
<script src="${ctp}/res/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${ctp}/res/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
<!-- ace scripts -->
<script src="${ctp}/res/assets/js/ace-elements.min.js"></script>
<script src="${ctp}/res/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctp}/res/scripts/js/yl.tools.js"></script>
<script src="${ctp}/res/scripts/js/hash.js"></script>
<script type="text/javascript">
	$.ajaxSetup ({cache:false});
</script>
