package cn.com.woong.readhub.network.resp

import cn.com.woong.readhub.bean.NewsMo


class NewsResp : BaseResponse() {
    var data: ArrayList<NewsMo>? = null
}