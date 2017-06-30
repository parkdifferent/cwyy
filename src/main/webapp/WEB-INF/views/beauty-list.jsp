<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="beautyList" title="美容项目列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/rest/beauty/beautyList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>

			<th data-options="field:'ck',checkbox:true"></th>

			<th data-options="field:'id',width:60,align:'center'">id</th>
			<th data-options="field:'name',width:120,align:'center'">项目名</th>
			<th data-options="field:'price',width:120,align:'center'">价格</th>
			<th data-options="field:'unit',width:180,align:'center'">单位</th>

            <th data-options="field:'created',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:150,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="beautyEditWindow" class="easyui-window" title="编辑美容项目" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/beauty-edit'" style="width:80%;height:80%;padding:10px;">
</div>

<div id="beautyAddWindow" class="easyui-window" title="宠物挂号" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/pet-add'" style="width:80%;height:80%;padding:10px;">

<script>

    function getSelectionsIds(){
    	var beautyList = $("#beautyList");
    	var sels = beautyList.datagrid("getSelections");
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
        	$(".tree-title:contains('新增美容项目')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个美容项目才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个美容项目!');
        		return ;
        	}
        	
        	$("#beautyEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#beautyList").datagrid("getSelections")[0];
        			//添加一个新的属性用于回显
        			data.priceView = TAOTAO.formatPrice(data.price);
        			$("#beautyEditForm").form("load",data);

        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中美容项目!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的美容项目吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};

                	/*$.post("/rest/beauty/delete",params, function(data){
            			if(data.statusCode == 200){
            				$.messager.alert('提示','删除美容项目成功!',undefined,function(){
            					$("#beautyList").datagrid("reload");
            				});
            			}
            		});*/

					$.ajax({
						type: "POST",
						url: "/rest/beauty/delete",
						data: params,
						success: function(){
							$.messager.alert('提示','删除美容项目成功!');
							$("#beautyList").datagrid("reload");
						}
					});


        	    }
        	});
        }
    }/*,'-',{
		text:'宠物挂号',
		iconCls:'icon-edit',
		handler:function(){
			var ids = getSelectionsIds();
			if(ids.length == 0){
				$.messager.alert('提示','必须选择一个美容项目才能进行宠物挂号!');
				return ;
			}
			if(ids.indexOf(',') > 0){
				$.messager.alert('提示','只能选择一个美容项目!');
				return ;
			}

			$("#beautyAddWindow").window({
				onLoad :function(){
					//回显数据
					var data = $("#beautyList").datagrid("getSelections")[0];
					data.hostId = data.id;
					$("#petAddForm").form("load",data);
					$("#petAddForm [name=hostId]").val(data.id);

				}
			}).window("open");
		}
	}*/];
</script>