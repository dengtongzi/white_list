package cn.locker.modular.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.locker.core.common.exception.BizExceptionEnum;
import cn.locker.core.common.page.LayuiPageFactory;
import cn.locker.core.util.ToolUtil;
import cn.locker.kernel.model.exception.ServiceException;
import cn.locker.modular.system.entity.Dict;
import cn.locker.modular.system.mapper.DictMapper;
import cn.locker.modular.system.model.DictDto;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2018-12-07
 */
@Service
public class DictService extends ServiceImpl<DictMapper, Dict> {

    @Resource
    private DictMapper dictMapper;

    /**
     * 添加字典
     *
     * @author dengtongzi
     * 
     */
    public void addDict(DictDto dictDto) {

        if (ToolUtil.isOneEmpty(dictDto, dictDto.getCode(), dictDto.getName())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        if (ToolUtil.isEmpty(dictDto.getDictTypeId())) {
            this.addDictType(dictDto);
        } else {
            this.addDictItem(dictDto);
        }
    }

    /**
     * 添加字典类型
     *
     * @author dengtongzi
     * 
     */
    private void addDictType(DictDto dictDto) {
        Dict dict = new Dict();
        BeanUtil.copyProperties(dictDto, dict);

        //类型的父级id都为0
        dict.setPid(0L);

        this.save(dict);
    }

    /**
     * 添加字典子类型
     *
     * @author dengtongzi
     * 
     */
    private void addDictItem(DictDto dictDto) {
        Dict dict = new Dict();
        BeanUtil.copyProperties(dictDto, dict);

        //字典的父级id为字典tyeId
        dict.setPid(dictDto.getDictTypeId());

        this.save(dict);
    }

    /**
     * 删除字典
     *
     * @author dengtongzi
     * 
     */
    @Transactional
    public void delteDict(Long dictId) {

        //删除这个字典的子词典
        QueryWrapper<Dict> dictEntityWrapper = new QueryWrapper<>();
        dictEntityWrapper = dictEntityWrapper.eq("PID", dictId);
        dictMapper.delete(dictEntityWrapper);

        //删除这个词典
        dictMapper.deleteById(dictId);
    }

    /**
     * 根据编码获取词典列表
     *
     * @author dengtongzi
     * 
     */
    public List<Dict> selectByCode(String code) {
        return this.baseMapper.selectByCode(code);
    }

    /**
     * 根据父类编码获取词典列表
     *
     * @author dengtongzi
     * 
     */
    public List<Dict> selectByParentCode(String code) {
        return this.baseMapper.selectByParentCode(code);
    }

    /**
     * 查询字典列表
     *
     * @author dengtongzi
     * 
     */
    public Page<Map<String, Object>> list(String conditiion) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, conditiion);
    }
}
