package go.application.com.go.model;

/**
 * Created by epc on 9/9/2016.
 */
public class ItemSlideMenu
{
private int imgId;
    private String title;

    public ItemSlideMenu(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
