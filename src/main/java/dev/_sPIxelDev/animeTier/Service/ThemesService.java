package dev._sPIxelDev.animeTier.Service;

import dev._sPIxelDev.animeTier.Repository.ThemesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ThemesService {

    @Autowired
    private ThemesRepo themesRepo;
}
