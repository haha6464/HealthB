package com.example.baseorder.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.baseorder.pojo.DTO.GoodsCreateReqDTO;
import com.example.baseorder.pojo.DTO.GoodsCreateResDTO;
import com.example.baseorder.pojo.DTO.GoodsUpdateReqDTO;

import java.util.List;

/**
 * @Description
 * @Author welsir
 * @Date 2024/7/2 12:57
 */
public interface GoodsService {

    GoodsCreateResDTO createGoods(GoodsCreateReqDTO reqDTO);

    List<GoodsCreateResDTO> queryGoodsAll();

    Page<GoodsCreateResDTO> queryGoodsByPage(Long limit,Long page);

    Boolean deleteGoodsById(Long id);

    Boolean updateGoodsById(GoodsUpdateReqDTO reqDTO);

}
