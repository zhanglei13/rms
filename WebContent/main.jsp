<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>RMS-WorkLoad</title>
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="res/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="res/jquery-easyui-1.2.6/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="res/jquery-easyui-1.2.6/themes/icon.css" />
<script type="text/javascript"
	src="res/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="res/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {

		$('a[title]')
				.click(
						function() {
							var src = $(this).attr('title');
							var title = $(this).html();
							if ($('#tt').tabs('exists', title)) {
								$('#tt').tabs('select', title);
							} else {
								$('#tt')
										.tabs(
												'add',
												{
													title : title,
													content : '<iframe frameborder=0 style=width:100%;height:100% src='
															+ src
															+ ' ></iframe>',
													closable : true
												});
							}
						});

	});
</script>

</head>
<body>
	<div id="cc" class="easyui-layout" fit=true
		style="width: 100%; height: 100%;">
		<div region="north" title="RMS-Lenovo" split="false"
			style="height: 100px;"></div>
		<!-- 
		    <div region="south" title="South Title" split="true" style="height:100px;"></div>  
		    <div region="east" collapsed=true iconCls="icon-reload" title="East" split="true" style="width:100px;"></div>  
		     -->
		<div region="west" iconCls="icon-add" split="true" title="Menu"
			style="width: 200px;">
			<div id="aa" class="easyui-accordion" fit=true>
				<div title="Profile" style="overflow: auto; padding: 10px;">
					<a title="profile.jsp">Profile</a> <br />
				</div>
				<div title="Personal Workload" selected="true"
					style="padding: 10px;">
					<a title="workload.jsp">Workload</a> <br />
				</div>
				<div id="" approval" title="Workload Approval" selected="fasle"
					style="padding: 10px;">
					<a id="itleader" title="itleader_approval.jsp">IT Leader</a> <br />
					<a id="linmanager" title="linemanager_approval.jsp">Line
						Manager</a> <br />
				</div>
				<div id="project" title="Project Admin" selected="fasle"
					style="padding: 10px;">
					<a title="jsp/001_message.jsp">Add Project</a><br /> <a
						title="jsp/001_message.jsp">Check</a>
				</div>
				<div id="report" title="Reporter" selected="fasle"
					style="padding: 10px;">
					<a title="jsp/001_message.jsp">Project</a> <br /> <a
						title="jsp/001_message.jsp">Man Day</a> <br />
				</div>

				<div title="Information" selected="fasle" style="padding: 10px;">
					<a title="jsp/001_message.jsp">Notification</a> <br />
				</div>


			</div>
		</div>

		<div region="center" title="Main" style="padding: 5px;">
			<div id="tt" class="easyui-tabs" fit=true
				style="width: 500px; height: 250px;"></div>
		</div>
	</div>



	<BR>

</body>
</html>
