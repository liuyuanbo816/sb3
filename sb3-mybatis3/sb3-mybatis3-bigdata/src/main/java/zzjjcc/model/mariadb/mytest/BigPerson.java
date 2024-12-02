package zzjjcc.model.mariadb.mytest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BigPerson {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private Integer married;
    private LocalDate birthDay;
    private String province;
    private String city;
    private String country;
    private String email;
    private String phone;
    private String zip;
    private String card;
    private String avatar;
    private String intro;
}
