package cn.mx.file.service;

import cn.mx.file.domain.MxFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
public interface MxFileService extends IService<MxFile> {


    /**
     * 查询文件通过文件ID
     * @param fileId
     * @return
     */
    MxFile selectMxFileByFileId(Long fileId);
}
