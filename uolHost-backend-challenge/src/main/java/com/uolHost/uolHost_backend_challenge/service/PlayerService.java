package com.uolHost.uolHost_backend_challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uolHost.uolHost_backend_challenge.dto.ResponsePlayerDto;
import com.uolHost.uolHost_backend_challenge.dto.SavePlayerDto;
import com.uolHost.uolHost_backend_challenge.entity.Player;
import com.uolHost.uolHost_backend_challenge.entity.avenger.AvengerCodename;
import com.uolHost.uolHost_backend_challenge.entity.avenger.Avengers;
import com.uolHost.uolHost_backend_challenge.entity.enums.HeroGroup;
import com.uolHost.uolHost_backend_challenge.entity.justiceLeague.JusticeLeague;
import com.uolHost.uolHost_backend_challenge.exceptionHandler.AppealExhaustedException;
import com.uolHost.uolHost_backend_challenge.exceptionHandler.ReadJSONException;
import com.uolHost.uolHost_backend_challenge.exceptionHandler.ReadXMLException;
import com.uolHost.uolHost_backend_challenge.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final RestClient restClient;

    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);


    ObjectMapper mapper = new ObjectMapper();

    public PlayerService(PlayerRepository playerRepository, RestClient restClient) {
        this.playerRepository = playerRepository;
        this.restClient = restClient;
    }

    public ResponsePlayerDto savePlayer(SavePlayerDto dto) {

        var player = new Player();
        player.setName(dto.name());
        player.setEmail(dto.email());
        player.setPhone(dto.phone());
        player.setHeroGroup(HeroGroup.valueOf(dto.heroGroup()));


        if (player.getHeroGroup().equals(HeroGroup.VINGADORES)) {

            var isAvailableCodename = verifyAvailabilityOfAvengersCodename(player);
            logger.info("is available: {}", isAvailableCodename);
            if (isAvailableCodename != null) {

                player.setCodename(isAvailableCodename);
                playerRepository.save(player);
            }
        }

        if (player.getHeroGroup().equals(HeroGroup.LIGA_DA_JUSTICA)) {

            var isAvailableCodename = verifyAvailabilityOfJusticeLeagueCodename(player);
            if (isAvailableCodename != null) {

                player.setCodename(isAvailableCodename);
                playerRepository.save(player);
            }
        }

        return new ResponsePlayerDto(player.getName(), player.getCodename(), player.getHeroGroup());

    }

    public List<ResponsePlayerDto> getAllPlayers() {

        var allPlayers = playerRepository.findAll();

        var response = allPlayers.stream()
                .map(player -> new ResponsePlayerDto(player.getName(), player.getCodename(), player.getHeroGroup()))
                .toList();

        return response;

    }

    private String verifyAvailabilityOfAvengersCodename(Player player) {

        var responseAvengers = restClient.get()
                .uri("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json")
                .retrieve()
                .body(String.class);

        try {

            Avengers vingadoresList = mapper.readValue(responseAvengers, Avengers.class);

            for (AvengerCodename vingador : vingadoresList.getVingadores()) {

                var isCodenameAlreadyRegistered = playerRepository.findByCodename(vingador.getCodename());
                if (isCodenameAlreadyRegistered.isEmpty()) {
                    String codename = vingador.getCodename();
                    return codename;
                }
            }

        } catch (JsonProcessingException exception) {
            throw new ReadJSONException("A error occurred in the JSON archive processing");
        }


        throw new AppealExhaustedException("All the codenames for Avengers have already been used");

    }

    private String verifyAvailabilityOfJusticeLeagueCodename(Player player)  {

        var responseJusticeLeague = restClient.get()
                .uri("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml")
                .retrieve()
                .body(String.class);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(JusticeLeague.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            JusticeLeague justiceLeague = (JusticeLeague) jaxbUnmarshaller.unmarshal(new StringReader(responseJusticeLeague));

            List<String> codenames = justiceLeague.getCodenames().getCodename();

            for (String codename : codenames) {
                var isCodenameAlreadyRegistered = playerRepository.findByCodename(codename);
                if (isCodenameAlreadyRegistered.isEmpty()) {
                    return codename;
                }
            }
        } catch (JAXBException exception) {
            throw new ReadXMLException("A error occurred in the XML archive processing");
        }

        throw new AppealExhaustedException("All the codenames for Justice League have already been used");
    }

}
