package dev._sPIxelDev.animeTier.Controller;

import dev._sPIxelDev.animeTier.Entity.Themes;
import dev._sPIxelDev.animeTier.Repository.ThemesRepo;
import dev._sPIxelDev.animeTier.Service.ThemesService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/addtheme")
    public String addTheme(@RequestBody Themes theme) {

        themesRepo.save(theme);

        return "Added theme succesfully!";
    }
}
