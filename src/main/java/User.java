public class User {

    int id;
    String nickname;
    String registration_date;
    String country_id;
    String user_type;
    String[] tags;
    String site_id;

    public User(int id, String nickname, String registration_date, String country_id, String user_type, String[] tags, String site_id) {

        this.id = id;
        this.nickname = nickname;
        this.registration_date = registration_date;
        this.country_id = country_id;
        this.user_type = user_type;
        this.tags = tags;
        this.site_id = site_id;
    }
}
