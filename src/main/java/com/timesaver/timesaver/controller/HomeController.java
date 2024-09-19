package com.timesaver.timesaver.controller;

import com.timesaver.timesaver.model.Admin;
import com.timesaver.timesaver.model.Donation;
import com.timesaver.timesaver.repository.DonationRepository;
import com.timesaver.timesaver.services.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private DonationRepository donationRepo;

    @Autowired
    private AdminService adminService;

    @GetMapping({"/","","/home"})
    public String homePage(Model model, Principal principal, HttpSession session){
        model.addAttribute("allDonations", donationRepo.findAll());
        if (principal == null) {
            return "redirect:/login";
        } else {
            Admin admin = adminService.findByUsername(principal.getName());
            session.setAttribute("username", admin.getFirstName() + " " + admin.getLastName());
            return "index";
        }
    }

//    @GetMapping("/donation/add")
//    public String homePage(Model model){
//        Donation newDonation = new Donation();
//        model.addAttribute("newDonation", newDonation);
//        return "addDonation";
//    }

    @PostMapping("/donation/save")
    public String saveDonation(Donation donation) {
        donationRepo.save(donation);
        return "redirect:/";
    }

}
