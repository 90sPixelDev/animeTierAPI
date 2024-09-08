package dev._sPIxelDev.animeTier.Controller;

import dev._sPIxelDev.animeTier.Entity.Anime;
import dev._sPIxelDev.animeTier.Repository.AnimeRepo;
import dev._sPIxelDev.animeTier.Service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimeController {

    @Autowired
    private AnimeService animeService;
    @Autowired
    private AnimeRepo animeRepo;


    @GetMapping("/anime")
    public List<Anime> getAllAnime() {
        List<Anime> anime = animeRepo.findAll();

        return anime;
    };

    @PostMapping("/addanime")
    public String addAnime(@RequestBody Anime anime) {

        animeService.addAnime(anime);

        return "Posted Succesfully";
    }
}
