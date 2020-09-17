<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <title>展示内容界面</title>
    <script src="static/jquery-3.3.1.min.js"></script>
</head>
<body>
<input id="zpmc">
<button value="保存" onclick="saveorupdate()"></button>
<script>

    function saveorupdate(){
        //公告内容
        var zpmc=$("#zpmc").val();
        $.ajax({
            url:'/finds',
            type: 'get',
            data:{zpmc:zpmc},
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

    }



</script>
</body>
</html>
