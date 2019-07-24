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
package cn.locker.modular.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.locker.core.base.controller.BaseController;
import cn.locker.core.common.annotion.BussinessLog;
import cn.locker.core.common.annotion.Permission;
import cn.locker.core.common.constant.Const;
import cn.locker.core.common.constant.dictmap.DictMap;
import cn.locker.core.common.constant.factory.ConstantFactory;
import cn.locker.core.common.page.LayuiPageFactory;
import cn.locker.core.log.LogObjectHolder;
import cn.locker.core.reqres.response.ResponseData;
import cn.locker.modular.system.model.DictDto;
import cn.locker.modular.system.service.DictService;
import cn.locker.modular.system.warpper.DictWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 字典控制器
 *
 * @author dengtongzi
 * 
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private String PREFIX = "/modular/system/dict/";

    @Autowired
    private DictService dictService;

    /**
     * 跳转到字典管理首页
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 跳转到添加字典类型
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping("/dict_add_type")
    public String deptAddType() {
        return PREFIX + "dict_add_type.html";
    }

    /**
     * 跳转到添加字典条目
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping("/dict_add_item")
    public String deptAddItem(@RequestParam("dictId") Long dictId, Model model) {
        model.addAttribute("dictTypeId", dictId);
        model.addAttribute("dictTypeName", ConstantFactory.me().getDictName(dictId));
        return PREFIX + "dict_add_item.html";
    }

    /**
     * 新增字典
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping(value = "/add")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData add(DictDto dictDto) {
        this.dictService.addDict(dictDto);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有字典列表
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
        Page<Map<String, Object>> list = this.dictService.list(condition);
        Page<Map<String, Object>> warpper = new DictWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(warpper);
    }

    /**
     * 删除字典记录
     *
     * @author dengtongzi
     * 
     */
    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
    @RequestMapping(value = "/delete")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData delete(@RequestParam Long dictId) {

        //缓存被删除的名称
        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));

        this.dictService.delteDict(dictId);

        return SUCCESS_TIP;
    }

}
