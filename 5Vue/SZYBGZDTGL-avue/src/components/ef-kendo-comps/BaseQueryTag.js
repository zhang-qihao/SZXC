import eframe from "../../util/eframe";

export var BaseQueryTag = {

    // protected String queryNo;  //查询号
    // protected String queryWindow; //窗口号
    // protected String showType; // 0不显示check框   1 显示check框， 默认为：1
    // protected String pageSize; //每页显示条数

    props: {
        queryNo: { //查询号
            type: String,
            require:true,
        },
        queryWindow:{ //窗口号
            type: [String,Number],
            default: "1",
        },
        connectId:{ //数据链接ID
            type: [String,Number],
            default: "1",
        },

        showType: {  // 0不显示check框   1 显示check框， 默认为：1
            type: [String, Number],
            default: "1",
        },
        pageSize: {  //每页显示条数
            type: [String, Number],
            default: 20,
        },
        catch: { // 是否使用缓存，默认true，非必传。
            type: [String, Boolean],
            default: true,
        },
        editable:{
            type: [String, Boolean],
            default: true,
        }
    },

    data(){
        return {
            id:'W_EF_'+eframe.uuid(),
            widget:null,
            compQueryNo:"",
            compConnectId:"1",
            compQueryWindow:"1",
            compShowType:"1",
            compPageSize:20,
            compStyle:"",
            compCatch:true,
            compEditable:false
        };
    },

    created(){

        if(typeof (this.queryNo)==="number") {
            this.compQueryNo = this.queryNo+"";
        }else if(typeof (this.queryNo)==="string"){
            this.compQueryNo =  this.queryNo;
        }

        if(typeof (this.connectId)==="number") {
            this.compConnectId = this.connectId+"";
        }else if(typeof (this.connectId)==="string"){
            this.compConnectId =  this.connectId;
        }


        if(typeof (this.queryWindow)==="number") {
            this.compQueryWindow = this.queryWindow+"";
        }else if(typeof (this.queryWindow)==="string"){
            this.compQueryWindow =  this.queryWindow;
        }

        if(typeof (this.showType)==="number") {
            this.compShowType = this.showType+"";
        }else if(typeof (this.showType)==="string"){
            this.compShowType =  this.showType;
        }

        if(typeof (this.pageSize)==="number") {
            this.compPageSize = this.pageSize;
        }else if(typeof (this.pageSize)==="string"){
            this.compPageSize = parseInt(this.pageSize)
        }

        if(typeof (this.catch)==="string") {
            this.compCatch = this.catch === 'true';
        }else{
            this.compCatch = this.catch === true;
        }

        if(typeof (this.editable)==="string") {
            this.compEditable = this.editable === 'true';
        }else{
            this.compEditable = this.editable === true;
        }

    },

    methods:{

        /**
         * 获取查询组件
         *
         * @return
         * @exception
         */
        getWidget(){
            return this.widget;
        },

        /**
         * 设置初始化函数
         *
         * @param
         * @return
         * @exception
         */
        setInitFun(initFun){
            if(typeof(initFun)=="function"){
                this.initFun = initFun;
            }else{
                this.$eframe.alertError("setInitFun入参错误，入参不是一个方法");
            }
        },
    },

    watch:{
        widget(){
            if(this.widget) {
                if(this.initFun){
                    this.initFun(this.widget);
                }
            }
        }
    },



};
