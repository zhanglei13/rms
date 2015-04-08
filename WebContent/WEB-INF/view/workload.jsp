<title>workload</title>
<%
	request.setAttribute("ctp", request.getContextPath());
%>
<link rel="stylesheet" href="${ctp}/res/assets/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctp}/res/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctp}/res/assets/css/ui.jqgrid.css" />

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

				<tbody>
				</tbody>
			</table>
			
			<div class="widget-toolbar no-border">
				<p>
					<a href="#" class="btn btn-sm btn-primary no-radius"
						onClick="addOrUpdateWorkload(0,'3.30-3.31')"> Add Workload </a> <a href="#"
						class="btn btn-sm btn-primary no-radius" onClick="addOrUpdateWorkload(1)">
						Submmit All </a>
				</P>
			</div>
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
					itCode : "eric"
				},
				error : function(request) {
					alert("Server Error!");
					return false;
				},
				success : function(data) {
					workloadList = data;
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
						content += "<td><div class='hidden-sm hidden-xs action-buttons'>"
							+ "<a class='green' href='#'>"
							+ "<i class='ace-icon fa fa-pencil bigger-130'></i></a>"
							+ "<a class='red' href='#'>"
							+ "<i class='ace-icon fa fa-trash-o bigger-130'></i></a></div></td>";
						content += "</tr>";
						$('.table').append(content);
					});
				}
			});
		});
	});
</script>

<script type="text/javascript">
	function addOrUpdateWorkload(flag, range) {
		var hashtable = new jQuery.Hashtable();
		$.each(workloadList, function(i, result) {
			if(!hashtable.containsKey(result['dateRange']))
				hashtable.add(result['dateRange'],[]);
			hashtable.get(result['dateRange']).push(result);
		});
		
		if(flag == 0) {
			//console.log(hashtable.get(range)[0]);
			//return hashtable.get(range);
			window.open("${ctp}/workload/add?workload=" + JSON.stringify(hashtable.get(range)));
		}
		else {
			//console.log(hashtable.getLast());
			//return hashtable.getLast();
			window.open("${ctp}/workload/add?workload=" + JSON.stringify(hashtable.getLast()));
			
			
		}
	}
	
</script>