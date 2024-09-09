package dev._sPIxelDev.animeTier.Service;

import dev._sPIxelDev.animeTier.Entity.Themes;
import dev._sPIxelDev.animeTier.Repository.ThemesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemesService {

    @Autowired
    private ThemesRepo themesRepo;
}
