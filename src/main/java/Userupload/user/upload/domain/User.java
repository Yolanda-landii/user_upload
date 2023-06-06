package Userupload.user.upload.domain;

import com.opencsv.bean.CsvBindByName;

public class User {
    @CsvBindByName
    private long id;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String email;
    @CsvBindByName(column = "country")
    private String countryCode;
    @CsvBindByName
    private int age;

    public User(long id, String name, String email, String countryCode, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.countryCode = countryCode;
        this.age = age;
    }

    // getters and setters removed for the sake of brevity


}
