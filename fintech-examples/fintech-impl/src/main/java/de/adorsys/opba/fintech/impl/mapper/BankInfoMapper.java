package de.adorsys.opba.fintech.impl.mapper;

import de.adorsys.opba.fintech.api.model.generated.InlineResponseBankInfo;
import de.adorsys.opba.tpp.bankinfo.api.model.generated.BankInfoResponse;
import org.mapstruct.Mapper;

@Mapper(implementationPackage = "de.adorsys.opba.fintech.impl.mapper.generated")
public interface BankInfoMapper {
    InlineResponseBankInfo mapFromTppToFintech(BankInfoResponse bankInfoResponse);
}