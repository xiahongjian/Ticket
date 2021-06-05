package tech.hongjian.ticket.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import tech.honejian.ticket.common.utils.PageUtils;
import tech.hongjian.ticket.member.entity.UserEntity;

import java.util.Map;

/**
 * 
 *
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 19:13:46
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

