export var BaseFieldTag = {

    // protected String name;  //name  名称
    // protected String model;  //k-ng-model   绑定到$scope
    // protected String value;  //ext-init-value   默认值
    //
    // protected String style; //style  样式
    // protected String label; //label  标题
    // protected String require; //require  是否可空
    // protected String onChange; //onChange change事件

    // protected String pattern;  //pattern   数据校验正则表达式
    // protected String validationMessage; //validationMessage 错误提示
    // protected String options;  //k-options
    // protected String placeholder;  //placeholder  说明
    // protected String readonly;  //readonly   只读

    props: {
        name: { // 是否可空，默认true。对应required。
            type: String,
            require:true,
        },
        value:{
            type: [String,Array,Boolean,Number],
            default: null,
        },
        label: { // 是否多选，默认false，非必传。
            type: String,
            default: null,
        },
        require: { // 是否可空，默认true。对应required。
            type: [String, Boolean],
            default: false,
        },
        readonly: { // 是否禁用，默认false，非必传。
            type: [String, Boolean],
            default: false,
        },
        placeholder: { // 选择框提示文字，默认‘请选择’，非必传。
            type: String,
            default: '请输入',
        },
        labelWidth: { // 选择框提示文字，默认‘请选择’，非必传。
            type: [String,Number],
            default: null,
        },
        //常用表达式：
        // 验证数字：^[0-9]*$
        // 验证n位的数字：^\d{n}$
        // 验证至少n位数字：^\d{n,}$
        // 验证m-n位的数字：^\d{m,n}$
        // 验证长度为3的字符：^.{3}$
        // 长度为3-20的所有字符：^.{3,20}$
        // 长度为0-20的所有字符：^.{0,20}$
        // Email地址：^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
        // 手机号码：^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$
        pattern: { // pattern   数据校验正则表达式。
            type: [String,RegExp],
            default: null,
        },
        validationMessage: { // validationMessage   错误提示
            type: String,
            default: null,
        },
        catch: { // 是否使用缓存，默认true，非必传。
            type: [String, Boolean],
            default: true,
        },
    },

    model:{
        prop:'value',
        event:'compChange'
    },

    data(){
        return {
            compValue:this.value,
            compRequire:false,
            compReadonly:false,
            compPattern:null,
            compValidateTrigger:'blur', //校验触发事件，默认blur
            compLabelWidth:null,
            compCatch:true,
            rules:[],
        };
    },

    watch:{
        readonly:{
            handler(val){
                this.compReadonly = val
                this.getRules()
            },
            deep:true
        },
        require:{
            handler(val){
                this.compRequire = val
                this.getRules()
            },
            deep:true
        },
    },

    created(){
        this.getRules()
    },

    methods:{
        getRules(){
            this.compValue=this.value;
            if(typeof (this.require)==="string") {
                // this.compRequire = this.require === 'true'
                if(this.require === 'true'){
                    this.compRequire = true
                }else if(this.require === 'false'){
                    this.compRequire = false
                }
            }else if(typeof (this.require)==="boolean"){
                this.compRequire = this.require
            }

            if(typeof (this.readonly)==="string") {
                // this.compReadonly = this.readonly === 'true'
                if(this.readonly === 'true'){
                    this.compReadonly = true
                }else if(this.readonly === 'false'){
                    this.compReadonly = false
                }
            }else if(typeof (this.readonly)==="boolean"){
                this.compReadonly = this.readonly
            }

            if(this.pattern) {
                if (typeof (this.pattern) == "string") {
                    if (this.pattern) {
                        this.compPattern = eval(this.pattern);
                    }
                } else {
                    this.compPattern = this.pattern;
                }
            }

            if(typeof (this.catch)==="string") {
                this.compCatch = this.catch === 'true';
            }else{
                this.compCatch = this.catch === true;
            }

            if(typeof (this.labelWidth)==="number") {
                this.compLabelWidth = this.labelWidth+"px";
            }else if(typeof (this.labelWidth)==="string"){
                if(this.labelWidth.toLowerCase().indexOf("px")<0){
                    this.compLabelWidth = this.labelWidth+"px";
                }else {
                    this.compLabelWidth = this.labelWidth.toLowerCase();
                }
            }

            if(this.compRequire){
                this.rules.splice(0,this.rules.length)
                if(this.validationMessage){
                    this.rules.push(
                        { required: true, message: this.validationMessage, trigger: this.compValidateTrigger }
                    )
                }else {
                    this.rules.push(
                        { required: true, message: '请录入'+this.label, trigger: this.compValidateTrigger }
                    )
                }
            }else{
                this.rules.splice(0,this.rules.length)
                this.rules.push(
                    { required: false, trigger: this.compValidateTrigger}
                )
            }
            this.rules.push({ validator:this.validateComp })
        },
        validateComp(rule, value,callback) {
            if(this.compPattern) {
                if (value === '' || value === undefined || value === null) {
                    callback();
                } else {
                    if (!this.compPattern.test(value)) {
                        let msg = "";
                        if(this.validationMessage) {
                            msg = this.validationMessage;
                        }else{
                            msg = ''+this.label+'格式错误';
                        }
                        callback(new Error(msg));
                    } else {
                        callback();
                    }
                }
            }else {
                callback();
            }
        },
        input(val){
            this.$emit('onInput', val);

        },
        change(val){
            this.$emit('onChange', val);
        },

    }



};
