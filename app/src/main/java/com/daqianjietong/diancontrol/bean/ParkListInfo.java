package com.daqianjietong.diancontrol.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class ParkListInfo implements Serializable {

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

        private String ps_id;
        private String ps_parknum;
        private String ps_status;
        private String ps_starttime;
        private String ps_endtime;

        public String getPs_id() {
            return ps_id;
        }

        public void setPs_id(String ps_id) {
            this.ps_id = ps_id;
        }

        public String getPs_parknum() {
            return ps_parknum;
        }

        public void setPs_parknum(String ps_parknum) {
            this.ps_parknum = ps_parknum;
        }

        public String getPs_status() {
            return ps_status;
        }

        public void setPs_status(String ps_status) {
            this.ps_status = ps_status;
        }

        public String getPs_starttime() {
            return ps_starttime;
        }

        public void setPs_starttime(String ps_starttime) {
            this.ps_starttime = ps_starttime;
        }

        public String getPs_endtime() {
            return ps_endtime;
        }

        public void setPs_endtime(String ps_endtime) {
            this.ps_endtime = ps_endtime;
        }
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
