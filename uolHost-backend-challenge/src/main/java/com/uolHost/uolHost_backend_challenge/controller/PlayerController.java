package com.uolHost.uolHost_backend_challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uolHost.uolHost_backend_challenge.dto.ResponsePlayerDto;
import com.uolHost.uolHost_backend_challenge.dto.SavePlayerDto;
import com.uolHost.uolHost_backend_challenge.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    public PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<ResponsePlayerDto> savePlayer(@Valid @RequestBody SavePlayerDto dto) {

        var response = playerService.savePlayer(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePlayerDto>> GetAllPlayers() {

        var response = playerService.getAllPlayers();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
