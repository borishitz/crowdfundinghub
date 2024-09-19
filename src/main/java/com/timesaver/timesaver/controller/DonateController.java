package com.timesaver.timesaver.controller;

import com.timesaver.timesaver.model.Admin;
import com.timesaver.timesaver.model.Donate;
import com.timesaver.timesaver.model.Donation;
import com.timesaver.timesaver.repository.AdminRepository;
import com.timesaver.timesaver.repository.DonateRepository;
import com.timesaver.timesaver.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class DonateController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private DonateRepository donateRepo;

    @Autowired
    private DonationRepository donationRepo;

    @GetMapping("/donate/all")
    private String allDonors(Model model, Principal principal){
        Admin admin = adminRepo.findByUsername(principal.getName());
        List<Donate> donateList = admin.getDonates();
        model.addAttribute("donateList", donateList);
        model.addAttribute("paidSum", donateList.stream().mapToDouble(Donate::getAmount).sum());
        return "allDonates";
    }

    @GetMapping("/donate/add/{id}")
    public String addDonateForm(@PathVariable("id") Long id, Model model, Principal principal){
        Admin admin = adminRepo.findByUsername(principal.getName());
        Donation donation = donationRepo.findById(id).get();
        model.addAttribute("donate",new Donate());
        model.addAttribute("donation", donation);
        model.addAttribute("loginUser",admin);
        return "addDonate";
    }

//    @GetMapping("/donate/view/{donationId}")
//    public String findDonatesByDonationId(@PathVariable Integer donationId, Model model, Principal principal){
//        Long adminId = adminRepo.findByUsername(principal.getName()).getId();
//        List<Donate> donateList = donateRepo.findByFeeInstallmentIdAndCustomerId(donationId, adminId);
//        model.addAttribute("donateList", donateList);
//        model.addAttribute("paidSum", donateList.stream().mapToDouble(Donate::getAmount).sum());
//        return "donation-pays";
//    }

    @RequestMapping(value = "/donate/view/{id}")
    public String getDonatesByDonationId(@PathVariable Long id,Model model){
        Donation thisDonation = donationRepo.findById(id).get();
        List<Donate> donateList = donateRepo.findByDonationId(id);
        model.addAttribute("donateList",donateList);
        model.addAttribute("paidSum", donateList.stream().mapToDouble(Donate::getAmount).sum());
        model.addAttribute("thisDonation", thisDonation);
        return "donation-pays";
    }

    @PostMapping("/donate/save")
    public String saveDonate(Donate donate) {
        donateRepo.save(donate);
        return "redirect:/";
    }

}
