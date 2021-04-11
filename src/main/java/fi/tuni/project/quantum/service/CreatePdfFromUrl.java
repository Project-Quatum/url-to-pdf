package fi.tuni.project.quantum.service;

import java.io.ByteArrayOutputStream;

public interface CreatePdfFromUrl {
    ByteArrayOutputStream createPdf(String url);
}
