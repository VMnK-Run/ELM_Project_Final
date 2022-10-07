package com.tju.elmboot.util;

import java.util.HashMap;
import java.util.Map;

public final class DistanceUtils {

    /**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137;

    /**
     * 根据经纬度，计算两点间的距离
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离 单位千米
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s =  s * EARTH_RADIUS;
        return s;
    }

    /**
     * 根据商家的经纬度，计算配送范围（另一种方法，实际选用）
     * @param longitude
     * 商家所在经度
     * @param latitude
     * 商家所在纬度
     * @param raidus
     * 地球半径
     * @return map
     * 返回一个距离范围，在此范围之内的用户地址被认为是可以送达的
     */
    public static Map<String,String> lonLatCalculation(Double longitude, Double latitude, Integer raidus){
        Map<String,String> map = new HashMap<String,String>();
        // 赤道周长24901英里 1609是转换成米的系数
        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;
        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        //return new double[] { minLat, minLng, maxLat, maxLng };
        map.put("minLat",minLat.toString());
        map.put("maxLat",maxLat.toString());
        map.put("minLng",minLng.toString());
        map.put("maxLng",maxLng.toString());
        return map;
    }

}
