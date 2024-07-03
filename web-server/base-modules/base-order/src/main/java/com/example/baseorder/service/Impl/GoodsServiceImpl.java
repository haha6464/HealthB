package com.example.baseorder.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.baseorder.mapper.GoodsMapper;
import com.example.baseorder.mapper.ServiceTypeMapper;
import com.example.baseorder.pojo.DO.GoodsDO;
import com.example.baseorder.pojo.DTO.GoodsCreateReqDTO;
import com.example.baseorder.pojo.DTO.GoodsCreateResDTO;
import com.example.baseorder.pojo.DTO.GoodsUpdateReqDTO;
import com.example.baseorder.pojo.GoodsType;
import com.example.baseorder.service.GoodsService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import huice.accompaniment.common.utils.snowflake.Snowflake;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 13:06
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsMapper goodsMapper;

    private Snowflake snowflake;
    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper,Snowflake snowflake){
        this.goodsMapper = goodsMapper;
        this.snowflake = snowflake;
    }

    @Override
    public GoodsCreateResDTO createGoods(GoodsCreateReqDTO reqDTO) {
        GoodsType goodsType = GoodsType.fromCode(reqDTO.getServiceId());
        GoodsDO goods = GoodsDO.builder()
                .id(snowflake.generate())
                .name(reqDTO.getName())
                .description(reqDTO.getDescription())
                .price(reqDTO.getPrice())
                .createBy(reqDTO.getUserId())
                .updateBy(reqDTO.getUserId())
                .picture(reqDTO.getPicture())
                .createTime(LocalDate.now())
                .updateTime(LocalDate.now())
                .typeId(goodsType.getCode())
                .doctorId(reqDTO.getDoctorId())
                .hospitalId(reqDTO.getHospitalId())
                .deleteFlag(false)
                .build();
        if(goodsType.getCode().equals(GoodsType.JOIN_GROUP.getCode())){
            goods.setRemainNum(reqDTO.getGoodsNum());
        }else{
            goods.setRemainNum(1);
        }
        goodsMapper.insert(goods);
        return GoodsCreateResDTO.convertGoodsDO(goods);
    }

    @Override
    public List<GoodsCreateResDTO> queryGoodsAll() {
        List<GoodsCreateResDTO> res = goodsMapper.selectList(new QueryWrapper<GoodsDO>().eq("delete_flag",true))
                .stream()
                .map(GoodsCreateResDTO::convertGoodsDO)
                .collect(Collectors.toList());
        return res;
    }

    @Override
    public Page<GoodsCreateResDTO> queryGoodsByPage(Long limit, Long page) {
        QueryWrapper<GoodsDO> wrapper = new QueryWrapper<GoodsDO>().eq("delete_flag",false);
        Page<GoodsDO> selectedPage = goodsMapper.selectPage(new Page<>(page, limit,false), wrapper);
        List<GoodsDO> records = selectedPage.getRecords();
        List<GoodsCreateResDTO> resDTOList = records.stream()
                .map(GoodsCreateResDTO::convertGoodsDO)
                .collect(Collectors.toList());
        Page<GoodsCreateResDTO> resultPage = new Page<>(selectedPage.getCurrent(), selectedPage.getSize());
        resultPage.setRecords(resDTOList);
        resultPage.setTotal(selectedPage.getTotal());
        resultPage.setPages(selectedPage.getPages());
        return resultPage;
    }

    @Override
    public Boolean deleteGoodsById(Long id) {
        GoodsDO goodsDO = goodsMapper.selectById(id);
        Assert.notNull(goodsDO,"not find the goods!");
        return true;
    }

    @Override
    public Boolean updateGoodsById(GoodsUpdateReqDTO reqDTO) {
        GoodsDO goodsDO = goodsMapper.selectById(reqDTO.getGoodsId());
        Assert.notNull(goodsDO,"not find goods!");
        UpdateWrapper<GoodsDO> wrapper = new UpdateWrapper<GoodsDO>()
                .set("update_by", reqDTO.getUserId())
                .set("name", reqDTO.getName())
                .set("description", reqDTO.getDescription())
                .set("price", reqDTO.getPrice())
                .set("picture", reqDTO.getPicture())
                .set("remain_num", reqDTO.getGoodsNum())
                .set("update_time", LocalDate.now());
        goodsMapper.update(wrapper);
        return true;
    }
}
