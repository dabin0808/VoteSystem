<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>修改用户信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改用户信息</h3>
    <form action="user_update" method="post">
        <%--隐藏域，提交id--%>
        <input type="hidden" name="userId" value="${model.userId}"/>
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" class="form-control" id="username" name="username" value="${model.username}"
                   readonly="readonly"/>
        </div>

        <div class="form-group">
            <label for="password">用户密码：</label>
            <input type="password" class="form-control" id="password" name="password" value="${model.password}"/>
        </div>

        <div class="form-group">
            <label for="version">用户类型：</label>
            <select name="status" class="form-control" id="version">

                    <option value="0" selected>普通用户</option>
                    <option value="1">高级用户</option>






            </select>
        </div>

       <%-- <div class="form-group">
            <label for="status">用户权限：</label>
            <select name="status" class="form-control" id="status">
                <c:if test="${model.status == 0}">
                    <option value="0" selected>没有权限</option>
                    <option value="1">部分权限</option>
                    <option value="2">所有权限</option>
                </c:if>
                <c:if test="${model.status == 1}">
                    <option value="0">没有权限</option>
                    <option value="1" selected>部分权限</option>
                    <option value="2">所有权限</option>
                </c:if>
                <c:if test="${model.status == 2}">
                    <option value="0">没有权限</option>
                    <option value="1">部分权限</option>
                    <option value="2" selected>所有权限</option>
                </c:if>
            </select>
        </div>--%>

        <div class="form-group">
            <button type="submit" class="btn btn-success form-control">提交</button>
        </div>
    </form>
</div>
</body>
</html>
