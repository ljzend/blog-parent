import request from '@/utils/request'

/**
 * 登录
 * @param data
 * @returns {AxiosPromise}
 */
export function login(data) {
  return request({
    url: '/api/login',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息
 * @returns {AxiosPromise}
 */
export function getInfo() {
  return request({
    url: '/api/info',
    method: 'get',
  })
}

/**
 * 退出登录
 * @returns {AxiosPromise}
 */
export function logout() {
  return request({
    url: '/api/logout',
    method: 'post'
  })
}

/**
 * 获取用户菜单信息
 * @returns {AxiosPromise}
 */
export function getMenuList(){
  return request({
    url: '/api/getRouters',
    method: 'get'
  })
}
