package kyonggi_girls.kgu_babmat.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import jakarta.servlet.http.HttpServletRequest;
import kyonggi_girls.kgu_babmat.dao.UserDao;
import kyonggi_girls.kgu_babmat.model.GoogleOAuthRequest;
import kyonggi_girls.kgu_babmat.model.GoogleOAuthResponse;
import kyonggi_girls.kgu_babmat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("login")
public class GoogleController {
    final static String GOOGLE_AUTH_BASE_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    final static String GOOGLE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
    final static String GOOGLE_REVOKE_TOKEN_BASE_URL = "https://oauth2.googleapis.com/revoke";

    @Value("${api.client_id}")
    String clientId;
    @Value("${api.client_secret}")
    String clientSecret;

    @Autowired
    UserDao userDao;

    private final LoginService loginService;

    public GoogleController(UserDao userDao, LoginService loginService) {
        this.userDao = userDao;
        this.loginService = loginService;
    }


    /**
     * Authentication Code? ?? ?? ?????
     **/
    @GetMapping("google/auth")
    public String googleAuth(HttpServletRequest request, Model model, @RequestParam(value = "code") String authCode, Map<String, String> params)
            throws JsonProcessingException {

       /* HttpSession session = request.getSession(false);

        if (session == null) {
            return "signin";
        }

        User user = (User) session.getAttribute(SessionConst.sessionId);
        model.addAttribute("user", user);

        */

        //HTTP Request? ?? RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //Google OAuth Access Token ??? ?? ???? ??
        GoogleOAuthRequest googleOAuthRequestParam = GoogleOAuthRequest
                .builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(authCode)
                .redirectUri("http://localhost:8080/login/google/auth")
                .grantType("authorization_code").build();


        //JSON ??? ?? ??? ??
        //??? ????? ???? ???? ????? Object mapper? ?? ?????.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //AccessToken ?? ??
        ResponseEntity<String> resultEntity = restTemplate.postForEntity(GOOGLE_TOKEN_BASE_URL, googleOAuthRequestParam, String.class);

        //Token Request
        GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {
        });

        System.out.println(resultEntity.getBody());

        //ID Token? ?? (???? ??? jwt? ??? ????)
        String jwtToken = result.getIdToken();
        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
                .queryParam("id_token", jwtToken).encode().toUriString();

        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});
        model.addAllAttributes(userInfo);
        model.addAttribute("token", result.getAccessToken());
        System.out.println(userInfo);

        System.out.println(params);


        String email = params.get("email");
        //String userPwd = params.get("user-password");
        String username = params.get("username");
        //String userFood = params.get("user-food");

        // back-end validation
        String results = "";
        boolean isAllValidate = true;
  /*      if(username.length() < 0) {
            results += "user-id must be at least 4 character long.\n";
            isAllValidate = false;
        }

        if(email.length() <= 4) {
            results += "user-name is not entered.\n";
            isAllValidate = false;
        }*/


        // submit to database
        if(isAllValidate) {

            try {
                int insertResult = userDao.insertUser(email, username);
                if(insertResult >= 1) {
                    results += "You have successfully registered.";
                } else {
                    results += "Sign-up failed. There may be duplicate information or other issues.";
                    isAllValidate = false;
                }
            } catch(Exception e) {
                e.printStackTrace();
                results += "Sign-up failed. There may be duplicate information or other issues.";
                isAllValidate = false;
            }

        }

        model.addAttribute("isSuccess", isAllValidate);
        model.addAttribute("resultMsg", results);
        return "signup";
    }



        /*return "/google.html";

    }*/

    /**
     * ?? ???
     **/
    @GetMapping("google/revoke/token")
    @ResponseBody
    public Map<String, String> revokeToken(@RequestParam(value = "token") String token) throws JsonProcessingException {

        Map<String, String> result = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        final String requestUrl = UriComponentsBuilder.fromHttpUrl(GOOGLE_REVOKE_TOKEN_BASE_URL)
                .queryParam("token", token).encode().toUriString();

        System.out.println("TOKEN ? " + token);

        String resultJson = restTemplate.postForObject(requestUrl, null, String.class);
        result.put("result", "success");
        result.put("resultJson", resultJson);

        return result;

    }


}