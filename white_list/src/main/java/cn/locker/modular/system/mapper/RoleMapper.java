package cn.locker.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.locker.core.common.node.ZTreeNode;
import cn.locker.modular.system.entity.Role;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author dengtongzi
 * @since 2018-12-07
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据条件查询角色列表
     *
     * @return
     * 
     */
    Page<Map<String, Object>> selectRoles(@Param("page") Page page, @Param("condition") String condition);

    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     * @return
     * 
     */
    int deleteRolesById(@Param("roleId") Long roleId);

    /**
     * 获取角色列表树
     *
     * @return
     * 
     */
    List<ZTreeNode> roleTreeList();

    /**
     * 获取角色列表树
     *
     * @return
     * 
     */
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);

}
