package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/29 17:12
 */
public class MoneyNotEhoughException extends ServiceException{
    public MoneyNotEhoughException() {
        super();
    }

    public MoneyNotEhoughException(String message) {
        super(message);
    }

    public MoneyNotEhoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyNotEhoughException(Throwable cause) {
        super(cause);
    }

    protected MoneyNotEhoughException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
