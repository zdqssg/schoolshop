package cn.tedu.schoolshop.exception.service;

/**
 * 请求参数异常
 *
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/10 10:28
 */
public class IllegalParameterException  extends ServiceException {
    public IllegalParameterException() {
        super();
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }

    protected IllegalParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
