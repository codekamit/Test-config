package com.example.testproject.service;

import com.example.testproject.model.GroupingType;
import com.example.testproject.dto.ClientPreferenceDTO;
import com.example.testproject.dto.FundGroupDTO;
import com.example.testproject.model.Earning;
import com.example.testproject.model.State;
import com.example.testproject.repository.EarningRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientConfigService {
    private final EarningRepo earningRepo;
    public Mono<Map<String, ClientPreferenceDTO>> getClientPreferences() {
        List<ClientPreferenceDTO> clientPreferenceDTOs = List.of(new ClientPreferenceDTO("SFDTS", true, GroupingType.CLIENT),
                new ClientPreferenceDTO("SFDTS", true, GroupingType.PAYMENT_FUND),
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
        map1.put("6RRST", "5ERST");
        map1.put("TSRUS", "5ERST");
        map1.put("7YTSF", "RTSEU");
        map1.put("98UIIY", "OPISO");
        Map<String, String> map2 = new HashMap<>();
        map2.put("7TSTD", "9YSUD");
        map2.put("LLJSK", "POIIS");
        Map<String, String> map3 = new HashMap<>();
        map3.put("HUSYU", "Y7UUI");
        map3.put("AE5RTE", "Y7UUI");
        map3.put("FT56RES", "UYISS");
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

    public List<Earning> getTestEarnings() {
        List<Earning> earnings = List.of(new Earning("3EQRW", "SFDTS", 230.10, "CAD", State.PENDING),
                new Earning("6RRST", "SFDTS", 100.10, "CAD", State.PENDING),
                new Earning("7TSTD", "AFTS", 70.50, "CAD", State.PENDING),
                new Earning("LLJSK", "AFTS", 190.20, "CAD", State.PENDING),
                new Earning("HUSYU", "RTSTW", 300.10, "CAD", State.PENDING),
                new Earning("TSRUS", "SFDTS", 760.20, "CAD", State.PENDING),
                new Earning("7YTSF", "SFDTS", 260.20, "CAD", State.PENDING),
                new Earning("98UIIY", "SFDTS", 90.00, "CAD", State.PENDING),
                new Earning("AE5RTE", "RTSTW", 50.20, "CAD", State.PENDING),
                new Earning("FT56RES", "RTSTW", 80.00, "CAD", State.PENDING)
                );
        return earningRepo.saveAll(earnings);
    }
}
