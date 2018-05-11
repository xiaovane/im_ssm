/**
 * 作者: 郭天琦
 * 创建时间: 2017/07/14
 * 版本: [1.0, 2017/07/14 ]
 * 版权: 江苏国泰新点软件有限公司
 * 描述: 表单验证器
 */

(function (doc, Util, stringTools) {
    'use strict';

    // 存储form实例
    var formInstance = null;

    // 默认设置
    var defaultSetting = {
        tag: 'input',
        errCls: ''
    };

    // 默认提示
    var defaultPrompt = {
        phone: '手机号码格式不正确',
        email: '邮箱格式不正确',
        tel: '电话号码格式不正确',
        idcard: '身份证输入不正确',
        required: function () {
            return this.innerHTML + '不能为空';
        },
        maxlength: function (num) {
            return this.innerHTML + '不能大于' + num + '位';
        },
        minlength: function (num) {
            return this.innerHTML + '不能小于' + num + '位';
        },
        regexp: function () {
            return this.innerHTML + '输入不正确';
        },
        equal: function (name) {
            if (name == 'password') {
                return '密码输入不一致';
            }
        }
    };

    /**
     * 存放注册的方法、主要用于未进行初始化时候的注册
     */
    var registerMethod = [];

    /**
     * Form表单-构造函数
     * constructor
     * @param {String or HTMLElement} 容器元素
     * @param {Object} options 配置
     * tag {String} 标签名，默认为input
     */
    function Form(container, options) {
        var self = this;
        options = options || {};

        self.options = Util.extend(defaultSetting, options);
        self.container = Util.selector(container);

        // 验证集合，是一个集合
        self.validatorEl = self.getValidatorEl(self.options.tag);
        // 默认已有规则
        self.ruleMethod = {
            range: self._handleRange,
            maxlength: self._handleMaxlength,
            minlength: self._handleMinlength,
            phone: stringTools.isPhoneNum,
            tel: stringTools.isTelNum,
            email: stringTools.isEmail,
            idcard: stringTools.idcard.validate
        };

        // 有自己注册的规则话
        if (registerMethod.length > 0) {
            self._ruleMergeMethod(registerMethod);
        }
    }

    /**
     * 原型
     */
    Form.prototype = {

        /**
         * 开启验证
         */
        validate: function () {
            // 处理规则
            return this._handleRuleType(this.validatorEl);
        },

        /**
         * 获取数据
         */
        getData: function () {
            var validatorEl = this.validatorEl,
                result = {};

            validatorEl.forEach(function (e) {
                if (e.id) {
                    result[e.id] = e.value || '';
                }
            });

            return result;
        },

        /**
         * 获取错误信息
         */
        getError: function () {
            return this.error;
        },

        /**
         * 获取验证集合
         */
        getValidatorEl: function (tag) {
            return doc.querySelectorAll(tag);
        },

        /**
         * 设置默认内置输出内容
         * @param {Object} options 设置的内容
         */
        setDefaultErrMsg: function (options) {
            defaultPrompt = Util.extend(defaultPrompt, options);
        },

        /**
         * 处理规则
         * @param {HTMLElement} validatorEl
         */
        _handleRuleType: function (validatorEl) {
            var i = 0,
                len = validatorEl.length,
                _handleRequire = this._handleRequire,
                _handleVType = this._handleVType;

            for (; i < len; i++) {
                var item = validatorEl[i];

                // 必填的情况下
                if (item.required) {
                    if (!_handleRequire.call(this, item)) {
                        return false;
                    } else if (item.hasAttribute('vtype') && !_handleVType.call(this, item)) {
                        return false;
                    }
                }
                // 不是必填的情况下，但是也填写了值则验证
                else if (item.value != '' && item.hasAttribute('vtype') && !_handleVType.call(this, item)) {
                    return false;
                }
            }

            return true;
        },

        /**
         * @param {HTMLelement} el 元素
         * @return {Boolean} 成功失败
         */
        _handleRequire: function (el) {
            if (el.value && el.value != '') {
                return true;
            }

            this._handleError(el, defaultPrompt.required, this.options.errCls)

            return false;
        },

        /**
         * 处理vtype
         * @param {HTMLElement} el 带有vtype属性的元素
         */
        _handleVType: function (el) {
            var vtype = el.getAttribute('vtype'),
                reg = /^[a-z]+:/i,
                self = this,
                ruleMethod = self.ruleMethod;

            // 解析规则
            if (vtype != '' && reg.test(vtype)) {
                vtype = vtype.toLowerCase().trim();
                vtype = vtype.split(';');

                for (var i = 0, len = vtype.length; i < len; i++) {
                    var e = vtype[i];
                    var vtypeName = e.slice(0, e.indexOf(':'));

                    // 处理常规内容
                    if (ruleMethod[vtypeName] && !ruleMethod[vtypeName].call(self, el, e)) {
                        return false;
                    }
                    // 处理regType
                    if (vtypeName == 'regtype' && !self._handleRegType(el, e)) {
                        return false;
                    }
                    // 处理regExp
                    if (vtypeName == 'regexp' && !self._handleRegExp(el, e)) {
                        return false;
                    }
                    // 处理相等项
                    if (vtypeName == 'equal' && !self._handleEqual(el, e)) {
                        return false;
                    }
                }

                return true;
            }
        },

        /**
         * 处理regtype
         * @param {HTMLElement} el处理元素
         * @param {String} vtype 处理内容
         * @return {Boolean} true or false
         */
        _handleRegType: function (el, vtype) {
            var self = this,
                ruleMethod = self.ruleMethod,
                labelEle = el.previousElementSibling,
                vtype = this._analysisVtype(vtype),
                fnc = ruleMethod[vtype];

            if (typeof fnc == 'function') {

                if (fnc.type === 'm7_util_form_custom_regtype') {
                    if (self._handleCustomRegType(el, fnc)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (fnc(el.value)) {
                        return true;
                    } else {
                        self._handleError(el, defaultPrompt[vtype], self.options.errCls);

                        return false;
                    }
                }
            } else {
                throw new Error('未找到改判定条件');
            }
        },

        /**
         * 处理自定义的规则
         * @param {HTMLElement} el处理元素
         * @param {String} callback 回调函数
         * @return {Boolean} true or false
         */
        _handleCustomRegType: function (el, callback) {
            var curRegTypeName = callback.regTypeName,
                param = null;

            for (var i = 0, len = registerMethod.length; i < len; i++) {
                var e = registerMethod[i];

                if (curRegTypeName == e.callback.regTypeName) {
                    param = e.param;
                    callback = callback.bind(el, e.param);
                }
            }

            if (callback()) {
                return true;
            } else {

                this._handleError(el, param.errMsg, param.errCls);

                return false;
            }
        },

        /**
         * 处理regexp自定义正则
         * @param {HTMLElement} el处理元素
         * @param {String} vtype 处理内容
         */
        _handleRegExp: function (el, vtype) {
            var self = this,
                labelEle = el.previousElementSibling;

            vtype = self._analysisVtype(vtype);
            vtype = eval(vtype);

            if (vtype.test(el.value)) {
                return true;
            } else {
                self._handleError(el, defaultPrompt.regexp(labelEle), self.options.errCls);

                return false;
            }
        },

        /**
         * 处理相等项equal
         * @param {HTMLElement} el处理元素
         * @param {String} vtype 处理内容
         */
        _handleEqual: function (el, vtype) {
            var self = this,
                curValue = el.value,
                vtype = self._analysisVtype(vtype),
                positionEle = doc.getElementById(vtype);

            if (curValue != positionEle.value) {
                self._handleError(el, defaultPrompt.equal(vtype), self.options.errCls);

                return false;
            } else {
                return true;
            }
        },

        /**
         * 处理range
         * @param {HTMLElement} el处理元素
         * @param {String} vtype 处理内容
         */
        _handleRange: function (el, vtype) {
            var self = this,
                label = el.previousElementSibling;

            // 判断是否存在 ',' 可能会单独只设置一个
            var existence = Boolean(vtype.indexOf(',') == -1);

            var minlength = vtype.slice(vtype.indexOf(':') + 1, existence ? vtype.length : vtype.indexOf(',')),
                maxlength = existence ? '' : vtype.slice(vtype.indexOf(',') + 1);

            // 处理minlength
            if (!self._handleMinlength(el, '', minlength)) {
                return false;
            }
            // 处理maxlength
            else if (!self._handleMaxlength(el, '', maxlength)) {
                return false;
            }

            return true;
        },

        /**
         * 处理minlength
         * @param {HTMLElement} el 处理元素
         * @param {String} vtype 处理内容
         * @param {String} minlength 已经处理好的minlength
         */
        _handleMinlength: function (el, vtype, minlength) {
            var curValueLength = el.value.length,
                labelEle = el.previousElementSibling,
                self = this;

            // 如果有vtype的话，解析vtype
            if (vtype) {
                minlength = self._analysisVtype(vtype);
            }

            if (curValueLength < minlength) {
                self._handleError(el, defaultPrompt.minlength(labelEle, minlength), self.options.errCls);

                return false;
            }

            return true;
        },

        /**
         * 处理maxlength
         * @param {HTMLElement} el 处理元素
         * @param {String} vtype 处理内容
         * @param {String} maxlength 已经处理好的maxlength
         */
        _handleMaxlength: function (el, vtype, maxlength) {
            var curValueLength = el.value.length,
                labelEle = el.previousElementSibling, // 默认label TODO
                self = this;

            if (vtype) {
                maxlength = self._analysisVtype(vtype);
            }

            if (curValueLength > maxlength) {
                self._handleError(el, defaultPrompt.maxlength(labelEle, maxlength), self.options.errCls);

                return false;
            }

            return true;
        },

        /**
         * 拓展规则 TODO
         * @param {Array} 绑定方法集合
         * @return {Object} 返回当前有的规则方法
         */
        _ruleMergeMethod: function (tools) {
            var self = this;

            tools.forEach(function (e) {
                var callback = e.callback,
                    name = e.name;

                // 自定义regType的标识，无法被修改
                callback.type = 'm7_util_form_custom_regtype';
                callback.regTypeName = name;

                Object.defineProperties(callback, {
                    type: {
                        writable: false
                    },
                    regtype: {
                        writable: false
                    }
                });

                self.ruleMethod[name] = callback;
            });
        },

        /**
         * 处理失败高亮样式
         * @param {HTMLElement} el 当前元素
         * @param {String} cls 高亮元素样式
         */
        _handleErrCls: function (el, cls) {
            if (cls && cls != '') {
                var parent = el.parentElement;

                while (parent && !parent.hasAttribute('data-role')) {
                    parent = parent.parentElement;
                }

                if (parent && parent.hasAttribute('data-role')) {
                    parent.classList.add(cls);
                }
            }
        },

        /**
         * 解析vtype对应规则所设置的值、主要解析maxlength、minlength这种单一值的类型
         * @param {String} str 对应规则字符串
         * @return {String} str 解析完成后的值
         */
        _analysisVtype: function (str) {
            str = str.slice(str.indexOf(':') + 1);

            return str;
        },

        /**
         * 处理error
         * @param {HTMLElement} el 当前元素
         * @param {String Function} value 要输出的值，或者自定义的函数
         * @param {String} errCls 处理失败过后高亮样式
         */
        _handleError: function (el, value, errCls) {
            if (typeof value == 'function') {
                value = value.call(el);
            }

            this._setError(el, value);
            this.toast(value);
            this._handleErrCls(el, errCls);
        },

        /**
         * 设置err
         * @param {HTMLElement} el 报错的元素
         * @param {String Function} message 输入内容
         */
        _setError: function (el, message) {
            this.error = {
                dom: el,
                message: message
            };
        },

        /**
         * 输出内容
         * @param {String} message 输出的内容
         */
        toast: function (message) {
            Util.ejs.ui.toast(message);
        },

        /**
         * 判断是否为空对象
         * @param {Object} obj 对象
         * @return {Boolean} true or false
         */
        isEmptyObject: function (obj) {
            for (var i in obj) {
                return false;
            }

            return true;
        },

        /**
         * 基于实例的绑定，提供两种，方便初始化后也可进行扩展
         * registerVtype
         * @param {String} name 规则名
         * @param {Object} options 配置
         * @param {Function} callback 回调函数
         */
        registerVtype: function (name, options, callback) {
            registerMethod.push({
                callback: callback,
                param: options,
                name: name
            });

            this._ruleMergeMethod(registerMethod);
        }
    };

    Util.form = {
        /**
         * 创建实例
         * @param {String or HTMLElement} 容器元素
         * @param {Object} options 配置
         */
        getInstance: function (container, options) {
            formInstance = new Form(container, options);

            return formInstance;
        },

        /**
         * 拓展规则
         * @param {String} name 规则名
         * @param {Object} options 配置
         */
        registerVtype: function (name, options) {
            var callback = options.callback;

            delete options.callback;

            if (formInstance) {
                formInstance.registerVtype(name, options, callback);
            } else {
                registerMethod.push({
                    callback: callback,
                    param: options,
                    name: name
                });
            }
        },

        /**
         * 设置默认错误信息
         * @param {Object} options 设置的内容
         */
        setDefaultErrMsg: function (options) {
            defaultPrompt = Util.extend(defaultPrompt, options);
        }
    };

}(document, this.Util, this.Util.string));