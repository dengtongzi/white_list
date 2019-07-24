package cn.locker.modular.system.warpper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.locker.core.base.warpper.BaseControllerWrapper;
import cn.locker.core.common.constant.factory.ConstantFactory;
import cn.locker.core.util.ToolUtil;
import cn.locker.kernel.model.page.PageResult;

/**
 * 部门列表的包装
 *
 * @author dengtongzi
 * 
 */
public class DeptWrapper extends BaseControllerWrapper {

    public DeptWrapper(Map<String, Object> single) {
        super(single);
    }

    public DeptWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public DeptWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public DeptWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

	@Override
    protected void wrapTheMap(Map<String, Object> map) {
        Long pid = (Long) map.get("pid");

        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            map.put("pName", "--");
        } else {
            map.put("pName", ConstantFactory.me().getDeptName(pid));
        }
    }
}
