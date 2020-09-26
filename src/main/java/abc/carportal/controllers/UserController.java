package abc.carportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;
import abc.carportal.models.Bid;
import abc.carportal.models.User;
import abc.carportal.services.BidService;
import abc.carportal.services.UserService;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private BidService bidService;

  /**
   * Get a list of all posts in the database, it should be able to paginate and
   * sort http://localhost:8090/posts?page=0&size=3&sort=id
   * 
   * @param model
   * @return
   */
  @RequestMapping("/admin/dashboard")
  public String index(Model model,
      @PageableDefault(sort = { "id" }, value = 10, direction = Sort.Direction.DESC) Pageable pageable) {
    // Get the content of the table, TODO. find a way to paginate
    Page<User> users = this.userService.findAll(pageable);

    Page<Bid> bid = this.bidService.findAll(pageable);

    model.addAttribute("bid", bid);
    // Define variables to be passed to view
    model.addAttribute("users", users);
    // Return the view model itself
    return "/admin/dashboard";
  }

}
