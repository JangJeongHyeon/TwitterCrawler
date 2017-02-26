package main;

import support.Creater;
import support.TwitterCrawler;

/**
 * Created by JJH on 2017-02-25.
 */
public class Main {

    public static void main(String args[]) {
        Creater creater = Creater.create();

        creater.setQuerySearch("밍키넷")
                .setUsername("12Ylo7");
        TwitterCrawler.getTweets(creater);
    }
}
