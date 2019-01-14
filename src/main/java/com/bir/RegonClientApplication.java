package com.bir;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.axiom.AxiomSoapMessageFactory;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
@PropertySource("classpath:application.yml")
public class RegonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegonClientApplication.class, args);
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.bir.wsdl");
        return marshaller;
    }

    @Bean
    public AxiomSoapMessageFactory createMessageFactory() {
        AxiomSoapMessageFactory axiomSoapMessageFactory = new AxiomSoapMessageFactory();
        axiomSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_12);
        return axiomSoapMessageFactory;
    }

    @Bean
    public WebServiceMessageSender createSender() {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setReadTimeout((int) TimeUnit.MILLISECONDS.convert(3, TimeUnit.MINUTES));
        httpComponentsMessageSender.setConnectionTimeout((int) TimeUnit.MILLISECONDS.convert(3, TimeUnit.MINUTES));
        return httpComponentsMessageSender;
    }
}
