package dev._sPIxelDev.animeTier.Controller;

import dev._sPIxelDev.animeTier.Entity.Anime;
import dev._sPIxelDev.animeTier.Entity.ResponseHandler;
import dev._sPIxelDev.animeTier.Repository.AnimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AnimeController {
    private AnimeRepo animeRepo;

    @Autowired
    public void setAnimeRepo(AnimeRepo animeRepo) {
        this.animeRepo = animeRepo;
    }

    @GetMapping("/animeTT")
    public List<Anime> getAnimeTT () {
        return animeRepo.getAllAnimeTitlesWithThemeTitles();
    }


    @GetMapping("/anime")
    public List<Anime> getAllAnime(@RequestParam(defaultValue = "1") int pageNumber) {
        Page<Anime> animePage = animeRepo.findAll(PageRequest.of(pageNumber, 21, Sort.Direction.ASC, "id"));

        return animePage.getContent();
    }

    @GetMapping("/anime/{anime_id}")
    public ResponseEntity<Object> getAnimeById(@PathVariable int anime_id) {
        Anime anime = animeRepo.findById(anime_id).get();

        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Anime info fetched successfully!", anime);
        } catch(Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, e.getMessage(), null);
        }
    }

    @GetMapping("/anime/{anime_id}/title")
    public ResponseEntity<Object> getAnimeTitle(@PathVariable int anime_id) {

        try {
            String anime_title = animeRepo.findById(anime_id).get().getTitle();
            return new ResponseEntity<>(anime_title, HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, e.getMessage(), null);
        }
    }


    // TAKES RAW TEXT AS PARAM
    @PutMapping("/anime/update/{anime_id}")
    @ResponseBody
    public ResponseEntity<Object> updateAnimeTitle(@PathVariable int anime_id,@RequestBody String anime_title) {
//        HttpHeaders headers = new HttpHeaders();


        if (!anime_title.matches(".*\\w.*"))
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, "Invalid anime title", null);


        try {
            Anime anime = animeRepo.findById(anime_id).get();
            anime.setTitle(anime_title);
            animeRepo.save(anime);
            System.out.println("New Anime Title:" + anime_title);
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Sucessfully updated anime in database", anime);
        } catch (Exception e ) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, e.getMessage(), null);
        }
    }


    @PostMapping("/anime/add")
    public ResponseEntity<Object> addAnime(@RequestBody(required = true) Anime anime) {

        try  {
            animeRepo.save(anime);
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Sucessfully added anime to database", anime);
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, e.getMessage(), null);
        }

    }

    @DeleteMapping("/anime/delete")
    public ResponseEntity<Object> deleteSection(@RequestParam(required = true) int anime_id) {

        try {
            Anime anime = animeRepo.findAll().get(anime_id);
            animeRepo.delete(anime);
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Sucessfully deleted anime from database", anime);
        } catch (Exception e){
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, true, e.getMessage(), null);

        }
    }
}
