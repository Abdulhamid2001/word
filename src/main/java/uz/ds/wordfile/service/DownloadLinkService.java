package uz.ds.wordfile.service;


import org.springframework.stereotype.Service;
import uz.ds.wordfile.payload.ApiResponse;

import java.io.*;
import java.util.Arrays;


@Service
public class DownloadLinkService {


    public ApiResponse downloadFile()  {
        File file = new File("d:/wordT.docx");
        byte[] bytes = new byte[(int) file.length()];
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("d:/wordT.docx")))) {
            dataInputStream.readFully(bytes);
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(Arrays.toString(bytes));

        byte[] data=bytes;

        try (
                FileOutputStream fos = new FileOutputStream("d:/newWordT.docx")) {
            fos.write(data);
        }catch (Exception e){
            System.out.println(e);
        }

        return new ApiResponse("Downloaded file in D-disk called newWordT.docx !!! bytes of your file:"+Arrays.toString(bytes),true);


    }

}
