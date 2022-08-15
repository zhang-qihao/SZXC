<template>
  <div>
    <basic-container>
      <div id="grid" ></div>
    </basic-container>

  </div>
</template>

<script type="text/javascript">

   // import $ from 'jquery';
    import  {BaseCtrl}  from '/src/util/eframe'

    export default {
        mixins: [BaseCtrl],         //继承基础BaseCtrl
        name: "",
        data() {
            return {}
        },
        components: {},
        created() {

        },
        mounted() {
            $("#grid").kendoGrid({
                dataSource: {
                    type: "odata",
                    transport: {
                        read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Customers"
                    },
                    pageSize: 20
                },
                height: 550,
                groupable: true,
                sortable: true,
                pageable: {
                    refresh: true,
                    pageSizes: true,
                    buttonCount: 5
                },
                columns: [{
                    template: "<div class='customer-photo'" +
                        "style='background-image: url(../content/web/Customers/#:data.CustomerID#.jpg);'></div>" +
                        "<div class='customer-name'>#: ContactName #</div>",
                    field: "ContactName",
                    title: "Contact Name",
                    width: 240
                }, {
                    field: "ContactTitle",
                    title: "Contact Title"
                }, {
                    field: "CompanyName",
                    title: "Company Name"
                }, {
                    field: "Country",
                    width: 150
                }]
            });
        },
        methods: {}
    }
</script>

<style scoped>

</style>
