package com.example.wahwah.Receipt.service;

import java.util.List;

import com.example.wahwah.Receipt.dto.ReceiptDTO;
import com.example.wahwah.Receipt.dto.ReceiptInterface;
import com.example.wahwah.Receipt.entity.ReceiptEntity;

public interface ReceiptService {

	// 접수 하기
	public void receipt(ReceiptDTO receiptDTO);
	
	// 내 접수 목록 확인
	public List<ReceiptInterface> receiptList(String rname);
	
	// 내 접수 하나 확인
	public ReceiptInterface receiptView(int seqno);
	
	// 내 접수 수정하기
	public void receiptUpdate(ReceiptDTO receiptDTO);
	
	// 내 접수 취소하기
	public void receiptDelete(int seqno);
	
	
	
}
