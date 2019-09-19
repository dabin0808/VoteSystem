<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>在线投票系统——个人中心</title>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div id="header" class="header">
    <div class="container">
        <div class="header-logo">OnlineVotingSystem</div>
        <div class="header-options">
            <ul>
                <li class="options-active"><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a>
                </li>
                <li><a href="user_findPage"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                <li><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
                <li><a href="${pageContext.request.contextPath}/participateVoteServlet"><span
                        class="glyphicon glyphicon-send"></span> 参与投票</a></li>
                <li><a href="${pageContext.request.contextPath}/viewVoteServlet"><span
                        class="glyphicon glyphicon-stats"></span> 查询投票</a></li>
            </ul>
        </div>
        <div class="login_in" id="login_in">
            <a href="userInfo.jsp" title="个人中心">${loginUser.username}</a>
            <a href="user_logout">退出登录</a>
        </div>
    </div>
</div>

<div class="userInfo-content container" style="width: 400px;">

    <div class="row"><h2 style="text-align: center;">个人中心</h2></div>

    <s:if test="#session.loginUser.status==2">
        <div class="row">
            <a href="auth_list">权限审核</a>
        </div>
        <div class="row">
            <a href="${pageContext.request.contextPath}/maintainSelfVoteServlet">投票维护</a>
        </div>
    </s:if>
    <s:else>
        <div class="row">
            <s:if test="#session.loginUser.status==0">
                <a href="auth_applyAuth">申请权限</a>
            </s:if>
            <s:else>
                <a href="#">已获得权限</a>
            </s:else>

        </div>
        <div class="row">
            <a href="">查看个人发起投票</a>
        </div>
        <div class="row">
            <a href="">查看个人参与投票</a>
        </div>


        <div class="row">
            <a href="${pageContext.request.contextPath}/maintainSelfVoteServlet">投票维护</a>
        </div>

    </s:else>



</div>
</body>
</html>
