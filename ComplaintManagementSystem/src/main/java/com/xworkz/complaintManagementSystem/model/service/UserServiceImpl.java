//package com.xworkz.complaintManagementSystem.model.service;
//
//import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
//import com.xworkz.complaintManagementSystem.dto.SignUpDto;
//import com.xworkz.complaintManagementSystem.model.repo.UserRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Slf4j
//public class UserServiceImpl implements UserService{
//
//    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
//
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private HttpSession httpSession;
//
//    public UserServiceImpl(){
//        log.info("created UserServiceImpl");
//    }
//
//
//    @Override
//    public boolean saveComplaintRaise(ComplaintDto complaintDto) {
//        log.info("Running saveComplaintRaiseMethod in userServiceImpl..");
//       boolean dataSaved = userRepo.saveComplaintRaise(complaintDto);
//       if(dataSaved){
//           log.info("raisecomplaint data saved successfully : raiseComplaintDto:"+complaintDto);
//           return dataSaved;
//       }else {
//           log.info("raise complaint data is not saved....");
//       }
//        return userRepo.saveComplaintRaise(complaintDto);
//    }
//
//    @Override
//    public Optional<ComplaintDto> findByUserId(int id) {
//        return userRepo.findByUserId(id);
//    }
//
//    @Override
//    public Optional<ComplaintDto> findBySignedInUserId(HttpServletRequest httpServletRequest) {
//        HttpSession httpSession = httpServletRequest.getSession();
//        SignUpDto signedInUser = (SignUpDto) httpSession.getAttribute("signUpDto");
//        if(signedInUser != null){
//            return  userRepo.findByUserId(signedInUser.getId());
//        }
//        return Optional.empty();
//    }
//
//
//    @Override
//    public List<ComplaintDto> getComplaintRaisedDetails(String email) {
//        log.info("Getting complaint raised Details by email");
//        return Collections.emptyList();
//    }
//}
