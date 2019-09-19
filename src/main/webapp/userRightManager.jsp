<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>在线投票系统——用户权限</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <%--<link rel="stylesheet" href="css/index.css" />--%>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
           $("#viewAllUser").click(function () {
               location.href = "user_findPage";
           });
            var tds = $("td[name='status']");

            for(var i =0;i<tds.length;i++){
                var status = $(tds[i]).text();

                if(status==0){
                    $(tds[i]).text("普通用户");
                }else if(status==1){
                    $(tds[i]).text("高级用户");
                }else if(status==2){
                    $(tds[i]).text("管理员");
                }
            }


        });


    </script>
</head>
<body>
    <div class="container" style="width: 600px; margin-top: 30px;">
        <table class="table table-bordered table-hover">
            <tr class="success">
                <td>申请权限者</td>
                <td>申请权限</td>
                <td>申请时间</td>
                <td>用户角色</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${authList}" var="item">
                <tr>
                    <td>${item.username}</td>
                    <td>
                        <a href="auth_updateAuth?authId=${item.authId}&userId=${item.userId}">修改权限</a>
                    </td>
                    <td >

                        ${item.createTime}
                    </td>
                    <td name="status">

                       ${item.status}

                    </td>
                    <td>
                        <a href="auth_updateAuth?authId=${item.authId}">通过申请</a>
                    </td>

                </tr>
            </c:forEach>
        </table>
        <div class="form-group">
            <button id="viewAllUser" type="submit" class="btn btn-success form-control">查看所有用户信息</button>
        </div>
    </div>
</body>

</html>
