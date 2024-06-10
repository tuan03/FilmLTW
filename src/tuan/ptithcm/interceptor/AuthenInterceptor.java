package ptithcm.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
    	Object value = session.getAttribute("tuann");
    	if (value != null) {
    	    System.out.println("Value of 'tuann': " + value.toString());
    	} else {
    	    System.out.println("'tuann' attribute not found in session");
    	}
        return false; // hoặc false nếu xác thực thất bại
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Được gọi sau khi Controller đã được gọi, nhưng trước khi render view
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Được gọi sau khi đã render view
    }
}
