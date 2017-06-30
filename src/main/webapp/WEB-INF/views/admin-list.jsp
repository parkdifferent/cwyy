<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="adminList" title="员工信息"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/rest/admin/adminList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>

			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:60,align:'center'">id</th>
			<th data-options="field:'userName',width:120,align:'center'">用户名</th>
			<th data-options="field:'trueName',width:120,align:'center'">真实姓名</th>
			<th data-options="field:'gender',width:120, align:'center',formatter:TAOTAO.formatAdminStatus">性别</th>
			<th data-options="field:'position',width:120,align:'center'">职位</th>
			<th data-options="field:'salary',width:120,align:'center'">收入</th>
			<th data-options="field:'phoneNumber',width:180,align:'center'">电话号码</th>
			<th data-options="field:'entryTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">入职日期</th>
            <%--<th data-options="field:'created',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:150,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>--%>
        </tr>
    </thead>
</table>
<div id="adminEditWindow" class="easyui-window" title="编辑员工" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/admin-edit'" style="width:80%;height:80%;padding:10px;">
</div>


<script>

    function getSelectionsIds(){
    	var adminList = $("#adminList");
    	var sels = adminList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [/*{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增员工')").parent().click();
        }
    },*//*{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一只员工才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一只员工!');
        		return ;
        	}
        	
        	$("#adminEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#adminList").datagrid("getSelections")[0];
        			//添加一个新的属性用于回显
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#adminEditForm").form("load",data);

        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中员工!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的员工吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};

                	/!*$.post("/rest/user/delete",params, function(data){
            			if(data.statusCode == 200){
            				$.messager.alert('提示','删除用户成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});*!/

					$.ajax({
						type: "POST",
						url: "/rest/admin/delete",
						data: params,
						success: function(){
							$.messager.alert('提示','删除员工成功!');
							$("#adminList").datagrid("reload");
						}
					});


        	    }
        	});
        }
    }*/];
</script>