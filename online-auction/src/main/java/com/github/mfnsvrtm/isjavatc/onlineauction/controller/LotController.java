package com.github.mfnsvrtm.isjavatc.onlineauction.controller;

import com.github.mfnsvrtm.isjavatc.onlineauction.dto.creation.LotCreationDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.LotDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.summary.LotSummaryDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.dto.update.LotUpdateDto;
import com.github.mfnsvrtm.isjavatc.onlineauction.service.LotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    @GetMapping("/api/lots/{id}")
    public LotDto getLotById(@PathVariable int id) {
        return lotService.getLotById(id);
    }

    @GetMapping("/api/lots")
    public List<LotSummaryDto> getLots(
        @RequestParam(required = false) Integer categoryId,
        @RequestParam(required = false) String name
    ) {
        return lotService.getLots(categoryId, name);
    }

    @GetMapping("/api/users/me/lots")
    public List<LotSummaryDto> getLotsByCurrentUser(Authentication authentication) {
        return lotService.getLotsByCurrentUser((UserDetails) authentication.getPrincipal());
    }

    @PostMapping("/api/lots")
    public LotDto createLot(@Valid @RequestBody LotCreationDto lotCreationDto, Authentication authentication) {
        return lotService.createLot(lotCreationDto, (UserDetails) authentication.getPrincipal());
    }

    @PatchMapping("/api/lots/{id}")
    public LotDto updateLotById(@PathVariable int id, @Valid @RequestBody LotUpdateDto lotUpdateDto,
                              Authentication authentication) {
        return lotService.updateLot(id, lotUpdateDto, (UserDetails) authentication.getPrincipal());
    }

    @DeleteMapping("/api/lots/{id}")
    public void deleteLotById(@PathVariable int id, Authentication authentication) {
        lotService.removeLot(id, (UserDetails) authentication.getPrincipal());
    }

}
