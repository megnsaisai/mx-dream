package cn.mx.file.service.impl;

import cn.mx.file.domain.MxFile;
import cn.mx.file.mapper.MxFileMapper;
import cn.mx.file.service.MxFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
@Service
public class MxFileServiceImpl extends ServiceImpl<MxFileMapper, MxFile> implements MxFileService {

    /**
     * 查询文件通过文件ID
     *
     * @param fileId
     * @return
     */
    @Override
    public MxFile selectMxFileByFileId(Long fileId) {
        return this.getById(fileId);
    }
}
