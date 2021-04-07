package fi.tuni.project.quantum.service;

import java.io.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;

public interface CreatePdfFromUrl {
    ByteArrayOutputStream createPdf(String url);
    ByteArrayOutputStream createPdf();
}
