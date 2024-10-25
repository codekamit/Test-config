package com.example.testproject.service;

import com.example.testproject.dto.ClientPreferenceDTO;
import com.example.testproject.dto.FundGroupDTO;
import com.example.testproject.dto.NettingInputDTO;
import com.example.testproject.model.Earning;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ClientPreferenceHandler extends BaseHandler<Map<String, List<Earning>>> {
    private ClientConfigService clientConfigService;
    private BaseHandler next;
    @Override
    public void handle(Map<String, List<Earning>> earnings) {
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
