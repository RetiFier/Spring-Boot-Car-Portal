package abc.carportal.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import abc.carportal.models.User;
import abc.carportal.services.UserService;
import abc.carportal.utils.UserRegistrationValid;

@Controller
@RequestMapping("/user/registration")
public class UserRegistrationController {

  @Autowired
  private UserService userService;

  @ModelAttribute("user")
  public UserRegistrationValid UserRegistrationValid() {
    return new UserRegistrationValid();
  }

  @GetMapping
  public String showRegistrationForm(Model model) {
    return "user/registration";
  }

  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationValid userDto,
      BindingResult result) {

    User existing = userService.findByEmail(userDto.getEmail());
    if (existing != null) {
      result.rejectValue("email", null, "There is already an account registered with that email");
    }

    if (result.hasErrors()) {
      return "user/registration";
    }

    userService.save(userDto);
    return "redirect:/user/registration?success";
  }

}