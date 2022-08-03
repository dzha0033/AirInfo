package yhp.bean;

import java.util.Date;

public class AirInfo {
    private int airid;
    private String airnumber;
    private String address;
    private Date airdate;


    public int getAirid() {
        return airid;
    }

    public void setAirid(int airid) {
        this.airid = airid;
    }

    public String getAirnumber() {
        return airnumber;
    }

    public void setAirnumber(String airnumber) {
        this.airnumber = airnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAirdate() {
        return airdate;
    }

    public void setAirdate(Date airdate) {
        this.airdate = airdate;
    }
}
