package com.rehman.netenergymeetring.Model;

public class HistoryModel
{
    String Id;
    String Light1;
    String Light2;
    String MeterInfos;
    String Vlotage;
    String img;
    String DateTime;

    public HistoryModel(String id, String light1, String light2, String meterInfos, String vlotage, String img, String dateTime) {
        Id = id;
        Light1 = light1;
        Light2 = light2;
        MeterInfos = meterInfos;
        Vlotage = vlotage;
        this.img = img;
        DateTime = dateTime;
    }

    public HistoryModel() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLight1() {
        return Light1;
    }

    public void setLight1(String light1) {
        Light1 = light1;
    }

    public String getLight2() {
        return Light2;
    }

    public void setLight2(String light2) {
        Light2 = light2;
    }

    public String getMeterInfos() {
        return MeterInfos;
    }

    public void setMeterInfos(String meterInfos) {
        MeterInfos = meterInfos;
    }

    public String getVlotage() {
        return Vlotage;
    }

    public void setVlotage(String vlotage) {
        Vlotage = vlotage;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }
}
