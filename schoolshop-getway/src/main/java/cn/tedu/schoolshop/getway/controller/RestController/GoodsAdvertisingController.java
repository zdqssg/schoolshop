package cn.tedu.schoolshop.getway.controller.RestController;


import cn.tedu.schoolshop.exception.service.IllegalParameterException;
import cn.tedu.schoolshop.getway.service.IGoodsAdvertisingService;
import cn.tedu.schoolshop.model.GoodsAdvertising;
import cn.tedu.schoolshop.util.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
@RestController
@RequestMapping("/api-advertising")
@CrossOrigin
@Slf4j
public class GoodsAdvertisingController {
    @Autowired
    private IGoodsAdvertisingService advertisingService;
    /**
     * 创建广告
     * @return
     */
    @PostMapping("/{goodId}")
    public R post(@PathVariable("goodId")Integer goodId){
      return   advertisingService.addAdvertising(goodId);
    }

    /**
     * 得到随机两个广告
     * @return
     */
    @GetMapping("/random")
    public R getRandom(){
        return advertisingService.getRandom();
    }

    /**
     * 查询advertisingID广告
     * @param advertisingID
     * @return
     */
    @GetMapping("/{advertisingID}")
    public R getAdvertisingID(@PathVariable("advertisingID")Integer advertisingID){

        return advertisingService.selectOne(advertisingID);
    }

    /**
     * 查询所有广告分页每页5条
     * @param pageNum
     * @return
     */
    @GetMapping("/all/{pageNum}")
    public R getAll(@PathVariable("pageNum")Integer pageNum){
        return advertisingService.getAll(pageNum);
    }
    /**
     * 得到商家的随机两个广告
     * @return
     */
    @GetMapping("/random/{merchantId}")
    public R getRandomByMerchantId(@PathVariable("merchantId")Integer merchantId){

        return advertisingService.getRandomByMerchantId(merchantId);
    }
}
