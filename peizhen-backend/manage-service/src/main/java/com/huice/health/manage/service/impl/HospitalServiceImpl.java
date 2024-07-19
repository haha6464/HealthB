package com.huice.health.manage.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.huice.health.common.core.PageImpl;
import com.huice.health.common.domain.Hospital;
import com.huice.health.common.domain.HospitalLabel;
import com.huice.health.common.domain.ToHospital;
import com.huice.health.common.domain.vo.AdminGetHospitalListVo;
import com.huice.health.common.utils.ThreadLocalUtils;
import com.huice.health.manage.mapper.HospitalLabelMapper;
import com.huice.health.manage.mapper.HospitalMapper;
import com.huice.health.manage.mapper.ToHospitalMapper;
import com.huice.health.manage.service.HospitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (Hospital)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-05 15:42:31
 */
@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {
    @Resource
    private HospitalMapper hospitalMapper;
    @Resource
    private HospitalLabelMapper hospitalLabelMapper;
    @Resource
    private ToHospitalMapper toHospitalMapper;


    /**
     * 管理员获取医院集合one
     *
     * @return
     */
    @Override
    public String adminGetHospitalListOne(Long id) {

        List<AdminGetHospitalListVo> list = hospitalMapper.adminGetHospitalListOne(id);


        Map<String, Object> map = new HashMap<>();

        for (AdminGetHospitalListVo adminGetHospitalListVo : list) {
            //获取医院标签
            HospitalLabel hospitalLabel = new HospitalLabel();
            hospitalLabel.setHospitalId(adminGetHospitalListVo.getId());
            List<HospitalLabel> hospitalLabels = hospitalLabelMapper.queryAllByLimit(hospitalLabel, 0, 1000000);
            adminGetHospitalListVo.setLabel(hospitalLabels);
        }

        map.put("list", list);

        return JSONArray.toJSONString(map);
    }

    /**
     * 管理员获取医院集合
     *
     * @return
     */
    @Override
    public String adminGetHospitalList(Integer page, Integer size, Integer status, String name) {
        Long uid = ThreadLocalUtils.getUid();
        List<AdminGetHospitalListVo> list = hospitalMapper.adminGetHospitalList(uid, (page - 1) * size, size, status, name);
        Long count = hospitalMapper.adminGetCount(uid, status, name);

        Map<String, Object> map = new HashMap<>();

        for (AdminGetHospitalListVo adminGetHospitalListVo : list) {
            //获取医院标签
            HospitalLabel hospitalLabel = new HospitalLabel();
            hospitalLabel.setHospitalId(adminGetHospitalListVo.getId());
            List<HospitalLabel> hospitalLabels = hospitalLabelMapper.queryAllByLimit(hospitalLabel, 0, 1000000);
            adminGetHospitalListVo.setLabel(hospitalLabels);
        }
        map.put("count", count);
        map.put("list", list);

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
        return this.hospitalMapper.queryById(id);
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
        long total = this.hospitalMapper.count(hospital);
        List<Hospital> hospitals = this.hospitalMapper.queryAllByLimit(hospital, (page - 1) * size, size);

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
        this.hospitalMapper.insert(hospital);

        this.toHospitalMapper.add(hospital.getId(), uid);

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
        System.err.println("??");
        hospital.setUpdateBy(ThreadLocalUtils.getUid());
        this.hospitalMapper.update(hospital);

        Long aLong = this.hospitalLabelMapper.deleteByHospitalId(hospital.getId());
        System.err.println(aLong);

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
        return this.hospitalMapper.deleteById(id) > 0;
    }

    @Override
    public List<Hospital> adminGetAllHospital() {
        // TODO 鉴权
        Long uid = ThreadLocalUtils.getUid();
        LambdaQueryWrapper<ToHospital> queryWrapper = Wrappers.<ToHospital>lambdaQuery()
                .eq(ToHospital::getUserId, uid);
        List<Hospital> hospitalList = this.toHospitalMapper.selectList(queryWrapper).stream()
                .map(o -> hospitalMapper.queryById(o.getId()))
                .collect(Collectors.toList());
        return hospitalList;
    }
}
