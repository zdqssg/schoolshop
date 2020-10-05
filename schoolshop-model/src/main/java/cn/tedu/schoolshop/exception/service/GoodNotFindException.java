package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/25 15:38
 */
public class GoodNotFindException  extends ServiceException{
    public GoodNotFindException() {
        super();
    }

    public GoodNotFindException(String message) {
        super(message);
    }

    public GoodNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoodNotFindException(Throwable cause) {
        super(cause);
    }

    protected GoodNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
