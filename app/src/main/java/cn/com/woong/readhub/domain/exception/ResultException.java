package cn.com.woong.readhub.domain.exception;

/**
 * @author woong
 * Created by woong on 18/4/23.
 */
public class ResultException extends RuntimeException {
    public int errCode;
    public String msg;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.msg = msg;
    }

}

