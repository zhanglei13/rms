<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lenovo.rms.workload.model.WorkloadRow"%>
<%@ page import="com.lenovo.rms.common.util.JsonUtils"%>
<%
	request.setAttribute("ctp", request.getContextPath());
   /*  List<WorkloadRow> workload = (List)request.getAttribute("workload");
    String[]  dates=new String[7];
    if(workload!=null){
    	 dates = workload.get(0).getDatePerWeek();
    }else{
    	 dates[0]="0";
    	 dates[1]="1";
    	 dates[2]="2";
    	 dates[3]="3";
    	 dates[4]="4";
    	 dates[5]="5";
    	 dates[6]="6";
    } */
    String workloadStr= (String)request.getParameter("workload");
   // List<WorkloadRow> workload = JsonUtils.jsonList2JavaList(workloadStr, WorkloadRow.class);
    
   
%>
<!DOCTYPE html>

<html lang="en">
<head>
<%@ include file="../../res/common/importAll.jsp"%>
<script src="${ctp}/res/scripts/js/map.js"></script>
<script src="${ctp}/res/scripts/js/add_workload.js"></script>
<script>
var projects;
$(function(){
	   if (projects == null) {
			$.ajax({
				type : 'GET',
				url : "../project/list",
				dataType : "json",
				async: false,
				success : function(data) {
					if(projects==null){
						projects=new Map();
						for(var i in data){
							if(projects.containsKey(data[i].projectType)){ //目前没有release信息
								projects.get(data[i].projectType).push(data[i]);
							}else{
								var value = new Array();
								value.push(data[i]);
								projects.put(data[i].projectType,value);
							}
						}
					}
					
				},
				error : function() {
					alert("异常！");
				}
			});
		}
	 var preWorkload = eval(<%=workloadStr%>);//preWorkloadString.parseJSON();
	 for(var i in preWorkload){
		 console.log(preWorkload);
		 addRow(preWorkload[i]);
	 }
	 
 });
</script>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
		<div class="main-content">

			<!-- /section:basics/content.breadcrumbs -->
			<div class="page-content">
				<div class="page-content-area">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<h3 class="header smaller lighter blue">Add you MD</h3>
									<div class="table-header">Name:Member1 &nbsp;&nbsp;&nbsp;
										Date:2015.04</div>

									<div>
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead class="center">
												<tr>
													<th class="hidden-480">Release</th>
													<th class="hidden-480">Project</th>
													<th class="hidden-480">Phase</th>
													<td class="hidden-480">1</td>
													<td class="hidden-480">2</td>
													<td class="hidden-480">2</td>
													<td class="hidden-480">3</td>
													<td class="hidden-480">4</td>
													<td class="hidden-480">5</td>
													<td class="hidden-480">6</td>
													<th class="hidden-480">Operation</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
										<div class="widget-toolbar no-border">
											<P>
												<a href="#" class="btn btn-sm btn-primary no-radius"
													onClick="addProject()"> Add Project </a> <a href="#"
													class="btn btn-sm btn-primary no-radius"
													onClick="saveWorkload()"> Save </a> <a href="#"
													class="btn btn-sm btn-primary no-radius "> Save &&
													Submit </a>
											</P>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content-area -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
</body>
</html>
