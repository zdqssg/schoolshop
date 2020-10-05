package cn.tedu.schoolshop.getway.service;

import cn.tedu.schoolshop.model.User;

public interface UserService {
    User findByNickName(String nickname);
}
