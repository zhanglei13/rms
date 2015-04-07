<%@include file="../../res/common/importAll.jsp"%>

<!-- ajax layout which only needs content area -->
<div class="page-header"></div><!-- /.page-header -->

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
												<tr>
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
													<th class="hidden-480"><span
														class="label label-xlg label-greyarrowed arrowed-right">Submitted</span>
													</th>
													<th class="hidden-480"><a href="#"
														class="btn btn-sm btn-warning"> 
														<i class="ace-icon fa fa-undo"></i> cancel
													</a></th>
												</tr>

												<tr>
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
													<th class="hidden-480"><span
														class="label label-xlg label-grey 
                               arrowed-in-right arrowed-in">Saved</span>
													</th>
													<th class="hidden-480"><a href="#"
														class="btn btn-sm btn-warning"> <i
															class="ace-icon fa fa-pencil-square-o"></i> Edit
													</a> <a href="#" class="btn btn-sm btn-warning"> <i
															class="ace-icon fa fa-cloud-upload"></i> Submit
													</a></th>
												</tr>
											</tbody>
										</table>
										<div class="widget-toolbar no-border">
											<p>
												<a href="#" class="btn btn-sm btn-primary no-radius"
													onClick="addmd()"> Add Workload </a> <a href="#"
													class="btn btn-sm btn-primary no-radius" onClick="addmd()">
													Submmit All </a>
											</P>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->