package com.example.testproject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientPreferenceHandler extends BaseHandler<Map<String, List<Earning>>, List<NetEarning>> {
    private ClientConfigService clientConfigService;
    private BaseHandler next;
    @Override
    public List<NetEarning> handle(Map<String, List<Earning>> earnings) {
        Map<String, ClientPreferenceDTO> clientPreferenceMap = clientConfigService.getClientPreferences().block();
        earnings.forEach((clientShortName, clientEarnings) -> {
            ClientPreferenceDTO clientPreferenceDTO = clientPreferenceMap.get(clientShortName);
            Map<String, FundGroupDTO> fundGroupMap = clientConfigService.getFundGroupsMap().block();
            FundGroupDTO fundGroupDTO = fundGroupMap.get(clientShortName);
            next.handle(new NettingInputDTO().builder()
                            .clientPreferenceDTO(clientPreferenceDTO)
                            .fundGroupDTO(fundGroupDTO)
                            .clientEarnings(earnings)
                            .build());
        });
    }
}
