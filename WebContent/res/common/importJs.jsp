<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${pageContext.request.contextPath}/res/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${pageContext.request.contextPath}/res/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/res/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${pageContext.request.contextPath}/res/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/res/assets/js/bootbox.min.js"></script>
<!-- jqgrid -->
<script src="${pageContext.request.contextPath}/res/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${pageContext.request.contextPath}/res/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/res/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/res/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${pageContext.request.contextPath}/res/scripts/js/yl.tools.js"></script>
<script src="${pageContext.request.contextPath}/res/scripts/js/hash.js"></script>
<script type="text/javascript">
	$.ajaxSetup ({cache:false});
</script>
