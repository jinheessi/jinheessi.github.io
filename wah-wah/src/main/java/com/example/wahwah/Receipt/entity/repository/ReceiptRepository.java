package com.example.wahwah.Receipt.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.wahwah.Receipt.dto.ReceiptInterface;
import com.example.wahwah.Receipt.entity.ReceiptEntity;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, String> {
	@Query(value="delete from tbl_receipt where seqno = :seqno", nativeQuery=true)
	public void deleteReceipt(@Param("seqno") int seqno) ;

	@Query(value="select * from tbl_receipt where rname = :rname", nativeQuery=true)
	public List<ReceiptInterface> viewReceipt(@Param("rname") String rname) ;
	
	
	@Query(value="select * from tbl_receipt where seqno = :seqno", nativeQuery=true)
	public ReceiptInterface viewReceipt(@Param("seqno") int seqno) ;
}
