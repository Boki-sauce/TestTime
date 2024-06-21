<%--
  Created by IntelliJ IDEA.
  User: boki
  Date: 2023/8/16
  Time: 21:57
  To change this template use File | Settings | File Templates.
  todo   学生端个人信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
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
    <link rel="stylesheet" type="text/css" href="css/top.css">
    <title>宿舍管理系统</title>
</head>
<body>
<div class="container-fluid">
    <div class="row"style="background-image: url('img/4.jpg');min-height: 600px;background-attachment: fixed">
        <div class="col-sm-10">

            <!-- 列表展示-->
            <div style="margin-top: 0px;" class="table-responsive">
            <form method="post" action="/studentAdmin?method=init" class="form-horizontal" style="margin-top: 0px" role="form"
                  id="formdata" style="margin: 20px;">
                <div role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">

                                    <div class="modal-header">
                                        <h4 class="modal-title" >个人信息</h4>
                                    </div>

                                    <%--<div class="form-group">
                                        <label  for="ph" class="col-sm-3 control-label">图片</label>
                                        <div class="col-sm-9">
                                            <input type="file" required  name="ph" id=ph accept="image/*">
                                        </div>
                                    </div>--%>
                                    <c:forEach items="${list}" var="studentAdmin">
                                    <div class="modal-body">
                                        <form class="form-horizontal" role="form">

                                            <div class="form-group">
                                                <label  for="ph" class="col-sm-3 control-label">头像</label>
                                                <img src="upload/${studentAdmin.fileName}" id="ph" style="height: 85px;width: 90px;border-radius: 50%;margin-right: 20px;">
                                                <button type="button" class="btn btn-default "
                                                        data-toggle="modal"
                                                        data-target="#uploadUserModal" style="margin-right: 0px">
                                                    <i class="glyphicon glyphicon-save-file">修改</i>
                                                </button>
                                            </div>

                                            <div class="form-group">
                                                <label for="id" class="col-sm-3 control-label">ID</label>
                                                <div class="col-sm-9">
                                                    <input type="text" readonly required class="form-control" id="id"
                                                           name="id" placeholder="${studentAdmin.id}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label  class="col-sm-3 control-label">用户名</label>
                                                <div class="col-sm-9">
                                                    <input type="text" readonly required class="form-control" id="username"
                                                           name="username" placeholder="${studentAdmin.username}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label  class="col-sm-3 control-label">密码</label>
                                                <div class="col-sm-9">
                                                    <input type="text" readonly required class="form-control" id="password"
                                                           name="password" value="" placeholder="${studentAdmin.password}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label  class="col-sm-3 control-label">姓名</label>
                                                <div class="col-sm-9">
                                                    <input type="text" readonly required class="form-control" id="name"
                                                           name="name" value="" placeholder="${studentAdmin.name}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label  class="col-sm-3 control-label">性别</label>
                                                <div class="col-sm-9">
                                                    <input type="text" readonly required class="form-control" id="gender"
                                                           name="gender" value="" placeholder="${studentAdmin.gender}">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label  class="col-sm-3 control-label">联系电话</label>
                                                <div class="col-sm-9">
                                                    <input type="text" readonly required class="form-control" id="telephone"
                                                           name="telephone" value="" placeholder="${studentAdmin.telephone}">
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                    </c:forEach>

                            <div class="modal-footer">
                                <c:forEach items="${list}" var="studentAdmin">
                                    <button type="button" class="btn btn-default "
                                            data-id="${studentAdmin.id}"
                                            data-username="${studentAdmin.username}"
                                            data-password="${studentAdmin.password}"
                                            data-name="${studentAdmin.name}"
                                            data-gender="${studentAdmin.gender}"
                                            data-telephone="${studentAdmin.telephone}"
                                            data-toggle="modal"
                                            data-target="#updateUserModal">
                                        <i class="glyphicon glyphicon-save-file">修改</i>
                                    </button>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

                     <%--   upload 修改头像--%>
                <form method="post" action="/studentAdmin?method=upload" enctype="multipart/form-data" class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="uploadUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">x</button>
                                    <h4 class="modal-title" id="myModalLabel">修改头像</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">

                                        <div class="form-group">
                                            <label  for="photo" class="col-sm-3 control-label">图片</label>
                                            <div class="col-sm-9">
                                                <input type="file"   name="ph" id=photo accept="image/*">
                                            </div>
                                        </div>

                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <!-- update框示例（Modal） -->
                <form method="post" action="/studentAdmin?method=update"  class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="updateUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">x</button>
                                    <h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">

                                        <div class="form-group">
                                            <label for="id" class="col-sm-3 control-label">ID</label>
                                            <div class="col-sm-9">
                                                <input type="text" readonly required class="form-control" id="id"
                                                       name="id">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="username" class="col-sm-3 control-label">用户名</label>
                                            <div class="col-sm-9">
                                                <input type="text" required class="form-control" id="username"
                                                       name="username" placeholder="请输入用户名">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="password" class="col-sm-3 control-label">密码</label>
                                            <div class="col-sm-9">
                                                <input type="text" required class="form-control" id="password"
                                                       name="password" value="" placeholder="请输入密码">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="name" class="col-sm-3 control-label">姓名</label>
                                            <div class="col-sm-9">
                                                <input type="text" required class="form-control" id="name"
                                                       name="name" value="" placeholder="请输入姓名">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label  class="col-sm-3 control-label">性别</label>
                                            <div class="col-sm-9">
                                                <input type="radio" checked value="男" class="gender"
                                                       name="gender"> 男
                                                &nbsp;&nbsp;&nbsp;<input type="radio" value="女" class="gender"
                                                                         name="gender"> 女
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="telephone" class="col-sm-3 control-label">联系电话</label>
                                            <div class="col-sm-9">
                                                <input type="text" required class="form-control" id="telephone"
                                                       name="telephone" value="" placeholder="">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <%--<!-- 删除模态框示例（Modal） -->
                <form method="post" action="/studentAdmin?method=delete"
                      class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="delUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">用户信息</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <div class="col-sm-9">
                                                <h3 class="col-sm-18 control-label" id="deleteLabel">删除信息</h3>
                                                <input type="hidden" class="form-control" id="tab"
                                                       name="tab" placeholder="" value="dor_admin"> <input
                                                    type="hidden" class="form-control" id="id"
                                                    name="id" placeholder="">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="submit" class="btn btn-danger">删除</button>
                                    <span id="tip"> </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>--%>
            </div>
        </div>
    </div>
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
    $('#updateUserModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget)
        var id = button.data('id')
        var username = button.data('username')
        var password = button.data('password')
        var name = button.data('name')
        var gender = button.data('gender')
        var telephone = button.data('telephone')
        var modal = $(this)

        modal.find('.modal-title').text('修改个人信息')
        modal.find('#id').val(id)
        modal.find('#username').val(username)
        modal.find('#password').val(password)
        modal.find('#name').val(name)
        modal.find('#telephone').val(telephone)
        var list = modal.find('.gender')
        //alert(${path});
        for(var i=0;i<list.length;i++){
            if(gender == $(list.get(i)).val()){
                $(list.get(i)).prop('checked',true)
            }
        }
    })

    $('#delUserModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget)
        var id = button.data('id')
        var modal = $(this)
        modal.find('.modal-title').text('删除个人信息')
        modal.find('#deleteLabel').text('是否删除ID为  ' + id + ' 的信息')
        modal.find('#id').val(id)
    })
</script>

</body>

</html>