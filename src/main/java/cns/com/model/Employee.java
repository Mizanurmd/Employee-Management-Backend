package cns.com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Basic Info
    private String employeeName;
    private String fatherName;
    private String motherName;
    private String gender;
    private String designation;
    private String address;
    private String phone;
    private String email;
    private Date dateOfBirth;
    private Date hireDate;
    private String remarks;
    private double salary;
    private double bonus;

    // Image Info
    @Lob
    private byte[] image;
    private String imageName;
    private String imageType;
    private Long imageSize;

    // Active Flag Info
    private boolean activeYn;

}
