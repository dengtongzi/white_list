/**
 * Copyright 2018-2020 dengtongzi  (https://gitee.com/dengtongzi)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.locker.core.common.page;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.locker.core.util.HttpContext;

/**
 * Layui Table默认的分页参数创建
 *
 * @author dengtongzi
 * 
 */
public class LayuiPageFactory {

    /**
     * 获取layui table的分页参数
     *
     * @author dengtongzi
     * 
     */
    public static Page defaultPage() {
        HttpServletRequest request = HttpContext.getRequest();

        //每页多少条数据
        int limit = Integer.valueOf(request.getParameter("limit"));

        //第几页
        int page = Integer.valueOf(request.getParameter("page"));

        return new Page(page, limit);
    }

    /**
     * 创建layui能识别的分页响应参数
     *
     * @author dengtongzi
     * 
     */
    public static LayuiPageInfo createPageInfo(IPage page) {
        LayuiPageInfo result = new LayuiPageInfo();
        result.setCount(page.getTotal());
        result.setData(page.getRecords());
        return result;
    }
}
