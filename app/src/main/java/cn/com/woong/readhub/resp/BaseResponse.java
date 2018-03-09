package cn.com.woong.readhub.resp;

/**
 * BaseResponse
 */
public class BaseResponse {
    public String errorMsg;
    public boolean success;
    public int errorCode;
    public int code = -1;
    public long timestamp;
    public int status;

    public int pageSize;
    public int totalItems;
    public int totalPages;
}
