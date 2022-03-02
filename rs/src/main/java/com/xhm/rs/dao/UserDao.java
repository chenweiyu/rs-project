package com.xhm.rs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xhm.rs.entity.User;

/**
 * dao
 * 
 * BaseMapper
 * 
 * 1.继承了哪些方法，直接使用
 * 
 * 2.自定义SQL怎么写 ，--》xml
 * 
 */
public interface UserDao extends BaseMapper<User> {



    // /**
    //  * 多表 分页 条件-->所有复杂查询
    //  * 
    //  * @param page   分页
    //  * @param params  别名 map  条件
    //  * @return 返回多表字段 ，kv
    //  * 
    //  * 
    //  */
   
    // public IPage<Map<String, Object>> daoQ1(IPage<Map<String, Object>> page, @Param("map") Map<String, Object> params);

}
