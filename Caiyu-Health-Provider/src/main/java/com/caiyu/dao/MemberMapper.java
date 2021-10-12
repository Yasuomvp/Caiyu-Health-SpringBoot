package com.caiyu.dao;

import com.caiyu.pojo.Member;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MemberMapper {

    @Select("select * from t_member")
    public List<Member> findAll();

    @Select({
            "<script>" ,
            "select * from t_member",
            "<if test='queryString != null and queryString.length>0'>",
            "where fileNumber = #{queryString} or phoneNumber = #{queryString} or name = #{queryString}",
            "</if>",
            "</script>"
    })
    public Page<Member> selectByCondition(String queryString);

    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("insert into t_member (fileNumber,name,sex,idCard,phoneNumber,regTime,password,email,birthday,remark) values (#{fileNumber},#{name},#{sex},#{idCard},#{phoneNumber},#{regTime},#{password},#{email},#{birthday},#{remark})")
    public void add(Member member);

    @Delete("delete from t_member where id = #{id}")
    public void deleteById(Integer id);

    @Select("select * from t_member where id = #{id}")
    public Member findById(Integer id);

    @Select("select * from t_member where phoneNumber = #{telephone}")
    public Member findByTelephone(String telephone);

    @Update({
            "<script>" ,
            "update t_member set",
            "<if test='fileNumber != null'>",
            "fileNumber = #{fileNumber},",
            "</if>",
            "<if test='name != null'>",
            "name = #{name},",
            "</if>",
            "<if test='idCard != null'>",
            "idCard = #{idCard},",
            "</if>",
            "<if test='phoneNumber != null'>",
            "phoneNumber = #{phoneNumber},",
            "</if>",
            "<if test='regTime != null'>",
            "regTime = #{regTime},",
            "</if>",
            "<if test='password != null'>",
            "password = #{password},",
            "</if>",
            "<if test='email != null'>",
            "email = #{email},",
            "</if>",
            "<if test='birthday != null'>",
            "birthday = #{birthday}",
            "</if>",
            "<if test='remark != null'>",
            "remark = #{remark}",
            "</if>",
            "where id = #{id}",
            "</script>"
    })
    public void edit(Member member);

    @Select("select count(id) from t_member where date_format(t_member.regTime, '%y-%m-%d') &lt; = date_format(#{date}, '%y-%m-%d')")
    public Integer findMemberCountBeforeDate(String date);

    @Select("select count(id) from t_member where date_format(t_member.regTime, '%y-%m-%d')  = date_format(#{date}, '%y-%m-%d')")
    public Integer findMemberCountByDate(String date);

    @Select("select count(id) from t_member where date_format(t_member.regTime, '%y-%m-%d') &gt; = date_format(#{date}, '%y-%m-%d')")
    public Integer findMemberCountAfterDate(String date);

    @Select("select count(id) from t_member")
    public Integer findMemberTotalCount();

}
