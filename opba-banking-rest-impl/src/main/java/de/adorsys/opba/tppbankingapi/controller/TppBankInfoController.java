package de.adorsys.opba.tppbankingapi.controller;

// import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.BankInfoResponse;
// import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.V1BankinfoBody;
// import de.adorsys.opba.tppbankingapi.bankinfo.resource.generated.BankInfoApi;
// import de.adorsys.opba.tppbankingapi.service.BankInfoService;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequiredArgsConstructor
// @CrossOrigin(origins = "*") // FIXME: Move CORS config to gateway
// public class TppBankInfoController implements BankInfoApi {

//     private final BankInfoService bankInfoService;

//     @Override
//     public ResponseEntity<BankInfoResponse> getBankInfoByIban(V1BankinfoBody request) {
//         log.debug("Received IBAN lookup request: {}", request);
//         BankInfoResponse result = bankInfoService.getBankInfoByIban(request.getIban());

//         if (result == null) {
//             return ResponseEntity.notFound().build();
//         }

//         return ResponseEntity.ok(result);
//     }
// }


import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.BankInfoResponse;
import de.adorsys.opba.tppbankingapi.bankinfo.model.generated.V1BankinfoBody;
import de.adorsys.opba.tppbankingapi.bankinfo.resource.generated.BankInfoApi;
import de.adorsys.opba.tppbankingapi.service.BankInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TppBankInfoController implements BankInfoApi {

    private final BankInfoService bankInfoService;

    @Override
    public ResponseEntity<BankInfoResponse> getBankInfoByIban(V1BankinfoBody body) {
        log.info("Received IBAN lookup request: {}", body.getIban());

        var response = bankInfoService.getBankInfoByIban(body.getIban());

        if (response == null) {
            log.warn("No bank info found for IBAN: {}", body.getIban());
            return ResponseEntity.notFound().build();
        }

        log.info("Returning bank info: {}", response);
        return ResponseEntity.ok(response);
    }
}
