package cn.locker.modular.system.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.locker.core.shiro.ShiroKit;
import cn.locker.core.shiro.ShiroUser;
import cn.locker.kernel.model.exception.ServiceException;
import cn.locker.kernel.model.exception.enums.CoreExceptionEnum;
import cn.locker.modular.system.entity.FileInfo;
import cn.locker.modular.system.entity.User;
import cn.locker.modular.system.mapper.FileInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文件信息表
 * 服务实现类
 * </p>
 *
 * @author dengtongzi
 * @since 2018-12-07
 */
@Service
public class FileInfoService extends ServiceImpl<FileInfoMapper, FileInfo> {

    @Autowired
    private UserService userService;

    /**
     * 上传头像
     *
     * @author dengtongzi
     * 
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadAvatar(String avatar) {
        ShiroUser currentUser = ShiroKit.getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        User user = userService.getById(currentUser.getId());

        //保存文件信息
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileId(IdWorker.getIdStr());
        fileInfo.setFileData(avatar);
        this.save(fileInfo);

        //更新用户的头像
        user.setAvatar(fileInfo.getFileId());
        userService.updateById(user);
    }
}
