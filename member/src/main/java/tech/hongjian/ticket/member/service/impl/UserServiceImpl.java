package tech.hongjian.ticket.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import tech.hongjian.ticket.common.utils.PageUtils;
import tech.hongjian.ticket.common.utils.Query;
import tech.hongjian.ticket.member.dao.UserDao;
import tech.hongjian.ticket.member.entity.UserEntity;
import tech.hongjian.ticket.member.service.UserService;

import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

}