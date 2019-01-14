package com.nexocode.bir.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
public class CompanyPrompt {
    private String nip;
    private String regon;
    private String companyName;
    private String voivodeship;
    private String county;
    private String commune;
    private String city;
    private String zipCode;
    private String street;

    @XmlElement(name = "Regon")
    public void setRegon(String regon) {
        this.regon = regon;
    }

    @XmlElement(name = "Nazwa")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement(name = "Wojewodztwo")
    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    @XmlElement(name = "Powiat")
    public void setCounty(String county) {
        this.county = county;
    }

    @XmlElement(name = "Gmina")
    public void setCommune(String commune) {
        this.commune = commune;
    }

    @XmlElement(name = "Miejscowosc")
    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "KodPocztowy")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @XmlElement(name = "Ulica")
    public void setStreet(String street) {
        this.street = street;
    }
}
