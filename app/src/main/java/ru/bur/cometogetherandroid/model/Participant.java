package ru.bur.cometogetherandroid.model;

public class Participant {

    String name;
    int photoPath;
    public Participant(String name) {
        this.name = name;
    }

    public Participant(String name, int photoPath) {
        this.name = name;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(int photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", photoPath=" + photoPath +
                '}';
    }
}
