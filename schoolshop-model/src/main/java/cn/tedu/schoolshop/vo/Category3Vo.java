package cn.tedu.schoolshop.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/22 9:00
 */
@Data
public class Category3Vo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Integer category2Id;
}
