package com.timesaver.timesaver.cartController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class TestCartController {

    @GetMapping("/testCart")
    public String testCart(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        return "testCart/testCart";
    }
}
