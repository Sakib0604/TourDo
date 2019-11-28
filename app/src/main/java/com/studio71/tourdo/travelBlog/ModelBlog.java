package com.studio71.tourdo.travelBlog;

public class ModelBlog {

    String pic,video,name,details;

    public ModelBlog() {
    }

    public ModelBlog(String pic, String video, String name, String details) {
        this.pic = pic;
        this.video = video;
        this.name = name;
        this.details = details;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
