
(function() {
    "use strict";

    var Token = '',
        UserGuid = '',
        url = '',
        doc = document,
        pulltoRefrshObj = null;

    var width = document.body.clientWidth;

    Util.loadJs(
        // 下拉刷新
        'js/lib/widgets/minirefresh/minirefresh.css',
        'js/lib/widgets/minirefresh/minirefresh.js',
        'js/lib/widgets/minirefresh/minirefresh.bizlogic.js',
        'js/lib/widgets/gallery/gallery.js',
        function() {
            initPage();
        });

    function initPage() {
        ejs.oauth.getToken(function(result, msg, detail) {
            Token = result.token;
            Token = 'epointjweb4.0@RqSrxpBUY7feUVAihDNebnGbMeM=@MjE0NzQ4MzY0Nw==';
            initListener();
            gallery();
            videoNews()
        });
    }

    /*
     
     * @descritpion
     * */
    function initListener() {
        $("#videolist").on("tap", function() {
            ejs.page.openPage('pages/abouttaicang/videolist.html', '图片新闻', {
                //页面额外参数传递,json键值对方式
                // guid: id
            }, {
                //页面的配置参数
                'finishAfterOpen': '0'
            }, function(result, msg, detail) {

            }, function(detail) {

            });
        });

        $("#video-news-block").on("tap", "li", function() {
            ejs.page.openPage('pages/abouttaicang/newsdetail.html', '新闻详情', {
                //页面额外参数传递,json键值对方式
                guid: this.id
            }, {
                //页面的配置参数
                'finishAfterOpen': '0'
            }, function(result, msg, detail) {

            }, function(detail) {

            });
        })

        $("#icons").on("tap", "li", function() {
            ejs.page.openPage('pages/abouttaicang/newslist.html', '信息列表', {
                //页面额外参数传递,json键值对方式
                catenum: this.dataset.catenum
            }, {
                //页面的配置参数
                'finishAfterOpen': '0'
            }, function(result, msg, detail) {

            }, function(detail) {

            });
        })
    }

    function gallery() {
        var width = document.body.clientWidth,
            height = width / 1.8 + 'px',
            para = JSON.stringify({
                token: "epointjweb4.0@RqSrxpBUY7feUVAihDNebnGbMeM=@MjE0NzQ4MzY0Nw==",
                params: {
                    cateNum: '002002',
                    currentPageIndex: 0,
                    height: '',
                    infoType: '3',
                    pageSize: 4,
                    title: '',
                    width: ''
                }
            });

        new Gallery({
            container: '#gallery',
            url: Config.serverUrl + "getInfoList",

            maxImgHeight: height,
            minImgHeight: height,
            dataRequest: para,
            dataChange: function(result) {
                // console.log(JSON.stringify(result));
                var outdata = result.custom.infoList;

                var imgList = [];
                for (var i = 0, len = outdata.length; i < len; i++) {
                    //需要先处理img的url
                    var imgurlindex = outdata[i].imgUrlList[0].lastIndexOf('.');

                    var imgurlHead = outdata[i].imgUrlList[0].substring(0, imgurlindex - 1),
                        imgurlBottom = outdata[i].imgUrlList[0].substring(imgurlindex, outdata[i].imgUrlList[0].length),
                        imgUrl = Config.imgHeader + imgurlHead + imgurlBottom;
                    imgList.push({
                        guid: outdata[i].infoID,
                        title: outdata[i].title,
                        pic: imgUrl,
                        introduce: outdata[i].strComment

                    })
                }

                return imgList;
            },
            maxImgHeight: height,
            itemClick: function(e, id) {
                console.log("点击:" + id);
                ejs.page.openPage('pages/abouttaicang/newsdetail.html', '图片新闻', {
                    //页面额外参数传递,json键值对方式
                    guid: id
                }, {
                    //页面的配置参数
                    'finishAfterOpen': '0'
                }, function(result, msg, detail) {

                }, function(detail) {

                });
            }
        });

    }

    function videoNews() {
        // new MiniRefreshBiz({
        //     url: Config.serverUrl + 'getInfoList',
        //     dataRequest: function(currPage) {
        //         return JSON.stringify({
        //             token: Token,
        //             params: {
        //                 cateNum: "074003",
        //                 currentPageIndex: currPage,
        //                 height: "",
        //                 infoType: "0",
        //                 pageSize: 10,
        //                 title: "",
        //                 width: ""
        //             }
        //         });
        //     },
        //     template: "#video-temp",
        //     contentType: 'application/json',
        //     headers: {
        //         accept: '*/*'
        //     },
        //     itemClick: function(e) {
        //         console.log(this.id);


        //     }
        // });

        Util.ajax({
            url: Config.serverUrl + 'getInfoList',
            data: JSON.stringify({
                token: Token,
                params: {
                    cateNum: "002006",
                    currentPageIndex: 0,
                    height: "",
                    infoType: "0",
                    pageSize: 4,
                    title: "",
                    width: ""
                }
            }),
            dataPath: 'custom',

            contentType: 'application/json',
            headers: {
                accept: '*/*'
            },
            success: function(result) {
                // 返回的result.data直接就是 custom 节点下的数据，注意为null的情况，这时候没有这个节点数据
                console.log(JSON.stringify(result.data))

                var html = $("#video-temp").html(),
                    $html = Mustache.render(html, result.data);
                console.log($html);
                $("#listdata").append($html);
            }
        });

    }
}());