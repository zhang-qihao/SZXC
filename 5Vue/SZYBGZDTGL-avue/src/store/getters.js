
const getters = {
  tag: state => state.tags.tag,
  language: state => state.common.language,
  website: state => state.common.website,
  setting: state => state.common.website.setting,
  userInfo: state => state.user.userInfo,
  colorName: state => state.common.colorName,
  themeName: state => state.common.themeName,
  isRefresh: state => state.common.isRefresh,
  isHorizontal: (state, getters) => getters.setting.sidebar === 'horizontal',
  isCollapse: state => state.common.isCollapse,
  keyCollapse: (state, getters) => getters.screen > 1 ? getters.isCollapse : false,
  screen: state => state.common.screen,
  isLock: state => state.common.isLock,
  isFullScren: state => state.common.isFullScren,
  isMenu: state => state.common.isMenu,
  lockPasswd: state => state.common.lockPasswd,
  tagList: state => state.tags.tagList,
  tagWel: state => state.tags.tagWel,
  token: state => state.user.token,
  roles: state => state.user.roles,
  permission: state => state.user.permission,
  menuId: state => state.user.menuId,
  menu: state => state.user.menu,
  menuAll: state => state.user.menuAll,
  topMenu: state => state.user.topMenu,
  logsList: state => state.logs.logsList,
  logsLen: state => state.logs.logsList.length || 0,
  logsFlag: (state, getters) => getters.logsLen === 0,
  querysMap:state => state.query.querysMap, //查询缓存Map
  dictMap:state => state.dict.dictMap, //字典缓存Map
  formStyle:state => state.common.formStyle, //表单大小
};
export default getters