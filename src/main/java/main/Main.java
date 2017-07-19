package main;

import support.Creater;
import support.TwitterCrawler;

/**
 * Created by JJH on 2017-02-25.
 */
public class Main {

    public static void main(String args[]) {
        Creater creater = Creater.create();
        
        /**
        * @Parma query - keyword for search tweets, if parm is empty you can crawled all of tweet datas on realtime
        */
        creater.setQuerySearch("");
        TwitterCrawler.getTweets(creater);
    }
}
