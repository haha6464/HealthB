package com.example.baseaccompanying.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.dao.HospitalLabelMapper;
import com.example.baseaccompanying.dao.HospitalMapper;
import com.example.baseaccompanying.dao.ToHospitalMapper;
import com.example.baseaccompanying.service.HospitalService;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.Hospital;
import huice.accompaniment.common.domain.HospitalLabel;
import huice.accompaniment.common.domain.vo.AdminGetHospitalListVo;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Hospital)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-05 15:42:31
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {
    @Resource
    private HospitalMapper hospitalDao;
    @Resource
    private HospitalLabelMapper hospitalLabelMapper;
    @Autowired
    private ToHospitalMapper toHospitalMapper;


    /**
     * 管理员获取医院集合one
     * @return
     */
    @Override
    public String adminGetHospitalListOne(Long id){

        List<AdminGetHospitalListVo> list = hospitalDao.adminGetHospitalListOne(id);


        Map<String, Object> map  =new HashMap<>();

        for (AdminGetHospitalListVo adminGetHospitalListVo : list) {
            //获取医院标签
            HospitalLabel hospitalLabel = new HospitalLabel();
            hospitalLabel.setHospitalId(adminGetHospitalListVo.getId());
            List<HospitalLabel> hospitalLabels = hospitalLabelMapper.queryAllByLimit(hospitalLabel, 0, 1000000);
            adminGetHospitalListVo.setLabel(hospitalLabels);
        }

        map.put("list",list);

        return JSONArray.toJSONString(map);
    }

    /**
     * 管理员获取医院集合
     * @return
     */
    @Override
    public String adminGetHospitalList(Integer page, Integer size,Integer status ,String name){
        Long uid = ThreadLocalUtils.getUid();
        List<AdminGetHospitalListVo> list = hospitalDao.adminGetHospitalList(uid,(page-1)*size,size,status,name);
        Long count = hospitalDao.adminGetCount(uid,status,name);

        Map<String, Object> map  =new HashMap<>();

        for (AdminGetHospitalListVo adminGetHospitalListVo : list) {
            //获取医院标签
            HospitalLabel hospitalLabel = new HospitalLabel();
            hospitalLabel.setHospitalId(adminGetHospitalListVo.getId());
            List<HospitalLabel> hospitalLabels = hospitalLabelMapper.queryAllByLimit(hospitalLabel, 0, 1000000);
            adminGetHospitalListVo.setLabel(hospitalLabels);
        }
        map.put("count",count);
        map.put("list",list);

        return JSONArray.toJSONString(map);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Hospital queryById(Long id) {
        return this.hospitalDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param hospital 筛选条件
     * @param page     页数
     * @param size     页大小
     * @return 查询结果
     */
    @Override
    public PageImpl queryByPage(Hospital hospital, Integer page, Integer size) {
        long total = this.hospitalDao.count(hospital);
        List<Hospital> hospitals = this.hospitalDao.queryAllByLimit(hospital, (page - 1) * size, size);

        return new PageImpl(hospitals, total);
    }

    /**
     * 新增数据
     *
     * @param hospital 实例对象
     * @return 实例对象
     */
    @Override
    public Hospital insert(Hospital hospital) {
        Long uid = ThreadLocalUtils.getUid();
        hospital.setCreateBy(uid);
        this.hospitalDao.insert(hospital);

        this.toHospitalMapper.add(hospital.getId(),uid);

        return hospital;
    }

    /**
     * 修改数据
     *
     * @param hospital 实例对象
     * @return 实例对象
     */
    @Override
    public Hospital update(Hospital hospital) {
        hospital.setUpdateBy(ThreadLocalUtils.getUid());
        this.hospitalDao.update(hospital);

        this.hospitalLabelMapper.deleteByHospitalId(hospital.getId());

        return this.queryById(hospital.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.hospitalDao.deleteById(id) > 0;
    }
}
