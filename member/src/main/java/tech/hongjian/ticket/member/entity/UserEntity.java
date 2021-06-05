package tech.hongjian.ticket.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author xiahongjian
 * @email hongjian.xia@qq.com
 * @date 2021-06-05 19:13:46
 */
@Data
@TableName("mbr_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private Date createAt;
	/**
	 * 
	 */
	private Integer enable;

}
