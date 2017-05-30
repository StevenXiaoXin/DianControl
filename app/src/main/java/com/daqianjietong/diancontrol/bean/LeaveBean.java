package com.daqianjietong.diancontrol.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30 0030.
 */

public class LeaveBean {

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
        private String r_reserveid;
        private String r_hours;
        private String r_money;

        public String getR_reserveid() {
            return r_reserveid;
        }

        public void setR_reserveid(String r_reserveid) {
            this.r_reserveid = r_reserveid;
        }

        public String getR_hours() {
            return r_hours;
        }

        public void setR_hours(String r_hours) {
            this.r_hours = r_hours;
        }

        public String getR_money() {
            return r_money;
        }

        public void setR_money(String r_money) {
            this.r_money = r_money;
        }
    }

}
