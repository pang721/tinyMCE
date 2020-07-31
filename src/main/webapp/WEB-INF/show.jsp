<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <title>展示内容界面</title>
    <script src="static/jquery-3.3.1.min.js"></script>
</head>
<body>
<h1>sdsd</h1>
    <table>
        <tr>
            <td>${Notice.title}</td>
            <td>${Notice.content}</td>
            <td>${Notice.publisher}</td>
            <td>${Notice.publish_time}</td>
        </tr>
    </table>
<hr>

<c:forEach items="${resultMap}" var="entry">
    <h1>${entry.key}</h1>
    <h1>${entry.value.content}</h1>
</c:forEach>

<div class="notice-list">
    <p class="mb10">公告</p>
    <ul class="notice-content"></ul>
</div>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12">
            <div class="notice-details-title">
                <p class="f20 tc fb mb10" id="title">标题</p>
                <p id="publisher"></p>
                <p id="publish_time"></p>
            </div>
            <div class="notice-details-content" style="padding: 0 2rem;word-break: break-word"></div>
        </div>
    </div>
</div>
<script>
    $(function () {

        //公告内容

            $.ajax({
                url:'find/1',
                type: 'get',
                data:{},
                dataType: 'json',
                success:function(res){
                    console.log(res.data.title);
                    if (res.code == "0"){
                        $("#title").text(res.data.title);
                        $("#publisher").text("发布者："+res.data.publisher);
                        $("#publish_time").text("发布时间："+res.data.publish_time);
                        $(".notice-details-content").html(res.data.content);
                    }
                },
                complete : function(){
                    /*if (iserror) {
                        layer.close(layerIndex);
                        layer.open({ shadeClose: false, content: '远程服务器没有响应', time: 6, btn: '确定' });
                    }*/
                }
            });


    })
</script>
</body>
</html>
