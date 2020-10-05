package cn.tedu.schoolshop.exception.service;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/21 14:52
 */
public class UpdateExteption extends ServiceException {
    public UpdateExteption() {
        super();
    }

    public UpdateExteption(String message) {
        super(message);
    }

    public UpdateExteption(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateExteption(Throwable cause) {
        super(cause);
    }

    protected UpdateExteption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
