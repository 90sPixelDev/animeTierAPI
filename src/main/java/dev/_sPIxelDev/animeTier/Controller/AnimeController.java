package dev._sPIxelDev.animeTier.Controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.util.JSONPObject;
import dev._sPIxelDev.animeTier.Entity.Anime;
import dev._sPIxelDev.animeTier.Entity.ResponseHandler;
import dev._sPIxelDev.animeTier.Repository.AnimeRepo;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

@RestController
public class AnimeController {
    private AnimeRepo animeRepo;

    @Autowired
    public void setAnimeRepo(AnimeRepo animeRepo) {
        this.animeRepo = animeRepo;
    };


    @GetMapping("/anime")
    public List<Anime> getAllAnime(@RequestParam(defaultValue = "1") int pageNumber) {
        Page<Anime> animePage = animeRepo.findAll(PageRequest.of(pageNumber, 21, Sort.Direction.ASC, "id"));

        return animePage.getContent();
    };

    @GetMapping("/anime/{anime_id}")
    public ResponseEntity<Object> getAnimeById(@PathVariable int anime_id) {
        Anime anime = animeRepo.findById(anime_id).get();

        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Anime info fetched successfully!", anime);
        } catch(Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, e.getMessage(), null);
        }
    };

    @GetMapping("/anime/{anime_id}/title")
    public ResponseEntity<String> getAnimeTitle(@PathVariable int anime_id) {
        String anime_title = animeRepo.findById(anime_id).get().getTitle();

        if (anime_title != null)
            return new ResponseEntity<>(anime_title, HttpStatus.OK);
        else
            return new ResponseEntity<>(anime_title, HttpStatus.NOT_FOUND);
    };


    // TAKES RAW TEXT AS PARAM
    @PutMapping("/anime/update/{anime_id}")
    @ResponseBody
    public String updateAnime(@PathVariable int anime_id,@RequestBody String anime_title) {
//        HttpHeaders headers = new HttpHeaders();

        Anime anime = animeRepo.findById(anime_id).get();

        if (!anime_title.matches(".*\\w.*")) return "Invalid Anime Title.";

        try {
            anime.setTitle(anime_title);
            animeRepo.save(anime);
            System.out.println("New Anime Title:" + anime_title);
            return "Successful Anime Title Update!";
        } catch (Exception e ) {
            return e.getMessage();
        }
    }

//    @PutMapping("/anime/update/{anime_id}")
//    public ResponseEntity<Anime> updateAnime(@PathVariable int anime_id,@RequestBody int seasons) {
//        HttpHeaders headers = new HttpHeaders();
//
//        if (animeRepo.findById(anime_id).isPresent()) {
//            Anime getAnime = animeRepo.findById(anime_id).get();
//
//            if (seasons > 0 && seasons < 999) {
//                getAnime.setSeasons(seasons);
//                animeRepo.save(getAnime);
//                headers.add("Anime Seasons Updated Successfully", getAnime.toString());
//                return new ResponseEntity<>(headers, HttpStatus.OK);
//            }
//
//        }
//
//        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
//    }


    @PostMapping("/anime/add")
    public ResponseEntity<String> addAnime(@RequestBody Anime anime) {

        HttpHeaders headers = new HttpHeaders();

        if (animeRepo.save(anime) != null) {
            headers.add("anime", anime.toString());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/anime/delete/{anime_id}")
     public void deleteAnimeById(@PathVariable Integer anime_id) {

        animeRepo.deleteById(anime_id);

//        if (animeService.deleteAnimeByID(anime_id) != null) {
//            return new ResponseEntity<>("Success", HttpStatus.OK);
//        }
//        else
//            return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }
}
