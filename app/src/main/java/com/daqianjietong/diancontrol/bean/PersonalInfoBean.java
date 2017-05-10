package com.daqianjietong.diancontrol.bean;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class PersonalInfoBean {

    private int code;
    private Data data;
    private String message;

    public PersonalInfoBean() {
        super();
    }

    public PersonalInfoBean(int code, Data data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

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

        private String pu_id;
        private String pu_username;

        public String getPu_id() {
            return pu_id;
        }

        public void setPu_id(String pu_id) {
            this.pu_id = pu_id;
        }

        public String getPu_username() {
            return pu_username;
        }

        public void setPu_username(String pu_username) {
            this.pu_username = pu_username;
        }
    }
}

