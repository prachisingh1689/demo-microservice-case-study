package com.impetus.casestudy.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Client controller, fetches User info from the microservice
 */
@Controller
public class WebUserController {

	@Autowired
	protected WebCreateUserService userCreateService;
	@Autowired
	protected WebSearchUserService userSearchService;

	protected Logger logger = Logger.getLogger(WebUserController.class
			.getName());

	public WebUserController(WebCreateUserService userCreateService,
			WebSearchUserService userSearchService) {
		this.userCreateService = userCreateService;
		this.userSearchService = userSearchService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("firstName", "lastName", "age", "email");
	}

	@RequestMapping(value = "/user")
	public String goHome() {
		return "index";
	}

	@RequestMapping(value = "/user/searchUser/")
	public String userSearch(Model model) {
		logger.info("WebUserController userSearch() invoked: ");
		model.addAttribute("userobj", new User());
		return "userSearch";
	}

	@RequestMapping(value = "/user/showall")
	public String userShowAll(Model model, User userobj, BindingResult result) {
		logger.info("WebUserController userShowAll() invoked");
		List<User> users = userSearchService.findAllUsers();
		model.addAttribute("users", users);
		return "userSearchResult";
	}

	@RequestMapping(value = "/user/searchUserResult")
	public String userSearchResult(Model model, User userobj,
			BindingResult result) {
		logger.info("WebUserController userSearchResult() invoked: "
				+ userobj.getFirstName());
		User user = userSearchService.findByFirstName(userobj.getFirstName());
		logger.info("WebUserController userSearchResult() found: " + user);
		model.addAttribute("user", user);
		return "userSearchResult";
	}

	@RequestMapping(value = "/user/input", method = RequestMethod.GET)
	public String userForm(Model model) {
		model.addAttribute("userobj", new User());
		return "userform";
	}

	@RequestMapping(value = "/user/addUser")
	public ModelAndView addUser(Model model, User userobj, BindingResult result) {
		logger.info("WebUserController addUser() invoked: " + userobj);
		User existingUser = userSearchService.findByFirstName(userobj
				.getFirstName());
		if (existingUser == null) {
			userCreateService.createUser(userobj);
		}

		// List<User> users = userSearchService.findAllUsers();
		// logger.info("WebUserController userSearchResult() found: " + users);
		// model.addAttribute("users", users);
		//
		// return "userSearchResult";
		return new ModelAndView("redirect:/user/showall");
	}
}