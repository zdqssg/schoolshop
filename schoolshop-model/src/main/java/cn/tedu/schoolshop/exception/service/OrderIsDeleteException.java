package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/29 17:22
 */
public class OrderIsDeleteException  extends ServiceException{
    public OrderIsDeleteException() {
        super();
    }

    public OrderIsDeleteException(String message) {
        super(message);
    }

    public OrderIsDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderIsDeleteException(Throwable cause) {
        super(cause);
    }

    protected OrderIsDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
