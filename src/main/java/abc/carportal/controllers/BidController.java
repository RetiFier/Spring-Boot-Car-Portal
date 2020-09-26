package abc.carportal.controllers;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import abc.carportal.models.Bid;
import abc.carportal.models.Post;
import abc.carportal.models.User;
import abc.carportal.services.BidService;
import abc.carportal.services.NotificationService;
import abc.carportal.services.PostService;
import abc.carportal.services.UserService;

@Controller
public class BidController {
  /**
   * With the annotation @Service for the service implementation, it tells the
   * Spring Framework that class will used by the application controller as a
   * service and it will be automatically instantiated and injected in the
   * controllers(through the @Autowired annotation).
   */
  @Autowired
  private BidService bidService;
  @Autowired
  private NotificationService notifyService;
  @Autowired
  private UserService userService;
  @Autowired
  private PostService postService;

  /**
   * Get post id from parameters and search in the database for it
   * 
   * @param id
   * @param model
   * @return
   */
  @RequestMapping("/bid/view/{id}")
  public String view(@PathVariable("item") Long id, Model model) {
    Bid bid = this.bidService.findByItem(id);
    if (bid == null) {
      notifyService.addErrorMessage("Cannot find Bid #" + id);
      return "redirect:/";
    }

    model.addAttribute("bid", bid);
    // To have something like
    // src/main/resources/templates/<CONTROLLER-NAME>/<Mapping-Name-view>
    return "bid/view";
  }

  /**
   * Display form to create a post
   * 
   * @return
   */
  @RequestMapping("/bid/create")
  public ModelAndView create() {
    ModelAndView modelAndView = new ModelAndView();
    Bid bid = new Bid();
    modelAndView.addObject(bid);
    modelAndView.setViewName("bid/create");
    return modelAndView;
  }

  /**
   * Display form to create a post
   * 
   * @return
   */
  @RequestMapping(value = "/bid/create/{id}", method = RequestMethod.POST)
  public ModelAndView create(@Valid Bid bid, BindingResult bindingResult, @PathVariable("id") Long id) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("bid/create");
    // Perform validation
    if (bid.getBidPrice().isEmpty()) {
      bindingResult.rejectValue("price", "error.post", "Bid Price cannot be empty");
    }

    // Get author
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = this.userService.findByEmail(auth.getName());
    Post item = this.postService.findById(id);
    if (user == null) {
      bindingResult.rejectValue("user", "error.post", "User cannot be null");
    }
    if (item == null) {
      bindingResult.rejectValue("user", "error.post", "Item cannot be null");
    }

    if (!bindingResult.hasErrors()) {
      bid.setBidUser(user);
      bid.setItem(item);
      this.bidService.create(bid);
      modelAndView.addObject("successMessage", "Post has been created");
      modelAndView.addObject("bid", new Bid());
    }
    return modelAndView;
  }

  /**
   * Remove a post from the database, notify user if post does not exist
   * 
   * @param id
   * @return
   */
  @RequestMapping("/bid/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
    Bid bid = this.bidService.findById(id);
    if (bid == null) {
      notifyService.addErrorMessage("Cannot find Bidding #" + id);
    } else {
      this.bidService.deleteById(id);
    }
    return "redirect:/bid/";
  }

  /**
   * Display for to edit a post
   * 
   * @param id @param model @return
   */
  @RequestMapping("/bid/edit/{id}")
  public String edit(@PathVariable("id") Long id, Model model) {
    Bid bid = this.bidService.findById(id);
    if (bid == null) {
      notifyService.addErrorMessage("Cannot find bid #" + id);
      return "redirect:/bid/";
    }
    model.addAttribute("bid", bid);
    return "bid/edit";
  }

  /**
   * Proceeds to update a post
   * 
   * @param bid
   * @return
   */
  @RequestMapping(value = "/bid/edit", method = RequestMethod.POST)
  public ModelAndView edit(@Valid Bid bid, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();

    modelAndView.setViewName("bid/edit");
    // Perform validation
    if (bid.getBidPrice().isEmpty()) {
      bindingResult.rejectValue("price", "error.bid", "Bid Price cannot be empty");
    }

    // Get author
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = this.userService.findByEmail(auth.getName());
    if (user == null) {
      bindingResult.rejectValue("User", "error.post", "Bid User cannot be null");
    }

    if (!bindingResult.hasErrors()) {
      bid.setBidUser(user);
      this.bidService.create(bid);
      modelAndView.addObject("successMessage", "Bidding has been updated");
      modelAndView.addObject("bid", bid);
    }
    return modelAndView;
  }

  /**
   * Get a list of all posts in the database, it should be able to paginate and
   * sort http://localhost:8090/posts?page=0&size=3&sort=id
   * 
   * @param model
   * @return
   */
  @RequestMapping("/bid")
  public String index(Model model) {
    // Get the content of the table, TODO. find a way to paginate
    List<Bid> bid = this.bidService.findAll();

    // Define variables to be passed to view
    model.addAttribute("bid", bid);
    // Return the view model itself
    return "posts/index";
  }
}
