package cn.tedu.schoolshop.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @email zdq247209@163.com
 * @date 2020/9/10 10:33
 */
@Data
public class R<T> implements Serializable {
    /**
     * 响应时的状态码
     */
    private Integer state;
    /**
     * 操作的提示信息
     */
    private String message;
    /**
     * 返回的数据
     */
    private T data;

    public R() {
        super();
    }

    public R(Integer state) {
        this.state = state;
    }


    public static R ok() {
        return new R(State.OK);
    }


    public static <T> R ok(T data) {
        R r = new R();
        r.setState(State.OK);
        r.setData(data);
        return r;
    }

    public static R failure(Integer state, Throwable e) {
        R r = new R();
        r.setState(state);
        r.setMessage(e.getMessage());
        return r;
    }

    public interface State {

        /**
         * 正确
         */
        Integer OK = 2000;
        /**
         * 错误：用户名已经被注册
         */
        Integer ERR_NICKNAME_DUPLICATE = 4000;
        /**
         * 错误：手机号码已经被注册
         */
        Integer ERR_PHONE_DUPLICATE = 4001;
        /**
         * 错误：插入数据失败
         */
        Integer ERR_INSERT = 4004;
        /**
         * 错误：非法的请求参数
         */
        Integer ERR_ILLEGAL_PARAMETER = 4005;
        /**
         * 错误：非法的请求参数
         */
        Integer ERR_FILE_EMPTY = 4007;
        /**
         * 错误：非法的请求参数
         */
        Integer ERR_FILE_SIZE = 4006;
        /**
         * 错误：非法的请求参数
         */
        Integer ERR_FILE_TYPE = 4007;

        /**
         * 错误：非法的请求参数
         */
        Integer ERR_FILE_IO = 4007;
        /**
         * 错误：非法的请求参数
         */
        Integer ERR_FILE_STATE = 4007;
        /**
         * 错误：未知错误
         */
        Integer ERR_UNKNOWN = 9000;
        /**
         * 商品未找到
         */
        Integer ERR_GOOD_NOT_FIND = 123;
        /**
         * 店铺未找到
         */
        Integer ERR_STORE_NOT_FIND = 12323;
        /**
         * 资源未找到
         */
        Integer ERR_NOT_FIND = 54156;
        /**
         * 购物车已存在商品
         */
        Integer ERR_CART_DUPLICATE = 1231;
        /**
         * 添加购物车失败
         */
        Integer ERR_CART_INSERT = 123;
        /**
         * 修改异常
         */
        Integer ERR_UPDATE = 3000;

        /**
         * 请勿重复提交
         */
        Integer ERR_RESUBMIT = 5000;
        /**
         * 订单已支付
         */

        Integer ERR_ORDER_IS_PAY = 5010;
        /**
         * 余额不足请充值
         */
        Integer ERR_MONEY_NOT_ENOUGH = 5020;
        /**
         * 订单异常
         */
        Integer ERR_ORDER_EXCEPTION =5030 ;

        /**
         * 订单未找到
         */
        Integer ERR_ORDER_NOT_FIND =5040 ;
        /**
         * 订单已删除
         */
        Integer ERR_ORDER_IS_DELETE =5050 ;
        /**
         * 发布广告时间过短
         */
        Integer ERR_ADVERTISING_BETWEEN_TIME = 54456;
    }
}
