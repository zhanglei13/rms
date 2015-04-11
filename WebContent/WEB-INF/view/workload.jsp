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
				<thead>
					<tr>
						<th class="hidden-480">Date</th>
						<th class="hidden-480">Project</th>
						<th class="hidden-480">Phase</th>
						<th class="hidden-480">Mon.</th>
						<th class="hidden-480">Tue.</th>
						<th class="hidden-480">Wed.</th>
						<th class="hidden-480">Thu.</th>
						<th class="hidden-480">Fri.</th>
						<th class="hidden-480">Sat.</th>
						<th class="hidden-480">Sun.</th>
						<th class="hidden-480">Status</th>
					</tr>
				</thead>
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
var scripts = [null,"${ctp}/res/assets/js/jquery.dataTables.min.js","${ctp}/res/assets/js/jquery.dataTables.bootstrap.js", null]
	ace.load_ajax_scripts(scripts, function() {
		//inline scripts related to this page
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
					var tableData = new Array();
					for(var i in data){
						var row={};
						row["Date"] = data[i].dateRange;
						row["Project"] = data[i].projectName;
						row["Phase"]  = data[i].phaseCode;
					    row["Mon"] = data[i].effortPerWeek[0];
					    row["Tue"] = data[i].effortPerWeek[1];
					    row["Wed"] = data[i].effortPerWeek[2];
					    row["Thu"] = data[i].effortPerWeek[3];
					    row["Fri"] = data[i].effortPerWeek[4];
					    row["Sat"] = data[i].effortPerWeek[5];
					    row["Sun"] = data[i].effortPerWeek[6];
					    row["Status"] = data[i].status;
					    tableData.push(row);
					}
					prepareTable(tableData);
				}
			});
			
			function prepareTable(tableData) {
				console.log(tableData);
				var oTable1 = 
					$('#workloadTable')
					//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
					.dataTable( {
						"data":tableData,
						"searching": false,
					    "ordering": false,
						"columns": [
				            { "data": "Date" },
				            { "data": "Project" },
				            { "data": "Phase" },
				            { "data": "Mon" },
				            { "data": "Tue" },
				            { "data": "Wed" },
				            { "data": "Thu" },
				            { "data": "Fri" },
				            { "data": "Sat" },
				            { "data": "Sun" },
				            { "data": "Status" }
				        ],
				        "columnDefs": [
	                       { "visible": false, "targets": 0 }
	                    ],
	                    "order": [[ 0, 'asc' ]],
	                    "bAutoWidth": false,
	                    "drawCallback": function ( settings ) {
	                       var api = this.api();
	                       var rows = api.rows( {page:'current'} ).nodes();
	                       var last=null;
	            
	                       api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                           if ( last !== group ) {
	                        	   var content = "<div class='hidden-sm hidden-xs action-buttons'>"
	       							+ "<a class='green' href='#'>"
	    							+ "<i class='ace-icon fa fa-pencil bigger-130'></i></a>"
	    							+ "<a class='red' href='#'>"
	    							+ "<i class='ace-icon fa fa-trash-o bigger-130'></i></a></div>";
	                               $(rows).eq( i ).before(
	                                   '<tr class="group"><td colspan="1">'+group+'</td><td colspan="9>"'+content+'</td></tr>'
	                               );
	                               last = group;
	                           }
	                       } );
	                    },
	                    "createdRow": function ( row, data, index ) {
	                    	var content;
	                        switch(data['Status']) {
	                        case '0':
	                        	content = "<span class='label label-sm label-info arrowed arrowed-righ'>saved</span>";
	                        	break;
	                        case '1':
	                        	content = "<span class='label label-sm label-inverse arrowed-in'>submitted</span>";
	                        	break;
	                        case '2':
	                        	content = "<span class='label label-sm label-warning'>rejected</span>";
	                        	break;
	                        default:
	                        	content = "<span class='label label-sm label-success'>approved</span>";
	                        }
	                        $('td', row).eq(9).html(content);
	                    }
					});
				
				$(document).on('click', 'th input:checkbox' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
				});
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					//var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			}
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