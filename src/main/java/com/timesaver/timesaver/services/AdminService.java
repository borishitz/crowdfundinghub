package com.timesaver.timesaver.services;

import com.timesaver.timesaver.dto.AdminDto;
import com.timesaver.timesaver.model.Admin;

public interface AdminService {
    Admin save(AdminDto adminDto);

    Admin findByUsername(String username);
}
