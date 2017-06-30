<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/rest/user/userList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>

			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:60,align:'center'">用户ID</th>
			<th data-options="field:'userName',width:120,align:'center'">用户名</th>
			<th data-options="field:'phone',width:120,align:'center'">电话</th>
			<th data-options="field:'email',width:180,align:'center'">邮箱</th>

            <th data-options="field:'created',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:150,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑用户" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/user-edit'" style="width:80%;height:80%;padding:10px;">
</div>

<div id="itemAddWindow" class="easyui-window" title="宠物挂号" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/pet-add'" style="width:80%;height:80%;padding:10px;">

<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('主人挂号')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个用户才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个用户!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			//添加一个新的属性用于回显
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#itemeEditForm").form("load",data);

        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};

                	/*$.post("/rest/user/delete",params, function(data){
            			if(data.statusCode == 200){
            				$.messager.alert('提示','删除用户成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});*/

					$.ajax({
						type: "POST",
						url: "/rest/user/delete",
						data: params,
						success: function(){
							$.messager.alert('提示','删除用户成功!');
							$("#itemList").datagrid("reload");
						}
					});


        	    }
        	});
        }
    },'-',{
		text:'宠物挂号',
		iconCls:'icon-edit',
		handler:function(){
			var ids = getSelectionsIds();
			if(ids.length == 0){
				$.messager.alert('提示','必须选择一个用户才能进行宠物挂号!');
				return ;
			}
			if(ids.indexOf(',') > 0){
				$.messager.alert('提示','只能选择一个用户!');
				return ;
			}

			$("#itemAddWindow").window({
				onLoad :function(){
					//回显数据
					var data = $("#itemList").datagrid("getSelections")[0];
					data.hostId = data.id;
					$("#petAddForm").form("load",data);
					$("#petAddForm [name=hostId]").val(data.id);

				}
			}).window("open");
		}
	}];
</script>