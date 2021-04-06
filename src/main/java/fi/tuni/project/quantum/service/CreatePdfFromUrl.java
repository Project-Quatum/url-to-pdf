package fi.tuni.project.quantum.service;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface CreatePdfFromUrl {
    PDDocument createPdf(String url);
}
