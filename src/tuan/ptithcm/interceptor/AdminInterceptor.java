package ptithcm.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ptithcm.dto.UserDTO;
import ptithcm.entity.User;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
    	
    	UserDTO value = (UserDTO)session.getAttribute("User");
    	if (value != null && value.getRole() == User.Role.Admin) {
    	    return true;
    	} else {
    		int errorCode = HttpServletResponse.SC_FORBIDDEN;
            
            // Trả về mã lỗi và thông điệp tương ứng
            response.sendError(errorCode, "Trang này không được phép truy cập.");
    		return false;
    	}
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
