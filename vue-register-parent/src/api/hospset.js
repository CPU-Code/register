import request from '@/utils/request'

export default {
  // 医院设置列表
  getHospSetList(current,limit,searchObj) {
    return request ({
      url: `/admin/hosp/hospitalSet/findPageHospSet/${current}/${limit}`,
      method: 'post',
      data: searchObj  // 使用json
    })
  }
}
