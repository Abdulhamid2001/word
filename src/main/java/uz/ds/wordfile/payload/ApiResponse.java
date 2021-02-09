package uz.ds.wordfile.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String messageUzb;
    private String messageEng;
    private String messageRus;
    private String messageUzK;
    private boolean success;





    public ApiResponse(String messageUzb, Boolean success) {
        this.messageUzb = messageUzb;
        this.success = success;
    }

}
