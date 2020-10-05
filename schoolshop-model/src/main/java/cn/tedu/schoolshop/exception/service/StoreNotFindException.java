package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/26 10:41
 */
public class StoreNotFindException extends ServiceException{
    public StoreNotFindException() {
        super();
    }

    public StoreNotFindException(String message) {
        super(message);
    }

    public StoreNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreNotFindException(Throwable cause) {
        super(cause);
    }

    protected StoreNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
