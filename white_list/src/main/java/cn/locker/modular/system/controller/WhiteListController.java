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

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.locker.core.base.controller.BaseController;
import cn.locker.core.common.annotion.Permission;
import cn.locker.core.common.exception.BizExceptionEnum;
import cn.locker.core.common.page.LayuiPageFactory;
import cn.locker.core.poi.ExcelSheetPO;
import cn.locker.core.poi.ExcelUtil;
import cn.locker.core.poi.FileUtil;
import cn.locker.kernel.model.exception.ServiceException;
import cn.locker.modular.system.entity.WhiteList;
import cn.locker.modular.system.service.WhiteListService;
import cn.locker.modular.system.warpper.WhiteListWrapper;

/**
 * 白名单控制器
 *
 * @author dengtongzi
 * 
 */
@Controller
@RequestMapping("/white_list")
public class WhiteListController extends BaseController {

    private static String PREFIX = "/modular/system/white_list";
    
    @Autowired
    private WhiteListService whiteListService;

    /**
     * 跳转到角色列表页面
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/white_list.html";
    }

    /**
     * 获取列表
     *
     * @author dengtongzi
     * 
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "addTime", required = false) String addTime, @RequestParam(value = "phone", required = false) String phone
    		,@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "addUser", required = false) String addUser
    		,@RequestParam(value = "bankCardNumber", required = false) String bankCardNumber, @RequestParam(value = "subordinateBranch", required = false) String subordinateBranch) {
        Page<Map<String, Object>> whiteList = this.whiteListService.selectWhiteList(addTime, phone, userName, addUser, bankCardNumber, subordinateBranch);
        Page<Map<String, Object>> wrap = new WhiteListWrapper(whiteList).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
    
    /**
     * 导入白名单
     *
     * @author dengtongzi
     * 
     */
    @RequestMapping("/importFile")
    @ResponseBody
    public Object uploadPicture(@RequestPart("file") MultipartFile file) {
    	 File f = null;
         if(file.equals("") || file.getSize() <= 0){
             file = null;
         }else{
        	 InputStream ins;
        	 try {
				ins = file.getInputStream();
				f = new File(file.getOriginalFilename());
				FileUtil.inputStreamToFile(ins, f);
				List<ExcelSheetPO> list = ExcelUtil.readExcel(f, 10000, 6);
				if(CollectionUtils.isEmpty(list)) {
					throw new ServiceException(BizExceptionEnum.IMPORT_ERROR);
				}else {
					List<List<Object>> saveL = list.get(0).getDataList();
					for (int i = 1; i < saveL.size(); i++) {
						WhiteList whiteList = new WhiteList();
						whiteList.setPhone(saveL.get(i).get(0).toString());
						whiteList.setUserName(saveL.get(i).get(1).toString());
						whiteList.setBankCardNumber(saveL.get(i).get(2).toString());
						whiteList.setSubordinateBranch(saveL.get(i).get(3).toString());
						whiteList.setBranchNetwork(saveL.get(i).get(4).toString());
						whiteList.setAddUser(saveL.get(i).get(5).toString());
						whiteList.setAddTime(new Date());
						whiteListService.save(whiteList);
					}
				}
	         } catch (Exception e) {
	             throw new ServiceException(BizExceptionEnum.IMPORT_ERROR);
	         }
         }

        return SUCCESS_TIP;
    }
}
