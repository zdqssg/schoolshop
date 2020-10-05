package cn.tedu.schoolshop.getway.service.impl;


import cn.tedu.schoolshop.exception.service.IllegalParameterException;
import cn.tedu.schoolshop.exception.service.InsertException;
import cn.tedu.schoolshop.exception.service.NotFindException;
import cn.tedu.schoolshop.getway.mapper.GoodsAdvertisingMapper;
import cn.tedu.schoolshop.getway.service.IGoodsAdvertisingService;
import cn.tedu.schoolshop.model.GoodsAdvertising;
import cn.tedu.schoolshop.util.R;
import cn.tedu.schoolshop.vo.GoodsAdvertising.GoodsAdvertisingVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-30
 */
@Service
@Slf4j
public class GoodsAdvertisingServiceImpl extends ServiceImpl<GoodsAdvertisingMapper, GoodsAdvertising> implements IGoodsAdvertisingService {

    @Autowired
    private GoodsAdvertisingMapper advertisingMapper;

    @Override
    public R addAdvertising(Integer goodId) {
        QueryWrapper<GoodsAdvertising> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("good_id", goodId)
                .orderByDesc("create_time");
        List<GoodsAdvertising> list = advertisingMapper.selectList(queryWrapper);
        if (list.size() != 0) {
            GoodsAdvertising advertising = list.get(list.size() - 1);

            LocalDateTime createTime = advertising.getCreateTime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(createTime, now);
            long days = duration.toDays(); //相差的天数
            log.debug("相差的天数{}", days);
//            long hours = duration.toHours();//相差的小时数
//            long minutes = duration.toMinutes();//相差的分钟数
//            long millis = duration.toMillis();//相差毫秒数
//            long nanos = duration.toNanos();//相差的纳秒数
            if (days < 3) {
                return R.failure(R.State.ERR_ADVERTISING_BETWEEN_TIME, new InsertException("距上一次发布小于3天,发布失败"));
            }
        }

        int insert = advertisingMapper.insert(new GoodsAdvertising().setGoodId(goodId));
        if (insert == 1) {
            return R.ok();
        } else {
            return R.failure(R.State.ERR_INSERT, new InsertException("服务器错误,请稍后重试"));
        }

    }

    @Override
    public R getRandom() {
        List<GoodsAdvertisingVo> list = advertisingMapper.selectPageHelper();
        return getRandomAdvertising(list);
    }

    @Override
    public R selectOne(Integer advertisingID) {
        if (advertisingID == null || advertisingID < 1) {
            return R.failure(R.State.ERR_ILLEGAL_PARAMETER, new IllegalParameterException("参数异常"));
        }

        GoodsAdvertisingVo vo = advertisingMapper.findOne(advertisingID);
        if (vo == null) {
            return R.failure(R.State.ERR_NOT_FIND, new NotFindException("未找到"));
        }
        return R.ok(vo);
    }

    @Override
    public R getAll(Integer pageNum) {

        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        pageNum = (pageNum - 1) * 5;
        List<GoodsAdvertisingVo> vos = advertisingMapper.selectLimit(pageNum);
        return R.ok(vos);
    }

    @Override
    public R getRandomByMerchantId(Integer merchantId) {
        List<GoodsAdvertisingVo> list = advertisingMapper.selectByMerchantId(merchantId);

        return getRandomAdvertising(list);
    }
    private R getRandomAdvertising(List<GoodsAdvertisingVo> list){
        int size = list.size();

        int forCount = 2;
        if (size < 2) {
            forCount = size;
        }
        List<GoodsAdvertisingVo> arrayList = new ArrayList<>();
        for (int i = 0; i < forCount; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(size);
            arrayList.add(list.get(nextInt));
        }
        return R.ok(arrayList);
    }

}
