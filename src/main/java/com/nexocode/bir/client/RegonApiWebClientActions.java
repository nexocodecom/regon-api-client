package com.nexocode.bir.client;

import com.nexocode.bir.wsdl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegonApiWebClientActions {

    private final RegonApiWebClient regonApiWebClient;

    public String login() {
        Zaloguj zaloguj = new Zaloguj();
        zaloguj.setPKluczUzytkownika(regonApiWebClient.getClientApiKey());

        ZalogujResponse zr = (ZalogujResponse) regonApiWebClient.getWebServiceTemplate().marshalSendAndReceive(zaloguj,
                regonApiWebClient.createActionCallback("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj"));

        return zr.getZalogujResult();
    }

    public String search(String nip, String sid) throws Exception {
        nip = IdValidator.normalizeAndValidateNip(nip);

        DaneSzukaj searchRequest = new DaneSzukaj();
        ParametryWyszukiwania params = new ParametryWyszukiwania();
        params.setNip(nip);
        searchRequest.setPParametryWyszukiwania(params);

        DaneSzukajResponse dr = (DaneSzukajResponse) regonApiWebClient.getWebServiceTemplate().marshalSendAndReceive(searchRequest,
                regonApiWebClient.createActionCallback("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/DaneSzukaj", sid));

        String searchResult = dr.getDaneSzukajResult();
        validateResponse(searchResult);
        return searchResult;
    }

    public String getReport(String regon, ReportType reportType, String sid) throws Exception {
        regon = IdValidator.normalizeAndValidateRegon(regon);

        DanePobierzPelnyRaport reportRequest = new DanePobierzPelnyRaport();

        reportRequest.setPRegon(regon);
        reportRequest.setPNazwaRaportu(reportType.name());

        DanePobierzPelnyRaportResponse response = (DanePobierzPelnyRaportResponse) regonApiWebClient.getWebServiceTemplate().marshalSendAndReceive(reportRequest,
                regonApiWebClient.createActionCallback("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/DanePobierzPelnyRaport", sid));

        String searchResult = response.getDanePobierzPelnyRaportResult();
        validateResponse(searchResult);

        return searchResult;
    }


    public boolean logout(String sid) {
        Wyloguj wyloguj = new Wyloguj();
        wyloguj.setPIdentyfikatorSesji(sid);

        WylogujResponse wr = (WylogujResponse) regonApiWebClient.getWebServiceTemplate().marshalSendAndReceive(wyloguj,
                regonApiWebClient.createActionCallback("http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Wyloguj"));

        return wr.isWylogujResult();
    }

    private void validateResponse(String response) throws Exception {
        if (StringUtils.isEmpty(response)) {
            throw new Exception("No data found.");
        }
    }
}