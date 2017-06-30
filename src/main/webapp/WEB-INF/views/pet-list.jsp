<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<table class="easyui-datagrid" id="petList" title="宠物列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/rest/pet/petList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>

			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:60,align:'center'">id</th>
			<th data-options="field:'hostId',width:60,align:'center'">主人id</th>
			<th data-options="field:'nickName',width:120,align:'center'">昵称</th>
			<th data-options="field:'gender',width:120, align:'center',formatter:TAOTAO.formatPetStatus">性别</th>
			<th data-options="field:'age',width:120,align:'center'">年龄</th>
			<th data-options="field:'type',width:120,align:'center'">类型</th>
			<th data-options="field:'veriety',width:180,align:'center'">品种</th>

            <th data-options="field:'created',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:150,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="petBeautyWindow" class="easyui-window" title="宠物美容" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/pet-beauty'" style="width:80%;height:80%;padding:10px;">
</div>

<div id="petEditWindow" class="easyui-window" title="编辑宠物" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/pet-edit'" style="width:80%;height:80%;padding:10px;">
</div>


<div id="petCareWindow" class="easyui-window" title="宠物寄养" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/pet-care'" style="width:80%;height:80%;padding:10px;">
</div>


<script>

    function getSelectionsIds(){
    	var petList = $("#petList");
    	var sels = petList.datagrid("getSelections");
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
        	$(".tree-title:contains('新增宠物')").parent().click();
        }
    },*/{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一只宠物才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一只宠物!');
        		return ;
        	}
        	
        	$("#petEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#petList").datagrid("getSelections")[0];
        			//添加一个新的属性用于回显
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#petEditForm").form("load",data);

        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中宠物!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的宠物吗？',function(r){
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
						url: "/rest/pet/delete",
						data: params,
						success: function(){
							$.messager.alert('提示','删除宠物成功!');
							$("#petList").datagrid("reload");

							$("#petBeautyList").datagrid("reload");
						}
					});


        	    }
        	});
        }
    },'-',{
	 text:'宠物美容',
	 iconCls:'icon-edit',
	 handler:function(){
	 var ids = getSelectionsIds();
	 if(ids.length == 0){
	 $.messager.alert('提示','必须选择一只宠物!');
	 return ;
	 }
	 if(ids.indexOf(',') > 0){
	 $.messager.alert('提示','只能选择一只宠物!');
	 return ;
	 }

	 $("#petBeautyWindow").window({
	 onLoad :function(){
	 //回显数据
	 var data = $("#petList").datagrid("getSelections")[0];
	 data.petId = data.id;
	 data.hostId	= data.hostId;
	 $("#petBeautyForm").form("load",data);
	 $("#petBeautyForm [name=petId]").val(data.petId);
		 $("#petBeautyForm [name=hostId]").val(data.hostId);
	 }
	 }).window("open");
	 }
	 },{
		text:'宠物寄养',
		iconCls:'icon-edit',
		handler:function(){
			var ids = getSelectionsIds();
			if(ids.length == 0){
				$.messager.alert('提示','必须选择一只宠物!');
				return ;
			}
			if(ids.indexOf(',') > 0){
				$.messager.alert('提示','只能选择一只宠物!');
				return ;
			}

			$("#petCareWindow").window({
				onLoad :function(){
					//回显数据
					var data = $("#petList").datagrid("getSelections")[0];
					data.petId = data.id;
					data.hostId	= data.hostId;
					$("#petCareForm").form("load",data);
					$("#petCareForm [name=petId]").val(data.petId);
					$("#petCareForm [name=hostId]").val(data.hostId);
				}
			}).window("open");
		}
	}];
</script>