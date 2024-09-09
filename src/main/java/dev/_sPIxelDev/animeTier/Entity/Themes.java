package dev._sPIxelDev.animeTier.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Table(name = "themes")
@Entity
public class Themes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int themeId;

    @Column
    private String theme_title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_id", nullable = false)
    @JsonBackReference
    private Anime anime_id;

    @Column
    private char rating;

    @Column
    private Boolean is_opening;

    public Themes() {};

    public Themes(int themeId) {
        this.themeId = themeId;
    };

    public Themes(int themeId, String theme_title, Anime anime_id, char rating, Boolean is_opening) {
        this.themeId = themeId;
        this.theme_title = theme_title;
        this.anime_id = anime_id;
        this.rating = rating;
        this.is_opening = is_opening;
    }

    public int getId() {
        return themeId;
    }

    public void setId(int themeId) {
        this.themeId = themeId;
    }

    public String getTitle() {
        return theme_title;
    }

    public void setTitle(String theme_title) {
        this.theme_title = theme_title;
    }

    public Anime getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(Anime anime_id) {
        this.anime_id = anime_id;
    }

    public char getRating() {
        return rating;
    }

    public void setRating(char rating) {
        this.rating = rating;
    }

    public Boolean getIs_opening() {
        return is_opening;
    }

    public void setIs_opening(Boolean is_opening) {
        this.is_opening = is_opening;
    }

    @Override
    public String toString() {
        return "Themes{" +
                "themeId=" + themeId +
                ", theme_title='" + theme_title + '\'' +
                ", anime_id=" + anime_id +
                ", rating=" + rating +
                ", is_opening=" + is_opening +
                '}';
    }
}
