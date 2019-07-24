package cn.locker.modular.system.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.locker.core.common.page.LayuiPageFactory;
import cn.locker.modular.system.entity.WhiteList;
import cn.locker.modular.system.mapper.WhiteListMapper;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2018-12-07
 */
@Service
public class WhiteListService extends ServiceImpl<WhiteListMapper, WhiteList> {

    /**
     * 根据条件查询角色列表
     * @param subordinateBranch 
     * @param bankCardNumber 
     * @param addUser 
     * @param userName 
     * @param phone 
     *
     * @return
     * 
     */
    public Page<Map<String, Object>> selectWhiteList(String addTime, String phone, String userName, String addUser, String bankCardNumber, String subordinateBranch) {
        String startTime = null;
        String endTime = null;
    	if(StringUtils.isNotEmpty(addTime)) {
    		startTime = addTime.substring(0, 19).trim();
    		endTime = addTime.substring(22).trim();
        }
    	Page<?> page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectWhiteList(page, startTime, endTime, phone, userName, addUser, bankCardNumber, subordinateBranch);
    }
}
