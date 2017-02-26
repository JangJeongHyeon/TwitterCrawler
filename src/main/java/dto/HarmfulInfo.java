package dto;

import java.util.ArrayList;

/**
 * Created by JJH on 2017-02-24.
 */
public class HarmfulInfo {

    private String userID; // primary ID of user
    private String userName; // user name
    private String content; // contents of tweet
    private String url; // harmful URL
    private ArrayList<String> hashTag; // hash tag list of tweet


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getHashTag() {
        return hashTag;
    }

    public void setHashTag(ArrayList<String> hashTag) {
        this.hashTag = hashTag;
    }
}
