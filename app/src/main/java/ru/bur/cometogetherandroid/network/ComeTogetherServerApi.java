package ru.bur.cometogetherandroid.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.bur.dto.AppUserDto;
import ru.bur.dto.AuthDto;
import ru.bur.dto.MeetingDto;

public interface ComeTogetherServerApi {

    @POST("/auth")
    Call<AppUserDto> tryAuth(@Body AuthDto authDto);

    @SerializedName("meetings")
    @GET("/rest/meetings")
    Call<List<MeetingDto>> getAllMeetings();

    @POST("/rest/meetings")
    Call<MeetingDto> createMeeting(@Body MeetingDto meetingDto);

    @GET("/rest/meetings/{meetingId}/owners")
    Call<List<Long>> getMeetingOwners(@Path("meetingId") Long meetingId);


}
