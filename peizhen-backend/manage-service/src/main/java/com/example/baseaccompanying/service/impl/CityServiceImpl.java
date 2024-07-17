package com.example.baseaccompanying.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.example.baseaccompanying.dao.CityMapper;
import com.example.baseaccompanying.service.CityService;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.City;
import huice.accompaniment.common.domain.vo.GetCityListVo;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 城市(City)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-04 13:08:26
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Resource
    private CityMapper cityDao;

    /**
     * 获取城市集合
     * @return
     */
    @Override
    public String getCityList() {
        List<GetCityListVo> list = cityDao.getCityList();
        return JSONArray.toJSONString(list);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public City queryById(Long id) {
        return this.cityDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param city 筛选条件
     * @param page 页数
     * @param size 页大小
     * @return 查询结果
     */
    @Override
    public PageImpl queryByPage(City city, Integer page, Integer size) {
        long total = this.cityDao.count(city);
        List<City> cities = this.cityDao.queryAllByLimit(city, (page - 1) * size, size);

        PageImpl page1 = new PageImpl(cities, total);
        return page1;
    }

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    @Override
    public City insert(City city) {
        city.setCreateBy(Long.valueOf(ThreadLocalUtils.getUid()));
        this.cityDao.insert(city);
        return city;
    }

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    @Override
    public City update(City city) {
        city.setUpdateBy(ThreadLocalUtils.getUid());
        this.cityDao.update(city);
        return this.queryById(city.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.cityDao.deleteById(id) > 0;
    }
}
