tinymce.init({
    selector: '#mytextarea',
    language: 'zh_CN',
    convert_urls: false,
    // automatic_uploads: true,
    images_upload_url:'/upload',
    images_upload_base_path: '/',

    //images_reuse_filename: true,
    plugins: 'print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template code codesample table charmap hr pagebreak nonbreaking anchor insertdatetime advlist lists wordcount imagetools textpattern help emoticons autosave autoresize',
    toolbar: 'code undo redo restoredraft | forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent | styleselect formatselect fontselect fontsizeselect | bullist numlist | blockquote subscript superscript removeformat | table image media charmap emoticons hr pagebreak insertdatetime print preview | fullscreen',
    toolbar_mode: 'wrap',
    media_live_embeds:true, //能够预览内嵌代码视频
    extended_valid_elements :'video[src|width]',
    //extended_valid_elements :'iframe[src|style|width|height|scrolling|marginwidth|marginheight|frameborder]',
  /*  video_template_callback:function(data){
        return '<span class="mce-preview-object mce-object-video" contenteditable="false" data-mce-object="video"data-mce-p-allowfullscreen="allowfullscreen"data-mce-p-frameborder="no" data-mce-p-scrolling="no" data-mce-p-src='+data.source+' data-mce-p-width='+data.width+' data-mce-p-height='+data.height+' data-mce-p-controls="controls" data-mce-html="%20"> <video width='+data.width+' height='+data.height+' controls="controls"> <source src='+data.source+' type='+data.sourcemime+'></source> </video> </span>';
    },*/
    video_template_callback:function(data){
        console.log(123);
        return ' <object width='+data.width+' height='+data.height+' src='+data.source+'  scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true" allowtransparency="true"> <source src='+data.source+' type='+data.sourcemime+'></source> </object> ';
        //return ' <iframe width='+data.width+' height='+data.height+' src='+data.source+'  scrolling="no" border="0" frameborder="no" framespacing="0" allowfullscreen="true" allowtransparency="true"> <source src='+data.source+' type='+data.sourcemime+'></source> </iframe> ';
    },
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
        input.onchange = function() {
            var file = this.files[0];
            var xhr, formData;
            xhr = new XMLHttpRequest();
            xhr.withCredentials = false;
            xhr.open('POST', upurl,true);
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
                callback(json.location);
            };
            formData = new FormData();
            formData.append('file', file, file.name );
            xhr.send(formData);
        };
        input.click();
    },



   /* init_instance_callback : function(editor) {
        console.log('Editor: ' + editor.id + ' is now initialized.');
        console.log(editor);
        console.log(self);
        if (self.value) {
            editor.setContent(self.value);
        }
        editor.on('input change undo redo execCommand KeyUp', function(e) {
            console.log(self.tinymce);
                var iframe = document.getElementById(self.editorSelectorId + '_ifr');
                console.log(iframe+"-sign");
               // var content = iframe.contentWindow.document.getElementById('mytextarea').innerHTML;
               // var contentText = iframe.contentWindow.document.getElementById('mytextarea').innerText;
               // if(!contentText.trim() && content.indexOf('img') == -1 && content.indexOf('video') == -1){
                  //  content = '';
              //  }
                self.$emit('change', content);
        })
    },
*/

   /* media_url_resolver: function(data, resolve) {
        try {
            var videoUri = encodeURI(data.url);
            var embedHtml = '<p> <span class = "mce-object mce-object-video" data-mce-selected="1" data-mce-object="video" data-mce-p-width="100%" data-mce-p-height="auto"data-mce-p-controls="controls"data-mce-p-controlslist="nodownload"data-mce-p-allowfullscreen="true"data-mce-p-src=${videoUri} > <video src=${data.url} width="100%" height="auto" controls="controls" controlslist="nodownload"> </video> </span> </p> <p style="text-align: left;"></p>';
            resolve({ html: embedHtml });
        } catch (e) {
            resolve({ html: "" });
        }
    }*/

})