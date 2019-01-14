package com.nexocode.bir.rest;

import com.nexocode.bir.client.ReportType;
import com.nexocode.bir.model.CompanyPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegonApiController {

    private final RegonApiPromptService regonApiPromptService;

    @GetMapping("/company")
    public CompanyPrompt findCompanyByNip(@RequestParam("nip") String nip) throws Exception {
        return regonApiPromptService.findCompanyByNip(nip);
    }

    @GetMapping("/company/{regon}/{report}")
    public String getCompanyReport(@PathVariable String regon, @PathVariable ReportType report) throws Exception {
        return regonApiPromptService.getCompanyReport(regon, report);
    }
}
