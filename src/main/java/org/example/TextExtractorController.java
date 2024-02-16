package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api")
public class TextExtractorController {

    @PostMapping("/pdf")
    public ResponseEntity getPdfText(@FormDataParam("file") InputStream inputStream) throws IOException {
        TextExtractor textExtractor = new TextExtractor();
        String text = textExtractor.extractPdf(inputStream);
        Response response = new Response();
        response.setText(text);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/docx")
    public ResponseEntity getDocxText(@FormDataParam("file") InputStream inputStream) throws IOException, InvalidFormatException {
        TextExtractor textExtractor = new TextExtractor();
        String text = textExtractor.extractDocx(inputStream);
        Response response = new Response();
        response.setText(text);
        return ResponseEntity.ok(response);
    }
}
