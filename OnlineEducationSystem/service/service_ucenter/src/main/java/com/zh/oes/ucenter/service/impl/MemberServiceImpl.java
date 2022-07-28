package com.zh.oes.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.common.utils.MD5Util;
import com.zh.oes.model.entity.ucenter.Member;
import com.zh.oes.model.vo.ucenter.admin.MemberQueryCondition;
import com.zh.oes.model.vo.ucenter.user.LoginVO;
import com.zh.oes.model.vo.ucenter.user.PasswordVO;
import com.zh.oes.model.vo.ucenter.user.RegisterVO;
import com.zh.oes.ucenter.mapper.MemberMapper;
import com.zh.oes.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 用户登录
     *
     * @param loginVO 登录表单提交数据
     * @return 生成的jwtToken
     */
    @Override
    public String userLogin(LoginVO loginVO) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        // 判断手机号是否在数据库中
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getMobile, mobile);
        Member member = baseMapper.selectOne(wrapper);
        if (member == null) {
            throw new OESException("用户不存在,请注册");
        }
        // 用户名和密码都正确则应该判断用户是否被禁用
        if (!member.getIsEnable()) {
            throw new OESException("用户账号被禁用,请申诉");
        }
        if (!password.equals(member.getPassword())) {
            throw new OESException("密码错误");
        }
        // 登录成功,返回token
        return JwtUtil.getUserJwtToken(String.valueOf(member.getId()), member.getNickname());
    }

    /**
     * 用户注册
     *
     * @param registerVO 注册表单提交数据
     */
    @Override
    public void userRegister(RegisterVO registerVO) {
        // 获取参数
        String mobile = registerVO.getMobile();
        String nickname = registerVO.getNickname();
        String code = registerVO.getCode();
        String password = registerVO.getPassword();

        // 判断手机号是否重复
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getMobile, mobile);
        Member mobileData = baseMapper.selectOne(wrapper);
        if (mobileData != null) throw new OESException("用户已经注册,请登录");

        // 到这里表示输入不为空,验证验证码是否正确,从redis中取
        String MobileCode = redisTemplate.opsForValue().get(mobile);

        if (!code.equals(MobileCode)) {
            throw new OESException("验证码错误");
        }

        // 到这里表示一切正确，将用户数据保存到数据库中
        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(MD5Util.getMD5(password));
        member.setNickname(nickname);
        member.setIsEnable(true);
        member.setAvatar("https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png");
        if (baseMapper.insert(member) < 1)
            throw new OESException("注册失败");
    }

    /**
     * 修改密码
     *
     * @param passwordVO 修改密码表单提交数据
     * @param userMember jwt解析出的用户信息
     */
    @Override
    public void updatePassword(PasswordVO passwordVO, Member userMember) {
        // 获取数据
        String mobile = passwordVO.getMobile();
        String password = passwordVO.getPassword();
        String code = passwordVO.getCode();

        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getMobile, mobile);
        Member member = baseMapper.selectOne(wrapper);
        if (!member.getId().equals(userMember.getId())) {
            throw new OESException("修改用户和当前登录用户不同,请重试");
        }
        // 判断验证码
        // 获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new OESException("验证码验证失败");
        }
        // 真正更新数据(通过设置密码更改,防止其他数据也被更改)
        member.setPassword(password);
        baseMapper.updateById(member);
    }

    /**
     * 注册人数统计
     *
     * @param day 日期
     * @return 统计人数
     */
    @Override
    public int registerCount(String day) {
        return baseMapper.registerCount(day);
    }

    /**
     * 根据微信openId获取用户信息
     *
     * @param openid 微信openId
     * @return 用户实体
     */
    @Override
    public Member getMemberByOpenId(String openid) {
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getOpenid, openid);
        return baseMapper.selectOne(wrapper);
    }

    /**
     * 分页查询用户信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 用户查询条件
     * @return 分页查询得到的用户列表
     */
    @Override
    public Page<Member> pageQueryMember(Long index, Long limit, MemberQueryCondition queryCondition) {
        Page<Member> memberPage = new Page<>(index, limit);
        Page<Member> memberList;
        if (queryCondition == null) {
            memberList = baseMapper.selectPage(memberPage, null);
        } else {
            // 获取查询条件
            String nickname = queryCondition.getNickname();
            Boolean isEnable = queryCondition.getIsEnable();
            String beginTime = queryCondition.getBeginTime();
            String endTime = queryCondition.getEndTime();
            // 设定查询条件
            LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(StringUtils.hasText(nickname), Member::getNickname, nickname);
            wrapper.eq(Objects.nonNull(isEnable), Member::getIsEnable, isEnable);
            wrapper.ge(StringUtils.hasText(beginTime), Member::getCreateTime, beginTime);
            wrapper.le(StringUtils.hasText(endTime), Member::getCreateTime, endTime);
            // 按创建时间排序
            wrapper.orderByDesc(Member::getCreateTime);
            // 数据查询
            memberList = baseMapper.selectPage(memberPage, wrapper);
        }
        return memberList;
    }
}
