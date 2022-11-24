<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body style="background-color: aqua">
<div class="loginForm">
    <h1 align="center">欢迎登录</h1>
    <form action="/demo1_war/Login" method="post" >
        <table border="0" align="center">
            <tr><td>用户名：</td><td><input class="input" id="username" name="username" type="text"></td></tr>
            <tr><td>密&nbsp码：</td><td><input id="password" name="password" type="password"></td></tr>
            <tr><td></td><td><a href="register.jsp"><input type="button" value="注册" ></a> <input type="submit" value="登陆"></td></tr>
        </table>
    </form>
</div>
</body>
</html>
