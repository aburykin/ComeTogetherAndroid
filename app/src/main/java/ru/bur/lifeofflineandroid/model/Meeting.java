package ru.bur.lifeofflineandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Meeting implements Parcelable {
    private Long meetingId;
    private String name;
    private String place;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private int amountParticipants;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(meetingId);
        dest.writeString(name);
        dest.writeString(place);
        dest.writeString(description);
        dest.writeInt(amountParticipants);
    }

    public static final Parcelable.Creator<Meeting> CREATOR = new Parcelable.Creator<Meeting>() {

        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public Meeting(){

    }

    private Meeting(Parcel in) {
        meetingId = in.readLong();
        name = in.readString();
        place = in.readString();
       // date = in.read(); // TODO
       // time = in.read();
        description = in.readString();
        amountParticipants = in.readInt();
    }
}
