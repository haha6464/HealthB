package com.example.baseaccompanying.service.impl;

import com.example.baseaccompanying.mapper.HospitalLabelMapper;
import com.example.baseaccompanying.service.HospitalLabelService;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.HospitalLabel;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (HospitalLabel)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-03
 */
@Service("hospitalLabelService")
public class HospitalLabelServiceImpl implements HospitalLabelService {
    @Autowired
    private HospitalLabelMapper hospitalLabelDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public HospitalLabel queryById(Long id) {
        return this.hospitalLabelDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hospitalLabel 筛选条件
     * @param page          页数
     * @param size          页大小
     * @return 查询结果
     */
    @Override
    public PageImpl queryByPage(HospitalLabel hospitalLabel, Integer page, Integer size) {
        long total = this.hospitalLabelDao.count(hospitalLabel);
        List<HospitalLabel> hospitalLabels = this.hospitalLabelDao.queryAllByLimit(hospitalLabel, (page - 1) * size, size);
        return new PageImpl(hospitalLabels, total);
    }

    /**
     * 新增数据
     *
     * @param hospitalLabel 实例对象
     * @return 实例对象
     */
    @Override
    public HospitalLabel insert(HospitalLabel hospitalLabel) {
        hospitalLabel.setCreateBy(ThreadLocalUtils.getUid());
        this.hospitalLabelDao.insert(hospitalLabel);
        return hospitalLabel;
    }

    /**
     * 修改数据
     *
     * @param hospitalLabel 实例对象
     * @return 实例对象
     */
    @Override
    public HospitalLabel update(HospitalLabel hospitalLabel) {
        this.hospitalLabelDao.update(hospitalLabel);
        return this.queryById(hospitalLabel.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hospitalLabelDao.deleteById(id) > 0;
    }
}
