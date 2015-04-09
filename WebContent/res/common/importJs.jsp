<%request.setAttribute("ctp", request.getContextPath());%>

<!-- ace settings handler -->
<script src="${ctp}/res/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
<script src="${ctp}/res/assets/js/html5shiv.js"></script>
<script src="${ctp}/res/assets/js/respond.min.js"></script>
<![endif]-->

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

<!-- ace scripts -->
<script src="${ctp}/res/assets/js/ace-elements.min.js"></script>
<script src="${ctp}/res/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script src="${ctp}/res/scripts/js/yl.tools.js"></script>
<script src="${ctp}/res/scripts/js/hash.js"></script>
<script type="text/javascript">
	$.ajaxSetup ({cache:false});
</script>
