package project.homelearn.service.teacher.homework.strategy;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import project.homelearn.service.common.StorageService;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfFileSimilarityStrategy extends AbstractFileSimilarityStrategy {

    public PdfFileSimilarityStrategy(StorageService storageService) {
        super(storageService);
    }

    @Override
    protected String extractText(InputStream inputStream) throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }
}