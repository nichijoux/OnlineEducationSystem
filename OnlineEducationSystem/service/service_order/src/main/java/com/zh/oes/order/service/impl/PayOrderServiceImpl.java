package com.zh.oes.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.client.EduClient;
import com.zh.oes.common.base.client.UcenterClient;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.model.entity.order.PayOrder;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import com.zh.oes.model.vo.edu.user.MemberVO;
import com.zh.oes.model.vo.order.OrderQueryCondition;
import com.zh.oes.order.mapper.PayOrderMapper;
import com.zh.oes.order.service.PayOrderService;
import com.zh.oes.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {


    private EduClient eduClient;

    private UcenterClient ucenterClient;

    @Autowired
    public void setCourseClient(EduClient eduClient) {
        this.eduClient = eduClient;
    }

    @Autowired
    public void setUcenterClient(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    /**
     * 根据课程id和用户信息生成订单
     *
     * @param courseId 课程id
     * @param request  带有用户信息的请求头
     * @return 生成的订单id
     */
    @Override
    public Long generateOrder(Long courseId, HttpServletRequest request) {
        // 远程调用获取课程信息
        CourseUserInfoVO courseInfo = eduClient.remoteGetCourseInfo(courseId);
        // 远程调用获取用户信息
        Long memberId = Long.valueOf(JwtUtil.getUserIdByJwtToken(request));
        MemberVO memberVO = ucenterClient.remoteGetUser(memberId);
        // 创建order对象,设置所需数据
        PayOrder order = new PayOrder();
        order.setOrderNo(OrderNoUtil.generateOrderNo());
        order.setCourseId(courseId);
        order.setCourseCover(courseInfo.getCover());
        order.setCourseTitle(courseInfo.getTitle());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setTotalFee(courseInfo.getPrice());

        order.setMemberId(memberId);
        order.setMobile(memberVO.getMobile());
        order.setNickname(memberVO.getNickname());
        // 支付类型(1微信,2支付宝)
        order.setPayType(1);
        // 支付状态(0未支付,1已支付)
        order.setStatus(0);

        if (baseMapper.insert(order) < 1) {
            throw new OESException("订单创建失败");
        }
        return order.getOrderNo();
    }

    /**
     * 根据订单号查询订单信息
     *
     * @param orderNo 订单号
     * @return 订单信息
     */
    @Override
    public PayOrder getOrderInfo(Long orderNo) {
        LambdaQueryWrapper<PayOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PayOrder::getOrderNo, orderNo);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 用户是否购买课程
     *
     * @param courseId 课程id
     * @param memberId 用户id
     * @return 是否购买课程
     */
    @Override
    public Boolean haveBuyCourse(Long courseId, Long memberId) {
        LambdaQueryWrapper<PayOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PayOrder::getCourseId, courseId);
        wrapper.eq(PayOrder::getMemberId, memberId);
        wrapper.eq(PayOrder::getStatus, 1);
        return baseMapper.selectCount(wrapper) > 0;
    }

    /**
     * 分页查询订单信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 分页查询获取的订单列表
     */
    @Override
    public Page<PayOrder> pageQueryOrder(Long index, Long limit, OrderQueryCondition queryCondition) {
        Page<PayOrder> orderPage = new Page<>(index, limit);
        // 获取查询条件
        String teacherName = queryCondition.getTeacherName();
        String courseTitle = queryCondition.getCourseTitle();
        Integer status = queryCondition.getStatus();
        String beginTime = queryCondition.getBeginTime();
        String endTime = queryCondition.getEndTime();
        // 设定查询条件
        LambdaQueryWrapper<PayOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(teacherName), PayOrder::getTeacherName, teacherName);
        wrapper.like(StringUtils.hasText(courseTitle), PayOrder::getCourseTitle, courseTitle);
        wrapper.eq(Objects.nonNull(status), PayOrder::getStatus, status);
        wrapper.ge(StringUtils.hasText(beginTime), PayOrder::getCreateTime, beginTime);
        wrapper.le(StringUtils.hasText(endTime), PayOrder::getCreateTime, endTime);
        // 获取数据
        return baseMapper.selectPage(orderPage, wrapper);
    }
}
