/**
 * Copyright (c) 2016 Couchbase, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.couchbase.lite;

import com.couchbase.lite.auth.Authenticator;
import com.couchbase.lite.auth.AuthenticatorFactory;
import com.couchbase.lite.auth.BasicAuthenticator;
import com.couchbase.lite.auth.PersonaAuthorizer;
import com.couchbase.lite.auth.TokenAuthenticator;
import com.couchbase.lite.util.Log;

import junit.framework.Assert;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AuthTest extends LiteTestCase {

    public void testParsePersonaAssertion() {
        try {
            Log.d(Database.TAG, "testParsePersonaAssertion");
            String sampleAssertion = "eyJhbGciOiJSUzI1NiJ9.eyJwdWJsaWMta2V5Ijp7ImFsZ29yaXRobSI6IkRTIiwieSI6ImNhNWJiYTYzZmI4MDQ2OGE0MjFjZjgxYTIzN2VlMDcwYTJlOTM4NTY0ODhiYTYzNTM0ZTU4NzJjZjllMGUwMDk0ZWQ2NDBlOGNhYmEwMjNkYjc5ODU3YjkxMzBlZGNmZGZiNmJiNTUwMWNjNTk3MTI1Y2NiMWQ1ZWQzOTVjZTMyNThlYjEwN2FjZTM1ODRiOWIwN2I4MWU5MDQ4NzhhYzBhMjFlOWZkYmRjYzNhNzNjOTg3MDAwYjk4YWUwMmZmMDQ4ODFiZDNiOTBmNzllYzVlNDU1YzliZjM3NzFkYjEzMTcxYjNkMTA2ZjM1ZDQyZmZmZjQ2ZWZiZDcwNjgyNWQiLCJwIjoiZmY2MDA0ODNkYjZhYmZjNWI0NWVhYjc4NTk0YjM1MzNkNTUwZDlmMWJmMmE5OTJhN2E4ZGFhNmRjMzRmODA0NWFkNGU2ZTBjNDI5ZDMzNGVlZWFhZWZkN2UyM2Q0ODEwYmUwMGU0Y2MxNDkyY2JhMzI1YmE4MWZmMmQ1YTViMzA1YThkMTdlYjNiZjRhMDZhMzQ5ZDM5MmUwMGQzMjk3NDRhNTE3OTM4MDM0NGU4MmExOGM0NzkzMzQzOGY4OTFlMjJhZWVmODEyZDY5YzhmNzVlMzI2Y2I3MGVhMDAwYzNmNzc2ZGZkYmQ2MDQ2MzhjMmVmNzE3ZmMyNmQwMmUxNyIsInEiOiJlMjFlMDRmOTExZDFlZDc5OTEwMDhlY2FhYjNiZjc3NTk4NDMwOWMzIiwiZyI6ImM1MmE0YTBmZjNiN2U2MWZkZjE4NjdjZTg0MTM4MzY5YTYxNTRmNGFmYTkyOTY2ZTNjODI3ZTI1Y2ZhNmNmNTA4YjkwZTVkZTQxOWUxMzM3ZTA3YTJlOWUyYTNjZDVkZWE3MDRkMTc1ZjhlYmY2YWYzOTdkNjllMTEwYjk2YWZiMTdjN2EwMzI1OTMyOWU0ODI5YjBkMDNiYmM3ODk2YjE1YjRhZGU1M2UxMzA4NThjYzM0ZDk2MjY5YWE4OTA0MWY0MDkxMzZjNzI0MmEzODg5NWM5ZDViY2NhZDRmMzg5YWYxZDdhNGJkMTM5OGJkMDcyZGZmYTg5NjIzMzM5N2EifSwicHJpbmNpcGFsIjp7ImVtYWlsIjoiamVuc0Btb29zZXlhcmQuY29tIn0sImlhdCI6MTM1ODI5NjIzNzU3NywiZXhwIjoxMzU4MzgyNjM3NTc3LCJpc3MiOiJsb2dpbi5wZXJzb25hLm9yZyJ9.RnDK118nqL2wzpLCVRzw1MI4IThgeWpul9jPl6ypyyxRMMTurlJbjFfs-BXoPaOem878G8-4D2eGWS6wd307k7xlPysevYPogfFWxK_eDHwkTq3Ts91qEDqrdV_JtgULC8c1LvX65E0TwW_GL_TM94g3CvqoQnGVxxoaMVye4ggvR7eOZjimWMzUuu4Lo9Z-VBHBj7XM0UMBie57CpGwH4_Wkv0V_LHZRRHKdnl9ISp_aGwfBObTcHG9v0P3BW9vRrCjihIn0SqOJQ9obl52rMf84GD4Lcy9NIktzfyka70xR9Sh7ALotW7rWywsTzMTu3t8AzMz2MJgGjvQmx49QA~eyJhbGciOiJEUzEyOCJ9.eyJleHAiOjEzNTgyOTY0Mzg0OTUsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6NDk4NC8ifQ.4FV2TrUQffDya0MOxOQlzJQbDNvCPF2sfTIJN7KOLvvlSFPknuIo5g";
            Map<String, Object> result = PersonaAuthorizer.parseAssertion(sampleAssertion);
            String email = (String) result.get(PersonaAuthorizer.ASSERTION_FIELD_EMAIL);
            String origin = (String) result.get(PersonaAuthorizer.ASSERTION_FIELD_ORIGIN);

            Assert.assertEquals(email, "jens@mooseyard.com");
            Assert.assertEquals(origin, "http://localhost:4984/");
            Assert.assertEquals(PersonaAuthorizer.registerAssertion(sampleAssertion), email);

            URL originURL = new URL(origin);
            String gotAssertion = PersonaAuthorizer.assertionForEmailAndSite(email, originURL);
            Assert.assertEquals(gotAssertion, sampleAssertion);

            // variant form of URL
            originURL = new URL("Http://LocalHost:4984/");
            gotAssertion = PersonaAuthorizer.assertionForEmailAndSite(email, originURL);
            Assert.assertEquals(sampleAssertion, gotAssertion);

            PersonaAuthorizer auth = new PersonaAuthorizer(email);
            Assert.assertEquals(email, auth.getEmailAddress());
            Assert.assertEquals(null, auth.assertionForSite(originURL));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public void testAuthenticatorFactory() {
        Authenticator basicAuth = AuthenticatorFactory.createBasicAuthenticator("username", "password");
        Assert.assertNotNull(basicAuth);
        Assert.assertTrue(basicAuth instanceof BasicAuthenticator);

        Authenticator facebookAuth = AuthenticatorFactory.createFacebookAuthenticator("DUMMY_TOKEN");
        Assert.assertNotNull(facebookAuth);
        Assert.assertTrue(facebookAuth instanceof TokenAuthenticator);

        Authenticator personalAuth = AuthenticatorFactory.createPersonaAuthenticator("DUMMY_ASSERTION", null);
        Assert.assertNotNull(personalAuth);
        Assert.assertTrue(personalAuth instanceof TokenAuthenticator);
    }

    public void testTokenAuthenticator() {
        String loginPath = "_facebook";
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", "facebookaccesstoken");
        TokenAuthenticator tokenAuth = new TokenAuthenticator(loginPath, params);

        Map<String, String> tokenAuthParams = tokenAuth.loginParametersForSite(null);
        Assert.assertNotNull(tokenAuthParams);
        Assert.assertEquals(tokenAuthParams.size(), params.size());
        Assert.assertEquals(tokenAuthParams.get("access_token"), params.get("access_token"));
        Assert.assertEquals(tokenAuth.loginPathForSite(null), "/_facebook");
        Assert.assertTrue(tokenAuth.usesCookieBasedLogin());
        Assert.assertNull(tokenAuth.authUserInfo());
    }

    public void testBasicAuthenticator() {
        String username = "username";
        String password = "password";
        BasicAuthenticator basicAuth = new BasicAuthenticator(username, password);

        Assert.assertNull(basicAuth.loginParametersForSite(null));
        Assert.assertTrue(basicAuth.usesCookieBasedLogin());
        Assert.assertEquals(basicAuth.authUserInfo(), username + ":" + password);
    }
}
