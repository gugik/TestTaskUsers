package net.userManager.controller;

import net.userManager.Model.User;
import net.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

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
        Integer c;
        if(user.getId() == 0){

            this.userService.add(user);
            Map<Integer, Integer> pageMap=this.userService.listPages(null);
            c=pageMap.get(pageMap.size());
            //Integer c = new TreeMap<Integer,Integer>(pageMap).descendingMap().firstEntry().getValue();
            if (c == null) c = 0;

        }else {

            this.userService.update(user);
            c=0;
        }

        return "redirect:/users/"+c;
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
        model.addAttribute("listPages", this.userService.listPages(null));

        return "userdata";
    }

}
