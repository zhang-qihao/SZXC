import { setStore, getStore } from '@/util/store'
import { diff } from '@/util/util'
import website from '@/config/website'
import {deepClone} from "../../util/util";
const isFirstPage = website.isFirstPage;
const tagWel = website.fistPage;
const tagObj = {
  label: '', //标题名称
  value: '', //标题的路径
  params: '', //标题的路径参数
  query: '', //标题的参数
  meta: {},//额外参数
  group: [], //分组
  saveFlag:false,
  topId:''
}
//处理首个标签
function setFistTag (list) {
  if (list.length == 1) {
    list[0].close = false;
  } else {
    list.forEach(ele => {
      if (ele.value === tagWel.value && isFirstPage === false) {
        ele.close = false
      } else {
        ele.close = true
      }
    })
  }
}


const navs = {
  state: {
    tagList: getStore({ name: 'tagList' }) || [],
    tag: getStore({ name: 'tag' }) || tagObj,
    tagWel: tagWel,
  },
  actions: {

  },
  mutations: {
    ADD_TAG: (state, action) => {
      state.tag = action;
      setStore({ name: 'tag', content: state.tag })
      //更改
      if(action.label == '首页' || action.label == '系统管理' || action.label == '开发用例'){
        state.tagList.splice(0,1,action)
      }else if(state.tagList.some(ele=>diff(ele,action))){
        state.tagList.map((ele)=>{
          if(diff(ele,action)){
              state.tagList.splice(state.tagList.indexOf(ele),1,action)
          }
        })
      }else{
        state.tagList.push(action)
      }
      //更改结束
      // if(state.tagList.some(ele=>diff(ele,action)))return
      // state.tagList.push(action)
      setFistTag(state.tagList);
      setStore({ name: 'tagList', content: state.tagList })
    },
    DEL_TAG: (state, action) => {
      state.tagList = state.tagList.filter(item => {
        return !diff(item, action);
      })
      setFistTag(state.tagList);
      setStore({ name: 'tagList', content: state.tagList })
    },
    DEL_ALL_TAG: (state) => {
      state.tagList = [state.tagWel];
      setStore({ name: 'tagList', content: state.tagList })
    },
    DEL_TAG_OTHER: (state) => {
      state.tagList = state.tagList.filter(item => {
        if (item.value === state.tag.value) {
          return true;
        } else if (!website.isFirstPage && item.value === website.fistPage.value) {
          return true;
        }
      })
      setFistTag(state.tagList);
      setStore({ name: 'tagList', content: state.tagList })
    },
    SET_TAG_LIST (state, tagList) {
      state.tagList = tagList;
      setStore({ name: 'tagList', content: state.tagList })
    },
    CHANGE_SAVEFLAG(state, {index,flag}){
      state.tagList.map((item,temp_index)=>{
        if(temp_index == index){
          item.saveFlag = flag
        }
      })
      state.tagList[index].saveFlag = flag
    },
  }
}
export default navs