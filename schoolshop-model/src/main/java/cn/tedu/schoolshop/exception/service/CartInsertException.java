package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/27 18:43
 */
public class CartInsertException extends ServiceException{
    public CartInsertException() {
        super();
    }

    public CartInsertException(String message) {
        super(message);
    }

    public CartInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartInsertException(Throwable cause) {
        super(cause);
    }

    protected CartInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
