package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/12 13:27
 */
public class NickNameDuplicateException extends ServiceException {
    public NickNameDuplicateException() {
        super();
    }

    public NickNameDuplicateException(String message) {
        super(message);
    }

    public NickNameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public NickNameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected NickNameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
