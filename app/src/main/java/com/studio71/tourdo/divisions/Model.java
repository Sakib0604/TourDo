package com.studio71.tourdo.divisions;

public class Model {

    String name,details,path,pic;

    public Model() {
    }

    public Model(String name, String details, String path, String pic) {
        this.name = name;
        this.details = details;
        this.path = path;
        this.pic = pic;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
