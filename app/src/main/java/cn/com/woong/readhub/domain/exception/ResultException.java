package cn.com.woong.readhub.domain.exception;

/**
 * Created by bing on 16/4/19.
 */
public class ResultException extends RuntimeException {

    public static final int EXCEPTION_CODE_COOKIE_INVALID = -1001;//COOKIE 失效
    public static final int EXCEPTION_CODE_COOKIE_VALIDATE = -1002;//非法COOKIE
    public static final int EXCEPTION_CODE_LOGINED_OTHER_DEVICE = 1004;//异地登录



    public int errCode;
    public String msg;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.msg = msg;
    }

}

