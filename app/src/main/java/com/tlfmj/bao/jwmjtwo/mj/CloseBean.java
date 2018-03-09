package com.tlfmj.bao.jwmjtwo.mj;

/**
 * Created by tanlifei on 2018/1/7.
 */

public class CloseBean {


    /**
     * one : {"mj_name":"c773D福彩","close":false,"flash":false}
     * two : {"mj_name":"c773D福彩(应用宝)","close":false,"flash":true}
     */

    private TwoBean two;



    public TwoBean getTwo() {
        return two;
    }

    public void setTwo(TwoBean two) {
        this.two = two;
    }

    public static class TwoBean {
        /**
         * mj_name : c773D福彩(应用宝)
         * close : false
         * flash : true
         */

        private String mj_name;
        private boolean close;
        private boolean flash;

        public String getMj_name() {
            return mj_name;
        }

        public void setMj_name(String mj_name) {
            this.mj_name = mj_name;
        }

        public boolean isClose() {
            return close;
        }

        public void setClose(boolean close) {
            this.close = close;
        }

        public boolean isFlash() {
            return flash;
        }

        public void setFlash(boolean flash) {
            this.flash = flash;
        }
    }
}
