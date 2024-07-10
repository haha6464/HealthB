package huice.accompaniment.common.domain.vo;

import huice.accompaniment.common.domain.Hospital;
import huice.accompaniment.common.domain.ServeItem;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Doge2077 2024/7/10
 * 后台管理端-服务分页返回视图
 */
public class ServePageVo {
    // 服务项目
    private ServeItem serveItem;
    // 服务类型
    private StringType stringType;
    // 服务医院
    private Hospital hospital;
    // 销量
    private Long sold;
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
