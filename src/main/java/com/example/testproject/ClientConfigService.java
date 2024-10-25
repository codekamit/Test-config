package com.example.testproject;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientConfigService {
    public Mono<Map<String, ClientPreferenceDTO>> getClientPreferences() {
        List<ClientPreferenceDTO> clientPreferenceDTOs = List.of(new ClientPreferenceDTO("SFDTS", true, GroupingType.CLIENT),
                new ClientPreferenceDTO("SFDTS", true, GroupingType.CLIENT),
                new ClientPreferenceDTO("AFTS", false, GroupingType.FUND),
                new ClientPreferenceDTO("RTSTW", true, GroupingType.PAYMENT_FUND));

        return Mono.just(clientPreferenceDTOs.stream()
                .collect(Collectors.toMap(
                        ClientPreferenceDTO::getClientShortName, // Key mapper
                        clientPreferenceDTO -> clientPreferenceDTO,          // Value mapper
                        (existing, replacement) -> existing // Handle duplicates (e.g., keep existing)
                )));
    }

    public Mono<Map<String, FundGroupDTO>> getFundGroupsMap() {
        Map<String, String> map1 = new HashMap<>();
        map1.put("3EQRW", "5ERST");
        map1.put("6RRST", "U8SSJ");
        Map<String, String> map2 = new HashMap<>();
        map2.put("7TSTD", "9YSUD");
        map2.put("LLJSK", "POIIS");
        Map<String, String> map3 = new HashMap<>();
        map3.put("3EQRW", "5ERST");
        List<FundGroupDTO> fundGroups = List.of(new FundGroupDTO("SFDTS", map1),
                new FundGroupDTO("AFTS", map2),
                new FundGroupDTO("RTSTW", map3));
        return Mono.just(fundGroups.stream()
                .collect(Collectors.toMap(
                        FundGroupDTO::getClientShortName,
                        fundGroupDTO -> fundGroupDTO,
                        (existing, replacement) -> existing
                        )));
    }
}
