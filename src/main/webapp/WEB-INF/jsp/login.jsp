<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<h2>登录页面</h2>
	<form id="form" action="doLogin">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" id="name" name="name" value="" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" id="password" name="password"
					value="" /></td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><button type="button" onclick="clickEvent()">登录</button></td>
			</tr>
		</table>
	</form>

	<script type="text/javascript" src="../js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		function clickEvent() {
			// 
			var name = $("#name").val();
			var address = $("#password").val();

			// url 
			var url = "http://localhost:8080/web-token/my/doLogin";
			// 
			$.ajax({
				type : "GET",
				url : url,
				data : "name=" + name + "&password=" + password,
				success : function(html) {
					if ("true" == html.flag) {
						$(location).attr('href', 'http://localhost:8080/web-token/my/index');
					}
				}
			});
		}
	</script>
</body>
</html>

