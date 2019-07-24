package cn.locker.modular.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典信息
 *
 * @author dengtongzi
 * 
 */
@Data
public class DeptDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long deptId;
    /**
     * 父部门id
     */
    private Long pid;
    /**
     * 父部门名称
     */
    private String pName;
    /**
     * 简称
     */
    private String simpleName;
    /**
     * 全称
     */
    private String fullName;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
}
