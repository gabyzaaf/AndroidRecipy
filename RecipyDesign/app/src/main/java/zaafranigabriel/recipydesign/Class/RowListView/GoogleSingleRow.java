package zaafranigabriel.recipydesign.Class.RowListView;

/**
 * Created by zaafranigabriel on 23/06/2016.
 */
public class GoogleSingleRow {
    private String title;
    private String description;
    private int Image;

    public GoogleSingleRow(int image, String title, String description) {
        Image = image;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
