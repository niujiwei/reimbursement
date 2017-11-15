/**
 * Created by Administrator on 2017/11/14.
 */
layui.define(['element','navbar', 'tab'], function (exports) {
    var $ = layui.jquery,
        element=layui.element,
        navbar = layui.navbar(),
        tab;
    var Index = function () {
        this.config = {};
        this.version = '1.0.0';
    };
    Index.prototype.set = function (option) {
        var that = this;
        $.extend(true, that.config, option);
        return that;
    };

    Index.prototype.initIframe = function (option) {
        if (option) {
            var className = option.className;
            var height = option.height;
            //iframe自适应
            $(window).on('resize', function () {
                var $content = $(className);
                $content.height($(this).height() - height);
                $content.find('iframe').each(function () {
                    $(this).height($content.height());
                });
            }).resize();
        }
    };

    Index.prototype.initHeard = function () {
        $('.admin-side-toggle').on('click', function () {
            var sideWidth = $('#admin-side').width();
            if (sideWidth === 200) {
                $('#admin-body').animate({
                    left: '0'
                }); //admin-footer
                $('#admin-footer').animate({
                    left: '0'
                });
                $('#admin-side').animate({
                    width: '0'
                });
            } else {
                $('#admin-body').animate({
                    left: '200px'
                });
                $('#admin-footer').animate({
                    left: '200px'
                });
                $('#admin-side').animate({
                    width: '200px'
                });
            }
        });
        $('.admin-side-full').on('click', function () {
            var docElm = document.documentElement;
            //W3C
            if (docElm.requestFullscreen) {
                docElm.requestFullscreen();
            }
            //FireFox
            else if (docElm.mozRequestFullScreen) {
                docElm.mozRequestFullScreen();
            }
            //Chrome等
            else if (docElm.webkitRequestFullScreen) {
                docElm.webkitRequestFullScreen();
            }
            //IE11
            else if (elem.msRequestFullscreen) {
                elem.msRequestFullscreen();
            }
            layer.msg('按Esc即可退出全屏');
        });
    };

    Index.prototype.initTab = function () {
        tab = layui.tab({
            elem: '.admin-nav-card', //设置选项卡容器
            contextMenu: true,
            autoRefresh:true,
            onSwitch: function (data) {
                console.log(data.id); //当前Tab的Id
                console.log(data.index); //得到当前Tab的所在下标
                console.log(data.elem); //得到当前的Tab大容器
                console.log(tab.getCurrentTabId())
            },
            closeBefore: function (obj) { //tab 关闭之前触发的事件
                console.log(obj);
                return true;
            }
            //maxSetting: {
            //	max: 5,
            //	tipMsg: '只能开5个哇，不能再开了。真的。'
            //},
        });
    };

    Index.prototype.initNavbar =function () {
        var that = this;
        if(tab===undefined){
            tab= that.initTab();
        }
        //设置navbar
        navbar.set({
            spreadOne: true,
            elem: '#admin-navbar-side',
            cached: false,
            url: 'data/navbar.json'
            // data: navs
        });
        //渲染navbar
        navbar.render();
        //监听点击事件
        navbar.on('click(side)', function (data) {
            tab.tabAdd(data.field);
        });
    };

    var index = new Index();
    exports('index', function (option) {
        return index.set(option);
    });
});