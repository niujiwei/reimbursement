/**
 * Created by niujiwei on 2017/11/14.
 */
layui.config({
    dir: '/plugin/layer/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
    , version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
    , debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
    , base: '/js/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
}).use(['element', 'layer', 'index' ], function () {
    var index = layui.index();
    //初始化Iframe
    index.initIframe({
        className: '.admin-nav-card .layui-tab-content',
        height: 147
    });
    //初始化头部左侧按钮
    index.initHeard();
    //初始化tab页面
    index.initTab();
    //初始化导航栏
    index.initNavbar();



});