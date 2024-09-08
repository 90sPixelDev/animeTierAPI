package dev._sPIxelDev.animeTier.Repository;

import dev._sPIxelDev.animeTier.Entity.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepo extends JpaRepository<Anime, Integer> {
}
