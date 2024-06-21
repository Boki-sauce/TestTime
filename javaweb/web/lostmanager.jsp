<%--
  Created by IntelliJ IDEA.
  User: boki
  Date: 2023/8/15
  Time: 16:58
  To change this template use File | Settings | File Templates.
  todo 遗失物品登记管理
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
    <link rel="stylesheet" type="text/css" href="css/top.css">
    <title>宿舍管理系统</title>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="background-image: url('img/4.jpg');min-height: 600px;background-attachment: fixed">
        <div class="col-sm-10">
            <!-- 顶部搜索部分 -->
            <div class="panel panel-default" style="background: #FFFFFF00">
                <div class="panel-heading" style="background: #FFFFFF00">搜索</div>
                <div class="panel-body">
                    <form role="form" class="form-inline" action="/lost?method=search" method="post">
                        <div class="form-group">
                            <label for="key">字段：</label>
                            <select name="key" id="key" class="form-control">
                                <option value="studentName">姓名</option>
                                <option value="dormitoryName">宿舍</option>
                                <option value="createDate">时间</option>
                            </select>
                        </div>
                        <div class="form-group" style="margin-left: 20px">
                            <label for="value">值：</label>
                            <input type="text" class="form-control" name="value" id="value" placeholder="字段值" maxlength="12" style="width: 130px">
                        </div>
                        <div class="form-group " style="margin-left: 20px">
                            <button type="submit" class="btn btn-info " style="background-color: rgba(218,215,20,0.38)">
										<span style="margin-right: 5px"
                                              class="glyphicon glyphicon-search" aria-hidden="true" >
                                            <strong style="color: #333333;font-family: 宋体 ;font-size: 18px;font-weight: 590">开始搜索</strong>
										</span>
                            </button>
                        </div>
                        <div class="form-group " style="margin-left: 48px">
                            <button style="background-color: #b0efde" type="button" class="btn btn-default" data-toggle="modal" data-target="#addUserModal">
										<span style="margin-right: 5px" class="" aria-hidden="true">
											<i class="fa fa-user-plus">添加物品信息</i>
											</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- 列表展示-->
            <div class="table-responsive">
                <table class="table table-hover ">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>宿舍</th>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>介绍</th>
                        <th>电话</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="lost">
                        <tr>
                            <td>${lost.id}</td>
                            <td>${lost.dormitoryName}</td>
                            <td>${lost.studentNumber}</td>
                            <td>${lost.studentName}</td>
                            <td>${lost.instruction}</td>
                            <td>${lost.telephone}</td>
                            <td>${lost.createDate}</td>
                            <td>
                                <div class="btn-group">

                                    <button type="button" class="btn btn-danger "
                                            data-id="${lost.id}"
                                            data-name="${lost.studentName}"
                                            data-toggle="modal"
                                            onclick="" data-target="#delUserModal">
                                        <i class="glyphicon glyphicon-trash">移除</i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- add框示例（Modal） -->
                <form method="post" action="/lost?method=save" class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="addUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">x</button>
                                    <h4 class="modal-title" id="myModalLabel">添加物品信息</h4>
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
                                                <input type="text" required class="form-control" id="instruction"
                                                       name="instruction" value="" placeholder="请输入介绍">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="telephone" class="col-sm-3 control-label">电话</label>
                                            <div class="col-sm-9">
                                                <input type="text" required class="form-control" id="telephone"
                                                       name="telephone" value="" placeholder="请输入电话">
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



                <!-- 删除模态框示例（Modal） -->
                <form method="post" action="/lost?method=delete"
                      class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="delUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">移除物品信息</h4>
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
                                                <input type="hidden" name="dormitoryId" id="dormitoryId"/>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                    <button type="submit" class="btn btn-danger">移除</button>
                                    <span id="tip"> </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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

    $('#delUserModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget)
        var id = button.data('id')
        var name = button.data('name')
        var modal = $(this)
        modal.find('#deleteLabel').text('确认移除ID为 '+id+'的信息')
        modal.find('#id').val(id)
        modal.find('#name').val(name)
    })
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