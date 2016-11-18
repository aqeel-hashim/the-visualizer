package go.application.com.go.model;

import java.io.Serializable;

/**
 * Created by DELL on 11/17/2016.
 */

public class Image implements Serializable {
    private String path;
    private String name;
    private String type;
    private String ratailer;
    private String address;
    private String contact;
    public Image(String path, String name, String type, String ratailer, String address, String contact) {
        this.path = path;
        this.name = name;
        this.type = type;
        this.ratailer = ratailer;
        this.address = address;
        this.contact = contact;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRatailer() {
        return ratailer;
    }

    public void setRatailer(String ratailer) {
        this.ratailer = ratailer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
