package de.adorsys.opba.fintech.impl.mapper;

import de.adorsys.opba.fintech.api.model.generated.InlineResponseBankInfo;
import de.adorsys.opba.tpp.bankinfo.api.model.generated.BankInfoResponse;

public final class BankInfoMapper {

    private BankInfoMapper() {
        // Utility class should not be instantiated
    }

    public static InlineResponseBankInfo fromTppToFintech(BankInfoResponse response) {
        return new InlineResponseBankInfo()
                .bankCode(response.getBankCode())
                .bankName(response.getBankName())
                .bic(response.getBic());
    }
}
