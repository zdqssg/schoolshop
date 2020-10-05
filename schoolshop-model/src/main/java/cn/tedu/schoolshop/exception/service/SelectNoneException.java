package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/23 13:56
 */
public class SelectNoneException extends ServiceException {
    public SelectNoneException() {
        super();
    }

    public SelectNoneException(String message) {
        super(message);
    }

    public SelectNoneException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectNoneException(Throwable cause) {
        super(cause);
    }

    protected SelectNoneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
