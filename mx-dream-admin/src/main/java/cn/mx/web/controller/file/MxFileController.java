package cn.mx.web.controller.file;

import cn.mx.common.core.controller.BaseController;
import cn.mx.common.core.domain.AjaxResult;
import cn.mx.common.core.page.TableDataInfo;
import cn.mx.common.utils.SecurityUtils;
import cn.mx.file.domain.MxFile;
import cn.mx.file.service.MxFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 类名: MxFileController
 *
 * @version 1.0
 * @since 2024-07-17
 */

@RestController
@RequestMapping("/mx/song")
public class MxFileController extends BaseController {


    private MxFileService mxFileService;

    @Autowired
    public void setMxFileService(MxFileService mxFileService) {
        this.mxFileService = mxFileService;
    }
    @PreAuthorize("@ss.hasPermi('mx:song:list')")
    @GetMapping("/listRows")
    public TableDataInfo listRows(MxFile mxFile){
        startPage();
        List<MxFile> list = mxFileService.list();
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('mx:song:list')")
    @GetMapping("/list")
    public AjaxResult list(MxFile mxFile){
        List<MxFile> list = mxFileService.list();
        return AjaxResult.success(list);
    }


    /**
     * 查询歌曲
     */
    @PreAuthorize("@ss.hasPermi('mx:song:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable Long fileId) {
        return AjaxResult.success(mxFileService.selectMxFileByFileId(fileId));
    }


    /**
     * 修改歌曲
     * @param mxFile
     * @return
     */
    @PutMapping
    @PreAuthorize("@ss.hasPermi('mx:song:edit')")
    public AjaxResult updateMxFile(@RequestBody MxFile mxFile){
        mxFile.setFileType("4");
        return AjaxResult.success(mxFileService.updateById(mxFile));
    }


    /**
     * 添加歌曲
     * @param mxFile
     * @return
     */
    @PostMapping
    @PreAuthorize("@ss.hasPermi('mx:song:add')")
    public AjaxResult addMxFile(@RequestBody MxFile mxFile){
        mxFile.setCreateBy(SecurityUtils.getUsername());
        mxFile.setCreateTime(new Date());
        return AjaxResult.success(mxFileService.save(mxFile));
    }

    /**
     * 删除歌曲
     */
    @DeleteMapping(value = "/{fileId}")
    @PreAuthorize("@ss.hasPermi('mx:song:remove')")
    public AjaxResult removeFile(@PathVariable Long fileId) {
        return AjaxResult.success(mxFileService.removeById(fileId));
    }

}
