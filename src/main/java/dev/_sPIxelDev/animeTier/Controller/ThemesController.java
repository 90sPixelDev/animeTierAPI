package dev._sPIxelDev.animeTier.Controller;

import dev._sPIxelDev.animeTier.Entity.ResponseHandler;
import dev._sPIxelDev.animeTier.Entity.Themes;
import dev._sPIxelDev.animeTier.Repository.ThemesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ThemesController {
    private ThemesRepo themesRepo;

    @Autowired
    public void setThemesRepo(ThemesRepo themesRepo) {
        this.themesRepo = themesRepo;
    };

    @PostMapping("/theme/add")
    public ResponseEntity<Object> addAnime(@RequestBody(required = true) Themes theme) {

        try  {
            themesRepo.save(theme);
            return ResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully added theme to database", theme);
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, true, e.getMessage(), null);
        }

    }
}
