<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="static/tinymce/tinymce.min.js"></script>
   <%-- <script src="static/jquery-3.3.1.min.js"></script>--%>
   <%-- <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>--%>

    <script>
        tinymce.init({
            selector: '#mytextarea',
            language: 'zh_CN',
           // automatic_uploads: true,
            images_upload_url:'/upload',
            images_upload_base_path: '/',
            //images_reuse_filename: true,
            plugins: 'print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template code codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists wordcount imagetools textpattern help emoticons autosave autoresize',
            toolbar: 'code undo redo restoredraft | cut copy paste pastetext | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | styleselect formatselect fontselect fontsizeselect | bullist numlist | blockquote subscript superscript removeformat | table image media charmap emoticons hr pagebreak insertdatetime print preview | fullscreen',

            /*video_template_callback:function(data){
                return '<span class="mce-preview-object mce-object-video" contenteditable="false" data-mce-object="video"data-mce-p-allowfullscreen="allowfullscreen"data-mce-p-frameborder="no" data-mce-p-scrolling="no" data-mce-p-src='+data.source+' data-mce-p-width='+data.width+' data-mce-p-height='+data.height+' data-mce-p-controls="controls" data-mce-html="%20"> <video width='+data.width+' height='+data.height+' controls="controls"> <source src='+data.source+' type='+data.sourcemime+'></source> </video> </span>';
            },*/

                file_picker_callback: function (callback, value, meta) {
                    //文件分类
                    var filetype='.pdf, .txt, .zip, .rar, .7z, .doc, .docx, .xls, .xlsx, .ppt, .pptx, .mp3, .mp4';
                    //后端接收上传文件的地址
                    var upurl='/uploadFile';
                    //为不同插件指定文件类型及后端地址
                    switch(meta.filetype){
                        case 'image':
                            filetype='.jpg, .jpeg, .png, .gif';
                            upurl='/upload';
                            break;
                        case 'media':
                            filetype='.mp3, .mp4';
                            upurl='/uploadFile';
                            break;
                        case 'file':
                        default:
                    }
                    //模拟出一个input用于添加本地文件
                    var input = document.createElement('input');
                    input.setAttribute('type', 'file');
                    input.setAttribute('accept', filetype);
                    input.click();
                    input.onchange = function() {
                        var file = this.files[0];

                        var xhr, formData;
                        console.log(file.name);
                        console.log(file);
                        xhr = new XMLHttpRequest();
                        xhr.withCredentials = false;
                        xhr.open('POST', upurl);
                        xhr.onload = function() {
                            var json;
                            if (xhr.status != 200) {
                                failure('HTTP Error: ' + xhr.status);
                                return;
                            }
                            json = JSON.parse(xhr.responseText);
                            if (!json || typeof json.location != 'string') {
                                failure('Invalid JSON: ' + xhr.responseText);
                                return;
                            }
                            console.log(json);
                            callback(json.location);
                        };
                        formData = new FormData();
                        formData.append('file', file, file.name );
                        xhr.send(formData);
                    };
                },
        });
        function get_editor_content() {
            // Get the HTML contents of the currently active editor
            //console.debug(tinyMCE.activeEditor.getContent());
            //method1 getting the content of the active editor
           // alert(tinyMCE.activeEditor.getContent());
            //method2 getting the content by id of a particular textarea
           // alert(tinyMCE.get('mytextarea').getContent());
            $("#content").html(tinyMCE.activeEditor.getContent());
        }





    </script>

</head>

<body>
<h1>TinyMCE Quick Start Guide</h1>
<%--<form method="post">--%>
    <textarea id="mytextarea"></textarea>
<%--</form>--%>
<button onclick="get_editor_content()">Get content</button>
<div id="content">

</div>

</body>
</html>

