package ru.bur.cometogetherandroid.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.bur.dto.AppUserDto;
import ru.bur.dto.AuthDto;

public interface ComeTogetherServerApi {

    @POST("/user/auth")
    Call<AppUserDto> tryAuth(@Body AuthDto authDto);

}
