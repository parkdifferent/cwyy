package com.szkingdom.ssm.api;

import com.szkingdom.ssm.bean.ChannelResult;
import com.szkingdom.ssm.util.GsonUtil;
import com.szkingdom.ssm.util.HostType;
import com.szkingdom.ssm.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;

/**
 * Created by admin on 2017/6/5.
 */
public class LiveAPI {


    private static final String UTF8 = "UTF-8";

    private static final String appKey = "efabec6ca0048ba015339a824ca1ae3e";
    private static final String appSecret = "d559fd9d91ab";


    /**
     * 创建一个直播频道
     * @param name
     * @param type
     * @return
     * @throws Exception
     */
    public ChannelResult createChannel(String name, Integer type) throws Exception {
        if (name == null) {
            throw new IllegalArgumentException("Paramer 'name' is required");
        }

        if (type == null) {
            throw new IllegalArgumentException("Paramer 'type' is required");
        }


        StringBuilder sb = new StringBuilder();
        sb.append("&name=").append(URLEncoder.encode(name.toString(), UTF8));
        sb.append("&type=").append(URLEncoder.encode(type.toString(), UTF8));
        String body = sb.toString();
        if (body.indexOf("&") == 0) {
            body = body.substring(1, body.length());
        }

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(HostType.API, appKey, appSecret, "/app/channel/create", "application/json;charset=utf-8");
        HttpUtil.setBodyParameter(body, conn);

        return (ChannelResult) GsonUtil.fromJson(HttpUtil.returnResult(conn), ChannelResult.class);
    }

    public static void main(String[] args) {
        LiveAPI liveAPI = new LiveAPI();
        try {
            ChannelResult channelResult = liveAPI.createChannel("testChannel",Integer.valueOf(0));
            System.out.println(channelResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
