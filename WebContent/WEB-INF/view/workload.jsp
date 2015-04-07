<title>workload</title>
<%
	request.setAttribute("ctp", request.getContextPath());
%>
<link rel="stylesheet" href="${ctp}/res/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctp}/res/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctp}/res/assets/css/ui.jqgrid.css" />
<script type="text/javascript">
function editWorkload(data){
	
}
</script>
<!-- ajax layout which only needs content area -->
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<h3 class="header smaller lighter blue">Check your MD</h3>
		<div class="table-header">Name: &nbsp;${name} &nbsp;&nbsp;&nbsp;
			Date: &nbsp;${date}</div>

		<div>
			<table id="workloadTable"
				class="table table-striped table-bordered table-hover">
				<thead class="center">
					<tr>
						<th class="hidden-480">Date</th>
						<th class="hidden-480">Project</th>
						<th class="hidden-480">Phase</th>
						<th class="hidden-480">Mon.</th>
						<th class="hidden-480">TUE.</th>
						<th class="hidden-480">WED.</th>
						<th class="hidden-480">THU</th>
						<th class="hidden-480">FRI.</th>
						<th class="hidden-480">SAT.</th>
						<th class="hidden-480">SUN.</th>
						<th class="hidden-480">Status</th>
						<th class="hidden-480">Operation</th>
					</tr>
				</thead>

				<tbody class="center">
					<tr>
						<td class="hidden-480">3.2-3.8</td>
						<td class="hidden-480">CSM</td>
						<td class="hidden-480">UAT</td>
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
							class="btn btn-sm btn-warning" onClick="editWorkload()"> <i
								class="ace-icon fa fa-pencil-square-o"></i> Edit
						</a> <a href="#" class="btn btn-sm btn-warning"> <i
								class="ace-icon fa fa-cloud-upload"></i> Submit
						</a></th>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
	var scripts = [ null, null ]
	ace.load_ajax_scripts(scripts, function() {
		jQuery(function($) {
			$.ajax({
				type : 'POST',
				cache : false,
				url : '${ctp}/workload/list',
				datatype : "json",
				data : {
					itCode : "fyahya"
				},
				error : function(request) {
					alert("Server Error!");
					return false;
				},
				success : function(data) {
					var content;
					$.each(data, function(i, result) {				
						var array = [];						
						array.push(result['dateRange'])
						array.push(result['projectName'])
						array.push(result['phaseCode']);
						for(i = 0; i < 7; i++) {
							if(result['effortPerWeek'][i] == null)
								array.push(0);
							else
								array.push(result['effortPerWeek'][i]);
						}
						
						var content = "<tr>";
						$.each(array,function(key,val){
						    content+="<td class='hidden-480'>" + val + "</td>";
						});
						
						var status = result['status'];
						content += "<td class='hidden-480'>";
						if(status == 0)
							content+="<span class='label label-sm label-info arrowed arrowed-righ'>saved</span>";
						else if(status == 1)
							content+="<span class='label label-sm label-inverse arrowed-in'>submitted</span>";
						else if(status == 2)
							content+="<span class='label label-sm label-warning'>rejected</span>";
						else
							content+="<span class='label label-sm label-success'>approved</span>";
						content += "</td>";
						$('.table').append(content);
					});
				}
			});
		});
	});
</script>