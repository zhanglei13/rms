<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>RMS-IT Leader Approval</title>
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="res/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="res/jquery-easyui-1.2.6/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="res/jquery-easyui-1.2.6/themes/icon.css" />
<script type="text/javascript"
	src="res/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="res/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function() {
		
		

		$('#l_aaprovaletable')
				.datagrid(
						{
							idField : 'id',
							title : 'Line Manager Approval',
							fit : true,
							height : 300,
							url : 'lapproval?method=getList',
							fitColumns : true,
							striped : true,
							loadMsg : 'load the data,please wait...',
							rownumbers : true,
							frozenColumns : [ [ 
                                 {
	                              field:'ck' ,
	                              width:50 ,
	                              checkbox: true
                                 },       
							    {
								field : 'name',
								title : 'Name',
								align : 'center',
								width : 100,
								styler : function(value, record) {
									return 'font-size:18px;';

								}
							}, {
								field : 'itcode',
								title : 'IT code',
								align : 'center',
								width : 50
							}, {
								field : 'release',
								title : 'Release',
								align : 'center',
								width : 50
							}, {
								field : 'project',
								title : 'Project',
								align : 'center',
								width : 100
							}, {
								field : 'phase',
								title : 'Phase',
								align : 'center',
								width : 50
							}
							]],
							
							columns:[[
							 {
								field : 'day1',
								title : '1',
								align : 'center',
								width : 20
							},{
								field : 'day2',
								title : '2',
								align : 'center',
								width : 20
							}, {
								field : 'day3',
								title : '3',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day4',
								title : '4',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day5',
								title : '5',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day6',
								title : '6',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day7',
								title : '7',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day8',
								title : '8',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day9',
								title : '9',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day10',
								title : '10',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day11',
								title : '11',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day12',
								title : '12',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day13',
								title : '13',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day14',
								title : '14',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day15',
								title : '15',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day16',
								title : '16',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day17',
								title : '17',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day18',
								title : '18',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day19',
								title : '19',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day20',
								title : '20',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day21',
								title : '21',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day22',
								title : '22',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day23',
								title : '23',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day24',
								title : '24',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day25',
								title : '25',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day26',
								title : '26',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day27',
								title : '27',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day28',
								title : '28',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day29',
								title : '29',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day30',
								title : '30',
								align : 'center',
								width : 20
							}, 
							{
								field : 'day31',
								title : '31',
								align : 'center',
								width : 20
							}, 
							{
								field : 'status',
								title : 'Status',
								width : 50,
								align : 'center',
								styler : function(value, record) {
									return 'background:#666666;';
								}
							},{
								field : 'description',
								title : 'Description',
								align : 'center',
								width : 50
							} ] ],
							pagination : true,
							pageNumber :3,
							pageSize : 5,
							pageList : [ 5, 10 ],
							toolbar : [
										{
									          //批准Approval
												text:'Approval' , 
												iconCls:'icon-ok',
												handler:function(){
															var arr =$('#l_aaprovaletable').datagrid('getSelections');
																if(arr.length <=0){
																	$.messager.show({
																		title:'Notice!',
																		msg:'please choice at least one line!'
																	});
																} else {
																  $.messager.confirm('Notice' , 'Are you sure?' , function(r){
																		if(r){
																			var ids = '';
																			for(var i =0 ;i<arr.length;i++){
																			ids += arr[i].id + ',' ;
																				}
																		ids = ids.substring(0 , ids.length-1);
																		$.post('lapproval?method=approval', {ids:ids} , function(result){
																			//1 刷新数据表格 
																		$('#l_aaprovaletable').datagrid('reload');
																		//2 清空idField   
																		$('#l_aaprovaletable').datagrid('unselectAll');
																		//3 给提示信息 
																		$.messager.show({
																		    title:'Notice!' , 
																		    msg:'Opernation Success!'
																		});
																		  });
																	  } else {
																			return ;
																		}
																	});
																	}
															}								
									  }, {
								          //拒绝
											text:'Reject' , 
											iconCls:'icon-no',
											handler:function(){
														var arr =$('#l_aaprovaletable').datagrid('getSelections');
															if(arr.length !=1){
																$.messager.show({
																	title:'Notice!',
																	msg:'please choice only one line!'
																});
															} else {
																$('#lreasondialog').dialog({
																	title:'Are you sure?'
																});
																$('#lreasondialog').dialog('open'); //打开窗口
																$('#lrejectform').get(0).reset();   //清空表单数据 
																$('#lrejectform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
																	id:arr[0].id ,
																	name:arr[0].name ,
																	idcode:arr[0].idcode
																	
																});
																}
														}								
										  
										}	
									  
						],
						onLoadSuccess : function(data) {

							if (data.rows.length > 0) {
								mergeCellsByField("l_aaprovaletable",
										"release,project");
							}
						}
						});
		 /*
		 *  拒绝申请
		 */
		$('#lreject_sure').click(function(){
				if($('#lrejectform').form('validate')){
					$.ajax({
						type: 'post' ,
						url: flag=='lapproval?method=reject' ,
						cache:false ,
						data:$('#lrejectform').serialize() ,
						dataType:'json' ,
						success:function(result){
							//1 关闭窗口
							$('#lreasondialog').dialog('close');
							//2刷新datagrid 
							$('#l_aaprovaletable').datagrid('reload');
							//3 提示信息
							$.messager.show({
								title:result.status , 
								msg:result.message
							});
						} ,
						error:function(result){
							$.meesager.show({
								title:result.status , 
								msg:result.message
							});
						}
					});
				} else {
					$.messager.show({
						title:'Notice' ,
						msg:'please input the reason!'
					});
				}
		});
		 
		 /*
		 *取消按钮
		 */
		 
		$('#lreject_cancel').click(function(){
			$('#lreasondialog').dialog('close');
		});
		 
		

	});
</script>
</head>
<body>
Tower:FES<BR><BR>
<table id="l_aaprovaletable"></table>
<div id="lreasondialog" title="RejectReason" modal=true  draggable=false class="easyui-dialog" closed=true style="width:300px;">
	    		<form id="lrejectform" action="" method="post">
	    				<input type="hidden" name="id" value="" />
	    				<input type="hidden" name="name" value="" />
	    				<input type="hidden" name="idcode" value="" />
	    				<table>
	    					<tr>
	    						<td>Reason</td>
	    						<td><input type="text" name="description" class="easyui-validatebox" required=true  missingMessage="用户名必填!" invalidMessage="must"  value="" /></td>
	    					</tr>
	    					
	    					<tr align="center">
	    						<td colspan="2">
	    							<a id="lreject_sure" class="easyui-linkbutton">Sure</a>
	    							<a id="lreject_cancel" class="easyui-linkbutton">Cancel</a>
	    						</td>
	    					</tr>   					 					    					    					    					    					    					    					    					
	    				</table>
	    		</form> 			
 			</div>
</body>
</html>
