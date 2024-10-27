package com.example.testproject.service;

import com.example.testproject.model.*;
import com.example.testproject.dto.ClientPreferenceDTO;
import com.example.testproject.dto.FundGroupDTO;
import com.example.testproject.repository.ClientPreferenceRepo;
import com.example.testproject.repository.FundGroupRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientConfigService {
    private final ClientPreferenceRepo clientPreferenceRepo;
    private final FundGroupRepo fundGroupRepo;
    public Mono<Map<String, ClientPreferenceDTO>> getClientPreferences() {
        List<ClientPreferenceDTO> clientPreferenceDTOs = clientPreferenceRepo.findAll()
                        .stream()
                        .map(clientPreference -> {
                            return ClientPreferenceDTO.builder()
                                    .clientShortName(clientPreference.getClientShortName())
                                    .groupingType(clientPreference.getGroupingType())
                                    .netting(clientPreference.getNetting())
                                    .build();
                        })
                .collect(Collectors.toList());
        return Mono.just(clientPreferenceDTOs.stream()
                .collect(Collectors.toMap(
                        ClientPreferenceDTO::getClientShortName,
                        clientPreferenceDTO -> clientPreferenceDTO,
                        (existing, replacement) -> existing
                )));
    }
    public Mono<Map<String, FundGroupDTO>> getFundGroupsMap() {
        List<FundGroup> fundGroups = fundGroupRepo.findAll();
        Map<String, List<FundGroup>> clientFundGroupMap = fundGroups.stream().collect(Collectors.groupingBy(FundGroup::getClientShortName));
        List<FundGroupDTO> fundGroupDTOs = new ArrayList<>();
        clientFundGroupMap.forEach((clientShortName, clientFundGroups) -> {
            fundGroupDTOs.add(IMapper.getFundGroupDTOFromModel(clientFundGroups));
        });
        return Mono.just(fundGroupDTOs.stream()
                .collect(Collectors.toMap(
                        FundGroupDTO::getClientShortName,
                        fundGroupDTO -> fundGroupDTO,
                        (existing, replacement) -> existing
                        )));
    }
}
