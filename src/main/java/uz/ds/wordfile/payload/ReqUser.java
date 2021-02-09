package uz.ds.wordfile.payload;

import lombok.Data;

@Data
public class ReqUser {
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String password;
    private String prePassword;
}
