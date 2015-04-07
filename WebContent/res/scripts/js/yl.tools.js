var tools={
		//获取url中的参数
		getQueryString:function(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		    var r = window.location.search.substr(1).match(reg);
		    r=decodeURIComponent(r);
		    if (r != null) return unescape(r.split(",")[2]); return null;
		},
		//获取当前日期
		nowDay:function(){     
			  var date = new Date();     
			  var year = date.getFullYear();     
			  var month = date.getMonth()+1;    
			  var day = date.getDate();     
			  return year + "年" + month + "月" + day+ "日   ";
		},
		//获取当前时间
		nowTime:function(){
			  var date = new Date();     
			  var year = date.getFullYear();     
			  var month = date.getMonth()+1;    
			  var day = date.getDate();     
			  //var week = date.getDay();
			  var hours = date.getHours();     
			  var minutes = date.getMinutes();    
			  var seconds = date.getSeconds();     
			  //var milliseconds = date.getMilliseconds();  
			  return year + "年" + month + "月" + day+ "日   " + hours + ":" + minutes + ":" + seconds;
		},
		//判断值是否为空
		isEmpty:function(str){
			if(str === undefined||str == "undefined"||str==null||str=="")return true;
			return false;
		},
		//判断值是否不为空
		isNotEmpty:function(str){
			if(str===undefined||str == "undefined"||str==null||str=="")return false;
			return true;
		},
		//字符串转数组
		stringToArray:function(str){
			return str.split(",");
		},
		//数组转字符串
		arrayToString:function(str){
			return str.join('');
		},
		
		//jqgrid宽度自适应
		jqGrid_autowidth:function(grid_selector){
			$(window).on('resize.jqGrid', function () {
				$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
		    });
			//resize on sidebar collapse/expand
			var parent_column = $(grid_selector).closest('[class*="col-"]');
			$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
				if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
					//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
					setTimeout(function() {
						$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
					}, 0);
				}
		    });
		},
		//替换jqgrid中的分页按钮图标
		jqGrid_updatePagerIcons:function(table) {
			var replacement = 
			{
				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
			};
			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			});
		},
		//更换导航栏中的行为图标
		jqGrid_updateActionIcons:function(table) {
			
			var replacement = 
			{
				'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
				'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
				'ui-icon-disk' : 'ace-icon fa fa-check green',
				'ui-icon-cancel' : 'ace-icon fa fa-times red'
			};
			$(table).find('.ui-pg-div span.ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			});
			
		},
		//更换选择框的样式
		jqGrid_styleCheckbox:function(table) {
			
			$(table).find('input:checkbox').addClass('ace')
			.wrap('<label />')
			.after('<span class="lbl align-top" />');
	
	
			$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
			.find('input.cbox[type=checkbox]').addClass('ace')
			.wrap('<label />').after('<span class="lbl align-top" />');
		
		},
		//jqGrid编辑框样式
		jqGrid_style_edit_form:function(form){
			//enable datepicker on "sdate" field and switches for "stock" field
			form.find('input[name=status]')
					.addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
	
			//update buttons classes
			var buttons = form.next().find('.EditButton .fm-button');
			buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
			buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
			buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>');
			
			buttons = form.next().find('.navButton a');
			buttons.find('.ui-icon').hide();
			buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
			buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');	
		},
		//jqGrid删除弹框样式
		jqGrid_style_delete_form:function(form){
			var buttons = form.next().find('.EditButton .fm-button');
			buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
			buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
			buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>');
		},
		//jqGrid过滤框样式
		jqGrid_style_search_filters:function(form){
			form.find('.delete-rule').val('X');
			form.find('.add-rule').addClass('btn btn-xs btn-primary');
			form.find('.add-group').addClass('btn btn-xs btn-success');
			form.find('.delete-group').addClass('btn btn-xs btn-danger');
		},
		//jqGrid查询弹框样式
		jqGrid_style_search_form:function(form){
			var dialog = form.closest('.ui-jqdialog');
			var buttons = dialog.find('.EditTable');
			buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
			buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
			buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
		},
		//chosen宽度自适应
		chosen_autoResize:function(){
			$(window)
			.off('resize.chosen')
			.on('resize.chosen', function() {
				$('.chosen-select').each(function() {
					 var $this = $(this);
					 $this.next().css({'width': $this.parent().width()});
				});
			}).trigger('resize.chosen');
		},
		//修改按钮动态样式，指向id为edit-modal-form 
		jqGrid_editbtnstr:'<div title="" style="float:left;cursor:pointer;margin-left:5px;" id="jqGrid_editbtnstr" href="#edit-modal-form" role="button" data-toggle="modal" class="ui-pg-div ui-inline-edit"  onmouseover="jQuery(this).addClass("ui-state-hover");" onmouseout="jQuery(this).removeClass("ui-state-hover")" data-original-title="修改当前行">'+
								'<span class="ui-icon ui-icon-pencil"></span>'+
						  '</div>',
		//删除按钮动态样式 
		jqGrid_delbtnstr: '<div title="" style="float:left;cursor:pointer;margin-left:5px;" id="jqGrid_delbtnstr" class="ui-pg-div ui-inline-del"  onmouseover="jQuery(this).addClass("ui-state-hover");" onmouseout="jQuery(this).removeClass("ui-state-hover");" data-original-title="删除当前行">'+
								'<span class="ui-icon ui-icon-trash"></span>'+
						  '</div>',
		//submit按钮动态样式 
		jqGrid_savebtnstr:'<div title="" style="float:left;cursor:pointer;margin-left:5px;" class="ui-pg-div ui-inline-save" id="jqGrid_savebtnstr" onmouseover="jQuery(this).addClass("ui-state-hover");" onmouseout="jQuery(this).removeClass("ui-state-hover");" data-original-title="保存修改">'+
								'<span class="ui-icon ace-icon fa fa-check green"></span>'+
						  '</div>',
		//cancel按钮动态样式			 
		jqGrid_cancelbtnstr:'<div title="" style="float:left;cursor:pointer;margin-left:5px;" class="ui-icon ace-icon fa fa-search-plus grey" id="jqGrid_cancelbtnstr"  onmouseover="jQuery(this).addClass("ui-state-hover");" onmouseout="jQuery(this).removeClass("ui-state-hover");" data-original-title="取消修改">'+
				  				'<span class="ui-icon ace-icon fa fa-times red"></span>'+
				  		    '</div>',
		//view按钮动态样式，指向id为view-modal-form 	  		    
		jqGrid_viewbtnstr:'<div title="" style="float:left;cursor:pointer;margin-left:5px;" class="ui-pg-div ui-inline-save" id="jqGrid_viewbtnstr" href="#view-modal-form" role="button" data-toggle="modal"  onmouseover="jQuery(this).addClass("ui-state-hover");" onmouseout="jQuery(this).removeClass("ui-state-hover");" data-original-title="查看详细">'+
					  			 '<span class="ui-icon ace-icon fa fa-search-plus grey"></span>'+
					  	  '</div>',
		//reflash按钮动态样式		  	  
		jqGrid_reflashbtnstr:'<div title="" style="float:left;cursor:pointer;margin-left:5px;" class="ui-pg-div ui-inline-del" id="jqGrid_reflashbtnstr"  onmouseover="jQuery(this).addClass("ui-state-hover");" onmouseout="jQuery(this).removeClass("ui-state-hover");" data-original-title="刷新数据">'+
								  '<span class="ui-icon ace-icon fa fa-refresh green"></span>'+
						     '</div>'
}; 


