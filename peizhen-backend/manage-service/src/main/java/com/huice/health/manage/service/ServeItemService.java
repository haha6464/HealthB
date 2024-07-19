package com.huice.health.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huice.health.common.domain.ServeItem;
import org.springframework.data.domain.Page;

/**
 * 服务项表(ServeItem)表服务接口
 *
 * @author Doge2077
 * @since 2024-06-04 17:40:17
 */
public interface ServeItemService extends IService<ServeItem> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ServeItem queryById(Long id);

    /**
     * 分页查询
     *
     * @param serveItem
     * @param page
     * @param size
     * @return
     */
    Page<ServeItem> queryByPage(ServeItem serveItem, Integer page, Integer size);

    /**
     * 新增数据
     *
     * @param serveItem 实例对象
     * @return 实例对象
     */
    ServeItem insert(ServeItem serveItem);

    /**
     * 修改数据
     *
     * @param serveItem 实例对象
     * @return 实例对象
     */
    ServeItem update(ServeItem serveItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过主键启用服务项
     *
     * @param id 服务项 id
     * @return 是否成功
     */
    boolean activeById(Long id);

    /**
     * 通过主键禁用服务项
     *
     * @param id 服务项 id
     * @return 禁用是否成功
     */
    boolean deactivateById(Long id);

    /**
     * 管理员添加服务项
     *
     * @param img         服务项图片
     * @param serveName   服务项名称
     * @param ServeTypeId 服务类型id
     * @param onSaleFlag  上架状态
     * @return 服务项
     */
    ServeItem addminAddServeItem(String img, String serveName, Long ServeTypeId, Integer onSaleFlag);

    /**
     * 根据服务项 id 查询服务项
     *
     * @param id 服务项 id
     * @return 服务项
     */
    public ServeItem findServeItemById(Long id);
}
