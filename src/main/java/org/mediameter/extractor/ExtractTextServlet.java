package org.mediameter.extractor;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class ExtractTextServlet extends HttpServlet {

    public static String VERSION = "0.5";
    public static String STATUS_OK = "ok";
    public static String STATUS_ERROR = "error";
    
    private static final Logger logger = LoggerFactory.getLogger(ExtractTextServlet.class);
    private static Gson gson = new Gson();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        logger.info("Text Parse Request from "+request.getRemoteAddr());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/plain;charset=UTF=8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        long startTime = System.currentTimeMillis();
        HashMap results = new HashMap();
        results.put("version",VERSION);
        String urlString = request.getParameter("url");
        logger.info("Request to parse "+urlString);
        if(urlString==null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            try {
                URL url = new URL(urlString);
                String text = ArticleExtractor.INSTANCE.getText(url);
                logger.info(text);
                logger.info("done");
                HashMap info = new HashMap();
                info.put("url",urlString);
                info.put("text", text);
                results.put("results",info);
                results.put("status",STATUS_OK);
            } catch (BoilerpipeProcessingException e) {
                logger.error(e.toString());
                HashMap error = new HashMap();
                error.put("type",e.getClass().getName());
                error.put("message",e.getMessage());
                results.put("error", error);
                results.put("status",STATUS_ERROR);
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsedMillis = endTime - startTime;
        results.put("milliseconds", elapsedMillis);
        
        String jsonResults = gson.toJson(results);
        logger.debug(jsonResults);
        response.getWriter().write(jsonResults);
    }
    
}
