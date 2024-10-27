package com.example.testproject.controller;

import com.example.testproject.dto.ClientPreferenceDTO;
import com.example.testproject.dto.EarningDTO;
import com.example.testproject.dto.FundGroupDTO;
import com.example.testproject.model.ClientPreference;
import com.example.testproject.model.Earning;
import com.example.testproject.model.FundGroup;
import com.example.testproject.model.NetEarning;
import com.example.testproject.service.EarningService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api")
public class EarningController {
    private final EarningService earningService;
    @PostMapping("add-earnings")
    public List<Earning> addEarningsBulk(@RequestBody List<EarningDTO> earningDTOs) {
        return earningService.addEarningsBulk(earningDTOs);
    }

    @PostMapping("add-fund-mapping-for-clients")
    public List<FundGroup> addFundGroupForClient(@RequestBody List<FundGroupDTO> fundGroupDTOs) {
        return earningService.addFundGroupForClients(fundGroupDTOs);
    }

    @PostMapping("add-preferences")
    public List<ClientPreference> addClientPreferences(@RequestBody List<ClientPreferenceDTO> clientPreferenceDTO) {
        return earningService.addClientPreferences(clientPreferenceDTO);
    }

    @GetMapping("calculate-netted-earning")
    public List<NetEarning> calculateNettedEarnings() {
        return earningService.calculateNettedEarnings();
    }

    @GetMapping("get-netted-earning")
    public List<NetEarning> getNettedEarnings() {
        return earningService.getNettedEarnings();
    }
}
