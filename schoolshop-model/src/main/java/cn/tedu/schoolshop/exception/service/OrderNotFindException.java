package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/29 17:18
 */
public class OrderNotFindException extends ServiceException {
    public OrderNotFindException() {
        super();
    }

    public OrderNotFindException(String message) {
        super(message);
    }

    public OrderNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFindException(Throwable cause) {
        super(cause);
    }

    protected OrderNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
