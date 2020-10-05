package cn.tedu.schoolshop.getway.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Mr.Zhou
 * @version 1.0
 * @function { 描述一下功能吧 }
 * @email zdq247209@163.com
 * @date 2020/9/18 23:09
 */
@Data
@Accessors(chain = true)
public class FilterSearchDto implements Serializable {
    private String msg;//模糊关键字
    private String category;//类型

    private Integer pageNum;//指定页
    private Integer orderBy;//通过什么排序
    private Integer orderSort;   //升序降序
    private Integer betweenBy;//通过什么搜索范围
    private Integer betweenStart;//范围
    private Integer betweenEnd;//范围


    private Integer category1Id;//类型
    private Integer category2Id;//类型
    private Integer category3Id;//类型

    private Integer merchantId;

}
