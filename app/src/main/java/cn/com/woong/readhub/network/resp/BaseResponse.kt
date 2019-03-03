package cn.com.woong.readhub.network.resp

/**
 * BaseResponse
 */
open class BaseResponse {
    var errorMsg: String? = null
    var success: Boolean = false
    var errorCode: Int = 0
    var code = -1
    var timestamp: Long = 0
    var status: Int = 0

    var pageSize: Int = 0
    var totalItems: Int = 0
    var totalPages: Int = 0
}