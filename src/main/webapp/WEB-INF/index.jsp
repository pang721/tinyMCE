<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>zds</h1><%--
<img src="static/1.jpg">
<img src="img/2.jpg">--%>
<%--<form method="post">
    <textarea id="mytextarea"></textarea>
</form>--%>
<textarea id="default">Hello, World!</textarea>
<script src="static/tinymce/tinymce.min.js"></script>
<!--初始化tinymce-->
<script type="text/javascript">
    /*tinymce.init({
        selector: '#mytextarea',
        language: 'zh_CN',
        directionality: 'rtl',
        browser_spellcheck: true,
        contextmenu: false,
        plugins: [
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime media table contextmenu paste imagetools wordcount",
            "code"
        ],
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | code",

    });*/
    tinymce.init({
        selector: 'textarea#default'
    });

    /*tinymce.activeEditor.uploadImages(function(success) {
        $.post('ajax/post.do', tinymce.activeEditor.getContent()).done(function() {
            console.log("Uploaded images and posted content as an ajax request.");
        });
    });*/
</script>
</body>
</html>
