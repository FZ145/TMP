package tmp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmp.dao.UserMapper;
import tmp.entity.User;
import tmp.service.IUserService;

/**
 * Created by shining.cui on 2015/11/5.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;

//    根据用户的id查询用户信息
    @Override
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }

}
