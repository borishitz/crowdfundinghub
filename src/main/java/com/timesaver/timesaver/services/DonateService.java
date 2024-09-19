package com.timesaver.timesaver.services;

import com.timesaver.timesaver.model.Donate;
import com.timesaver.timesaver.repository.DonateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonateService {

    @Autowired
    private DonateRepository donateRepo;

    public List<Donate> getDonateByDonationId(Long id) {
        return donateRepo.findByDonationId(id);
    }

    public List<Donate> getDonateByAdminId(String username) {
        return donateRepo.findByAdminUsername(username);
    }

}
