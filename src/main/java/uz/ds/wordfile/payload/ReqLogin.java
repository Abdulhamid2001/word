package uz.ds.wordfile.payload;

import lombok.Data;

@Data
public class ReqLogin {
    private String phoneNumber;
    private String password;
}
