<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线投票系统——发起投票</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        function addItem(btn) {
            var $voteContent = $("<input type='text' name='voteContent' class='form-control' placeholder='输入选项内容' />");
            $(btn).before($voteContent);
        }

        function checkVoteTitle() {
            var length = $("#voteTitle").val().trim().length;
            if (length < 2) {
                $("#voteTitle").css("borderColor","red");
                return false;
            } else {
                $("#voteTitle").css("borderColor","#ccc");
                return true;
            }
        }
        function checkVoteContent() {
            var length = $("#voteContent").val().trim().length;
            if (length == 0) {
                $("#voteContent").css("borderColor","red");
                return false;
            }
            $("#voteContent").css("borderColor","#ccc");
            return true;
        }
        function checkVoteContent2() {
            var voteContentVal = $("#voteContent").val().trim();
            var voteContent2Val = $("#voteContent2").val().trim();
            if (voteContent2Val.length == 0 || voteContent2Val == voteContentVal) {
                $("#voteContent2").css("borderColor","red");
                return false;
            }
            $("#voteContent2").css("borderColor","#ccc");
            return true;
        }


        $(function () {
            if (${loginUser==null}) {
                $("#login_out").show();
                $("#login_in").hide();
            } else {
                $("#login_out").hide();
                $("#login_in").show();
            }

            $("#voteSend").click(function () {
                if (checkVoteTitle() && checkVoteContent() && checkVoteContent2()) {
                    $.post("pollVoteServlet",$("#voteSendForm").serialize(),function (data) {
                        alert("发布成功");
                        location.href = "index.jsp";
                    });
                }
                return false;
            });

            $("#voteTitle").blur(checkVoteTitle);
            $("#voteContent").blur(checkVoteContent);
            $("#voteContent2").blur(checkVoteContent2);
        });
    </script>
</head>
<body>
    <div id="header" class="header">
        <div class="container">
            <div class="header-logo">OnlineVotingSystem</div>
            <div class="header-options">
                <ul>
                    <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
                    <%--<li><a href="${pageContext.request.contextPath}/findUserByPageServlet"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>--%>
                    <li><a href="user_findPage"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                    <li class="options-active"><a href="pollVote.jsp"><span class="glyphicon glyphicon-pencil"></span> 发起投票</a></li>
                    <li><a href="${pageContext.request.contextPath}/participateVoteServlet"><span class="glyphicon glyphicon-send"></span> 参与投票</a></li>
                    <li><a href="${pageContext.request.contextPath}/viewVoteServlet"><span class="glyphicon glyphicon-stats"></span> 查询投票</a></li>
                </ul>
            </div>
            <div class="login_out" id="login_out">
                <a href="register.jsp">注册</a>
                <a href="login.jsp">登录</a>
            </div>
            <div class="login_in" id="login_in">
                <a href="userInfo.jsp" title="个人中心">${loginUser.username}</a>
                <a href="user_logout">退出登录</a>
            </div>
        </div>
    </div>

    <div class="pollVote-content container" style="width: 600px; margin-top: 20px; padding: 20px; border: 1px solid #2e9057; border-radius: 5px;">
        <form id="voteSendForm" action="" method="post">
            <input type="hidden" value="${loginUser.userId}" name="userId" />
            <div class="form-group">
                <label for="voteTitle">投票主题</label>
                <input type="text" name="voteTitle" id="voteTitle" class="form-control" placeholder="输入投票主题，2~200字之间" />
            </div>
            <div class="form-group">
                <label for="voteDiscription">投票主题描述</label>
                <textarea name="voteDiscription" id="voteDiscription" class="form-control" placeholder="输入投票描述（可不写）"></textarea>
            </div>
            <div class="form-group" id="voteContents">
                <label for="voteContent">投票选项</label>
                <input type="text" name="voteContent" id="voteContent" class="form-control" placeholder="输入选项内容" />
                <input type="text" name="voteContent" id="voteContent2" class="form-control" placeholder="输入选项内容" />
                <button onclick="addItem(this);" id="addVoteContent" class="form-control btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"></span> 添加选项</button>
            </div>
            <div class="form-group">
                <label>投票类型</label>
                <select class="form-control" name="select">
                    <option value="0" selected>单选</option>
                    <option value="1">多选</option>
                </select>
            </div>
            <div class="form-group">
                <div class="row">
                    <div class="col-md-6">
                        <label for="startTime">开始时间</label>
                        <input class="form-control" type="datetime-local" name="startTime" id="startTime" />
                    </div>
                    <div class="col-md-6">
                        <label for="endTime">结束时间</label>
                        <input type="datetime-local" id="endTime" class="form-control" name="endTime" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <button id="voteSend" class="btn btn-primary form-control" type="submit"><span class="glyphicon glyphicon-check"></span> 发布</button>
            </div>
        </form>
    </div>
</body>
</html>
