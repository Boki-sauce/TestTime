<%--
  Created by IntelliJ IDEA.
  User: boki
  Date: 2023/8/12
  Time: 17:39
  To change this template use File | Settings | File Templates.

  todo  维修登记
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- 引入 Bootstrap -->
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入 font-awesome -->
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- 引入 datetimepicker -->
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/top.css">
    <title>宿舍管理系统</title>
</head>
<body>
<div class="container-fluid" style="background-image: url('img/4.jpg');min-height: 600px;background-attachment: fixed">
    <form method="post" action="/repair?method=save" class="form-horizontal" style="margin-top: 0px" role="form"
          id="form_data" style="margin: 20px;">
        <div role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">添加维修信息</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form">

                            <div class="form-group">
                                <label for="dormitory" class="col-sm-3 control-label">宿舍</label>
                                <div class="col-sm-9">
                                    <select id="dormitory" required class="form-control" name="dormitoryId">
                                        <c:forEach items="${dormitoryList}" var="dormitory">
                                            <option value="${dormitory.id}">${dormitory.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="student" class="col-sm-3 control-label">学生</label>
                                <div class="col-sm-9">
                                    <select id="student" required class="form-control" name="studentId">
                                        <c:forEach items="${studentList}" var="student">
                                            <option value="${student.id}">${student.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="instruction" class="col-sm-3 control-label">介绍</label>
                                <div class="col-sm-9">
                                    <input type="text" required class="form-control" name="instruction" id="instruction" placeholder="请输入维修介绍">
                                </div>
                            </div>


                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
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

<script>
    $(function(){

        $('#dormitory').change(function(){
            var id = $(this).val();
            $.ajax({
                url:"/student?method=findByDormitoryId&dormitoryId="+id,
                type:"post",
                dataType:"json",
                success:function (data) {
                    var str = '';
                    for(var i = 0;i<data.length;i++){
                        var student = $(data).get(i);
                        str += '<option value="'+student.id+'">'+student.name+'</option>'
                    }
                    $('#student').html(str);
                }
            })
        })



    })
</script>
</body>

</html>
