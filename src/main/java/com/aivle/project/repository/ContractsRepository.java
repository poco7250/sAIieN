package com.aivle.project.repository;

import com.aivle.project.entity.ContractsEntity;
import com.aivle.project.entity.OrdersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractsRepository extends JpaRepository<ContractsEntity, Long> {
    ContractsEntity findByContractId(Long contractId);

    // select 전용 쿼리
    @Query("SELECT c.contractId FROM ContractsEntity c")
    List<Long> findAllContractIds();

    @Query("SELECT c FROM ContractsEntity c WHERE CAST(c.contractId AS string) LIKE %:contractId%")
    Page<ContractsEntity> findByContractIdLike(@Param("contractId") String contractId, Pageable pageable);

    @Query("SELECT CAST(c.contractStatus AS string), COUNT(c) FROM ContractsEntity c GROUP BY c.contractStatus")
    List<Object[]> countContractsByStatus();

    // 차트 그래프
    @Query("SELECT MONTH(c.terminationDate), COUNT(c) " +
            "FROM ContractsEntity c " +
            "WHERE YEAR(c.terminationDate) = :year AND c.contractStatus = 'Activated' " +
            "GROUP BY MONTH(c.terminationDate)")
    List<Object[]> getMonthlyContracts(@Param("year") int year);

    @Modifying
    @Query(value = "UPDATE contracts SET file_data = :fileData, file_name = :fileName, mime_type = :mimeType WHERE contract_id = :contractId", nativeQuery = true)
    void updateFileData(@Param("contractId") Long contractId,
                        @Param("fileData") byte[] fileData,
                        @Param("fileName") String fileName,
                        @Param("mimeType") String mimeType);
}
