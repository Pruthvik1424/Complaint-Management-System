package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.ComplaintDto;

import java.util.List;
import java.util.Optional;

public interface ComplaintRaiseRepo {
    boolean saveRaiseComplaintType(ComplaintDto complaintDto);

    Optional<ComplaintDto> findByUserId(int id);

    //used for view raised complaint

    List<ComplaintDto> getComplaintsByUserId(int userId);

    //edit
    public Optional<ComplaintDto> findByComplaintId(int complaintId) ;

    //update
    ComplaintDto updateRaiseComplaintUserDetails(ComplaintDto complaintDto);





}
