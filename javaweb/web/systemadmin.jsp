<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--   todo 管理员 和 登陆后的首页 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <!-- 引入 Bootstrap -->
  <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <!-- 引入 font-awesome -->
  <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="css/top.css">
    <title>宿舍管理系统-宿管端</title>
  <script type="application/javascript">
    function change(url,index){
      $(".list-group-item").removeClass("active");
      $(".list-group-item").eq(index).addClass("active");
      $("iframe").attr("src",url);
    }
  </script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <ul class="nav navbar-nav navbar-left">
      <li>
        <a style="font-size: 28px; font-family:仿宋;">LQ宿舍管理系统-宿管端</a>
      </li>
    </ul>
    <span style="color: #CCCCCC;font-size: 26px;position: relative;top: 5px;"></span>
    <ul class="nav navbar-nav navbar-right">
      <li>
        <a  style="font-family: '微软雅黑 Light' ; " >欢迎您,${dormitoryAdmin.name}</a>
      </li>
      <li>
        <a style="font-family: '微软雅黑 Light' ;" href="/account?method=logout" >
            <i class="glyphicon glyphicon-off"></i>
            安全退出
        </a>
      </li>
    </ul>
  </div>
</nav>
<div class="container-fluid">
  <div class="row" >
    <div class="col-sm-2">

      <a href="javascript:void(0)" class="list-group-item active" onclick="change('/dormitoryAdmin?method=init',0)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-user"></i>
						</span>个人信息
      </a>

      <a href="javascript:void(0)" class="list-group-item" onclick="change('/student?method=list',1)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-education"></i>
						</span>学生管理
      </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('/building?method=list',2)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-home"></i>
						</span>楼宇管理
      </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('/dormitory?method=list',3)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-bed"></i>
						</span>宿舍管理
      </a>


      <a href="javascript:void(0)" class="list-group-item" onclick="change('/moveout?method=list',4)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-log-out"></i>
						</span>学生迁出登记
      </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('/moveout?method=record',5)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-pencil"></i>
						</span>学生迁出记录
      </a>
<%--        修改--%>

        <a href="javascript:void(0)" class="list-group-item" onclick="change('/absent?method=init',6)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-edit"></i>
						</span>学生缺寝登记
        </a>

<%--     ===   --%>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('/absent?method=list',7)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-check"></i>
						</span>学生缺寝记录
      </a>

        <a href="javascript:void(0)" class="list-group-item" onclick="change('/repair?method=init',8)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-tag"></i>
						</span>宿舍报修登记
        </a>

        <a href="javascript:void(0)" class="list-group-item" onclick="change('/repair?method=list',9)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-wrench"></i>
						</span>宿舍报修管理
        </a>

        <a href="javascript:void(0)" class="list-group-item" onclick="change('/visitor?method=list',10)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-plus-sign"></i>
						</span>来访人员登记管理
        </a>

        <a href="javascript:void(0)" class="list-group-item" onclick="change('/visitor?method=init',11)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-minus-sign"></i>
						</span>离开人员查询
        </a>

        <a href="javascript:void(0)" class="list-group-item" onclick="change('/lost?method=list',12)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-book"></i>
						</span>物品遗失管理
        </a>

        <c:if test="${dormitoryAdmin.id=='1'}">
            <a href="javascript:void(0)" class="list-group-item " onclick="change('/dormitoryAdmin?method=list',13)">
						<span class="" aria-hidden="true">
							<i class="glyphicon glyphicon-credit-card"></i>
						</span>宿管管理
            </a>
        </c:if>
    </div>
    <!--右边内容-->
    <iframe style="width: 81%; height: 600px; border: 0px;" src="/dormitoryAdmin?method=init"></iframe>
  </div>
</div>
<div class="footer">
  <p class="text-center">
    2023 © LQNB @ 666
  </p>
</div>
<div id="gotop"><a class="gotop"></a></div>
<script>
    $("#gotop").hide();
    $(window).scroll(function () {
        if ($(window).scrollTop() > 100) {
            $("#gotop").fadeIn()
        } else {
            $("#gotop").fadeOut()
        }
    });
    $("#gotop").click(function () {
        $('html,body').animate({
            'scrollTop': 0
        }, 500)
    });
</script>

</body>
</html>
