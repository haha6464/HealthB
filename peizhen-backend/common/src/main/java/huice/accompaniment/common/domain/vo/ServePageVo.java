package huice.accompaniment.common.domain.vo;

import huice.accompaniment.common.domain.Hospital;
import huice.accompaniment.common.domain.ServeItem;
import huice.accompaniment.common.domain.ServeType;
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
    private ServeType serveType;
    // 服务医院
    private Hospital hospital;
    // 销量
    private Long sold;
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public ServeType getServeType() {
        return serveType;
    }

    public void setServeType(ServeType serveType) {
        this.serveType = serveType;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ServeItem getServeItem() {
        return serveItem;
    }

    public void setServeItem(ServeItem serveItem) {
        this.serveItem = serveItem;
    }
}
