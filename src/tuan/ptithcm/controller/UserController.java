package ptithcm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@RequestMapping(value = "login",method = RequestMethod.GET)
    public String showLoginForm() {
        return "user/login";
    }
	@RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,@RequestParam("password") String password,@RequestParam("email") String email) {
		System.out.print(email + ' ' + password);
		return "user/login";
	}
	@RequestMapping(value = "sign-up",method = RequestMethod.GET)
    public String showRegisterForm() {
        return "user/register";
    }
	@RequestMapping(value = "sign-up",method = RequestMethod.POST)
    public String register(HttpServletRequest request,@RequestParam("password") String password,@RequestParam("email") String email) {
		System.out.print(email + ' ' + password);
		return "user/login";
	}
}
