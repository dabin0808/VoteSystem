<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>在线投票系统——用户管理</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            if (${loginUser==null}) {
                $("#login_out").show();
                $("#login_in").hide();
            } else {
                $("#login_out").hide();
                $("#login_in").show();
            }

            var tds = $("td[name='status']");
            for(var i =0;i<tds.length;i++){
                var status = $(tds[i]).text();
                if(status==0){

                    $(tds[i]).text("普通用户");
                }else if(status==1){
                    var text = "<font color='blue'>高级用户</font>";
                    $(tds[i]).html(text);
                }else if(status==2){
                    var text = "<font color='red'>管理员</font>";
                    $(tds[i]).html(text);
                    var ele = $(tds[i]).next().children("a");
                    $(ele).css("cursor","default");
                    $(ele).attr("href","#");
                }
            }

            $("#delSelected").click(function () {
                if (confirm("您确定要删除选定条目吗？")) {
                    // 判定是否有选中条目
                    var flag = false;
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i<cbs.length; i++) {
                        if (cbs[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        document.getElementById("userForm").submit();
                    }
                }
            });

            $("#firstCb").click(function () {
                var cbs = document.getElementsByName("uid");
                for (var i=0; i<cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            });
        });

        function deleteUser(id) {
            if (confirm("您确定要删除吗？")) {
                location.href="${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
            }
        }
    </script>
</head>
<body>
<div id="header" class="header">
    <div class="container">
        <div class="header-logo">OnlineVotingSystem</div>
        <div class="header-options">
            <ul>
                <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
                <%--<li class="options-active"><a href="${pageContext.request.contextPath}/findUserByPageServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>--%>
                <li class="options-active"><a href="${pageContext.request.contextPath}/userManagerServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                <li><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
                <li><a href="participateVote.jsp"><span class="glyphicon glyphicon-send"></span> 参与投票</a></li>
                <li><a href="${pageContext.request.contextPath}/viewVoteServlet"><span class="glyphicon glyphicon-stats"></span> 查询投票</a></li>
            </ul>
        </div>
        <div class="login_out" id="login_out">
            <a href="register.jsp">注册</a>
            <a href="login.jsp">登录</a>
        </div>
        <div class="login_in" id="login_in">
            <a href="userInfo.jsp" title="个人中心">${loginUser.username}</a>
            <a href="${pageContext.request.contextPath}/existUserServlet">退出登录</a>
        </div>
    </div>
</div>

<div class="userManager-content container" style="width: 1000px;">
    <h3 style="text-align: center; margin-bottom: 30px;">用户管理</h3>
    <form action="user_findPage" method="post" class="form-inline">
        <div class="form-group">
            <label >用户名</label>


            <s:textfield name="conditions.username" class="form-control" ></s:textfield>
            <%--<input type="text" id="username" name="username" class="form-control" placeholder="名称查询" value="${condition.username[0]}" />--%>
        </div>
        <div class="form-group">
            <label >用户类型</label>
            <s:textfield name="conditions.version" class="form-control" title="用户类型" ></s:textfield>
            <%-- <input type="text" id="version" name="version" class="form-control" placeholder="用户类型查询 0或1" value="${condition.version[0]}" />--%>
        </div>
        <div class="form-group">
            <label  >用户角色</label>
            <s:textfield name="conditions.status" class="form-control" title="用户权限查询"></s:textfield>
            <%--<input type="text" id="status" name="status" class="form-control" placeholder="用户权限查询 0或1或2" value="${condition.status[0]}" />--%>
        </div>
        <button id="search_Content_submit" type="submit" class="btn btn-success" style="width: 135px;"><span class="glyphicon glyphicon-search"></span> 搜索</button>
    </form>
    <div style="float: right;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/register.jsp">添加用户</a>
        <%--<a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>--%>
    </div>
    <form id="userForm" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table class="table table-bordered table-hover">
            <tr class="success">
                <%--<th><input type="checkbox" id="firstCb" /></th>--%>
                <th>编号</th>
                <th>姓名</th>
                <th>用户类型</th>
                <th>用户权限</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${page.list}" var="user" varStatus="s">
                <tr>
                        <%--<th><input type="checkbox" name="uid" value="${user.userId}" /></th>--%>
                    <td>${s.count}</td>
                    <td>${user.username}</td>


                    <td>${user.version}</td>
                    <td name="status">${user.status}</td>
                    <td>
                        <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/user_findUser?userId=${user.userId}">修改</a>&nbsp;
                            <%--<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.userId});">删除</a>--%>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div style="text-align: center">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${page.currentPage == 1}">
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${userByPage.currentPage - 1}&rows=6" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${page.totalPage}" var="i">
                    <c:if test="${page.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=6">${i}</a></li>
                    </c:if>
                    <c:if test="${page.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=6">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${page.currentPage == page.totalPage}">
                    <li class="disabled">
                        <a href="#">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${page.currentPage != page.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${page.currentPage + 1}&rows=6">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 25px; margin-left: 8px;">共${page.totalCount}条记录，共${page.totalPage}页</span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
