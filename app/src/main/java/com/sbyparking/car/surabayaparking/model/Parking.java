package com.sbyparking.car.surabayaparking.model;

public class Parking {

    public int id;
    public String pencarianZona;
    public String saranZona;
    public String waktuTempuh;
    public String hargaTiketMobil;
    public String hargaTiketMotor;
    public String jamOperasional;
    public int photo;
    public String lat;
    public String lng;

    public Parking() {
    }

    public Parking(int id, String pencarianZona, String saranZona, String waktuTempuh
            , String hargaTiketMobil, String hargaTiketMotor, String jamOperasional, int photo
            , String lat, String lng
    ) {
        this.id = id;
        this.pencarianZona = pencarianZona;
        this.saranZona = saranZona;
        this.waktuTempuh = waktuTempuh;
        this.hargaTiketMobil = hargaTiketMobil;
        this.hargaTiketMotor = hargaTiketMotor;
        this.jamOperasional = jamOperasional;
        this.photo = photo;
        this.lat = lat;
        this.lng = lng;
    }

    //
}
