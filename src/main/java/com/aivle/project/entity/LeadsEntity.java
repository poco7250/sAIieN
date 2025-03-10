package com.aivle.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "leads")
public class LeadsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leadId;

    @Column(nullable = false)
    private String leadStatus;

    @Column(nullable = false, length = 255)
    private String leadSource;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetCloseDate;

    @Column(nullable = false , length = 1000)  // 리드내용
    private String customerRequirements;

    @Column(nullable = false) // 계정명
    private String companyName;

    @Column(nullable = false) // 계정 담당자
    private String customerRepresentitive;

    @Column(nullable = false)  // 연락처
    private String c_tel;

    // 외래키 부분
    // 외부 외래키
    @OneToMany(mappedBy = "leadId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<OpportunitiesEntity> opportunities;

    // 내부 외래키
    // Employee 외래키 설정
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_leads_employee_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EmployeeEntity employeeId;

    // Account 외래키 설정
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = true , foreignKey = @ForeignKey(name = "fk_leads_account_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AccountEntity accountId;
}
