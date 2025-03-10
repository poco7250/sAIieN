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
@Table(name = "opportunities")
public class OpportunitiesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opportunityId;

    @Column(nullable = false, length = 255)
    private String opportunityName;

    @Column(nullable = false, length = 50)
    private String region;

    @Column(nullable = false)
    private int companySize;

    @Column(nullable = false)
    private String opportunityInquiries;

    @Column(nullable = false)
    private String customerEmployee;

    @Column(nullable = false)
    private float quantity;

    @Column(nullable = false)
    private float expectedRevenue;

    @Column(nullable = false)
    private float companyRevenue;

    @Column(nullable = false)
    private String opportunityNotes;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetCloseDate;

    @Column(nullable = false)
    private String opportunityStatus;

    @Column(nullable = false)
    private String successRate;


    // 외부 외래키 부분
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL)
    private List<OpportunitiesCommentEntity> comments;

    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.ALL)
    private List<HistoryEntity> history;

    // 내부 외래키 부분
    @ManyToOne
    @JoinColumn(name = "lead_id", nullable = true, foreignKey = @ForeignKey(name = "fk_opportunities_lead_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LeadsEntity leadId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, foreignKey = @ForeignKey(name = "fk_opportunities_account_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AccountEntity accountId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_opportunities_product_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProductsEntity productId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false, foreignKey = @ForeignKey(name = "fk_opportunities_employee_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EmployeeEntity employeeId;

}
