package com.timesaver.timesaver.repository;

import com.timesaver.timesaver.model.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateRepository extends JpaRepository<Donate,Long> {
    public List<Donate> findByDonationId(Long id);

    public List<Donate> findByAdminUsername(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM donate WHERE donation_id = :donationId AND admin_id = :adminId")
    public List<Donate> findByFeeInstallmentIdAndCustomerId(@Param("donationId")Integer feeInstallmentId, @Param("adminId") Long adminId);

}
