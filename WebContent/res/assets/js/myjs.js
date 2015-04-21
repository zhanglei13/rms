function change(){
	var project=[
	 ["project1.1","project1.2","project1.3","project1.4"],
	 ["project2.1","project2.2","project2.3","project2.4"],
	 ["project3.1","project3.2","project3.3","project3.4"],
	];
	var sltrelease=document.getElementById("release");
	var sltproject=document.getElementById("project");
	var projectname=project[sltrelease.selectedIndex-1];
	sltproject.length=0;
	for(var i=0;i<projectname.length;i++){
		sltproject[i]=new Option(projectname[i],projectname[i]);
	}
}

function addRow(){
 var bodyObj=document.getElementById("sample-table-1");  
 bodyObj.style.display = '';
    if(bodyObj==null)   
    {  
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
	
    aCell.innerHTML = "<select name='release' id='release' style='width:80px' onchange='change()'><option value='0'>choice</option><option value='1'>Relase1</option><option value='1'>Relase2</option><option value='1'>Relase3</option></select>";
    bCell.innerHTML="<select name='project' id='project' style='width:80px'><option value='0'>choice</option></select>";
    cCell.innerHTML="SIT";
	dCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	eCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	fCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	gCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	hCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	iCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	jCell.innerHTML="<input type='text' style='width:30px;height:30px'></input>";
	kCell.innerHTML="<button class='btn btn-sm btn-warning' onClick='deleteRow(this)'><i class='ace-icon fa fa-undo'></i>delete</button>";  
}

function deleteRow(inputobj){  
    if(inputobj==null) return;  
    var parentTD = inputobj.parentNode; 
    var parentTR = parentTD.parentNode;  
    var parentTBODY = parentTR.parentNode;  
    parentTBODY.removeChild(parentTR); 
}
function setting(role){
	  //by simple member
		if(role==1){
			document.getElementById('evaluation').style.display = "none";
			document.getElementById('approval').style.display = "none";
			document.getElementById('project').style.display = "none";
			document.getElementById('reporter').style.display = "none";
			}
		//by itleader
		if(role==2){
		document.getElementById('approval').style.display = "";
		document.getElementById('linemanager').style.display = "none";
		document.getElementById('project').style.display = "none";
		}
		//by linemanager
		if(role==3){
		document.getElementById('itleader').style.display = "none";
		document.getElementById('project').style.display = "none";
		}
		//by admin
		if(role==4){
		    document.getElementById('evaluation').style.display = "none";
			document.getElementById('approval').style.display = "none";
			document.getElementById('project').style.display = "none";
		}
	}