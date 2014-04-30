package org.mediameter.extractor;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class ExtractTextServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ExtractTextServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        logger.info("Text Parse Request from "+request.getRemoteAddr());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/plain;charset=UTF=8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String urlString = request.getParameter("url");
        logger.info("Request to parse "+urlString);
        if(urlString==null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                URL url = new URL(urlString);
                String text = ArticleExtractor.INSTANCE.getText(url);
                logger.info(text);
                response.getWriter().write(text);
                logger.info("done");
            } catch (BoilerpipeProcessingException e) {
                logger.error(e.toString());
                response.getWriter().write(e.toString());            
            }
        }
        response.getWriter().flush();
    }
    
}
