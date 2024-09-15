package dev._sPIxelDev.animeTier.Repository;

import dev._sPIxelDev.animeTier.Entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepo extends JpaRepository<Anime, Integer> {

    @Query(nativeQuery = true, value = "SELECT anime_title, theme_title FROM anime, themes WHERE anime.id = themes.anime_id")
    List<Anime> getAllAnimeTitlesWithThemeTitles();
}
