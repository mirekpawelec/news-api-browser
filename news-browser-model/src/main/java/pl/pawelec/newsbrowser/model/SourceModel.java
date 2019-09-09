package pl.pawelec.newsbrowser.model;

public class SourceModel {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SourceModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}