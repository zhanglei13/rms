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
		
		

		$('#i_aaprovaletable')
				.datagrid(
						{
							idField : 'id',
							title : 'IT leader Approval',
							fit : true,
							height : 300,
							url : 'iapproval?method=getList',
							fitColumns : true,
							striped : true,
							loadMsg : 'load the data,please wait...',
							rownumbers : true,
							columns : [ [ 
							     
							   {
				                  field:'ck' ,
				                    width:50 ,
				                    checkbox: true
			                     },  
							     {
								field : 'tower',
								title : 'Tower',
								align : 'center',
								width : 50,
								styler : function(value, record) {
									return 'font-size:18px;';

								}
							}, {
								field : 'name',
								title : 'Name',
								align : 'center',
								width : 50
							}, {
								field : 'itcode',
								title : 'It Code',
								align : 'center',
								width : 50
							}, {
								field : 'phase',
								title : 'Phase',
								align : 'center',
								width : 50
							}, {
								field : 'actual',
								title : 'Actual Manday',
								align : 'center',
								width : 50
							}, {
								field : 'total',
								title : 'Total Actual',
								align : 'center',
								width : 50
							},{
								field : 'planned',
								title : 'Planned',
								align : 'center',
								width : 50
							}, {
								field : 'utiliaztion',
								title : 'Utiliaztion',
								align : 'center',
								width : 50
							}, {
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
								width : 100
							} ] ],

							pagination : true,
							pageNumber : 3,
							pageSize : 5,
							pageList : [ 5, 10 ],
							toolbar : [
										{
									          //批准Approval
												text:'Approval' , 
												iconCls:'icon-ok',
												handler:function(){
															var arr =$('#i_aaprovaletable').datagrid('getSelections');
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
																		$.post('iapproval?method=approval', {ids:ids} , function(result){
																			//1 刷新数据表格 
																		$('#i_aaprovaletable').datagrid('reload');
																		//2 清空idField   
																		$('#i_aaprovaletable').datagrid('unselectAll');
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
														var arr =$('#i_aaprovaletable').datagrid('getSelections');
															if(arr.length !=1){
																$.messager.show({
																	title:'Notice!',
																	msg:'please choice only one line!'
																});
															} else {
																$('#ireasondialog').dialog({
																	title:'Are you sure?'
																});
																$('#ireasondialog').dialog('open'); //打开窗口
																$('#irejectform').get(0).reset();   //清空表单数据 
																$('#irejectform').form('load',{	   //调用load方法把所选中的数据load到表单中,非常方便
																	id:arr[0].id ,
																	name:arr[0].name ,
																	
																	idcode:arr[0].idcode
																	
																});
																}
														}								
										  
										}	
									  
						]
						});
		 /*
		 *  拒绝申请
		 */
		$('#ireject_sure').click(function(){
				if($('#irejectform').form('validate')){
					$.ajax({
						type: 'post' ,
						url: flag=='iapproval?method=reject' ,
						cache:false ,
						data:$('#irejectform').serialize() ,
						dataType:'json' ,
						success:function(result){
							//1 关闭窗口
							$('#ireasondialog').dialog('close');
							//2刷新datagrid 
							$('#i_aaprovaletable').datagrid('reload');
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
		 
		$('#ireject_cancel').click(function(){
			$('#ireasondialog').dialog('close');
		});
		 
		

	});
</script>
</head>
<body>
Project:<select id="isel_project" class="easyui-combobox" url="iapproval?method=getProjct" valueField="id" textField="name"  ></select><BR><BR>
<table id="i_aaprovaletable"></table>
<div id="ireasondialog" title="RejectReason" modal=true  draggable=false class="easyui-dialog" closed=true style="width:300px;">
	    		<form id="irejectform" action="" method="post">
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
	    							<a id="ireject_sure" class="easyui-linkbutton">Sure</a>
	    							<a id="ireject_cancel" class="easyui-linkbutton">Cancel</a>
	    						</td>
	    					</tr>   					 					    					    					    					    					    					    					    					
	    				</table>
	    		</form> 			
 			</div>
</body>
</html>
