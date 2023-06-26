package com.example.wahwah.Receipt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.entity.HospitalEntity;
import com.example.wahwah.Receipt.dto.ReceiptDTO;
import com.example.wahwah.Receipt.dto.ReceiptInterface;
import com.example.wahwah.Receipt.entity.ReceiptEntity;
import com.example.wahwah.Receipt.entity.repository.ReceiptRepository;
import com.example.wahwah.hospitaluser.entity.HospitalUserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService{
	
		private final ReceiptRepository receiptRepository;
		
		// 접수 하기
		@Override
		public void receipt(ReceiptDTO receiptDTO) {
			ReceiptEntity receiptEntity = new ReceiptEntity().builder()
												.seqno(receiptDTO.getSeqno())
												.hpid(receiptDTO.getHpid())
												.dutyName(receiptDTO.getDutyName())
												.rname(receiptDTO.getRname())
												.regdate(receiptDTO.getRegdate())
												.pname(receiptDTO.getPname())
												.birth(receiptDTO.getBirth())
												.gender(receiptDTO.getGender())
												.symptom(receiptDTO.getSymptom())
												.state(receiptDTO.getState())
												.build();
			receiptRepository.save(receiptEntity);
			
		}
		
		// 내 접수 목록 확인
		@Override
		public List<ReceiptInterface> receiptList(String rname){
		     List<ReceiptInterface> receiptInterfaceList =receiptRepository.viewReceipt(rname);
		     return receiptInterfaceList;
			
		}
		
		// 내 접수 하나 확인
		@Override
		public ReceiptInterface receiptView(int seqno) {
			ReceiptInterface receiptInterface = receiptRepository.viewoneReceipt(seqno);
			return receiptInterface;
			
			
		}
		
		// 내 접수 수정하기
		@Override
		public void receiptUpdate(ReceiptDTO receiptDTO) {
			 ReceiptEntity entity = receiptDTO.dtoToEntity(receiptDTO);
			 
		}
		
		// 내 접수 취소하기
		@Override
		public void receiptDelete(int seqno) {
			receiptRepository.deleteReceipt(seqno);
		}
}
