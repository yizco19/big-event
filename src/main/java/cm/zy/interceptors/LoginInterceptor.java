package cm.zy.interceptors;

import cm.zy.pojo.Result;
import cm.zy.utils.JwtUtil;
import cm.zy.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);

            // hilo local de usuario
            ThreadLocalUtil.set(claims);
            return true;//  correcto
        }catch (Exception e){
            // token invalido y http status 401
            response.setStatus(401);
            return false; // no correcto
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // vaciar hilo local
        ThreadLocalUtil.remove();
    }
}
