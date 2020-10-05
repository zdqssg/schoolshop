package cn.tedu.schoolshop.api.home;

import cn.tedu.schoolshop.api.user.mapper.UserOrderMapper;
import cn.tedu.schoolshop.model.UserOrder;
import cn.tedu.schoolshop.vo.order.UserOrderVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SchoolshopApiHomeApplicationTests {
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Test
	void contextLoads() {
		UserOrder userOrder = new UserOrder().setCartId("1,1").setUserId(1);
		Integer order = userOrderMapper.insertOrder(userOrder);
		System.out.println(order);
		System.out.println(userOrder.getId());
	}
	@Test
	void getAll() {
		UserOrder userOrder = new UserOrder().setUserId(1);
		List<UserOrderVo> orderVos = userOrderMapper.selectByUserId(userOrder);
		System.out.println(orderVos);
	}

}
