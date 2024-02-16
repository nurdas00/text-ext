package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.IOException;
import java.io.InputStream;

public class TextExtractor {
    public String extractPdf(InputStream inputStream) throws IOException {
        PDFParser parser = new PDFParser();
        BodyContentHandler contentHandler
                = new BodyContentHandler();
        try {
            parser.parse(inputStream, contentHandler, new Metadata(), new ParseContext());
        } catch (Exception e) {
            System.out.println("Parse error");
            return null;
        }
        inputStream.close();
        return contentHandler.toString();
    }

    public String extractDocx(InputStream inputStream) throws IOException, InvalidFormatException {
        XWPFDocument file   = new XWPFDocument(OPCPackage.open(inputStream));
        XWPFWordExtractor ext = new XWPFWordExtractor(file);
        return ext.getText();

    }
}
