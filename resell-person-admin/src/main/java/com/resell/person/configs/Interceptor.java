package com.resell.person.configs;

import com.resell.person.dto.oauth.UserTokenDTO;
import com.resell.person.entities.session.UserSessionRedis;
import com.resell.person.services.OauthService;
import com.resell.person.services.RabbitSessionToken;
import com.resell.person.services.UserSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class Interceptor implements HandlerInterceptor {


//    private final UserSessionService userSessionService;
//    private final OauthService oauthService;

    private final RabbitSessionToken rabbitSessionToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return HandlerInterceptor.super.preHandle(request, response, handler);

        String token = request.getHeader("Authorization");

        Enumeration<String> HeaderNames = request.getHeaderNames();
        String sessionHeader = request.getHeader("session");

        HttpSession session = request.getSession();


        // create a cookie
        Cookie cookie = new Cookie("Cookie",sessionHeader);
        response.addCookie(cookie);

        session.setAttribute("session", sessionHeader);

        response.setHeader("Session",sessionHeader);

        System.out.println("Session");
        System.out.println(sessionHeader);


        /**
         *
         *         //////////////////////////
         *                  Mandar para a FILA
         *
         * */

        rabbitSessionToken.send(UserSessionRedis.builder().token(token).session(sessionHeader).build());

        /**
         * get infos do toke add dados user na session
         * */

//        UserTokenDTO userTokenDTO = oauthService.getUserData(token);
//
//        userSessionService.saveSession(UserSessionRedis.builder().token(token).session(sessionHeader).email(userTokenDTO.getEmail()).build());


        /**
         * if existe session
         *      buscar redis dados user salvar em global
         * Not if
         *      call function get dados user add redis on session
         *
         * */

        System.out.println("***********************************interceptor**********************************************");
        //System.out.println(token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        String sessionHeader = request.getHeader("session");

        System.out.println("postHandle");
        System.out.println("session");
        System.out.println(sessionHeader);


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        String sessionHeader = request.getHeader("session");

        System.out.println("afterCompletion");
        System.out.println("session");
        System.out.println(sessionHeader);


    }
}
