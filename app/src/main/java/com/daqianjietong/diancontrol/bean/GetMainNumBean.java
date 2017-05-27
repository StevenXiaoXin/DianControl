package com.daqianjietong.diancontrol.bean;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class GetMainNumBean {

    private int code;
    private Data data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {
        private String reservenum;
        private String parknum;

        public String getReservenum() {
            return reservenum;
        }

        public void setReservenum(String reservenum) {
            this.reservenum = reservenum;
        }

        public String getParknum() {
            return parknum;
        }

        public void setParknum(String parknum) {
            this.parknum = parknum;
        }
    }

}
