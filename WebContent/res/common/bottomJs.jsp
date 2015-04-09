<%request.setAttribute("ctp", request.getContextPath());%>
<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctp}/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
	<script type="text/javascript">
 		window.jQuery || document.write("<script src='${ctp}/res/assets/js/jquery1x.min.js'>"+"<"+"/script>");
	</script>
	<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${ctp}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${ctp}/res/assets/js/bootstrap.min.js"></script>

<!-- ace scripts -->
<script src="${ctp}/res/assets/js/ace-elements.min.js"></script>
<script src="${ctp}/res/assets/js/ace.min.js"></script>