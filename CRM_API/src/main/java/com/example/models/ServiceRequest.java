package com.example.models;

public class ServiceRequest {
    private int ID;
    private String SR_Classification;
    private String SR_Type;
    private String SR_Area;
    private String SR_SubArea;

    public ServiceRequest(int ID, String SR_Classification, String SR_Type, String SR_Area, String SR_SubArea) {
        this.ID = ID;
        this.SR_Classification = SR_Classification;
        this.SR_Type = SR_Type;
        this.SR_Area = SR_Area;
        this.SR_SubArea = SR_SubArea;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSR_Classification(String SR_Classification) {
        this.SR_Classification = SR_Classification;
    }

    public void setSR_Type(String SR_Type) {
        this.SR_Type = SR_Type;
    }

    public void setSR_Area(String SR_Area) {
        this.SR_Area = SR_Area;
    }

    public void setSR_SubArea(String SR_SubArea) {
        this.SR_SubArea = SR_SubArea;
    }

    public String getSR_Classification() {
        return SR_Classification;
    }

    public String getSR_Type() {
        return SR_Type;
    }

    public String getSR_Area() {
        return SR_Area;
    }

    public String getSR_SubArea() {
        return SR_SubArea;
    }
}
