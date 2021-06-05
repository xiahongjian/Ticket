package tech.hongjian.ticket.member.dao;

import tech.hongjian.ticket.member.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 19:13:46
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
