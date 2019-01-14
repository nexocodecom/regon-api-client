package com.bir.model;

import com.bir.model.CompanyPrompt;
import com.bir.model.SearchResponseWrapper;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class CompanyModelConverter {

    public CompanyPrompt convert(String companyXml, String nip) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(SearchResponseWrapper.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        InputStream stream = new ByteArrayInputStream(companyXml.getBytes(StandardCharsets.UTF_8));
        SearchResponseWrapper wrapped = (SearchResponseWrapper) jaxbUnmarshaller.unmarshal(stream);
        validateSearchResponse(wrapped);

        CompanyPrompt prompt = wrapped.getData().get(0);
        prompt.setNip(nip);
        return prompt;
    }

    private void validateSearchResponse(SearchResponseWrapper wrapped) throws Exception {
        if (wrapped.getData() == null || wrapped.getData().isEmpty()) {
            throw new Exception("Company not found");
        }

        if (wrapped.getData().size() != 1) {
            throw new Exception("found more than one company.");
        }
    }
}
