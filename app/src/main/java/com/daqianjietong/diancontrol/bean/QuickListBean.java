package com.daqianjietong.diancontrol.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30 0030.
 */

public class QuickListBean {

    private int code;
    private List<Data> data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        private String r_id;
        private String r_parknum;
        private String r_starttime;
        private String r_endtime;

        public String getR_id() {
            return r_id;
        }

        public void setR_id(String r_id) {
            this.r_id = r_id;
        }

        public String getR_parknum() {
            return r_parknum;
        }

        public void setR_parknum(String r_parknum) {
            this.r_parknum = r_parknum;
        }

        public String getR_starttime() {
            return r_starttime;
        }

        public void setR_starttime(String r_starttime) {
            this.r_starttime = r_starttime;
        }

        public String getR_endtime() {
            return r_endtime;
        }

        public void setR_endtime(String r_endtime) {
            this.r_endtime = r_endtime;
        }
    }

}
