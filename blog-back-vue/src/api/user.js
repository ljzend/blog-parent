import request from '@/utils/request'

/**
 * 查询用户列表
 * @param params
 * @returns {AxiosPromise}
 */
export function listUser(params){
  return request({
    url: '/api/user/list',
    method: 'get',
    params: params
  })
}

/**
 * 添加用户
 * @param data
 * @returns {AxiosPromise}
 */
export function addUser(data){
  return request({
    url: '/api/user/add',
    method: 'post',
    data: data
  })
}

/**
 * 删除用户
 * @param userIds
 * @returns {AxiosPromise}
 */
export function deleteUser(userIds){
  return request({
    url: `/api/user/delete/` + userIds,
    method: 'delete'
  })
}

/**
 * 更新用户
 * @param user
 * @returns {AxiosPromise}
 */
export function updateUser(user){
  return request({
    url: `/api/user/update`,
    method: 'put',
    data: user
  })
}

/**
 * 修改用户状态
 * @param userId
 * @param status
 * @returns {AxiosPromise}
 */
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/api/user/changeStatus',
    method: 'put',
    data: data
  })
}

/**
 * 用户密码重置
 * @param userId
 * @param password
 * @returns {AxiosPromise}
 */
export function resetPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/api/user/resetPwd',
    method: 'put',
    data: data
  })
}


