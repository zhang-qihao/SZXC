import {
  setStore,
  getStore,
  removeStore
} from '../../util/store'
import website from '../../config/website'
const common = {
  state: {
    language: getStore({ name: 'language' }) || 'zh',
    isCollapse: false,
    isFullScren: false,
    isMenu: true,
    isRefresh: true,
    screen: -1,
    isLock: getStore({ name: 'isLock' }),
    colorName: getStore({ name: 'colorName' }) || '#5c90d2',
    // themeName: getStore({ name: 'themeName' }) || 'default',
    // themeName: getStore({ name: 'themeName' }) || 'theme-beautiful',
    themeName:website.styleList.themeName || 'default',
    lockPasswd: getStore({ name: 'lockPasswd' }) || '',
    website: website,

    formStyle:[
      {size: 'mini'},
      {
        fontSize: '12px',
        marginBottom:'2px',
        display:'inline-block'
      },
    ],
    appStyle:{fontSize:'12px'},
    appName:'',
    elSubmenuTitleStyle:{
      fontSize:'12px',
      height:'34px',
      // display:'flex',
      // alignItems:'center',
      lineHeight:'34px'
    },
    elMenuItemStyle:{
      fontSize:'12px',
      height:'30px',
      // display:'flex',
      // alignItems:'center',
      lineHeight:'30px'
    }
  },
  mutations: {
    SET_LANGUAGE: (state, language) => {
      state.language = language
      setStore({
        name: 'language',
        content: state.language
      })
    },
    SET_COLLAPSE: (state,type) => {
      if(type == '0'){
        state.isCollapse = !state.isCollapse;
      }
      if(type == '1'){
        state.isCollapse = true;
      }
      if(type == '2'){
        state.isCollapse = false;
      }
    },
    SET_IS_MENU: (state, menu) => {
      state.isMenu = menu;
    },
    SET_IS_REFRESH: (state, refresh) => {
      state.isRefresh = refresh;
    },
    SET_FULLSCREN: (state) => {
      state.isFullScren = !state.isFullScren;
    },
    SET_LOCK: (state) => {
      state.isLock = true;
      setStore({
        name: 'isLock',
        content: state.isLock,
        type: 'session'
      })
    },
    SET_SCREEN: (state, screen) => {
      state.screen = screen;
    },
    SET_COLOR_NAME: (state, colorName) => {
      state.colorName = colorName;
      setStore({
        name: 'colorName',
        content: state.colorName,
      })
    },
    SET_THEME_NAME: (state, themeName) => {
      state.themeName = themeName
      setStore({
        name: 'themeName',
        content: state.themeName,
      })
      document.body.className = state.appName + ' ' + themeName
    },
    SET_LOCK_PASSWD: (state, lockPasswd) => {
      state.lockPasswd = lockPasswd;
      setStore({
        name: 'lockPasswd',
        content: state.lockPasswd,
        type: 'session'
      })
    },
    CLEAR_LOCK: (state) => {
      state.isLock = false;
      state.lockPasswd = '';
      removeStore({
        name: 'lockPasswd',
        type: 'session'
      });
      removeStore({
        name: 'isLock',
        type: 'session'
      });
    },
    SET_FORMSTYLE:(state,num) => {
      const formMiniStyle = [
        {size: 'mini'},
        {
          fontSize: '12px',
          marginBottom:'2px',
          display:'inline-block'
        },
      ]
      const formSmallStyle = [
        {size: 'small'},
        {
          fontSize: '14px',
          marginBottom:'4px',
          display:'inline-block'
        },
      ]
      const formMediumStyle = [
        {size: 'medium'},
        {
          fontSize: '16px',
          marginBottom:'6px',
          display:'inline-block'
        },
      ]
      const elSubmenuTitleMiniStyle = {
            fontSize:'12px',
            height:'34px',
            lineHeight:'34px'
      }
      const elSubmenuTitleSmallStyle = {
        fontSize:'14px',
        height:'36px',
        lineHeight:'36px'
      }
      const elSubmenuTitleMediumStyle = {
        fontSize:'16px',
        height:'38px',
        lineHeight:'38px'
      }
      const elMenuItemMiniStyle = {
        fontSize:'12px',
        height:'30px',
        lineHeight:'30px'
      }
      const elMenuItemSmallStyle = {
        fontSize:'14px',
        height:'32px',
        lineHeight:'32px'
      }
      const elMenuItemMediumStyle = {
        fontSize:'16px',
        height:'34px',
        lineHeight:'34px'
      }
      if(num == 0){
        state.formStyle = formMiniStyle
        state.appStyle.fontSize = '12px'
        state.appName = 'body-mini'
        state.elSubmenuTitleStyle = elSubmenuTitleMiniStyle
        state.elMenuItemStyle = elMenuItemMiniStyle
      }
      if(num == 1){
        state.formStyle = formSmallStyle
        state.appStyle.fontSize = '14px'
        state.appName = 'body-small'
        state.elSubmenuTitleStyle = elSubmenuTitleSmallStyle
        state.elMenuItemStyle = elMenuItemSmallStyle
      }
      if(num == 2){
        state.formStyle = formMediumStyle
        state.appStyle.fontSize = '16px'
        state.appName = 'body-medium'
        state.elSubmenuTitleStyle = elSubmenuTitleMediumStyle
        state.elMenuItemStyle = elMenuItemMediumStyle
      }
    }
  }
}
export default common