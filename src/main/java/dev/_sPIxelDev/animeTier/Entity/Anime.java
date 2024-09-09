package dev._sPIxelDev.animeTier.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "anime")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column
    private String anime_title;

    @Column
    private int seasons;

    @Column
    @OneToMany(mappedBy="anime_id", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Themes> themes;

    public Anime() {}

    public Anime(int id, String anime_title, int seasons, int openings, int endings) {
        this.id = id;
        this.anime_title = anime_title;
        this.seasons = seasons;
        this.themes = themes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return anime_title;
    }

    public void setTitle(String anime_title) {
        this.anime_title = anime_title;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public List<Themes> getThemes() {
        return themes;
    }

    public void setThemes(List<Themes> themes) {
        this.themes = themes;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", anime_title='" + anime_title + '\'' +
                ", seasons=" + seasons +
                ", themes=" + themes +
                '}';
    }
}
