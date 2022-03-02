
package com.xhm.rs.common.core;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页辅助类，配合Mybatisplus 里的 .page 使用
 *
 */
public class P {
    

    //当前页
    private static String pagenum="pagenum";

    //当前页条数
    private static String pagesize="pagesize";

    //默认分页设置
    private static long curPage = 1;
    private static long limit = 10;

    //返回IPage<T>  
    public static<T> IPage<T> page(Map<String, Object> params) {
        //分页参数
        if(params.get(pagenum) != null){
            curPage = Long.parseLong((String)params.get(pagenum));
        }
        if(params.get(pagesize) != null){
            limit = Long.parseLong((String)params.get(pagesize));
        }

        //分页对象
        Page<T> page = new Page<>(curPage, limit);

        return page;
    }
}
