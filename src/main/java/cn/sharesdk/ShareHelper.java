package cn.sharesdk;

import android.content.Context;
import android.text.TextUtils;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2015/12/7.
 */
public class ShareHelper {

    public static void init(Context context) {
        ShareSDK.initSDK(context);
    }

    public static void stop(Context context) {
        ShareSDK.stopSDK(context);
    }

    // 分享微信
    public static void shareToWechat(Context context, String title, String content, String imgPath, String url) {

        showShare(context, Wechat.NAME, title, null, content, null, imgPath, url, null, null, null, true);
    }

    // 分享朋友圈
    public static void shareToWechatMoments(Context context, String title, String content, String imgPath, String url) {
        showShare(context, WechatMoments.NAME, title, null, content, null, imgPath, url, null, null, null, true);
    }

    // 分享QQ
    public static void shareToQQ(Context context, String title, String titleUrl, String content, String imgUrl, String imgPath, String comment, String siteName, String siteUrl) {
        showShare(context, QQ.NAME, title, titleUrl, content, imgUrl, imgPath, null, comment, siteName, siteUrl, true);
    }

    // 分享新浪
    public static void shareToSina(Context context, String title, String content, String imgUrl, String imgPath) {
        showShare(context, SinaWeibo.NAME, title, null, content, imgUrl, imgPath, null, null, null, null, true);
    }

    // 分享短信
//    public static void shareToShortMessage(Context context, String title, String content, String imgUrl, String imgPath) {
//        showShare(context, ShortMessage.NAME, title, null, content, imgUrl, imgPath, null, null, null, null, true);
//    }

    public static void showShare(Context context, String platform, String title, String titleUrl, String content, String imgUrl, String imgPath,
                                 String url, String comment, String siteName, String siteUrl, boolean silent) {
        OnekeyShare oks = new OnekeyShare();
        //分享到哪个平台,设置后则不弹出分享选项（朋友圈、微博等）
        if (!TextUtils.isEmpty(platform)) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        // oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        if (!TextUtils.isEmpty(title))
            oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        if (!TextUtils.isEmpty(titleUrl))
            oks.setTitleUrl(titleUrl);
        // content是分享文本，所有平台都需要这个字段
        if (!TextUtils.isEmpty(content))
            oks.setText(content);
        // 图片的url，Linked-In以外的平台都支持此参数,imgUrl和imgPath同时设置时，imgPath无效
        if (!TextUtils.isEmpty(imgUrl))
            oks.setImageUrl(imgUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数,imgUrl和imgPath同时设置时，imgPath无效
        if (!TextUtils.isEmpty(imgPath))
            oks.setImagePath(imgPath); //确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        if (!TextUtils.isEmpty(url))
            oks.setUrl(url);
        //  comment是我对这条分享的评论，仅在人人网和QQ空间使用
        if (!TextUtils.isEmpty(comment))
            oks.setComment(comment);
        //  site是分享此内容的网站名称，仅在QQ空间使用
        if (!TextUtils.isEmpty(siteName))
            oks.setSite(siteName);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        if (!TextUtils.isEmpty(siteUrl))
            oks.setSiteUrl(siteUrl);
        //  启动分享GUI
        // oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {});
        //  如果silent为true，表示不进入编辑页面，否则会进入。
        oks.setSilent(silent);
        oks.show(context);
    }

}
