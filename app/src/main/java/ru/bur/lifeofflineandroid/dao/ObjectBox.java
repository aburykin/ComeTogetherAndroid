package ru.bur.lifeofflineandroid.dao;

import java.util.List;

import ru.bur.lifeofflineandroid.model.Meeting;

public interface ObjectBox {

     void create(Meeting meeting);

     List<Meeting> getAll();

}
