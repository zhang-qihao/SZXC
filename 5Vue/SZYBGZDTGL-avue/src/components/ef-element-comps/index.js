//import Vue from 'vue';

import EfText from "./EfText";
EfText.install = function(Vue) {
    Vue.component(EfText.name, EfText);
};

import EfTel from "./EfTel";
EfTel.install = function(Vue) {
    Vue.component(EfTel.name, EfTel);
};

import EfMail from "./EfMail";
EfMail.install = function(Vue) {
    Vue.component(EfMail.name, EfMail);
};

import EfSelect from "./EfSelect";
EfSelect.install = function(Vue) {
    Vue.component(EfSelect.name, EfSelect);
};

import EfTextarea from "./EfTextarea";
EfTextarea.install = function(Vue) {
    Vue.component(EfTextarea.name, EfTextarea);
};

import EfDate from "./EfDate";
EfDate.install = function(Vue) {
    Vue.component(EfDate.name, EfDate);
};

import EfTime from "./EfTime";
EfTime.install = function(Vue) {
    Vue.component(EfTime.name, EfTime);
};

import EfDatetime from "./EfDatetime";
EfDatetime.install = function(Vue) {
    Vue.component(EfDatetime.name, EfDatetime);
};

import EfDateRange from "./EfDateRange";
EfDateRange.install = function(Vue) {
    Vue.component(EfDateRange.name, EfDateRange);
};

import EfDatetimeRange from "./EfDatetimeRange";
EfDatetimeRange.install = function(Vue) {
    Vue.component(EfDatetimeRange.name, EfDatetimeRange);
};

import EfPassword from "./EfPassword";
EfPassword.install = function(Vue) {
    Vue.component(EfPassword.name, EfPassword);
};

import EfNumber from "./EfNumber";
EfNumber.install = function(Vue) {
    Vue.component(EfNumber.name, EfNumber);
};

import EfMoney from "./EfMoney";
EfMoney.install = function(Vue) {
    Vue.component(EfMoney.name, EfMoney);
};

import EfSwitch from "./EfSwitch";
EfSwitch.install = function(Vue) {
    Vue.component(EfSwitch.name, EfSwitch);
};

import EfRadio from "./EfRadio";
EfRadio.install = function(Vue) {
    Vue.component(EfRadio.name, EfRadio);
};

import EfCheckbox from "./EfCheckbox";
EfCheckbox.install = function(Vue) {
    Vue.component(EfCheckbox.name, EfCheckbox);
};

import EfPageGrid from "../ef-kendo-comps/EfPageGrid";
EfPageGrid.install = function(Vue) {
    Vue.component(EfPageGrid.name, EfPageGrid);
};

import EfTree from "../ef-kendo-comps/EfTree";
EfPageGrid.install = function(Vue) {
    Vue.component(EfTree.name, EfTree);
};

import EfTreeGrid from "../ef-kendo-comps/EfTreeGrid";
EfPageGrid.install = function(Vue) {
    Vue.component(EfTreeGrid.name, EfTreeGrid);
};

import EfGridEditor from "../ef-kendo-comps/EfGridEditor";
EfGridEditor.install = function(Vue) {
    Vue.component(EfGridEditor.name, EfGridEditor);
};

import BaseBlock from "../ef-bootstrap-comps/BaseBlock";
BaseBlock.install = function(Vue) {
    Vue.component(BaseBlock.name, BaseBlock);
};
import EfUpload from "../ef-element-comps/EfUpload";
EfUpload.install = function(Vue) {
    Vue.component(EfUpload.name, EfUpload);
};

import EfImport from "../ef-element-comps/EfImport";
EfImport.install = function(Vue) {
    Vue.component(EfImport.name, EfImport);
};

import EfShowValue from "../ef-element-comps/EfShowValue";
EfShowValue.install = function(Vue) {
    Vue.component(EfShowValue.name, EfShowValue);
};
import EfShow from "./EfShow";
EfShow.install = function(Vue) {
    Vue.component(EfShow.name, EfShow);
};
import EfShowLoading from "./EfShowLoading";
EfShowLoading.install = function(Vue) {
    Vue.component(EfShowLoading.name, EfShowLoading);
};
import EfInfomation from "./EfInfomation";
EfInfomation.install = function(Vue) {
    Vue.component(EfInfomation.name, EfInfomation);
};

const components = [
    EfInfomation,
    EfShowLoading,
    EfText,
    EfTel,
    EfMail,
    EfSelect,
    EfTextarea,
    EfDate,
    EfTime,
    EfDatetime,
    EfDateRange,
    EfDatetimeRange,
    EfPassword,
    EfNumber,
    EfMoney,
    EfSwitch,
    EfRadio,
    EfCheckbox,
    EfPageGrid,
    EfTree,
    EfTreeGrid,
    EfGridEditor,
    BaseBlock,
    EfUpload,
    EfImport,
    EfShowValue,
    EfShow,
];

//const install = function(Vue, opts = {}) {
const install = function(Vue ) {
    components.forEach(component => {
        Vue.component(component.name, component);
    });
};

if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue);
}

export default {
    version: '2.15.1',
    install,
    EfInfomation,
    EfShowLoading,
    EfText,
    EfTel,
    EfMail,
    EfSelect,
    EfTextarea,
    EfDate,
    EfTime,
    EfDatetime,
    EfDateRange,
    EfDatetimeRange,
    EfPassword,
    EfNumber,
    EfMoney,
    EfSwitch,
    EfRadio,
    EfCheckbox,
    EfPageGrid,
    EfTree,
    EfTreeGrid,
    EfGridEditor,
    BaseBlock,
    EfUpload,
    EfImport,
    EfShowValue,
    EfShow,
}