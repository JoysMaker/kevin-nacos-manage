package com.kevin.usc.common.config.db;

import java.util.ArrayList;
import java.util.List;

public class DynamicDataSourceContextHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>(){

        /** * 将 master 数据源的 key 作为默认数据源的 key */
        @Override
        protected String initialValue() {
            return "default";
        }
    };

    /** * 数据源的 key 集合，用于切换时判断数据源是否存在 */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**转换数据库标识key到本地副本ThreadLocal,切换数据库 */
    public static void setDataSourceKey(String key){contextHolder.set(key);}

    /**获取数据库标识*/
    public static String getDataSourceKey(){return contextHolder.get();}

    /**清除数据恢复默认值*/
    public static void clearDataSourceKey(){contextHolder.remove();}

    /**是否包含数据库,防止传入未知数据库*/
    public static Boolean containDataSourceKey(String key){return dataSourceKeys.contains(key);}


}
