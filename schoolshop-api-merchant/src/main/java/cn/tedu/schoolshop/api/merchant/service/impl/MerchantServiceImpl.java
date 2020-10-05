package cn.tedu.schoolshop.api.merchant.service.impl;


import cn.tedu.schoolshop.api.merchant.dto.RegisterMerchantDTO;
import cn.tedu.schoolshop.api.merchant.mapper.MerchantMapper;
import cn.tedu.schoolshop.api.merchant.service.IMerchantService;
import cn.tedu.schoolshop.api.merchant.service.IMerchantSlideService;
import cn.tedu.schoolshop.exception.service.InsertException;
import cn.tedu.schoolshop.exception.service.NickNameDuplicateException;
import cn.tedu.schoolshop.exception.service.PhoneDuplicateException;
import cn.tedu.schoolshop.exception.service.StoreNameDuplicateException;
import cn.tedu.schoolshop.model.Merchant;
import cn.tedu.schoolshop.model.MerchantSlide;
import cn.tedu.schoolshop.util.PasswordUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zdq247209@163.com
 * @since 2020-09-22
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements IMerchantService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private IMerchantSlideService slideService;

    @Override
    public void registerMerchant(RegisterMerchantDTO registerMerchantDTO) {
        String nickname = registerMerchantDTO.getNickname();
        if (merchantMapper.selectByNickName(nickname) != null) {
            throw new NickNameDuplicateException("注册失败！用户名被占用");
        }
        String phone = registerMerchantDTO.getPhone();
        if (merchantMapper.selectByPhone(phone) != null) {
            throw new PhoneDuplicateException("注册失败！手机号被占用");
        }
        String storeName = registerMerchantDTO.getStoreName();
        if (merchantMapper.selectByStoreName(storeName) != null) {
            throw new StoreNameDuplicateException("注册失败！店铺名被占用");
        }
        String encodePassword = PasswordUtils.encode(registerMerchantDTO.getPassword());

        Merchant merchant = new Merchant()
                .setNickname(nickname)
                .setPassword(encodePassword)
                .setPhone(phone)
                .setStoreName(storeName)
                .setCategory1Id(registerMerchantDTO.getCategory1Id())
                .setCategory2Id(registerMerchantDTO.getCategory2Id());

        int insert = merchantMapper.insert(merchant);
        if (insert != 1) {
            throw new InsertException("注册失败！服务器忙，请稍后再次尝试！");
        }
        MerchantSlide slide = new MerchantSlide()
                .setGoodId(1)
                .setMerchantId(merchant.getId())
                .setState(1)
                .setBaseTitle("看上去很美")
                .setSecondTitle("这一季")
                .setBtn("查看详情");
        //这里先直接调用第一个商品作为商家轮播图  数据库字段默认商品Id为1后期更改
        slideService.save(slide);
    }

    @Override
    public Merchant selectClickCountAndSumMoneyById(Integer id) {
        return merchantMapper.selectClickCountAndSumMoneyById(id);
    }
}
