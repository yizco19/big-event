package cm.zy.service.impl;

import cm.zy.pojo.User;
import cm.zy.service.UserService;
import cm.zy.utils.Md5Util;
import cm.zy.mapper.UserMapper;
import cm.zy.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
        User u =userMapper.findByUsername(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // codificar password
        String md5Password = Md5Util.getMD5String(password);
        // registrar usuario
        userMapper.add(username,md5Password);




    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    // actualizar imagen de perfil
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);

    }

    @Override
    public void updatePwd(String newPassword) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPassword),id);
    }
}
