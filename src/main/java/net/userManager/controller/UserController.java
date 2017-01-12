package net.userManager.controller;

import net.userManager.Model.User;
import net.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/{first}", method = RequestMethod.GET)
    public String listUsers(Model model,

                            @PathVariable ("first") Integer first,
                            @RequestParam(value="name", required=false) String name
    ){

        model.addAttribute("user", new User());
        model.addAttribute("namesearch", name);
        model.addAttribute("listUsers", this.userService.listUsers(first, name));
        model.addAttribute("listPages", this.userService.listPages(name));

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            this.userService.add(user);
        }else {
            this.userService.update(user);
        }

        return "redirect:/users/0";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.delete(id);

        return "redirect:/users/0";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model ){
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers(0,null));

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));

        return "userdata";
    }

}
