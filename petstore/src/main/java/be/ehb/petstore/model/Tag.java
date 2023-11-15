package be.ehb.petstore.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long id;
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Item> itemList;

    public Tag() {
    }
    public Tag(String tagName, List<Item> itemList) {
        this.tagName = tagName;
        this.itemList = itemList;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(getTagName(), tag.getTagName()) && Objects.equals(getItemList(), tag.getItemList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTagName(), getItemList());
    }
}
