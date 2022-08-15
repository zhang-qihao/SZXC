import Vue from 'vue';
import Vuex from 'vuex';
import user from './modules/user';
import common from './modules/common';
import tags from './modules/tags';
import logs from './modules/logs';
import querys from './modules/querys';
import dict from './modules/dict';
import getters from './getters';

Vue.use(Vuex);
const store = new Vuex.Store({
  modules: {
    user,
    common,
    logs,
    tags,
    querys,
    dict,
  },
  getters,
});

export default store;