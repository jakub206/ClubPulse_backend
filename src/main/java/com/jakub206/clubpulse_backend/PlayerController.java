package com.jakub206.clubpulse_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    //CREATE
    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    //READ
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    //READ{id}
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie ma piłkarza o id: " + id));
        return player;
    }

    //UPDATE
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie ma piłkarza o id: " + id));
        player.setName(playerDetails.getName());
        player.setSurname(playerDetails.getSurname());
        player.setPhoneNumber(playerDetails.getPhoneNumber());
        player.setBirthDate(playerDetails.getBirthDate());
        player.setEmail(playerDetails.getEmail());
        player.setPosition(playerDetails.getPosition());
        return playerRepository.save(player);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie ma piłkarza o id: " + id));
        playerRepository.delete(player);
        return "Usunięto piłkarza o id: " + id;
    }
}