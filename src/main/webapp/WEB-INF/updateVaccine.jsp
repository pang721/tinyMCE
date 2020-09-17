<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>xiugai</title>
    <script src="/static/jquery-3.3.1.min.js"></script>
</head>
<body>
<form action="/updateVaccine" method="get">
    <input name="id" type="hidden" value="${v.id}">
    疫苗类别
    <select id="ymlb" name="ymlb">
        <option value="1">成人疫苗</option>
        <option value="0">儿童疫苗</option>

    </select><br>
    疫苗名称<input name="ymmc"  type="text" value="${v.ymmc}"><br>
    接种月龄<input name="jzyl"  type="text" value="${v.jzyl}"><br>
    是否免费
    <select id="ymfy" name="ymfy">
        <option value="1">自费</option>
        <option value="0">免费</option>

    </select><br>
    剂次<input name="zc" type="text" value="${v.zc}"><br>
    接种效果<input name="jzxg" type="text" value="${v.jzxg}"><br>
    接种部位<input name="jzbw" type="text" value="${v.jzbw}"><br>
    接种禁忌<input name="jzjj" type="text" value="${v.jzjj}"><br>
    注意事项<input name="zysx" type="text" value="${v.zysx}"><br>
    可能反应<input name="knfy" type="text" value="${v.knfy}"><br>
    <button type="submit" >提交</button>
</form>
<script>
    $("#ymfy").val("${v.ymfy}");
    $("#ymlb").val("${v.ymlb}");
</script>
</body>
</html>
