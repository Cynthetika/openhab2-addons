package org.openhab.binding.aqualink.internal;

public class AqualinkStatus {
    String type = "";
    String version = "";
    String date = "";
    String time = "";
    String temp_units = "";
    String air_temp = "";
    String pool_temp = "";
    String spa_temp = "";
    String battery = "";
    String pool_htr_set_pnt = "";
    String spa_htr_set_pnt = "";
    String freeze_protection = "";
    String frz_protect_set_pnt = "";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp_units() {
        return temp_units;
    }

    public void setTemp_units(String temp_units) {
        this.temp_units = temp_units;
    }

    public String getAir_temp() {
        return air_temp.trim().equals("")?"0":air_temp.trim();
    }

    public void setAir_temp(String air_temp) {
        this.air_temp = air_temp;
    }

    public String getPool_temp() {
        return pool_temp.trim().equals("")?"0":pool_temp.trim();
    }

    public void setPool_temp(String pool_temp) {
        this.pool_temp = pool_temp;
    }

    public String getSpa_temp() {
        return spa_temp.trim().equals("")?"0":spa_temp.trim();
    }

    public void setSpa_temp(String spa_temp) {
        this.spa_temp = spa_temp;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public String getPool_htr_set_pnt() {
        return pool_htr_set_pnt;
    }

    public void setPool_htr_set_pnt(String pool_htr_set_pnt) {
        this.pool_htr_set_pnt = pool_htr_set_pnt;
    }

    public String getSpa_htr_set_pnt() {
        return spa_htr_set_pnt;
    }

    public void setSpa_htr_set_pnt(String spa_htr_set_pnt) {
        this.spa_htr_set_pnt = spa_htr_set_pnt;
    }

    public String getFreeze_protection() {
        return freeze_protection;
    }

    public void setFreeze_protection(String freeze_protection) {
        this.freeze_protection = freeze_protection;
    }

    public String getFrz_protect_set_pnt() {
        return frz_protect_set_pnt;
    }

    public void setFrz_protect_set_pnt(String frz_protect_set_pnt) {
        this.frz_protect_set_pnt = frz_protect_set_pnt;
    }
}
