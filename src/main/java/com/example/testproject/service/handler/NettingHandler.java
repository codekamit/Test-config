package com.example.testproject.service.handler;

import com.example.testproject.model.GroupingType;
import com.example.testproject.dto.NettingInputDTO;
import com.example.testproject.model.NetEarning;
import com.example.testproject.repository.NetEarningRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
public class NettingHandler extends BaseHandler<NettingInputDTO> {
    private NetEarningRepo netEarningRepo;
    @Override
    public void handle(NettingInputDTO nettingInputDTO) {
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.PAYMENT_FUND)) {
            List<NetEarning> netEarnings = NettingScenarios.noNettingPaymentFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
            netEarningRepo.saveAll(netEarnings);
            return;
        }
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.FUND)) {
            List<NetEarning> netEarnings = NettingScenarios.noNettingFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()));
            netEarningRepo.saveAll(netEarnings);
            return;
        }
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.CLIENT)) {
            List<NetEarning> netEarnings = NettingScenarios.noNettingClientLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
            netEarningRepo.saveAll(netEarnings);
            return;
        }
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(true) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.PAYMENT_FUND)) {
            List<NetEarning> netEarnings = NettingScenarios.NettingPaymentFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
            netEarningRepo.saveAll(netEarnings);
            return;
        }
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(true) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.FUND)) {
            List<NetEarning> netEarnings = NettingScenarios.NettingFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()));
            netEarningRepo.saveAll(netEarnings);
            return;
        }
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(true) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.CLIENT)) {
            List<NetEarning> netEarnings = NettingScenarios.NettingClientLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
            netEarningRepo.saveAll(netEarnings);
            return;
        }
    }
}
