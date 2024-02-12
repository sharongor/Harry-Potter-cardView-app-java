package il.example.assignment2;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DataModel {

    private String name;
    private String description;

    private int image;
    private String extraText;

    public DataModel(String name, String description, int image, String extraText ) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.extraText = extraText;
    }

    public String getExtraText() {
        return extraText;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        DataModel other = (DataModel) obj;
        return this.name.equals(other.name) && this.description.equals(other.description) &&
                this.image==other.image && this.extraText.equals(other.extraText);
    }
}
