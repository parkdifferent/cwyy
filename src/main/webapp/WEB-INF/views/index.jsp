<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宠物医院后台管理系统</title>
<jsp:include page="/commons/common-js.jsp"></jsp:include>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
</style>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<%--<li>
         		<span>商品管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/rest/page/item-add'}">新增商品</li>
	         		<li data-options="attributes:{'url':'/rest/page/item-list'}">查询商品</li>
	         		<li data-options="attributes:{'url':'/rest/page/item-param-list'}">规格参数</li>
	         	</ul>
         	</li>
         	<li>
         		<span>网站内容管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/rest/page/content-category'}">内容分类管理</li>
	         		<li data-options="attributes:{'url':'/rest/page/content'}">内容管理</li>
	         	</ul>
         	</li>--%>

			<%--<li>
				<span>用户管理</span>
				<ul>
					<li data-options="attributes:{'url':'/rest/page/user-add'}">新增用户</li>
					<li data-options="attributes:{'url':'/rest/page/user-list'}">查询用户</li>
				</ul>
			</li>

			<li>
				<span>宠物管理</span>
				<ul>
					&lt;%&ndash;<li data-options="attributes:{'url':'/rest/page/pet-add'}">新增宠物</li>&ndash;%&gt;
					<li data-options="attributes:{'url':'/rest/page/pet-list'}">查询宠物</li>
				</ul>
			</li>--%>

				<li>
					<span>员工管理</span>
					<ul>
						<li data-options="attributes:{'url':'/rest/page/admin-list'}">查询员工</li>
					</ul>
				</li>



				<li>
					<span>挂号管理</span>
					<ul>
						<li data-options="attributes:{'url':'/rest/page/user-add'}">主人挂号</li>
						<li data-options="attributes:{'url':'/rest/page/user-list'}">查询挂号信息</li>

						<li data-options="attributes:{'url':'/rest/page/pet-list'}">查询宠物</li>
					</ul>
				</li>

				<li>
					<span>美容项目管理</span>
					<ul>
						<li data-options="attributes:{'url':'/rest/page/beauty-add'}">新增美容项目</li>
						<li data-options="attributes:{'url':'/rest/page/beauty-list'}">查询美容项目</li>
					</ul>
				</li>


				<li>
					<span>宠物美容管理</span>
					<ul>
						<li data-options="attributes:{'url':'/rest/page/petBeauty-list'}">查询宠物美容</li>
					</ul>
				</li>

				<li>
					<span>宠物寄养管理</span>
					<ul>
						<li data-options="attributes:{'url':'/rest/page/petCare-list'}">查询宠物寄养</li>
					</ul>
				</li>




         </ul>



    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
		        	
		    </div>
		</div>
    </div>
    
<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){ //判断点击的是否为叶子节点
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
});
</script>
</body>
</html>