package org.mediameter.extractor.test;

import java.net.URL;

import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class SimpleExtractionText {
    public static void main(final String[] args) throws Exception {
        final URL url = new URL("http://www.theguardian.com/cities/2014/apr/30/rio-favelas-world-cup-olympics-vision-future-criminal-eyesore");
        String text = ArticleExtractor.INSTANCE.getText(url);
        System.out.println(text);
    }
}