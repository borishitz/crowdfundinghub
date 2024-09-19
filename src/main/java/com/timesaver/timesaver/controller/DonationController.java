package com.timesaver.timesaver.controller;

import com.timesaver.timesaver.model.Donation;
import com.timesaver.timesaver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DonationController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/donation/all")
    private String allDonations(){
        return "all-donations";
    }

    @GetMapping("/donation/add")
    private String allDonations(Model model, Principal principal){
        model.addAttribute("loginUser", adminService.findByUsername(principal.getName()));
        Donation newDonation = new Donation();
        model.addAttribute("newDonation", newDonation);
        return "addDonation";
    }
}
