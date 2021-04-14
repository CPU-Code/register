<template>
  <div class="app-container">
    医院设置列表
  </div>
</template>

<script>

//引入接口定义的js文件
import hospset from '@/api/hospset'

export default {
   //定义变量和初始值
   data() {
      return {
         current:1,     //当前页
         limit:3,       //每页显示记录数
         searchObj:{},  //条件封装对象
         list:[],       //每页数据集合
         total:0,       //总记录数
         multipleSelection: [] // 批量选择中选择的记录列表
      }
   },
   created() { //在页面渲染之前执行
      //一般调用methods定义的方法，得到数据
        this.getList()
   },
   methods: {//定义方法，进行请求接口调用
      //医院设置列表
      getList() { //添加当前页参数
         hospset.getHospSetList(this.current,this.limit,this.searchObj)
            .then(response => { //请求成功response是接口返回数据
               //返回集合赋值list
               this.list = response.data.records
               //总记录数
               this.total = response.data.total
               
               console.log(response)
            })
            .catch(error => {//请求失败
               console.log(error)
            })
      }
   }
}
</script>