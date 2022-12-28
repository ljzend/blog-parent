import request from '@/utils/request'

/**
 * 获取所有角色信息
 * @param params
 * @returns {AxiosPromise}
 */
export function listMenu(params) {
  return request({
    url: '/api/menu/list',
    method: 'get',
    params
  })
}

/**
 * 添加角色
 * @param data
 * @returns {AxiosPromise}
 */
export function addMenu(data) {
  return request({
    url: '/api/menu/add',
    method: 'post',
    data
  })
}

/**
 * 删除角色
 * @param id
 * @returns {AxiosPromise}
 */
export function deleteMenu(id) {
  return request({
    url: `/api/menu/delete/${id}`,
    method: 'delete'
  })
}

/**
 * 更新角色
 * @param data
 * @returns {AxiosPromise}
 */
export function updateMenu(data) {
  return request({
    url: `/api/menu/update`,
    method: 'put',
    data
  })
}

/**
 * 根据角色Id查询角色信息
 * @param id
 * @returns {AxiosPromise}
 */
export function getMenu(id){
  return request({
    url: `/api/menu/get/${id}`,
    method: 'get'
  })
}
