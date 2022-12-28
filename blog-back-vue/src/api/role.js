import request from '@/utils/request'
import {parseStrEmpty} from '@/utils/common'

/**
 * 获取所有角色信息
 * @param params
 * @returns {AxiosPromise}
 */
export function listRole(params) {
  return request({
    url: '/api/role/list',
    method: 'get',
    params
  })
}

/**
 * 添加角色
 * @param data
 * @returns {AxiosPromise}
 */
export function addRole(data) {
  return request({
    url: '/api/role/add',
    method: 'post',
    data
  })
}

/**
 * 删除角色
 * @param id
 * @returns {AxiosPromise}
 */
export function deleteRole(id) {
  return request({
    url: `/api/role/delete/${id}`,
    method: 'delete'
  })
}

/**
 * 更新角色
 * @param data
 * @returns {AxiosPromise}
 */
export function updateRole(data) {
  return request({
    url: `/api/role/update`,
    method: 'put',
    data
  })
}

/**
 * 根据角色Id查询角色信息
 * @param id
 * @returns {AxiosPromise}
 */
export function getRole(id){
  return request({
    url: `/api/role/get/${id}`,
    method: 'get'
  })
}

/**
 * 根据用户ID查询用户所拥有的角色
 * @param userId
 * @returns {AxiosPromise}
 */
export function getRolesByUserId(userId){
  return request({
    url: '/api/role/getRolesByUserId/' + parseStrEmpty(userId),
    method: 'get'
  })
}
