package be.ehb.petstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity(name = "image_data")
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_data_id")
    private Long id;
    @NotNull(message = "Image url can not be empty")
    @Column(unique = true)
    private String imageUrl;
    private String fileName;
    @ManyToMany(mappedBy = "imageData")
    private List<Item> itemList;

    public ImageData() {
    }
    public ImageData(String imageUrl, String fileName, List<Item> itemList) {
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.itemList = itemList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageData imageData = (ImageData) o;
        return Objects.equals(id, imageData.id) && Objects.equals(getImageUrl(), imageData.getImageUrl()) && Objects.equals(getFileName(), imageData.getFileName()) && Objects.equals(getItemList(), imageData.getItemList());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, getImageUrl(), getFileName(), getItemList());
    }
}
