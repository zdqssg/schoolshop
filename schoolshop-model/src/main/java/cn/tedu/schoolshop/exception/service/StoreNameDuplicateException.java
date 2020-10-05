package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 10:04
 */
public class StoreNameDuplicateException extends ServiceException {
    public StoreNameDuplicateException() {
        super();
    }

    public StoreNameDuplicateException(String message) {
        super(message);
    }

    public StoreNameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNameDuplicateException(Throwable cause) {
        super(cause);
    }

    protected StoreNameDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
