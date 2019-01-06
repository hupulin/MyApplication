package hzxmkuar.com.applibrary;

/**
 * Created by Administrator on 2019/1/3.
 */

public class LoginTo {

    /**
     * success : 200
     * errorCode : 0
     * msg :
     * choiceNum : 0
     * body : {"increase":7,"hallUserId":80042,"province":20,"city":20,"crgId":"200000400314","parentOrgName":"广东","cityName":"广州","orgName":"测试账号","number":45}
     */

    private int success;
    private String errorCode;
    private String msg;
    private int choiceNum;
    private BodyBean body;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getChoiceNum() {
        return choiceNum;
    }

    public void setChoiceNum(int choiceNum) {
        this.choiceNum = choiceNum;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * increase : 7
         * hallUserId : 80042
         * province : 20
         * city : 20
         * crgId : 200000400314
         * parentOrgName : 广东
         * cityName : 广州
         * orgName : 测试账号
         * number : 45
         */

        private int increase;
        private int hallUserId;
        private int province;
        private int city;
        private String crgId;
        private String parentOrgName;
        private String cityName;
        private String orgName;
        private int number;

        public int getIncrease() {
            return increase;
        }

        public void setIncrease(int increase) {
            this.increase = increase;
        }

        public int getHallUserId() {
            return hallUserId;
        }

        public void setHallUserId(int hallUserId) {
            this.hallUserId = hallUserId;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getCrgId() {
            return crgId;
        }

        public void setCrgId(String crgId) {
            this.crgId = crgId;
        }

        public String getParentOrgName() {
            return parentOrgName;
        }

        public void setParentOrgName(String parentOrgName) {
            this.parentOrgName = parentOrgName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "increase=" + increase +
                    ", hallUserId=" + hallUserId +
                    ", province=" + province +
                    ", city=" + city +
                    ", crgId='" + crgId + '\'' +
                    ", parentOrgName='" + parentOrgName + '\'' +
                    ", cityName='" + cityName + '\'' +
                    ", orgName='" + orgName + '\'' +
                    ", number=" + number +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginTo{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", msg='" + msg + '\'' +
                ", choiceNum=" + choiceNum +
                ", body=" + body +
                '}';
    }
}
