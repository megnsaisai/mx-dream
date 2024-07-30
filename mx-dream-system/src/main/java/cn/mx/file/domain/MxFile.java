package cn.mx.file.domain;

import cn.mx.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
@Data
@TableName("mx_file")
public class MxFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件的唯一标识符
     */
    @TableId(value = "file_id", type = IdType.ASSIGN_ID)
    private Long fileId;

    /**
     * 文件的名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 歌手
     */
    @TableField("file_singer")
    private String fileSinger;

    /**
     * 歌曲图
     */
    @TableField("file_img")
    private String fileImg;

    /**
     * 文件在服务器上的存储路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件的类型，例如pdf、jpg、png等
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}
