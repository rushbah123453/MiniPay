package com.ubschallenge.upay.Passbook;

public class DataModel {

    String toPhone;
    String fromPhone;
    String timeStamp;
    String amt;
    String name;
    int id_;


    public DataModel( int id_,String fromPhone,String toPhone, String amt, String timeStamp) {
        this.toPhone = toPhone;
        this.fromPhone = fromPhone;
        this.id_ = id_;
        this.timeStamp= timeStamp;
        this.amt =  amt;

    }

    public String getFromPhone() {
        return fromPhone;
    }

    public String getToPhone()
    {
        return toPhone;
    }

    public int getId() {
        return id_;
    }

    public String getAmt() {
        return amt;
    }
    public String getTimeStamp()
    {
        return timeStamp;
    }

}
