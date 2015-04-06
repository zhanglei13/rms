<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<%@ include file="../../res/common/header.txt" %>
	<title>RMS WorkLoad</title>
	</head>

	<body class="no-skin">
	     <!-- -top information -->
         <%@ include file="../../res/common/top.txt" %>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<!-- -leftlist -->
			<%@ include file="../../res/common/leftlist.txt" %>
	
			<div class="main-content">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">Home</a>
						</li>

						<li>
							<a href="#">Personal Workload</a>
						</li>
						<li class="active">check</li>
					</ul><!-- /.breadcrumb -->			
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="page-content-area">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->						
								<div class="row">
									<div class="col-xs-12">
										<h3 class="header smaller lighter blue">Check you MD</h3>
										<div class="table-header">
										 Name: &nbsp;${name} &nbsp;&nbsp;&nbsp; Date: &nbsp;${date}
										</div>
										
										<div>
												<table id="sample-table-1" 
                                                 class="table table-striped table-bordered table-hover">
										    <thead class="center">
												<tr >
													<th class="hidden-480">Date</th>
													<th class="hidden-480">Project</th>
													<th class="hidden-480">Phase</th>
													<td class="hidden-480">Mon.</td>
													<td class="hidden-480">TUE.</td>
													<td class="hidden-480">WED.</td>
													<td class="hidden-480">THU</td>
													<td class="hidden-480">FRI.</td>
													<td class="hidden-480">SAT.</td>
													<td class="hidden-480">SUN.</td>
													<th class="hidden-480">Status</th>
													<th class="hidden-480">Operation</th>
												</tr>
											</thead>

											<tbody class="center">
												<tr>
													<th class="hidden-480"></th>
													<th class="hidden-480">SFT</th>
													<th class="hidden-480">No Phase</th>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>	
													<th class="hidden-480">
														<span class="label label-xlg label-grey
                            arrowed arrowed-right">Submitted</span>
													</th>
													<th class="hidden-480">
														<a href="#" class="btn btn-sm btn-warning">
												            <i class="ace-icon fa fa-undo"></i>
												            cancel
											      </a>
													</th>
												</tr>
											
												<tr >
													<th class="hidden-480">3.2-3.8</th>
													<th class="hidden-480">CSM</th>
													<th class="hidden-480">UAT</th>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<td class="hidden-480">8</td>
													<th class="hidden-480">
                               <span class="label label-xlg label-grey 
                               arrowed-in-right arrowed-in">Saved</span>
                          </th>
													<th class="hidden-480">
														
                                                        <a href="#" class="btn btn-sm btn-warning">
												            <i class="ace-icon fa fa-pencil-square-o"></i>
												             Edit
											            </a>
                                                        <a href="#" class="btn btn-sm btn-warning">
												            <i class="ace-icon fa fa-cloud-upload"></i>
												             Submit
											            </a>
													</th>
												</tr>					
											</tbody>
										</table>
                                         <div class="widget-toolbar no-border">
										  <P>
											  <script language="javascript">
											  	function addmd(){
											  		var hdc=window.open('mdadd.jsp','mdadd',                      'width=1000,height=400,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
											  		width=screen.width;
											  		height=screen.height;
											  		hdc.moveTo((width-1000)/2,(height-400)/2);
											  	}
												</script>
											 <a href="#" class="btn btn-sm btn-primary no-radius" onClick="addmd()"> 
												  Add Workload
											  </a>
											<a href="#" class="btn btn-sm btn-primary no-radius" onClick="addmd()"> 
												  Submmit All
											</a>
									      </P>
									      </div>
										</div>
									</div>
								</div>	
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<%@ include file="../../res/common/footer.txt" %>
		</div><!-- /.main-container -->
       <%@ include file="../../res/common/bottomjs.txt" %>
   </body>
</html>
