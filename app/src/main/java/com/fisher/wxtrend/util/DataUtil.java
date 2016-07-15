package com.fisher.wxtrend.util;

import com.fisher.wxtrend.po.WxType;

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

}
