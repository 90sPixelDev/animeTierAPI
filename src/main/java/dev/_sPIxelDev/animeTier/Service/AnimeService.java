package dev._sPIxelDev.animeTier.Service;

import dev._sPIxelDev.animeTier.Entity.Anime;
import dev._sPIxelDev.animeTier.Repository.AnimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepo animeRepo;

}
