<%--
  Created by IntelliJ IDEA.
  User: boki
  Date: 2023/8/12
  Time: 10:24
  To change this template use File | Settings | File Templates.

  todo   报修管理
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
                    <form role="form" class="form-inline" action="/repair?method=search" method="post">
                        <div class="form-group">
                            <label for="key">字段：</label>
                            <select name="key" id="key" class="form-control">
                                <option value="studentName">学生</option>
                                <option value="dormitoryName">宿舍</option>
                                <option value="createDate">时间</option>
                                <option value="state">状态</option>
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
                        <th>学生</th>
                        <th>宿管</th>
                        <th>状态</th>
                        <th>介绍</th>
                        <th>报修时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="repair">
                        <tr>
                            <td>${repair.id}</td>
                            <td>${repair.dormitoryName}</td>
                            <td>${repair.studentNumber}</td>
                            <td>${repair.studentName}</td>
                            <td>${repair.dormitoryAdminName}</td>
                            <td>${repair.state}</td>
                            <td>${repair.instruction}</td>
                            <td>${repair.createDate}</td>
                            <td>

                                <div class="btn-group">
                                    <button type="button" class="btn btn-default "
                                            data-id="${repair.id}"
                                            data-dormitory-id="${repair.dormitoryId}"
                                            data-student-id="${repair.studentId}"
                                            data-dormitory-name="${repair.dormitoryName}"
                                            data-student-number="${repair.studentNumber}"
                                            data-student-name="${repair.studentName}"
                                            data-state="${repair.state}"
                                            data-instruction="${repair.instruction}"
                                            data-create-date="${repair.createDate}"
                                            data-toggle="modal"
                                            data-target="#updateUserModal">
                                        <i class="glyphicon glyphicon-save-file">修改</i>
                                    </button>

                                    <button type="button" class="btn btn-danger "
                                            data-id="${repair.id}"
                                            data-dormitory-id="${repair.dormitoryId}"
                                            data-toggle="modal"
                                            onclick="" data-target="#delUserModal">
                                        <i class="glyphicon glyphicon-trash">删除</i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <!-- update框示例（Modal） -->
                <form method="post" action="/repair?method=update" class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="updateUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">x</button>
                                    <h4 class="modal-title" id="myModalLabel">修改维修信息</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <label for="id" class="col-sm-3 control-label">ID</label>
                                            <div class="col-sm-9">
                                                <input type="text" readonly required class="form-control" id="id"
                                                       name="id">
                                                <input type="text" readonly required class="form-control" id="dormitoryId"
                                                       name="dormitoryId" style="display:none">
                                                <input type="text" readonly required class="form-control" id="studentId"
                                                       name="studentId" style="display:none">
                                            </div>
                                        </div>

                                       <%-- <div class="form-group" aria-hidden="true">
                                            <label for="user_id" class="col-sm-3 control-label">dormitoryID</label>
                                            <div class="col-sm-9">
                                                <input type="text" readonly required class="form-control" id="dormitoryId"
                                                       name="dormitoryId">
                                            </div>
                                        </div>--%>

                                        <%--<div class="form-group">
                                            <label for="user_id" class="col-sm-3 control-label">宿舍</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" name="dormitoryId" id="dormitory">
                                                    <c:forEach items="${dormitoryList}" var="dormitory">
                                                        <option class="dormitory" value="${dormitory.id}">${dormitory.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>--%>

                                        <div class="form-group">
                                            <label for="dormitoryName" class="col-sm-3 control-label">宿舍</label>
                                            <div class="col-sm-9">
                                                <input type="text" required readonly class="form-control" id="dormitoryName"
                                                       name="dormitoryName" value="" placeholder="">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="studentNumber" class="col-sm-3 control-label">学号</label>
                                            <div class="col-sm-9">
                                                <input type="text" required readonly class="form-control" id="studentNumber"
                                                       name="studentNumber" value="" placeholder="请输入学号">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="studentName" class="col-sm-3 control-label">姓名</label>
                                            <div class="col-sm-9">
                                                <input type="text" readonly required class="form-control" id="studentName"
                                                       name="studentName" value="" placeholder="请输入姓名">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="state" class="col-sm-3 control-label">状态</label>
                                            <%--<div class="col-sm-9">
                                                <input type="text" required class="form-control" id="state"
                                                       name="state" value="" placeholder="请输入状态">
                                            </div>--%>
                                            <div class="col-sm-9">
                                                <select class="form-control" name="state" id="state">
                                                    <option>未处理</option>
                                                    <option>已处理</option>
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
                                            <label for="createDate" class="col-sm-3 control-label">报修时间</label>
                                            <div class="col-sm-9">
                                                <input type="text" readonly class="form-control" id="createDate">
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
                <form method="post" action="/repair?method=delete"
                      class="form-horizontal" style="margin-top: 0px" role="form"
                      id="form_data" style="margin: 20px;">
                    <div class="modal fade" id="delUserModal" tabindex="-1"
                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"
                                            aria-hidden="true">×</button>
                                    <h4 class="modal-title" id="myModalLabel">删除维修信息</h4>
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

    $('#updateUserModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget)
        var id = button.data('id')
        var dormitoryId = button.data('dormitory-id')
        var studentId = button.data('student-id')
        var studentNumber = button.data('student-number')
        var studentName = button.data('student-name')
        var state = button.data('state')
        var instruction = button.data('instruction')
        var createDate = button.data('create-date')
        var dormitoryName = button.data('dormitory-name')
        var modal = $(this)

        modal.find('#id').val(id)
        modal.find('#studentNumber').val(studentNumber)
        modal.find('#studentName').val(studentName)
        modal.find('#state').val(state)
        modal.find('#instruction').val(instruction)
        modal.find('#dormitoryName').val(dormitoryName)
        modal.find('#createDate').val(createDate)
        modal.find('#dormitoryId').val(dormitoryId)
        modal.find('#studentId').val(studentId)
      //  alert(sutdentId);
        var list2 = modal.find('.dormitory')
        for(var i=0;i<list2.length;i++){
            if(dormitoryId == $(list2.get(i)).val()){
                $(list2.get(i)).prop('selected',true)
            }
        }
    })

    $('#delUserModal').on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget)
        var id = button.data('id')
        var dormitoryId = button.data('dormitory-id')
        var modal = $(this)
        modal.find('.modal-title').text('删除维修信息')
        modal.find('#deleteLabel').text('是否删除ID为  ' + id + ' 的信息')
        modal.find('#id').val(id)
        modal.find('#dormitoryId').val(dormitoryId)
    })

</script>

</body>

</html>