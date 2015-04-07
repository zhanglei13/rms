<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
	<script type="text/javascript">
 		window.jQuery || document.write("<script src='${pageContext.request.contextPath}/res/assets/js/jquery1x.min.js'>"+"<"+"/script>");
	</script>
	<![endif]-->
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${pageContext.request.contextPath}/res/assets/js/bootstrap.min.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/res/assets/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/res/assets/js/ace.min.js"></script>