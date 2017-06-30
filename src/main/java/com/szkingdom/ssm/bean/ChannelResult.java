package com.szkingdom.ssm.bean;

/**
 * Created by admin on 2017/6/5.
 */
public class ChannelResult {


    /**
     * ret : {"httpPullUrl":"http://v1.live.126.net/live/cidxxxxxxxxx.flv","hlsPullUrl":"http://pullhls1.live.126.net/live/cidxxxxxxxxx/playlist.m3u8","rtmpPullUrl":"rtmp://v1.live.126.net/live/cidxxxxxxxxx","name":"channel_name","pushUrl":"rtmp://p1.live.126.net/live/cidxxxxxxxxx?wsSecret=312e11af6136a77b46afd0ebbb3102af&wsTime=1469411599","ctime":1469411598850,"cid":"cidxxxxxxxxx"}
     * code : 200
     * msg : test
     */

    private RetBean ret;
    private int code;
    private String msg;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class RetBean {
        /**
         * httpPullUrl : http://v1.live.126.net/live/cidxxxxxxxxx.flv
         * hlsPullUrl : http://pullhls1.live.126.net/live/cidxxxxxxxxx/playlist.m3u8
         * rtmpPullUrl : rtmp://v1.live.126.net/live/cidxxxxxxxxx
         * name : channel_name
         * pushUrl : rtmp://p1.live.126.net/live/cidxxxxxxxxx?wsSecret=312e11af6136a77b46afd0ebbb3102af&wsTime=1469411599
         * ctime : 1469411598850
         * cid : cidxxxxxxxxx
         */

        private String httpPullUrl;
        private String hlsPullUrl;
        private String rtmpPullUrl;
        private String name;
        private String pushUrl;
        private long ctime;
        private String cid;

        public String getHttpPullUrl() {
            return httpPullUrl;
        }

        public void setHttpPullUrl(String httpPullUrl) {
            this.httpPullUrl = httpPullUrl;
        }

        public String getHlsPullUrl() {
            return hlsPullUrl;
        }

        public void setHlsPullUrl(String hlsPullUrl) {
            this.hlsPullUrl = hlsPullUrl;
        }

        public String getRtmpPullUrl() {
            return rtmpPullUrl;
        }

        public void setRtmpPullUrl(String rtmpPullUrl) {
            this.rtmpPullUrl = rtmpPullUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPushUrl() {
            return pushUrl;
        }

        public void setPushUrl(String pushUrl) {
            this.pushUrl = pushUrl;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        @Override
        public String toString() {
            return "RetBean{" +
                    "httpPullUrl='" + httpPullUrl + '\'' +
                    ", hlsPullUrl='" + hlsPullUrl + '\'' +
                    ", rtmpPullUrl='" + rtmpPullUrl + '\'' +
                    ", name='" + name + '\'' +
                    ", pushUrl='" + pushUrl + '\'' +
                    ", ctime=" + ctime +
                    ", cid='" + cid + '\'' +
                    '}';
        }
    }

    public ChannelResult(RetBean ret, int code, String msg) {
        this.ret = ret;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ChannelResult{" +
                "ret=" + ret +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
