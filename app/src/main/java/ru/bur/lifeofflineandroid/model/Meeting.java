package ru.bur.lifeofflineandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalTime;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;
import lombok.Data;

@Data
@Entity
public class Meeting implements Parcelable {
    @Id
    public long meetingId;
    public String name;
    public String place;
    @Transient
    public LocalDate date;
    @Transient
    public LocalTime time;
    public String description;
    public int amountParticipants;


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

    public Meeting() {

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
