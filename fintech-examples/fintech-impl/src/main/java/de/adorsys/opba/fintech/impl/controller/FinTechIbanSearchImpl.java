package de.adorsys.opba.fintech.impl.controller;

import de.adorsys.opba.fintech.api.model.generated.InlineResponseBankInfo;
import de.adorsys.opba.fintech.api.resource.generated.FinTechIbanSearchApi;
import de.adorsys.opba.fintech.api.model.generated.SearchBankInfoBody;
import de.adorsys.opba.fintech.impl.service.IbanSearchService;
import de.adorsys.opba.fintech.impl.service.SessionLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FinTechIbanSearchImpl implements FinTechIbanSearchApi {

    private final IbanSearchService ibanSearchService;
    private final SessionLogicService sessionLogicService;

    @Override
    public ResponseEntity<InlineResponseBankInfo> getBankInfoByIban(
            UUID xRequestID,
            String xXsrfToken,
            SearchBankInfoBody body
    ) {
        if (!sessionLogicService.isSessionAuthorized()) {
            log.warn("getBankInfoByIban failed: user is not authorized!");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        InlineResponseBankInfo fintechModel = ibanSearchService.searchByIban(body.getIban());

        return sessionLogicService.addSessionMaxAgeToHeader(
                new ResponseEntity<>(fintechModel, HttpStatus.OK)
        );
    }
}
