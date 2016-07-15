package com.fisher.wxtrend.util;

import com.fisher.wxtrend.po.WxType;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class DataUtil {

    public static List<String> convertTypeToStr(List<WxType> types) {
        List<String> titles = new ArrayList<>();
        for (WxType type : types) {
            titles.add(type.getName());
        }
        return titles;
    }

    public static final List convertObjToBean(List<LinkedTreeMap> mapList, Class clazz) {
        List list = new ArrayList(mapList.size());

        Gson gson = new Gson();
        Object object = null;

        for (LinkedTreeMap map : mapList) {
            object = gson.fromJson(gson.toJson(map), clazz);
            list.add(object);
        }
        return list;
    }

}
