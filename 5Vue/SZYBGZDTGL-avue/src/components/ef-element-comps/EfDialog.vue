<template>
	<el-dialog :class="{'noClose':paramsInner.withClose == false}"  :title="titleInner" :visible.sync="visible"  :fullscreen="fullscreen" :width="dialogWidth"  :before-close="handleClose" :close-on-click-modal="maskClose" :modal-append-to-body="body" :append-to-body="body">
		<template slot="title">
			<div class="myTitle">
				<div>{{titleInner}}</div>
				<button @click="full" type="button" aria-label="Fullscreen" class="el-dialog__headerbtn">
					<i class="el-dialog__close el-icon el-icon-full-screen" />
				</button>
			</div>
		</template>
		<component :is="tempComponent" :params="paramsInner" />
	</el-dialog>
</template>

<script type="text/javascript">
	export default {
		name: "EfDialog",
		props: {
			show: {
				require: true,
				type: Boolean,
				default: false,
			},
			title: {
				require: true,
				type: String,
				default: "提示",
			},
			view: {
				type: String,
				require:false,
				default:''
			},
			width: {
				require:false,
				type: [String, Number],
				default:800
			},
			height: {
				require:false,
				type: [String, Number],
				default:400
			},
			params: {
				require:false,
				type: Object,
				default: function() {
					return {};
				},
			},
		},
		computed: {
			dialogWidth: function() {
				let width = "50%";
				if (this.widthInner) {
					if (typeof this.widthInner == "number") {
						width = this.widthInner + "px";
					} else {
						width = this.widthInner;
					}
				}
				return width;
			},
		},
		data() {
			return {
				componentType:'EfDialog',
				visible: false,
				tempComponent: null,
				fullscreen: false,
				maskClose: false,
				body:true,
				// 新增代码
				showInner: this.show,
				titleInner: this.title,
				viewInner: this.view,
				widthInner: this.width,
				paramsInner: this.params,
        withCloseInner:this.withClose
			};
		},
		watch: {
			show: function(newl) {
				this.visible = newl;
				this.fullscreen = false
			},
		},
		created() {
			this.getComponent();
		},
		methods: {
			close(bakParams) {
				if(typeof (bakParams) == 'undefined'){
					bakParams = null;
				}
				this.visible = false;
				if(typeof(this.callBackFun) == 'function') {
					this.callBackFun(bakParams);
				}
			},
			getComponent() {
				if(this.viewInner) {
					this.tempComponent = (resolve) =>
							require([`@/views${this.viewInner}`], resolve);
				}
			},
			full() {
				this.fullscreen = !this.fullscreen;
			},
			handleClose() {
				this.close(null);
			},


			showDialog( params ) {

				if (typeof (params) == 'undefined' || params == null || params == "") {
					alert("slideReveal函数错误，params不可为空");
					return;
				}
				var title = params.title;
				var view = params.view;
				var width = params.width;
				var callBackFun = params.callBackFun;
				var iframeFlag = params.iframeFlag; //打开模式   默认:vue路由   iframe时view嵌入iframe中展示
				var windowParams = params.params;

				if (typeof (windowParams) == 'undefined' ){
					windowParams = {};
				}

				if (iframeFlag != true) {
					iframeFlag = false;
				}

				if (typeof (view) == 'undefined' || view == null || view == "") {
					alert("slideReveal函数错误，view不可为空");
					return;
				}
				if (typeof (title) == 'undefined' || title == null || title == "") {
					title = "";
				}

				if (typeof (width) == 'undefined' || width == null) {
					width = 500;
				}


				this.visible = true;
				this.fullscreen = false;
				this.titleInner=title;
				this.viewInner=view;
				this.widthInner=width;
				this.paramsInner=windowParams;
				this.callBackFun=callBackFun;
				// if(this.viewInner) {
				// 	this.tempComponent = (resolve) => require([`@/views${this.viewInner}`], resolve);
				// }
        if (this.viewInner) {
          let path = this.viewInner;
          // path="@/page/frame/systemmanagement/usermanagement/user/usermanagement"
          // console.log(path);
          path = path.replace(/\/\//g, "/");
          if (path.indexOf('/')==0) {
            path = path.substring(1, path.length);
          } else if (path.indexOf('@/')==0) {
            path = path.substring(2, path.length);
          }
          // console.log(path);
          this.tempComponent = (resolve) => require([`@/${path}.vue`], resolve);

        }
			}


		},
	};
</script>

<style>
	.myTitle button {
		right: 51px;
	}

	.myTitle .el-dialog__headerbtn {
		font-size: 14px;
	}

	div.el-dialog__header {
		height: 56px;
		border-bottom: 1px solid #ececec;
		box-sizing: border-box;
		font-size: 16px;
		padding: 20px 24px 10px;
		/* color: rgba(0, 0, 0, 0.85); */
	}

	div.el-dialog__body {
		padding: 24px;
	}
  .noClose .el-dialog__header > .el-dialog__headerbtn{
    display: none;
  }
  .noClose .myTitle button {
    right: 20px;
  }
</style>
