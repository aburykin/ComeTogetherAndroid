package ru.bur.lifeofflineandroid.dao.objectBox;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import ru.bur.lifeofflineandroid.LifeOfflineApp;
import ru.bur.lifeofflineandroid.dao.ObjectBox;
import ru.bur.lifeofflineandroid.model.Meeting;

public class ObjectBoxImpl implements ObjectBox {

    @Inject
    Context pContext;

    private BoxStore boxStore;


    public ObjectBoxImpl(Context context) {
        this.pContext = context;
        this.boxStore = ((LifeOfflineApp) context.getApplicationContext()).getBoxStore();
    }

    @Override
    public void create(Meeting meeting) {
        Box<Meeting> meetingBox = boxStore.boxFor(Meeting.class);
        meetingBox.put(meeting);
    }

    @Override
    public List<Meeting> getAll() {
        Box<Meeting> meetingBox = boxStore.boxFor(Meeting.class);
        List<Meeting> result = meetingBox.getAll();
        return result;
    }


}
