import request from '@/utils/request'

const api_name = '/admin/hosp/hospitalSet'

export default {
  // 医院设置列表
  getHospSetList(current,limit,searchObj) {
    return request ({
      url: `${api_name}/findPageHospSet/${current}/${limit}`,
      method: 'post',
      data: searchObj  // 使用json
    })
  },
  //删除医院设置
  deleteHospSet(id) {
    return request ({
      url: `${api_name}/removeHospSet/${id}`,
      method: 'delete'
    })
  },
  batchRemoveHospSet(idList) {
    return request({
      url: `${api_name}/batchRemove`,
      method: 'delete',
      data: idList
    })
  }

}
