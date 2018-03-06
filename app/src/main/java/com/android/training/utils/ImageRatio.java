package com.android.training.utils;

import android.content.Context;

/**
 * Created by violet on 15/11/8.
 */
public enum ImageRatio {
    HOME_BANNER(906,432),//首页Banner图片宽高比(906:432)
    HOME_RECOMMAND_IMAGES(456,294),//首页横向滚动推荐图片宽高比(456:294)
    MALL_INDEX_BANNER(750,330),//积分商城首页Banner
    MALL_INDEX_AD(684,300),//积分商城首页广告图
    MALL_GIFT_SHOP_BOON_BANNER(750,240),//凤凰礼品店-购实惠-首页Banner
    MALL_GOOD_WATERFALL(750,420),//商品概要信息（瀑布流大图片）
    MALL_GOOD_SQUARE(276,276),//正方形商品概要信息（瀑布流小图片）
    MALL_GOOD_THUMBNAIL(138,138),//商品购买信息，小图（该尺寸为瀑布流中小图的50%)
    WECHAT_SHARE_THUMBNAIL(120,120),//微信分享小图片
    WECHAT_LAND_PAGE_BIG_IMAGE(552,552),//该尺寸为瀑布流中小图的200%
    UNDEFINED(100,100);
    double width;
    double height;
    ImageRatio(double width, double height){
        this.width = width;
        this.height = height;
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    /**
     * 根据图片实际宽度，计算图片的实际高度
     * @param context
     * @param realWidth
     * @return
     */
    public double getHeightByWidth(Context context, double realWidth){
        double width = getWidth();
        double height = getHeight();
        double imageHeight = height / width * realWidth;
        return imageHeight;
    }
}
