package uz.ds.wordfile.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ds.wordfile.payload.ApiResponse;
import uz.ds.wordfile.service.DownloadLinkService;
import uz.ds.wordfile.service.WordTableService;

import java.io.IOException;


@RestController
@RequestMapping("/api/wordDoc")
public class WordTableController {
    @Autowired
    WordTableService wordTableService;
    @Autowired
    DownloadLinkService downloadLinkService;



    @GetMapping("/docs")
    public HttpEntity<?> getDoc() throws IOException {
        ApiResponse response=wordTableService.createTableWord();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/downloading")
    public HttpEntity<?> download() {
        ApiResponse response=downloadLinkService.downloadFile();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }
}
