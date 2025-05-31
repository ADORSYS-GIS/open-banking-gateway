package de.adorsys.opba.fintech.impl.service;

import de.adorsys.opba.fintech.api.model.generated.InlineResponseBankInfo;
import de.adorsys.opba.fintech.impl.controller.utils.RestRequestContext;
import de.adorsys.opba.fintech.impl.mapper.BankInfoMapper;
import de.adorsys.opba.fintech.impl.tppclients.TppIbanSearchClient;
import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.BankInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class IbanSearchService {

    private final TppIbanSearchClient tppIbanSearchClient;
    private final RestRequestContext restRequestContext;

    @SneakyThrows
    public InlineResponseBankInfo searchByIban(String iban) {
        UUID xRequestId = UUID.fromString(restRequestContext.getRequestId());

        BankInfoResponse response = tppIbanSearchClient.bankInfoByIbanPOST(
                xRequestId,
                /* provide required headers or tokens here */
                null, null, null, // or actual token values
                iban
        ).getBody();

        return BankInfoMapper.fromTppToFintech(response);
    }
}
