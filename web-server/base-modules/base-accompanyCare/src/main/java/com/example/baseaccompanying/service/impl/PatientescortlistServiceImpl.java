package com.example.baseaccompanying.service.impl;

import com.example.baseaccompanying.dao.PatientescortlistMapper;
import com.example.baseaccompanying.service.PatientescortlistService;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.Patientescortlist;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Patientescortlist)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
@Service("patientescortlistService")
public class PatientescortlistServiceImpl implements PatientescortlistService {
    @Resource
    private PatientescortlistMapper patientescortlistDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Patientescortlist queryById(Long id) {
        return this.patientescortlistDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param patientescortlist 筛选条件
     * @param page              页数
     * @param size              页大小
     * @return 查询结果
     */
    @Override
    public PageImpl<?> queryByPage(Patientescortlist patientescortlist, Integer page, Integer size) {
        long total = this.patientescortlistDao.count(patientescortlist);
        List<Patientescortlist> patientescortlists = this.patientescortlistDao.queryAllByLimit(patientescortlist, page, size);
        PageImpl pageRequest = new PageImpl(patientescortlists, total);
        return pageRequest;
    }

    /**
     * 新增数据
     *
     * @param patientescortlist 实例对象
     * @return 实例对象
     */
    @Override
    public Patientescortlist insert(Patientescortlist patientescortlist) {
        this.patientescortlistDao.insert(patientescortlist);
        return patientescortlist;
    }

    /**
     * 修改数据
     *
     * @param patientescortlist 实例对象
     * @return 实例对象
     */
    @Override
    public Patientescortlist update(Patientescortlist patientescortlist) {
        this.patientescortlistDao.update(patientescortlist);
        return this.queryById(patientescortlist.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.patientescortlistDao.deleteById(id) > 0;
    }
}
