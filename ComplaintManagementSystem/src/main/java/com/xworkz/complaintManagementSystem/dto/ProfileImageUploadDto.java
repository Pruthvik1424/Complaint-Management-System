package com.xworkz.complaintManagementSystem.dto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;


import lombok.*;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;


@Data // it is used for getter setter
@ToString // it is for generate to string
@Slf4j
@Entity


@Table(name = "image_upload")
public class ProfileImageUploadDto {

    private static final Logger log = LoggerFactory.getLogger(ProfileImageUploadDto.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_size")
    private String imageSize;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "id")
    private int id;





    //@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString

    public ProfileImageUploadDto(){
        System.out.println("created ProfileImageUploadDto..");
        log.info("created ProfileImageUploadDto.");
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfileImageUploadDto{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", imageSize='" + imageSize + '\'' +
                ", imageType='" + imageType + '\'' +
                ", id=" + id +
                '}';
    }


}
