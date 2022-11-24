<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body style="background-color: aqua">
<div class="loginForm">
    <h1 align="center">修改密码</h1>
    <form action="/demo1_war/info" method="post" >
        <table border="0" align="center">
            <tr><td>用户名：</td><td><input class="input" id="username" name="username" type="text"></td></tr>
            <tr><td>原密&nbsp码：</td><td><input id="password" name="password" type="password"></td></tr>
            <tr><td>新密&nbsp码：</td><td><input id="npassword" name="npassword" type="password"></td></tr>
            <tr><td></td><td><input type="submit" value="修改"></td></tr>
        </table>
    </form>
</div>
</body>
</html>
