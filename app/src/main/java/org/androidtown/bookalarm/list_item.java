package org.androidtown.bookalarm;

/**
 * Created by wlals on 2017-08-30.
 */

public class list_item {

    private String link;
    private String title;
    private String author;
    private String cover;


    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public list_item(String title, String link, String author,String cover) {

        this.title = title;
        this.link = link.substring(1, link.length() - 1);
        this.author = author.substring(1, author.length() - 1);
        this.cover = cover.substring(1, cover.length() - 1);
    }



}
