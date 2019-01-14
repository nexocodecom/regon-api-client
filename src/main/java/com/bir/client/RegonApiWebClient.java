package com.bir.client;

import com.bir.config.RegonEnv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.axiom.AxiomSoapMessage;
import org.springframework.ws.soap.axiom.AxiomSoapMessageFactory;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpComponentsConnection;

import javax.xml.namespace.QName;
import java.io.IOException;

@Slf4j
@Component
public class RegonApiWebClient extends WebServiceGatewaySupport {

    private RegonEnv regonEnv;

    public RegonApiWebClient(RegonEnv regonEnv, Jaxb2Marshaller marshaller, AxiomSoapMessageFactory axiomSoapMessageFactory, WebServiceMessageSender webServiceMessageSender) {
        this.regonEnv = regonEnv;

        this.setDefaultUri(regonEnv.getUrl());
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);

        this.getWebServiceTemplate().setMessageFactory(axiomSoapMessageFactory);
        this.getWebServiceTemplate().setMessageSender(webServiceMessageSender);
    }

    SoapActionCallback createActionCallback(String action) {
        return createActionCallback(action, null);
    }

    SoapActionCallback createActionCallback(String action, @Nullable String sid) {
        return new SoapActionCallback(action) {
            public void doWithMessage(WebServiceMessage message) {
                AxiomSoapMessage soapMessage = (AxiomSoapMessage) message;
                SoapHeader soapHeader = soapMessage.getSoapHeader();

                SoapHeaderElement wsaTo = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "To", "wsa"));
                wsaTo.setText(regonEnv.getUrl());

                SoapHeaderElement wsaAction = soapHeader.addHeaderElement(new QName("http://www.w3.org/2005/08/addressing", "Action", "wsa"));
                wsaAction.setText(action);

                addSessionIdAsHeader();
            }

            private void addSessionIdAsHeader() {
                if (sid != null) {
                    TransportContext context = TransportContextHolder.getTransportContext();
                    HttpComponentsConnection connection = (HttpComponentsConnection) context.getConnection();
                    try {
                        connection.addRequestHeader("sid", sid);
                    } catch (IOException e) {
                        log.error("Unable to add sid header");
                    }
                }
            }
        };
    }

    String getClientApiKey() {
        return regonEnv.getKey();
    }
}