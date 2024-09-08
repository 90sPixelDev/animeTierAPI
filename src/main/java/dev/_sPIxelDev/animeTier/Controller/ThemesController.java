package dev._sPIxelDev.animeTier.Controller;

import dev._sPIxelDev.animeTier.Entity.Themes;
import dev._sPIxelDev.animeTier.Repository.ThemesRepo;
import dev._sPIxelDev.animeTier.Service.ThemesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThemesController {

    @Autowired
    private ThemesRepo themesRepo;
    @Autowired
    private ThemesService themesService;

    @PostMapping("/addtheme")
    public String addTheme(@RequestBody Themes theme) {
        themesService.addTheme(theme);

        return "Added theme succesfully!";
    }
}
