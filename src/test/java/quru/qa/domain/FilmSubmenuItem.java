package quru.qa.domain;

public enum FilmSubmenuItem {
    ALL("Все фильмы NetFlix"), NEW("Новые фильмы"), BEST2018("Лучшие фильмы 2018"), BEST2020("Лучшие фильмы 2020"), SCHOOL("Про школу"), COMEDY("Комедии");
    public final String rusName;

    FilmSubmenuItem(String rusName) {
        this.rusName = rusName;
    }
}
