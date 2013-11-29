package com.zzxhdzj.douban.api.auth;

import com.zzxhdzj.douban.Constants;
import com.zzxhdzj.douban.Douban;
import com.zzxhdzj.douban.api.mock.TestResponses;
import com.zzxhdzj.http.Callback;
import com.zzxhdzj.http.mock.TestApiGateway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: yangning.roy
 * Date: 11/26/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(RobolectricTestRunner.class)
public class AuthenGetCaptchaGatewayTest {
    private TestApiGateway apiGateway;
    private AuthenGetCaptchaGateway authenGetCaptchaGateway;
    private Douban douban;

    @Before
    public void setUp() {
        apiGateway = new TestApiGateway();
        douban = new Douban();
        authenGetCaptchaGateway = new AuthenGetCaptchaGateway(douban, apiGateway);
    }

    //test#01
    @Test
    public void shouldMakeARemoteCallWhenFetchNewCaptchaId() {
        authenGetCaptchaGateway.newCapthaId(new Callback());
        String urlString = apiGateway.getLatestRequest().getUrlString();
        assertThat(urlString, equalTo("http://douban.fm/j/new_captcha"));
    }

    @Test
    public void shouldReturnNewCaptchaId() throws Exception {
        authenGetCaptchaGateway.newCapthaId(new Callback());
        apiGateway.simulateTextResponse(200, TestResponses.NEW_CAPTCHA, null);
        assertThat(douban.captchaImageUrl, equalTo(Constants.CAPTCHA_URL + "&id=8Z9w6tODHEukHkAmBz52dWg4:en"));
    }


}