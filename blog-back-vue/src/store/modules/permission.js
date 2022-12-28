import { constantRoutes } from '@/router'
import { getMenuList } from '@/api/login'
import Layout from '@/layout'

/**
 * Use meta.role to determine if the current user has permission
 * 判断当前登录用户是否有拥有该角色下的菜单信息
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * 过滤出所拥有的菜单信息
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    // 判断是否拥有相应的权限
    if (hasPermission(roles, tmp)) {
      //获取组件
      const component = tmp.component
      //判断该路由使用有组件
      if (route.component) {
        //判断是否是根组件
        if (component === 'Layout') {
          tmp.component = Layout
        } else {
          //获取对应的具体的组件信息
          tmp.component = (resolve) => require([`@/views${component}.vue`], resolve)
        }
      }
      //判断是否有子菜单
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  // 将路由信息保存到 Vuex 中
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  /**
   * 动态生成路由
   * @param commit
   * @param roles
   * @returns {Promise<unknown>}
   */
  generateRoutes({ commit }, roles) {
    return new Promise((resolve, reject) => {
      getMenuList().then(response => {
        let accessedRoutes
        if(response.success){
          accessedRoutes = filterAsyncRoutes(response.data, roles)
        }
        // 将路由信息保存到 store 中
        commit('SET_ROUTES', accessedRoutes)
        resolve(accessedRoutes)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
