function addProject() {
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

function initSelects(index,releaseName,projectName){
	//console.log(releaseName);
	//console.log(projectName);
	var sltRelease = document.getElementById("release_"+index);
	var sltProject = document.getElementById("project_"+index);
	var releases = projects.keys();
	for(var i in releases){
		sltRelease.options.add(new Option(releases[i],releases[i]));
	}
	//console.log("haha");	
	if(releaseName==null||releaseName==""){
		//console.log("haha");
		sltRelease.options[0].selected = true;
		var projectArray = projects.get(releases[0]);//第一个release下的projects
		for(var i in projectArray){
			sltProject.options.add(new Option(projectArray[i].projectName,projectArray[i].projectNo));
		}
		sltProject.options[0].selected = true;
	}else{
		//将对应relaseName的option设置为选择
		for(var i in sltRelease.options){
			if(sltRelease.options[i].text==releaseName){
				sltRelease.options[i].selected=true;
				break;
			}
		}
		var projectArray = projects.get(releaseName);//该release下的projects
		for(var i in projectArray){
			sltProject.options.add(new Option(projectArray[i].projectName,projectArray[i].projectNo));
		}
		if(projectName==null||projectName==""){
			sltProject.options[0].selected = true;
		}else{
			//将对应projectName的option设置为选择
			for(var i in sltProject.options){
				if(sltProject.options[i].text==projectName){
					sltProject.options[i].selected=true;
					break;
				}
			}
		}
	}
	var selctReleaseName = sltRelease.options[sltRelease.options.selectedIndex].text;
	//console.log(sltProject.options);
	var selctProjectName = sltProject.options[sltProject.options.selectedIndex].text;
	var project = getProject(selctReleaseName,selctProjectName);
	return project.phase;
}

function getProject(releaseName,projectName){
	var projectArray = projects.get(releaseName);
	for (var i = 0; i < projectArray.length; i++) {
		if(projectArray[i].projectName===projectName){
			return projectArray[i];
		}
	}
}
var selectId=0;//全局量，用作select的ID
function addRow(historyWorkloadRow) {
	//console.log(historyWorkloadRow);
	var tBody = document.getElementById("add_workload_table");
	var rowCount = tBody.rows.length;
	var newRow = tBody.insertRow(rowCount++); //新插入一行
	var cells = new Array();
	//为该行添加单元格
	for(var i=0;i<=10;i++){
		cells.push(newRow.insertCell(i));
		cells[i].style.textAlign = "center";
	}
	selectId++;
	cells[0].innerHTML="<select name='release' id='release_"+selectId+"' style='width:120px' onchange='changeRelease("+selectId+",this)'></select>";
	cells[1].innerHTML="<select name='project' id='project_"+selectId+"' style='width:120px' onchange='changeProject("+selectId+",this)'></select>";
	
	//是否有历史数据
	if(historyWorkloadRow==null){
		cells[2].innerHTML= initSelects(selectId,null,null); //该函数返回project所在的phase
		for(var i=3;i<=9;i++){
			cells[i].innerHTML="<input type='text' style='width:40px;height:30px'></input>";
		}
	}else{
		cells[2].innerHTML= initSelects(selectId,historyWorkloadRow.projectType,historyWorkloadRow.projectName); //该函数返回project所在的phase
		for(var i=3;i<=9;i++){
			cells[i].innerHTML="<input type='text' style='width:40px;height:30px' value='"+historyWorkloadRow.effortPerWeek[i-3]+"'></input>";
		}
	}
	cells[10].innerHTML="<button class='btn btn-sm btn-warning' onClick='deleteRow(this)'><i class='ace-icon fa fa-undo'></i>delete</button>";
}

function deleteRow(inputobj) {
	if (inputobj == null) return;
	var parentTD = inputobj.parentNode;
	var parentTR = parentTD.parentNode;
	var parentTBODY = parentTR.parentNode;
	parentTBODY.removeChild(parentTR);
}


function getWorkloadData(){
	var table = document.getElementById("add_workload_table");
	if (table == null) {
		return null;
	}
	var rowCount = table.rows.length;
	//console.log(table.rows[1].childNodes[1].childNodes[0]);
	var workloadData = new Array();
	for(var i=0;i<rowCount;i++){
		var row = {};//构造一个worloadrow
		//if(document.getElementById("project_"+(i+1))==null) continue;
		//var projectNo = document.getElementById("project_"+(i+1)).value;
		var projectNo = table.rows[i].cells[1].getElementsByTagName("SELECT")[0].value;
		//console.log(projectNo);
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
		//console.log(projectMatrix);
		//console.log(selectedProject);
		row["projectType"]=selectedProject.projectType;
		row["projectName"]=selectedProject.projectName;
		row["projectNo"]=selectedProject.projectNo;
		
		var phaseCode = table.rows[i].cells[2].innerHTML;
		row["phaseCode"]=phaseCode;
		//var datePerWeek = new Array(7);
		var effortPerWeek = new Array(7);
		for(var j=3;j<=9;j++){
			//datePerWeek[j-3]   = table.rows[0].cells[j].innerHTML;
			effortPerWeek[j-3] = table.rows[i].cells[j].getElementsByTagName("INPUT")[0].value;
		}
		row["datePerWeek"]=datePerWeek;
		row["effortPerWeek"]=effortPerWeek;
		
		//TODO
		row["itCode"]=itCode;
		row["creator"]=itCode;
		workloadData.push(row);
	}
	return workloadData;
}

function checkWorkloadData(workloadData){
	//TODO 这里需要做校验
	return true;
}
//将要提交的workload分为3类，要更新的，要新加的，和要删除的
var toDelete = new Array();
var toUpdate = new Array();
var toAdd = new Array();

function classifyWorkloads(historyWorkloads,curWorkloads){
	
	toDelete.splice(0,toDelete.length);
	toUpdate.splice(0,toUpdate.length);
	toAdd.splice(0,toAdd.length);
	//求新旧交集，即要更新的
	console.log(historyWorkloads);
	console.log(curWorkloads);
	for(var i in historyWorkloads){
		for(var j in curWorkloads){
			if(historyWorkloads[i].projectName==curWorkloads[j].projectName){
				toUpdate.push(curWorkloads[j]);
			}
		}
	}
	//求旧新差集，即要删除的
	for(var i in historyWorkloads){
		var flag=false;
		for(var j in toUpdate){
			alert("#"+historyWorkloads[i].projectName+" "+toUpdate[j].projectName+"#");
			if(historyWorkloads[i].projectName==toUpdate[j].projectName){
				flag=true;
				break;
			}
		}
		if(!flag) toDelete.push(historyWorkloads[i]);
	}
	
	//求新旧差集，即要新加的
	for(var i in curWorkloads){
		var flag=false;
		for(var j in toUpdate){
			if(curWorkloads[i].projectName==toUpdate[j].projectName){
				flag=true;
				break;
			}
		}
		if(!flag) toAdd.push(curWorkloads[i]);
	}
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
	//划分要提交的数据
	//classifyWorkloads(preWorkload,curWorkloads);
	
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
	//划分要提交的数据
	classifyWorkloads(historyWorkloads,workloadData);
	//console.log("haha");
	console.log(toUpdate);
	console.log(toDelete);
	console.log(toAdd);

	$.ajax({
		type : 'POST',
		url : "../workload/saveOrSubmit",
		data:  {
			toUpdate :JSON.stringify(toUpdate),
			toDelete:JSON.stringify(toDelete),
			toAdd:JSON.stringify(toAdd),
			optMonth:2,
			submit:false,
			itCode:itCode,
		},
		success : function(data) {
			alert(data);
		},
		error : function() {
			alert("保存失败！");
		}
	});	
}