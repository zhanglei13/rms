<%--
  Created by IntelliJ IDEA.
  User: zhanglei
  Date: 2015/4/1
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@include file="common/header.jsp" %>
    <title>Workload</title>
<!-- ajax layout which only needs content area -->
<div class="page-header">

</div><!-- /.page-header -->

<div class="row">
							<div class="col-xs-12">
								<div class="row">
									<div class="col-xs-12">
										<h3 class="header smaller lighter blue">Check your MD</h3>
										<div class="table-header">
											填报人：Member&nbsp;&nbsp;&nbsp; 工作时间：2015.04
										</div>
									</div>
								</div>
							
							<div class="row">
									<div class="col-xs-12">
										<table id="sample-table-1" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">
														<label class="position-relative">				
															<span class="lbl">Date</span>
														</label>
													</th>
													<th>Project</th>
													<th>Phase</th>
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

											<tbody>
												<tr>
													<th class="center">
														<label class="position-relative">				
															<span class="lbl"></span>
														</label>
													</th>
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
														Submitted
													</th>
													<th class="hidden-480">
														<button class="btn btn-sm btn-warning">
												     <i class="ace-icon fa fa-undo"></i>
												    cancel
											      </button>
													</th>
												</tr>
											
												<tr>
													<th class="center">
														<label class="position-relative">				
															<span class="lbl">3.2-3.8</span>
														</label>
													</th>
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
													  Saved
													</th>
													<th class="hidden-480">
														<button class="btn btn-sm btn-warning">
												     <i class="ace-icon fa fa-undo"></i>
												    cancel
											      </button>
													</th>
												</tr>					
											</tbody>
										</table>
                  <div class="widget-toolbar no-border">
										<P>
											  <script language="javascript">
											  	function addmd(){
											  		var hdc=window.open('mdadd.html','mdadd','width=1000,height=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
											  		width=screen.width;
											  		height=screen.height;
											  		hdc.moveTo((width-1000)/2,(height-400)/2);
											  	}
												</script>
												<span class="btn btn-info btn-sm tooltip-info" data-rel="tooltip" data-placement="bottom" title="Bottm Info" onClick="addmd()">Add Workload</span>
												<span class="btn btn-info btn-sm tooltip-info" data-rel="tooltip" data-placement="bottom" title="Bottm Info">Submit All</span>  
										</P>
                  </div>
										
									</div><!-- /.span -->
								</div><!-- /.row -->
							
							<!--biaoji-->	
							</div><!-- /.col -->
						</div><!-- /.row -->

</html>
