package dev._sPIxelDev.animeTier.Repository;

import dev._sPIxelDev.animeTier.Entity.Anime;
import dev._sPIxelDev.animeTier.Entity.Themes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemesRepo extends JpaRepository<Themes, Integer> {
}
