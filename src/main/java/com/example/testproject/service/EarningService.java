package com.example.testproject.service;

import com.example.testproject.dto.ClientPreferenceDTO;
import com.example.testproject.dto.EarningDTO;
import com.example.testproject.dto.FundGroupDTO;
import com.example.testproject.model.*;
import com.example.testproject.repository.ClientPreferenceRepo;
import com.example.testproject.repository.EarningRepo;
import com.example.testproject.repository.FundGroupRepo;
import com.example.testproject.repository.NetEarningRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EarningService {
    private final EarningRepo earningRepo;
    private final FundGroupRepo fundGroupRepo;
    private final ClientPreferenceRepo clientPreferenceRepo;
    private final Preprocessor preprocessor;
    private final NetEarningRepo netEarningRepo;

    public List<Earning> addEarningsBulk(List<EarningDTO> earningDTOs) {
        List<Earning> earnings = earningDTOs.stream()
                .map(earningDTO -> {
            Earning earning = new Earning();
            earning.setFund(earningDTO.getFund());
            earning.setClientShortName(earningDTO.getClientShortName());
            earning.setAmount(earningDTO.getAmount());
            earning.setCurrency(earningDTO.getCurrency());
            earning.setState(State.PENDING);
            return earning;
                })
                .collect(Collectors.toList());
        return earningRepo.saveAll(earnings);
    }

    public List<FundGroup> addFundGroupForClients(List<FundGroupDTO> fundGroupDTOs) {
        List<FundGroup> fundGroups = new ArrayList<>();
        fundGroupDTOs.forEach(fundGroupDTO -> {
            fundGroups.addAll(IMapper.getFundGroupFromDTO(fundGroupDTO));
        });
        return fundGroupRepo.saveAll(fundGroups);
    }

    public List<ClientPreference> addClientPreferences(List<ClientPreferenceDTO> clientPreferenceDTOs) {
        List<ClientPreference> clientPreferences = clientPreferenceDTOs.stream()
                .map(clientPreferenceDTO -> {
                    ClientPreference clientPreference = new ClientPreference();
                    clientPreference.setClientShortName(clientPreferenceDTO.getClientShortName());
                    clientPreference.setNetting(clientPreferenceDTO.getNetting());
                    clientPreference.setGroupingType(clientPreferenceDTO.getGroupingType());
                    return clientPreference;
                })
                .collect(Collectors.toList());
        return clientPreferenceRepo.saveAll(clientPreferences);
    }

    public List<NetEarning> getNettedEarnings() {
        return netEarningRepo.findAll();
    }

    public List<NetEarning> calculateNettedEarnings() {
        List<Earning> earnings = earningRepo.findAll();
        return preprocessor.process(earnings);
    }
}
