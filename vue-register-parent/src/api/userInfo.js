import request from '@/utils/request'

const api_name = '/admin/user'

export default {
  //用户列表
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  }
}
