/**
 * Created by feiwen8772 on 15/4/24.
 *                       _oo0oo_
 *                      o8888888o
 *                      88" . "88
 *                      (| -_- |)
 *                      0\  =  /0
 *                    ___/`---'\___
 *                  .' \\|     |// '.
 *                 / \\|||  :  |||// \
 *                / _||||| -:- |||||- \
 *               |   | \\\  -  /// |   |
 *               | \_|  ''\---/''  |_/ |
 *               \  .-\__  '-'  ___/-. /
 *             ___'. .'  /--.--\  `. .'___
 *          ."" '<  `.___\_<|>_/___.' >' "".
 *         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *         \  \ `_.   \_ __\ /__ _/   .-` /  /
 *     =====`-.____`.___ \_____/___.-`___.-'=====
 *                       `=---='
 *
 *
 *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *
 *               佛祖保佑         永无BUG
 *
 */
seajs.config({
    'alias': {
        'juicer':'res-build/res/module/juicer/juicer-min.js',
        'page':'res-build/res/module/ajaxpage/src/page.js',
        "tool":'res-build/src/tools.js'
    },
    'map': [
       [ /^(.*\.(?:css|js))(.*)$/i, '$1?t=20140327']
    ],
    base:"/"
});
(function () {
    var dev = true, //上线时,修改为false
        scripts = document.scripts,
        script = scripts[scripts.length - 1],
        boot = script.getAttribute('data-init'),
        dir = script.getAttribute('src');

    dir = dir.slice(0, dir.lastIndexOf('/') + 1);
    //dev
    if (dev) {
        if (location.href.indexOf('debug') === -1) {
            seajs.config({
                'map': [
                    [ /^(.*\.(?:css|js))(.*)$/i, '$1?t=' + (+new Date())]
                ]
            });
        }
        dir = dir + 'src/';
    } else {
        dir = dir + 'dist/';
    }

    /*
     * 上面获取路径脚本需要立刻执行
     * 将加载脚本放到domReady后执行,避免ie浏览器终止操作错误
     */
    $(function () {
        if (boot) {
            seajs.use(dir + boot);
        }

        //公共交互文件
        seajs.use(dir + 'public');
        /*//今日试用
        if (location.pathname == '/') {
            seajs.use('aside-trial/dist/index.js#');
        }*/
    });
})();
