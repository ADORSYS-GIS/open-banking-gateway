package de.adorsys.opba.fintech.impl.service;

import de.adorsys.opba.fintech.api.model.generated.InlineResponseBankInfo;
import de.adorsys.opba.fintech.impl.controller.utils.RestRequestContext;
import de.adorsys.opba.fintech.impl.mapper.BankInfoMapper;
import de.adorsys.opba.fintech.impl.tppclients.TppIbanSearchClient;
import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.BankInfoResponse;
import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.V1BankinfoBody;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IbanSearchService {

    private final TppIbanSearchClient tppIbanSearchClient;
    private final RestRequestContext restRequestContext;
    private final BankInfoMapper bankInfoMapper;

    @SneakyThrows
    public InlineResponseBankInfo searchByIban(String iban) {
        log.info("Searching for bank info by IBAN: {}", iban);
        UUID.fromString(restRequestContext.getRequestId());

        V1BankinfoBody body = new V1BankinfoBody();
        body.setIban(iban);

        BankInfoResponse response = tppIbanSearchClient
            .getBankInfoByIban(body)
            .getBody();
        log.info("Received bank info response for IBAN {}: {}", iban, response);

        return bankInfoMapper.mapFromTppToFintech(response);
    }
}
