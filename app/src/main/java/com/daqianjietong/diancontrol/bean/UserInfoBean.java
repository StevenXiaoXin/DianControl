package com.daqianjietong.diancontrol.bean;


/**
 * Created by Administrator on 2017/5/9 0009.
 */
public class UserInfoBean {

    public UserInfoBean() {
        super();
    }

    public UserInfoBean(int code, Data data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    private int code;
    private Data data;
    private String message;

    public int getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        private String uid;
        private String pu_username;
        private String pu_photo;
        private String pu_phone;
        private String pu_parkname;
        private String pu_parkid;
        private String token;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPu_username() {
            return pu_username;
        }

        public void setPu_username(String pu_username) {
            this.pu_username = pu_username;
        }

        public String getPu_photo() {
            return pu_photo;
        }

        public void setPu_photo(String pu_photo) {
            this.pu_photo = pu_photo;
        }

        public String getPu_phone() {
            return pu_phone;
        }

        public void setPu_phone(String pu_phone) {
            this.pu_phone = pu_phone;
        }

        public String getPu_parkname() {
            return pu_parkname;
        }

        public void setPu_parkname(String pu_parkname) {
            this.pu_parkname = pu_parkname;
        }

        public String getPu_parkid() {
            return pu_parkid;
        }

        public void setPu_parkid(String pu_parkid) {
            this.pu_parkid = pu_parkid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "code=" + code +
                "data:{"+
                ", uid=" + data.uid +
                ", pu_username=" + data.pu_username +
                ", pu_photo=" + data.pu_photo +
                ", pu_phone=" + data.pu_phone +
                ", pu_parkname=" + data.pu_parkname +
                ", pu_parkid=" + data.pu_parkid +
                ", token=" + data.token +
                ", message='" + message + '\'' +
                '}';
    }
}
