package ru.bur.cometogetherandroid.activities.authorization;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.bur.cometogetherandroid.ComeTogetherApp;
import ru.bur.cometogetherandroid.common.Cookies;
import ru.bur.cometogetherandroid.common.CookiesEnum;
import ru.bur.dto.AppUserDto;
import ru.bur.dto.AuthDto;

import static ru.bur.cometogetherandroid.MainLogger.debug;
import static ru.bur.cometogetherandroid.MainLogger.error;

public class AutorizationPresender {

    private String LOG_TAG = "AutorizationPresender";
    private Authorization view;
    private Cookies cookies;

    @Inject
    public AutorizationPresender(Cookies cookies) {
        this.cookies = cookies;
    }


    public void attachView(Authorization activity) {
        view = activity;
    }


    /**
     * Проверяем есть ли у пользователя токен, и если есть, то пропускает страницу авторизации.
     */
    public boolean isUserAuthrizated() {
        String token = cookies.get(CookiesEnum.token.toString());
        if (token != null && !token.isEmpty()) {
            return true;
        }
        return false;
    }

    public void tryAuthorization(String phoneNumber) {
        AuthDto authDto = new AuthDto();
        authDto.setPhoneNumber(phoneNumber);
        debug(LOG_TAG, "tryAutorization(): authDto=" + authDto);

        view.showProgress();
        ComeTogetherApp.getApi().tryAuth(authDto).enqueue(new Callback<AppUserDto>() {
            @Override
            public void onResponse(Call<AppUserDto> call, Response<AppUserDto> response) {
                view.hideProgress();
                debug(LOG_TAG, "tryAutorization(): response=" + response);
                AppUserDto dto = response.body();
                if (dto != null) {
                    cookies.set(CookiesEnum.token.toString(), dto.getAuthorizationToken());
                    view.completeAuthorizationSuccess(dto.getAuthorizationToken());
                } else {
                    view.completeAuthorizationFault("Авторизоваться не удалось, попробуйте позднее.");
                }
            }

            @Override
            public void onFailure(Call<AppUserDto> call, Throwable t) {  // сюда попадаем, если не смогли отправить запрос на сервер.
                view.hideProgress();
                error(LOG_TAG, "tryAutorization(): Throwable=" + t);
                view.completeAuthorizationFault("Авторизоваться не удалось: " + t.getLocalizedMessage()); //TODO
            }
        });

    }
}
// Пример синхронного вызов в retrofit
// Response response = ComeTogetherApp.getApi().tryAuth(authDto).execute(); //   NetworkOnMainThreadException