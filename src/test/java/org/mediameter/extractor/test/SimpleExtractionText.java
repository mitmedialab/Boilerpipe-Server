package org.mediameter.extractor.test;

import java.net.URL;

import org.mediameter.extractor.HTMLFetcher;

import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;

public class SimpleExtractionText {
    public static void main(final String[] args) throws Exception {
        URL url = new URL("http://finance.yahoo.com/news/ukraine-russia-eu-may-meet-094508779.html");
        HTMLDocument htmlDoc = HTMLFetcher.fetch(url);
        TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
        String text = ArticleExtractor.INSTANCE.getText(doc);
        System.out.println(text);
    }
}