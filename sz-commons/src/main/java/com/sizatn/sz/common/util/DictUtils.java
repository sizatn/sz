package com.sizatn.sz.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.sizatn.sz.common.model.SCache;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据字典工具类
 */
@Slf4j
@Component
@SuppressWarnings("unchecked")
public class DictUtils {

    @Autowired
    private RedisUtil redisUtil;
    
    /**
     * 通过字典编号获取字段缓存
     *
     * @param dictCode 字典编号
     * @return
     */
	public List<SCache> getDictValue(String dictCode) {
        List<SCache> result = new ArrayList<>();
        List<Object> cache = redisUtil.lGet("dictCache:" + dictCode, 0, redisUtil.lGetListSize("dictCache:" + dictCode));
        if (cache == null || cache.size() == 0) {
            log.error("无法匹配缓存'dictCache:{}'", dictCode);
            return result;
        }
        //获取对应缓存并转换为map
        result = (List<SCache>) cache.get(0);
        return result;
    }

    /**
     * 通过字典编号获取字段缓存List<Map>
     *
     * @param dictCode 字典编号
     * @param keyName  存储字典名称的key名称
     * @param dictCode 存储字典值的key名称
     * @return
     */
    public List<Map<String, String>> getDictValueMap(String dictCode, String keyName, String valueName) {
        List<SCache> caches = getDictValue(dictCode);
        List<Map<String, String>> result = new ArrayList<>(caches.size());
        for (SCache cache : caches) {
            Map<String, String> m = new HashMap<>();
            m.put(keyName, cache.getCode());
            m.put(valueName, cache.getValue());
            result.add(m);
        }
        return result;
    }

    /**
     * 列表类 缓存转换
     *
     * @param list         待转换列表
     * @param dictCode     缓存code值
     * @param keyColumn    待转换字段,支持逗号隔开
     * @param targetColumn 转换后值存字段
     * @return
     */
    public List<Object> dictToValueList(List<Object> list, String dictCode, String keyColumn, String targetColumn) {
        List<Object> cache = redisUtil.lGet("dictCache:" + dictCode, 0, redisUtil.lGetListSize("dictCache:" + dictCode));
        if (cache == null || cache.size() == 0) {
            log.error("无法匹配缓存'dictCache:{}'", dictCode);
            return list;
        }
        //获取对应缓存并转换为map
        List<SCache> cacheList = (List<SCache>) cache.get(0);
        HashMap<String, String> cacheMap = new HashMap<>(cacheList.size());
        for (SCache sysCache : cacheList) {
            cacheMap.put(sysCache.getCode(), sysCache.getValue());
        }

        for (Object o : list) {
            try {
                String key = "";
                //获取待转化的key的值
                if (o instanceof JSONObject || o instanceof Map) {
                    key = (String) o.getClass().getMethod("get", Object.class).invoke(o, keyColumn);
                } else {
                    key = (String) o.getClass().getMethod(
                            new StringBuffer().append("get").append(keyColumn.substring(0, 1).toUpperCase()).append(keyColumn.substring(1)).toString()
                    ).invoke(o);
                }
                if (StringUtils.isEmpty(key)) continue;
                String value = key;
                //转换字段值
                if (StringUtils.contains(key, ",")) {
                    String[] keyArr = key.split(",");
                    StringBuffer sb = new StringBuffer();
                    for (String s : keyArr) {
                        sb.append(cacheMap.get(s) == null ? s : cacheMap.get(s)).append(",");
                    }
                    sb.deleteCharAt(sb.lastIndexOf(","));
                    value = sb.toString();
                } else {
                    value = cacheMap.get(key) == null ? key : cacheMap.get(key);
                }
                //字典值回写
                if (o instanceof JSONObject || o instanceof Map) {
                    if (StringUtils.isEmpty(targetColumn)) targetColumn = keyColumn + "&text";
                    o.getClass().getMethod("put", Object.class, Object.class).invoke(o, targetColumn, value);
                } else {
                    if (StringUtils.isEmpty(targetColumn)) targetColumn = keyColumn;
                    String valueSetMethod = new StringBuffer().append("set").append(targetColumn.substring(0, 1).toUpperCase()).append(targetColumn.substring(1)).toString();
                    o.getClass().getMethod(valueSetMethod, String.class).invoke(o, value);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        ;
        return list;
    }


    /**
     * ENTITY/JSON/Map 缓存转换
     *
     * @param o            待转换 ENTITY/JSON/Map
     * @param dictCode     缓存code值
     * @param keyColumn    待转换字段
     * @param targetColumn 转换后值存字段
     * @return
     */
    public Object dictToValue(Object o, String dictCode, String keyColumn, String targetColumn) {
        //获取缓存并转换为map
        List<Object> cache = redisUtil.lGet("dictCache:" + dictCode, 0, redisUtil.lGetListSize("dictCache:" + dictCode));
        if (cache.size() == 0) {
            log.error("无法匹配缓存'dictCache:{}'", dictCode);
            return o;
        }
        List<SCache> cacheList = (List<SCache>) cache.get(0);
        HashMap<String, String> cacheMap = new HashMap<>(cacheList.size());
        for (SCache sysCache : cacheList) {
            cacheMap.put(sysCache.getCode(), sysCache.getValue());
        }

        try {
            //获取待转换的key值
            String key = "";
            if (o instanceof JSONObject || o instanceof Map) {
                key = (String) o.getClass().getMethod("get", Object.class).invoke(o, keyColumn);
            } else {
                key = (String) o.getClass().getMethod(
                        new StringBuffer().append("get").append(keyColumn.substring(0, 1).toUpperCase()).append(keyColumn.substring(1)).toString()
                ).invoke(o);
            }
            if (StringUtils.isEmpty(key)) return o;
            String value = key;
            //获取转换后的值
            if (StringUtils.contains(key, ",")) {
                String[] keyArr = key.split(",");
                StringBuffer sb = new StringBuffer();
                for (String s : keyArr) {
                    sb.append(cacheMap.get(s) == null ? s : cacheMap.get(s)).append(",");
                }
                sb.deleteCharAt(sb.lastIndexOf(","));
                value = sb.toString();
            } else {
                value = cacheMap.get(key) == null ? key : cacheMap.get(key);
            }
            if (o instanceof JSONObject || o instanceof Map) {
                if (StringUtils.isEmpty(targetColumn)) targetColumn = keyColumn + "&text";
                o.getClass().getMethod("put", Object.class, Object.class).invoke(o, targetColumn, value);
            } else {
                if (StringUtils.isEmpty(targetColumn)) targetColumn = keyColumn;
                String valueSetMethod = new StringBuffer().append("set").append(targetColumn.substring(0, 1).toUpperCase()).append(targetColumn.substring(1)).toString();
                o.getClass().getMethod(valueSetMethod, String.class).invoke(o, value);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return o;
    }

}
