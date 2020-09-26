package abc.carportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

  // @GetMapping("/")
  // public String root() {
  // return "index";
  // }

  @GetMapping("/user/login")
  public String login(Model model) {
    return "user/login";
  }

  @RequestMapping("/accessdenied")
  public ModelAndView userAccessError() {
    return new ModelAndView("accessdenied");
  }

  @GetMapping("/user")
  public String userIndex() {
    return "user/index";
  }

  @RequestMapping("/user/")
  public ModelAndView user() {
    return new ModelAndView("/user/login");
  }

  @RequestMapping("/admin/")
  public ModelAndView admin() {
    return new ModelAndView("admin/login");
  }

  @GetMapping("/admin/login")
  public String adminlogin(Model model) {
    return "admin/login";
  }

  // @GetMapping("/admin/dashboard")
  // public String dashboard(Model model) {
  // return "/admin/dashboard";
  // }

  @GetMapping("/user/profile")
  public String profile(Model model) {
    return "/user/profile";
  }

}
