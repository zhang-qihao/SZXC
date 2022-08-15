<template>
	<iframe :src="src" frameborder="0" style="width: 100%;height: 100%"></iframe>
</template>

<script>
	import {
		BaseCtrl
	} from '/src/util/eframe'
	import {
		mapGetters
	} from "vuex";
	import constData from "../../../const";

	export default {
		name: "serieslist",
		mixins: [BaseCtrl],
		computed: {},
		data() {
			return {
				src: '',
				pageurl: '',
				classurl: '',
			}
		},
		created() {
			let that = this;
			if (typeof(this.$route.query) != 'undefined') {
				let params = this.$route.query;
				this.pageurl = params.pageurl;
				this.classurl = params.classurl;
			}
			this.src = constData.baseUrl + this.classurl + this.pageurl;
		},
		mounted() {

		},
		methods: {

		}
	}
</script>

<style lang="scss" scoped>

</style>
