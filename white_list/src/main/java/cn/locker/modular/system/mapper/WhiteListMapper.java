package cn.locker.modular.system.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.locker.modular.system.entity.WhiteList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dengtongzi
 * @since 2019-07-20
 */
public interface WhiteListMapper extends BaseMapper<WhiteList> {

	/**
     * 根据条件查询列表
	 * @param subordinateBranch 
	 * @param bankCardNumber 
	 * @param addUser 
	 * @param userName 
	 * @param phone 
	 * @param endTime 
     * @param startTime 
     * @return
     * 
     */
    Page<Map<String, Object>> selectWhiteList(@Param("page") Page<?> page, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("phone") String phone, @Param("userName") String userName, @Param("addUser") String addUser, @Param("bankCardNumber") String bankCardNumber, @Param("subordinateBranch") String subordinateBranch);

}
