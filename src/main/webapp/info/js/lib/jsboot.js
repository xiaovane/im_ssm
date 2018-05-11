
(function() {
    var arr = [];

    var paths = {
        // base层会最先加载，作为框架的基石，base层之后是ejs层再然后才是core层
        base: [
            'js/lib/libs/flexible_css.debug.js',
            'js/lib/libs/flexible.debug.js',
            'js/lib/libs/promise.js',
            'js/lib/mui/mui.js',
            'js/lib/libs/zepto.min.js',
            'js/lib/libs/mustache.min.js'
        ],
        // core层会在base层和ejs层加载完毕后再加载
        core: [
            'js/lib/common/common.js',
            'js/lib/common/common.ajax.js',
            'js/lib/common/common.ajax.upload.js',
            // 这个会对 ajax 和 upload都进行一次数据请求的代理
            'js/lib/common/common.ajax.dataprocess.js',
            'js/lib/common/common.clazz.js',
            'js/lib/common/common.dataprocess.js',
            'js/lib/common/common.dataprocess.v6path.js',
            'js/lib/common/common.dataprocess.v7path.js',
            'js/lib/common/common.loadjs.js',
            'js/lib/common/common.os.js',
            'js/lib/common/common.path.js',
            'js/lib/common/common.input.js',
            'js/lib/common/common.ejs.webenv.js',
            // 依赖了下面的 ejs 类库，以3.x的用法 来兼容部分2.x的 api
            'js/lib/common/common.ejs.compatible.js',
            'js/lib/common/common.token.compatible.js'
        ],
        ejsv2: {
            base: [
                'js/lib/ejs/v2/epoint.moapi.v2.js'
            ],
            h5: [
                'js/lib/ejs/v2/epoint.moapi.v2.h5mui.js'
            ],
            dd: [
                'js/lib/ejs/dingtalk.js',
                'js/lib/ejs/v2/epoint.moapi.v2.dd.js'
            ]
        },
        ejsv3: {
            base: [
                'js/lib/ejs/v3/ejs.core.js'
            ],
            h5: [
                'js/lib/ejs/v3/ejs.api.v3.h5mui.js'
            ],
            dd: [
                'js/lib/ejs/dingtalk.js',
                'js/lib/ejs/v3/ejs.api.v3.dd.js'
            ],
            ejs: [
                'js/lib/ejs/v3/ejs.api.v3.native.js'
            ]
        }
    };

    function createPathArr(paths, ejsVer, env) {
        var arr = [],
            envs = env.split('_');
        
        // base 层
        arr = arr.concat(paths.base);
        
        // ejs 层
        var correctPaths = paths['ejsv' + ejsVer];

        arr = arr.concat(correctPaths.base);

        for (var i = 0, len = envs.length; i < len; i++) {
            arr = arr.concat(correctPaths[envs[i]] || []);
        }
        
        // core 层
        arr = arr.concat(paths.core);

        return arr;
    }

    if (typeof Config !== 'undefined') {
        if (Config.isDebug) {
            // debug模式下的资源，通过版本号和环境，自动读取配置，生成最终的文件
            arr = createPathArr(paths, Config.ejsVer, Config.env);
            
            if (Config.isDebugPanel) {
                // 用来捕获移动端的 log
                arr.push('js/libs/vconsole.min.js');
            }
        } else {
            // 正式模式下的，比较简单
            arr.push('js/lib/_dist/core.v' + Config.ejsVer + '.' + Config.env + '.min.js');
        }
        
        if (Config.isComdto) {
            // comdto通过配置项引入
            arr.push('js/lib/comdto/comdto.' + (Config.isDebug ? 'js' : 'min.js'));
        }
              
        // 可以在这加入项目自定义的全局js
        
        SrcBoot.output(arr);
    }

    if (typeof module !== 'undefined' && module.exports) {
        // 暴露给gulpfile自动构建
        module.exports = {
            paths: paths,
            createPathArr: createPathArr
        };
    }
}());