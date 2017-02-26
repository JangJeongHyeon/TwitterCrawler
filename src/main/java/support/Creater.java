package support;

/**
 * Created by JJH on 2017-02-24.
 */
public class Creater {

    private String username;
    private String since;
    private String until;
    private String querySearch;
    private int maxTweets;

    private Creater() {
    }

    public static Creater create(){return new Creater();}

    public String getUsername() {
        return username;
    }

    public Creater setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getSince() {
        return since;
    }

    public Creater setSince(String since) {
        this.since = since;
        return this;
    }

    public String getUntil() {
        return until;
    }

    public Creater setUntil(String until) {
        this.until = until;
        return this;
    }

    public String getQuerySearch() {
        return querySearch;
    }

    public Creater setQuerySearch(String querySearch) {
        this.querySearch = querySearch;
        return this;
    }

    public int getMaxTweets() {
        return maxTweets;
    }

    public Creater setMaxTweets(int maxTweets) {
        this.maxTweets = maxTweets;
        return this;
    }
}
