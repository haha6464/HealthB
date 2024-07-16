package com.example.baseaccompanying.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.baseaccompanying.dao.HospitalMapper;
import com.example.baseaccompanying.dao.PatientescortlistMapper;
import com.example.baseaccompanying.service.PatientescortlistService;
import huice.accompaniment.common.constant.ErrorInfo;
import huice.accompaniment.common.core.PageImpl;
import huice.accompaniment.common.domain.Patientescortlist;
import huice.accompaniment.common.domain.vo.AdminGetHospitalListVo;
import huice.accompaniment.common.domain.vo.AdminGetPatientEscortListVo;
import huice.accompaniment.common.enums.DelFlagEnum;
import huice.accompaniment.common.enums.PatientEscortStatus;
import huice.accompaniment.common.exception.BadRequestException;
import huice.accompaniment.common.utils.ThreadLocalUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (Patientescortlist)表服务实现类
 *
 * @author Yida Yang
 * @since 2024-06-11 09:50:27
 */
@Service("patientescortlistService")
public class PatientescortlistServiceImpl extends ServiceImpl<PatientescortlistMapper, Patientescortlist> implements PatientescortlistService {
    @Resource
    private PatientescortlistMapper patientescortlistMapper;

    @Resource
    private HospitalMapper hospitalMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Patientescortlist queryById(Long id) {
        return this.patientescortlistMapper.queryById(id);
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
        long total = this.patientescortlistMapper.count(patientescortlist);
        List<Patientescortlist> patientescortlists = this.patientescortlistMapper.queryAllByLimit(patientescortlist, page, size);
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
        this.patientescortlistMapper.insert(patientescortlist);
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
        this.patientescortlistMapper.update(patientescortlist);
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
        return this.patientescortlistMapper.deleteById(id) > 0;
    }

    @Override
    public PageImpl<?> adminGetPatientEscortList(Integer offset, Integer limit) {
        // TODO 鉴权
        Long uid = ThreadLocalUtils.getUid();

        long total = patientescortlistMapper.countPatientescortList(uid);
        if (total == 0) {
            return new PageImpl<>(null, total);
        }

        // 获取管理员关联医院下的陪诊师
        List<Patientescortlist> patientescortlists = patientescortlistMapper.addminGetPatientescortListByUidPage(uid, offset, limit);

        List<AdminGetPatientEscortListVo> result = patientescortlists.stream().map(o -> {
            AdminGetPatientEscortListVo adminGetPatientEscortListVo = new AdminGetPatientEscortListVo();
            adminGetPatientEscortListVo.setPatientescortlist(o);
            adminGetPatientEscortListVo.setHospital(this.hospitalMapper.getHospitalByEscortId(o.getId()));
            adminGetPatientEscortListVo.setCreateTime(o.getCreateTime());
            return adminGetPatientEscortListVo;
        }).collect(Collectors.toList());
        return new PageImpl<>(result, total);
    }

    @Override
    public PageImpl<AdminGetPatientEscortListVo> adminFindEscortByNameAndSexAndHospital(String escortName, String escortSex, Long hospitalId, Integer offset, Integer limit) {
        // TODO 鉴权
        Long uid = ThreadLocalUtils.getUid();
        // 查询总数量
        long total = this.patientescortlistMapper.countAdminFindEscort(uid, escortName, escortSex, hospitalId);
        if (total == 0) {
            return new PageImpl<>(null, total);
        }
        List<AdminGetPatientEscortListVo> result = this.patientescortlistMapper.getAdminFindEscortPage(uid, escortName, escortSex, hospitalId, offset, limit).stream()
                .map(o -> {
                    AdminGetPatientEscortListVo adminGetPatientEscortListVo = new AdminGetPatientEscortListVo();
                    adminGetPatientEscortListVo.setPatientescortlist(o);
                    adminGetPatientEscortListVo.setHospital(this.hospitalMapper.getHospitalByEscortId(o.getId()));
                    adminGetPatientEscortListVo.setCreateTime(o.getCreateTime());
                    return adminGetPatientEscortListVo;
                }).collect(Collectors.toList());
        return new PageImpl<>(result, total);
    }

    @Override
    public boolean activeByUserId(Long userId) {
        LambdaUpdateWrapper<Patientescortlist> updateWrapper = Wrappers.<Patientescortlist>lambdaUpdate()
                .eq(Patientescortlist::getUserId, userId)
                .eq(Patientescortlist::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus())
                .set(Patientescortlist::getStatus, PatientEscortStatus.ACTIVE.getStatus());
        return super.update(updateWrapper);
    }

    @Override
    public boolean deactiveByUserId(Long userId) {
        LambdaUpdateWrapper<Patientescortlist> updateWrapper = Wrappers.<Patientescortlist>lambdaUpdate()
                .eq(Patientescortlist::getUserId, userId)
                .eq(Patientescortlist::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus())
                .set(Patientescortlist::getStatus, PatientEscortStatus.INACTIVE.getStatus());
        return super.update(updateWrapper);
    }

    @Override
    public boolean veryfyPatientEscort(Patientescortlist patientescortlist) {
        // 校验该陪诊师是否存在，信息是否完善
        Long userId = patientescortlist.getUserId();
        Long count = baseMapper.selectCount(Wrappers.<Patientescortlist>lambdaQuery()
                .eq(Patientescortlist::getDelFlag, DelFlagEnum.NOT_DELETE.getStatus())
                .eq(Patientescortlist::getUserId, userId));
        if (count == 0) {
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL + " 不存在该陪诊师");
        }
        // TODO 检查注册填写信息是否完整

        // TODO 校验实名信息

        // TODO 陪诊师资质审核

        // 通过审核，修改状态为已认证
        patientescortlist.setStatus(PatientEscortStatus.VERIFIED.getStatus());
        return baseMapper.updateById(patientescortlist) > 0;
    }
}
