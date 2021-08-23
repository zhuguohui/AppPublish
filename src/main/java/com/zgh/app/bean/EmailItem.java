package com.zgh.app.bean;


public class EmailItem {
    public String smtpUrl;
    public String address;
    public String password;
    public boolean enable;



    public EmailItem(String smtpUrl, String userName, String password, boolean enable) {
        this.smtpUrl = smtpUrl;
        this.address = userName;
        this.password = password;
        this.enable = enable;
    }

    public boolean isEmailEnable(){
        if(isEmpty(smtpUrl)||isEmpty(address)||isEmpty(password)||!enable){
            return false;
        }
        return true;
    }
    private boolean isEmpty(Object obj){
        if(obj instanceof String){
            String str= (String) obj;
            if(!"".equals(obj)){
                return false;
            }
        }
        return true;
    }


}
