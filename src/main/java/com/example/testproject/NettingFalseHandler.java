package com.example.testproject;

import java.util.List;
import java.util.Map;

public class NettingHandler extends BaseHandler<NettingInputDTO , List<NetEarning>> {
    private ClientConfigService clientConfigService;
    private BaseHandler next;
    private
    @Override
    public List<NetEarning> handle(NettingInputDTO nettingInputDTO) {
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.PAYMENT_FUND))
            return NettingScenarios.noNettingPaymentFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
        else if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.FUND))
            return NettingScenarios.noNettingFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()));
        else if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.CLIENT))
            return NettingScenarios.noNettingClientLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
        else if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(true) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.PAYMENT_FUND))
            return NettingScenarios.NettingPaymentFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
        else if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(true) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.FUND))
            return NettingScenarios.NettingFundLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()));
        else {
            return NettingScenarios.NettingClientLevel(nettingInputDTO.getClientEarnings().get(nettingInputDTO.getClientPreferenceDTO().getClientShortName()), nettingInputDTO.getFundGroupDTO());
        }
    }
}
