/**
 * 
 */



function addProject() {
	/*if (projects == null) {
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
	}*/
	
	addRow(null);
}

function changeRelease(index,obj) {
	var release = obj.options[obj.options.selectedIndex].text;
	var projectArray = projects.get(release);
	var sltProject = document.getElementById("project_"+index);
	sltProject.length = 0;
	for (var i = 0; i < projectArray.length; i++) {
		sltProject.options.add(new Option(projectArray[i].projectName, projectArray[i].projectNo));
	}
}

function changeProject(index,obj){
	//TODO 这里需要判断:当前要change到的project是否在之前已经被选中了，如果被选中过，则不能再选
}

function initSelects(index){
	var sltRelease = document.getElementById("release_"+index);
	var sltProject = document.getElementById("project_"+index);
	var releases = projects.keys();
	for(var i in releases){
		sltRelease.options.add(new Option(releases[i],releases[i]));
	}
	sltRelease.options[0].selected = true;
	var projectArray = projects.get(releases[0]);//第一个release下的projects
	for(var i in projectArray){
		sltProject.options.add(new Option(projectArray[i].projectName,projectArray[i].projectNo));
	}
}

function addRow(preWorkload) {
	var bodyObj = document.getElementById("sample-table-1");
	bodyObj.style.display = '';
	if (bodyObj == null) {
		alert("Body of Table not Exist!");
		return;
	}
	var rowCount = bodyObj.rows.length;
	var newRow = bodyObj.insertRow(rowCount++);
	var aCell = newRow.insertCell(0);
	var bCell = newRow.insertCell(1);
	var cCell = newRow.insertCell(2);
	var dCell = newRow.insertCell(3);
	var eCell = newRow.insertCell(4);
	var fCell = newRow.insertCell(5);
	var gCell = newRow.insertCell(6);
	var hCell = newRow.insertCell(7);
	var iCell = newRow.insertCell(8);
	var jCell = newRow.insertCell(9);
	var kCell = newRow.insertCell(10);
	aCell.style.textAlign = "center";
	bCell.style.textAlign = "center";
	cCell.style.textAlign = "center";
	dCell.style.textAlign = "center";
	eCell.style.textAlign = "center";
	fCell.style.textAlign = "center";
	gCell.style.textAlign = "center";
	hCell.style.textAlign = "center";
	iCell.style.textAlign = "center";
	jCell.style.textAlign = "center";
	kCell.style.textAlign = "center";
	
	if(preWorkload==null){
		aCell.innerHTML = "<select name='release' id='release_"+rowCount+"' style='width:80px' onchange='changeRelease("+rowCount+",this)'></select>";
		bCell.innerHTML = "<select name='project' id='project_"+rowCount+"' style='width:80px' onchange='changeProject("+rowCount+",this)'></select>";
		initSelects(rowCount);
		cCell.innerHTML = "SIT";
		dCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		eCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		fCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		gCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		hCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		iCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		jCell.innerHTML = "<input type='text' style='width:30px;height:30px'></input>";
		kCell.innerHTML = "<button class='btn btn-sm btn-warning' onClick='deleteRow(this)'><i class='ace-icon fa fa-undo'></i>delete</button>";
	}else{
		aCell.innerHTML = "<select name='release' id='release_"+rowCount+"' style='width:80px' onchange='changeRelease("+rowCount+",this)'><option>"+preWorkload.projectType+"</option></select>";
		bCell.innerHTML = "<select name='project' id='project_"+rowCount+"' style='width:80px' onchange='changeProject("+rowCount+",this)'><option>"+preWorkload.projectName+"</option></select>";
		//initSelects(rowCount);
		cCell.innerHTML = preWorkload.phaseCode;
		dCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[0]+"'></input>";
		eCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[1]+"'></input>";
		fCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[2]+"'></input>";
		gCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[3]+"'></input>";
		hCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[4]+"'></input>";
		iCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[5]+"'></input>";
		jCell.innerHTML = "<input type='text' style='width:30px;height:30px' value='"+preWorkload.effortPerWeek[6]+"'></input>";
		kCell.innerHTML = "<button class='btn btn-sm btn-warning' onClick='deleteRow(this)'><i class='ace-icon fa fa-undo'></i>delete</button>";
	}
	
}

function deleteRow(inputobj) {
	if (inputobj == null)
		return;
	var parentTD = inputobj.parentNode;
	var parentTR = parentTD.parentNode;
	var parentTBODY = parentTR.parentNode;
	parentTBODY.removeChild(parentTR);
}

function getWorkloadData(){
	var table = document.getElementById("sample-table-1");
	if (table == null) {
		return null;
	}
	var rowCount = table.rows.length;
	var workloadData = new Array();
	for(var i=1;i<rowCount;i++){
		var row = {};//构造一个worloadrow
		var projectNo = document.getElementById("project_"+(i+1)).value;
		var projectMatrix =  projects.values();
		var selectedProject;//找到选中的project对象
		//console.log(projectMatrix);
		for(var m in projectMatrix){
			for(var t in projectMatrix[m])
			if(projectMatrix[m][t].projectNo==projectNo){
				selectedProject = projectMatrix[m][t];
				break;
			} 
		}
		//console.log(selectedProject);
		row["projectType"]=selectedProject.projectType;
		row["projectName"]=selectedProject.projectName;
		row["projectNo"]=selectedProject.projectNo;
		
		var phaseCode = table.rows[i].cells[2].innerHTML;
		row["phaseCode"]=phaseCode;
		var datePerWeek = new Array(7);
		var effortPerWeek = new Array(7);
		for(var j=3;j<=9;j++){
			datePerWeek[j-3]   = table.rows[0].cells[j].innerHTML;
			effortPerWeek[j-3] = table.rows[i].cells[j].getElementsByTagName("INPUT")[0].value;
		}
		row["datePerWeek"]=datePerWeek;
		row["effortPerWeek"]=effortPerWeek;
		
		//TODO
		row["itCode"]="eric";
		row["creator"]="eric";
		workloadData.push(row);
	}
	return workloadData;
}

function checkWorkloadData(workloadData){
	//TODO 这里需要做校验
	return true;
}

function saveWorkload(){
	var workloadData = getWorkloadData();
	if(workloadData==null){
		alert("没有要保存的数据");
		return;
	}else{
		if(!checkWorkloadData(workloadData)){
			alert("要保存的数据错误");
			return;
		}
	}
	$.ajax({
		type : 'POST',
		url : "../workload/save",
		data:  {
			workloadRows:JSON.stringify(workloadData),
			itCode:"eric"
		},
		success : function(data) {
			alert("保存成功！");
		},
		error : function() {
			alert("保存失败！");
		}
	});	
}

function saveAndSubmitWorkload(){
	
}