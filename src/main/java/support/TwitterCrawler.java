package support;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by JJH on 2017-02-24.
 */
public class TwitterCrawler {

    private static final HttpClient defaultHttpClient = HttpClients.createDefault();

    static {
        Logger.getLogger("org.apache.http").setLevel(Level.OFF);
    }

    private static String getURLResponse(String username, String since, String until, String querySearch, String scrollCusor) throws Exception {
        String appendQuery = "";
        if (username != null) {
            appendQuery += "from:" + username;
        }
        if (since != null) {
            appendQuery += " since:" + since;
        }
        if (until != null) {
            appendQuery += " until:" + until;
        }
        if (querySearch != null) {
            appendQuery += " " + querySearch;
        }

        String url = String.format("https://twitter.com/i/search/timeline?f=tweets&vertical=default&q=%s&src=typd&max_position=%s", URLEncoder.encode(appendQuery, "UTF-8"), scrollCusor);
        HttpGet httpGet = new HttpGet(url);
        HttpEntity response = defaultHttpClient.execute(httpGet).getEntity();
        return EntityUtils.toString(response);
    }

    public static void getTweets(Creater creater) {
//        List<HarmfulInfo> results = new ArrayList<HarmfulInfo>();
        int length = 0;
        Pattern pattern = Pattern.compile("#([가-힣A-Za-z0-9]+)");

        try {
            String refreshCursor = null;
            outerLace:
            {
                while (true) {
                    JSONObject json = new JSONObject(getURLResponse(creater.getUsername(), creater.getSince(), creater.getUntil(), creater.getQuerySearch(), refreshCursor));
                    refreshCursor = json.getString("min_position");
                    Document doc = Jsoup.parse((String) json.get("items_html"));
                    Elements tweets = doc.select("div.js-stream-tweet");

                    if (tweets.size() == 0) {
                        break;
                    }

                    for (Element tweet : tweets) {
                        String username = tweet.select("span.username.js-action-profile-name b").text(); // user name : ~
                        String userId = tweet.select("strong.show-popup-with-id").text(); // user id : @Something
                        String content = tweet.select("p.js-tweet-text.tweet-text").text().replaceAll("[^\\u0000-\\uFFFF]", ""); // contents of tweet
                        String link = tweet.select("p.js-tweet-text.tweet-text > a").attr("title"); // link in tweet
                        long dateMs = Long.valueOf(tweet.select("small.time span.js-short-timestamp").attr("data-time-ms")); // Date milliseconds
                        Date date = new Date(dateMs); // milliseconds convert to date
                        Time time = new Time(dateMs); // milliseconds convert to time
                        ArrayList<String> hashTags = new ArrayList<String>();

                        if (content != null) {
                            Matcher matcher = pattern.matcher(content);
                            while (matcher.find()) {
                                hashTags.add(matcher.group().substring(1,matcher.group().length()));
                            }
                        }


                        System.out.println("======================================================================");
                        System.out.println("유저 이름: " + username);
                        System.out.println("유저 아이디: " + userId);
                        System.out.println("내용: " + content);
                        System.out.println("업로드 날짜 / 시간: " + date + " / " + time);
                        System.out.println("링크: " + link);
                        for (String hash : hashTags) {
                            System.out.println("해쉬태그:"+ hash);
                        }

                        if (creater.getMaxTweets() > 0 && length >= creater.getMaxTweets()) {
                            break outerLace;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
