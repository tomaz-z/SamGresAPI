package si.samgres.api.models;

public class SimpleUser {
    int id;
    String phone_number;
    String fullname;
    String email;

    public SimpleUser() {}

    public SimpleUser(int id, String phone_number, String fullname, String email) {
        this.id = id;
        this.phone_number = phone_number;
        this.fullname = fullname;
        this.email = email;
    }

    public SimpleUser(User user) {
        this.id = user.id;
        this.phone_number = user.phone_number;
        this.fullname = user.fullname;
        this.email = user.email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
