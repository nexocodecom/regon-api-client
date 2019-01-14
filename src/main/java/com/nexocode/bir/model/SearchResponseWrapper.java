package com.nexocode.bir.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "root")
public class SearchResponseWrapper {
    private List<CompanyPrompt> data;


    @XmlElement(name = "dane")
    public void setData(List<CompanyPrompt> data) {
        this.data = data;
    }
}
