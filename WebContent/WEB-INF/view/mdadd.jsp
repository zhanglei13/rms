<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<%@ include file="../../res/common/header.txt" %>
	<script src="res/assets/js/myjs.js"></script>
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
										<div class="table-header">
										 Name:Member1 &nbsp;&nbsp;&nbsp; Date:2015.04
										</div>
										
										<div>
										<table id="sample-table-1" class="table table-striped table-bordered table-hover"> 
											<thead class="center">
												<tr>
													<th class="hidden-480">Release</th>
													<th class="hidden-480">Project</th>
													<th class="hidden-480">Phase</th>
													<td class="hidden-480">03/17</td>
													<td class="hidden-480">03/18</td>
													<td class="hidden-480">03/19</td>
													<td class="hidden-480">03/20</td>
													<td class="hidden-480">03/21</td>
													<td class="hidden-480">03/22</td>
													<td class="hidden-480">03/23</td>
													<th class="hidden-480">Operation</th>
												</tr>
											</thead>
											<tbody>		
										   </tbody>
										</table>
                                      <div class="widget-toolbar no-border">
                                          <P>
										 	   <a href="#" class="btn btn-sm btn-primary no-radius" onClick="addRow()"> 
												  Add Project
											   </a>
											   <a href="#" class="btn btn-sm btn-primary no-radius" > 
												  Save
											   </a>
											   <a href="#" class="btn btn-sm btn-primary no-radius "> 
												  Save && Submit
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
		</div><!-- /.main-container -->
   </body>
</html>
