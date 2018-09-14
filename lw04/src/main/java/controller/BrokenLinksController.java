package controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BrokenLinksController {
    private static Logger LOG = Logger.getLogger(BrokenLinksController.class.getName());

    private String instanceUrl;
    private Map<String, Integer> brokenLinks = new HashMap<String, Integer>();
    private Map<String, Integer> workingLinks = new HashMap<String, Integer>();
    private List<String> links = new ArrayList<String>();
    private String siteName = "";

    public BrokenLinksController(String url) {
        this.instanceUrl = url;
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

    public void findBrokenLinks() {
        try {
            for (String href: this.links) {
                URL url = new URL(href);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int code = connection.getResponseCode();
                String message = connection.getResponseMessage();
                if (message.equals("OK") && code == 200) {
                    workingLinks.put(href, code);
                    continue;
                }
                brokenLinks.put(href, code);
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }


    public void findAllLinks() {
        try {
            URL url = new URL(this.instanceUrl);
            if (!this.instanceUrl.matches("[0-9]+")) {
                this.siteName = url.getHost().substring(0, url.getHost().indexOf("."));
            }
            if (this.instanceUrl.substring(this.instanceUrl.length() - 1).equals("/")) {
                this.instanceUrl = this.instanceUrl.substring(0, this.instanceUrl.length() - 2);
            }
            Document doc = Jsoup.connect(this.instanceUrl).get();

            Elements links = doc.select("a[href]");
            Elements media = doc.select("[src]");
            Elements imports = doc.select("link[href]");
            findLinks(links, "href");
            findLinks(media, "src");
            findLinks(imports, "href");
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    private void findLinks(Elements links, String Attribute) {
        for (Element link : links) {
            Attributes attributes = link.attributes();
            String href = attributes.get(Attribute);
            if (href.contains("#")) {
                continue;
            }
            if (href.contains(this.instanceUrl)
                    || href.contains(this.siteName)
                    || href.contains("//")) {
                this.links.add(href);
            } else {
                href = this.instanceUrl + href;
                this.links.add(href);
            }
        }
    }
}
