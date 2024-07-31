package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.ComplaintDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ComplaintRaiseService {


    boolean saveRaiseComplaintType(ComplaintDto complaintDto);

    Optional<ComplaintDto> findByUserId(int id);

    Optional<ComplaintDto> findBySignedInUser(HttpServletRequest request);

    //view complaint
    List<ComplaintDto> getComplaintsByUserId(int userId);

    //edit
    public ComplaintDto getComplaintById(int complaintId) ;

    //update

    List <ComplaintDto> updateRaiseComplaintUserDetails(ComplaintDto complaintDto);

}
