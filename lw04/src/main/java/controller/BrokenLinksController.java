package controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

public class BrokenLinksController {
    private static Logger LOG = Logger.getLogger(BrokenLinksController.class.getName());

    private String instanceUrl;
    private Map<String, Integer> brokenLinks = new HashMap<String, Integer>();
    private Map<String, Integer> workingLinks = new HashMap<String, Integer>();
    private List<String> links = new ArrayList<String>();

    private Queue<String> queue = new LinkedList<String>();

    public BrokenLinksController(String url) {
        this.instanceUrl = url;
        this.queue.add(url);
    }

    public Map<String, Integer> getBrokenLinks() {
        return brokenLinks;
    }

    public void setBrokenLinks(Map<String, Integer> brokenLinks) {
        this.brokenLinks = brokenLinks;
    }

    public void setWorkingLinks(Map<String, Integer> workingLinks) {
        this.workingLinks = workingLinks;
    }

    public Map<String, Integer> getWorkingLinks() {
        return workingLinks;
    }

    public void findLinks() {
        while (!this.queue.isEmpty()) {
            String currentUrl = this.queue.element();
            this.queue.remove();
            findAllLinks(currentUrl);
        }
    }

    public void findAllLinks(String urlStr) {
        try {
            this.links.add(urlStr);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int code = connection.getResponseCode();
            if (code >= 400) {
                brokenLinks.put(urlStr, code);
                return;
            }
            workingLinks.put(urlStr, code);
            Document doc = Jsoup.connect(urlStr).get();

            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");
            findLinks(links, "href");
            findMedia(media, "src");
            findMedia(imports, "href");
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    private void findMedia(Elements links, String Attribute) {
        for (Element link : links) {
            String href = link.absUrl(Attribute);
            this.links.add(href);
        }
    }

    private void findLinks(Elements links, String Attribute) {
        for (Element link : links) {
            String href = link.absUrl(Attribute);
            if (!this.queue.contains(href) && !this.links.contains(href) && href.contains(this.instanceUrl)) {
                queue.add(href);
            }
        }
    }
}
