package com.example.wahwah.Receipt.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="tbl_receipt")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
    name="RECEIPT_SEQ_GENERATOR",
    sequenceName = "RECEIPT_SEQ",
    initialValue = 1, allocationSize = 1
)
public class ReceiptEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECEIPT_SEQ_GENERATOR")
	@Column(name="seqno")
	int seqno;
	
    @Column(name="hpid")
    String hpid;
    
    @Column(name="dutyName")
    String dutyName;
    
    @Column(name="regdate")
    String regdate;
    
    @Column(name="rname")
    String rname;
    
    @Column(name="pname")
    String pname;

    @Column(name="birth")
    String birth;

    @Column(name="gender")
    String gender;

    @Column(name="symptom")
    String symptom;

    @Column(name="state")
    String state;
    
}
