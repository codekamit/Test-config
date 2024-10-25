package com.example.testproject;

import java.util.List;
import java.util.Map;

public class NettingHandler extends BaseHandler<NettingInputDTO, List<NetEarning>> {
    private ClientConfigService clientConfigService;
    private BaseHandler next;
    private
    @Override
    public List<NetEarning> handle(NettingInputDTO nettingInputDTO) {
        if(nettingInputDTO.getClientPreferenceDTO().getNetting().equals(false) &&
                nettingInputDTO.getClientPreferenceDTO().getGroupingType().equals(GroupingType.PAYMENT_FUND))


    }
}
