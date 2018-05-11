
(function() {
    "use strict";

    var Token = '',
        UserGuid = '',
        url = '',
        $ = '',
        doc = document,
        pulltoRefrshObj = null;

    var topPover = doc.getElementById('topPopover');

    Util.loadJs(
        // 下拉刷新
        'js/lib/widgets/pulltorefresh/pulltorefresh.skin.css',
        'js/lib/widgets/pulltorefresh/pulltorefresh.skin.type2.js',
        'js/lib/widgets/pulltorefresh/pulltorefresh.bizlogic.impl.js',
        'js/lib/widgets/mui.poppicker/mui.poppicker.js',
        'js/lib/widgets/mui.picker/mui.picker.js',
        function() {
            //test();
            getEJS();
        });

    function test() {
        UserGuid = 'a0d77a9534e621b301355588e3a80118';
    }

    function getEJS() {
        ejs.sql.getConfigValue('MOA_KEY_UserGuid', function(result, msg, detail) {
            UserGuid = result.value;
            ejs.oauth.getToken(function(result, msg, detail) {
                //Token = result.token;
                Token = 'jt@epointoa@MTQ4ODc3MDY3Ng==';
            });
        });
    }
 

}());