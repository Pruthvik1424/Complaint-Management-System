package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.ComplaintRaiseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintRaiseServiceImpl implements ComplaintRaiseService{

    @Autowired
    private ComplaintRaiseRepo complaintRaiseRepo;

    public ComplaintRaiseServiceImpl()
    {
        System.out.println("Created RaiseComplaintServiceImpl");

    }



    @Override
    public boolean saveRaiseComplaintType(ComplaintDto complaintDto) {
        System.out.println("Running saveRaiseComplaintType method in RaiseComplaintServiceImpl ");
        boolean save=complaintRaiseRepo.saveRaiseComplaintType(complaintDto);
        if(save)
        {
            System.out.println(" saved RaiseComplaint successfully ");
        }
        else {
            System.out.println(" Not saved RaiseComplaint successfully ");
        }
        return true;
    }

    @Override
    public Optional<ComplaintDto> findByUserId(int id) {
        return complaintRaiseRepo.findByUserId(id);

    }

    @Override
    public Optional<ComplaintDto> findBySignedInUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        SignUpDto signedInUserId = (SignUpDto) httpSession.getAttribute("signUpDto");
        if (signedInUserId != null) {
            return complaintRaiseRepo.findByUserId(signedInUserId.getId());
        }
        return Optional.empty();
    }

    @Override
    public List<ComplaintDto> getComplaintsByUserId(int userId) {
        return complaintRaiseRepo.getComplaintsByUserId(userId);
    }

    @Override
    public ComplaintDto getComplaintById(int complaintId) {
        return complaintRaiseRepo.findByComplaintId(complaintId).orElse(null);

    }

    @Override
    public List<ComplaintDto> updateRaiseComplaintUserDetails(ComplaintDto complaintDto) {
        ComplaintDto complaintDTO= this.complaintRaiseRepo.findByComplaintId(complaintDto.getComplaintId()).get();
        complaintDTO.setUserid(complaintDTO.getUserid());
        ComplaintDto raiseComplaintDTO1=   complaintRaiseRepo.updateRaiseComplaintUserDetails(complaintDTO);
        List<ComplaintDto> dtos=this.complaintRaiseRepo.getComplaintsByUserId(raiseComplaintDTO1.getUserid().getId());
        if(dtos!=null)
        {
            System.out.println("update data successfullyyy");
            return  dtos;
        }
        else
        {
            System.out.println("update not successfulll");
            return  null;
        }
    }


}
