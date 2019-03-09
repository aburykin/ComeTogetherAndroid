package ru.bur.lifeofflineandroid.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.bur.dto.AppUserDto;
import ru.bur.dto.AuthDto;
import ru.bur.dto.MeetingDto;

public interface LifeOfflineServerApi {

    @POST("/auth")
    Call<AppUserDto> tryAuth(@Body AuthDto authDto);

    @SerializedName("meetings")
    @GET("/rest/meetings")
    Call<List<MeetingDto>> getFirstNmeatings();

    @POST("/rest/meetings/next")
    Call<List<MeetingDto>> getNextMeetings(@Body MeetingDto meetingDto);

    @POST("/rest/meetings")
    Call<MeetingDto> createMeeting(@Body MeetingDto meetingDto);

    @PUT("/rest/meetings")
    Call<MeetingDto> updateMeeting(@Body MeetingDto meetingDto);

    @GET("/rest/meetings/{meetingId}")
    Call<MeetingDto> getMeeting(@Path("meetingId") Long meetingId);

    @GET("/rest/meetings/{meetingId}/owners")
    Call<List<Long>> getMeetingOwners(@Path("meetingId") Long meetingId);

    @GET("/rest/meetings/{meetingId}/participants")
    Call<List<Long>> getMeetingParticipants(@Path("meetingId") Long meetingId);

    @DELETE("/rest/meetings/{meetingId}")
    Call<Void> deleteMeeting(@Path("meetingId") Long meetingId);

    @POST("/rest/meetings/{meetingId}/participants/{appUserId}")
    Call<Void> addParticipant(@Path("meetingId") Long meetingId, @Path("appUserId") Long appUserId);

    @DELETE("/rest/meetings/{meetingId}/participants/{appUserId}")
    Call<Void> deleteParticipant(@Path("meetingId") Long meetingId, @Path("appUserId") Long appUserId);

}
