package com.example.wahwah.member.util;

import java.net.URLEncoder;

 class Page2 {
	
	public String getPageList(int pageNum, int postNum, int pageListCount, int totalCount, String keyword) {
		
		//pageNum : 현재 페이지 번호
		//postNum : 한 화면에 보여지는 게시물 행 갯수
		//pageListCount : 하단 페이지리스트에 보여질 페이지 갯수
		//totalCount : 전체 행갯수
		//totalPage : 전체 페이지갯수
		//section : 한 개의 페이지 목록 예) 1 2 3 4 5 --> section 1, 6 7 8 9 10 --> section2..
		//totalSection : 전체 section 갯수
		//예)  totalCOunt = 7, postNum = 5, totalPage = 2, totalSection = 1, pageListCount = 5
		
		//int totalPage = (int)Math.ceil(totalCount/(double)postNum);
		//int totalSection = (int)Math.ceil(totalPage/(double)pageListCount);
		//int section = (int)Math.ceil(pageNum/(double)pageListCount);
		
		
		
		//페이지 리스트에서 마지막 번호
		int endPageNum = (int)(Math.ceil(pageNum/(double)pageListCount)*pageListCount);
		
		//페이지 리스트에서의 시작 번호
		int startPageNum = endPageNum - (pageListCount - 1);
		
		//실제 마지막 페이지 번호
		int realEndPageNum = (int)(Math.ceil(totalCount/(double)pageNum));
		if(endPageNum > realEndPageNum) {
			endPageNum = realEndPageNum;
		}
		
		boolean prev = startPageNum == 1?false:true;
		boolean next = endPageNum * pageListCount >= totalCount?false:true;
		
		
		String pageList = "";
		
		// 이전 버튼 출력 조건
		if(prev) pageList += " <a href=list?page=" + Integer.toString(startPageNum-1) + ">◀</a>";
		
		// 페이지리스트 출력
		for(int i=startPageNum; i <= endPageNum; i++) {
			// 링크가 붙는 페이지번호
			if(pageNum != i)
				pageList += " <a href=list?page=" + Integer.toString(i) + ">" + Integer.toString(i) + "</a>";
			//링크가 안붙는 페이지 번호
			if(pageNum == i)
				pageList += " <span style='font-weight: bold'>" + Integer.toString(i) + "</span>";
		}
		
		// 다음 버튼 출력 조건
		if(next) pageList += " <a href=list?page=" + Integer.toString(endPageNum+1) + ">▶</a>"; 
		
		/*
		if(totalPage != 1) {
			
			for(int i=1; i<=pageListCount; i++) {
				//1. ◀출력조건
				// - section 값이 1보다 큼
				// - i 가1일때
				if(section>1 && i == 1) {
					pageList += " <a href=list?page=" + Integer.toString((section-2)*pageListCount + pageListCount)+">◀</a>";
				}
				//2. 페이지 출력 중단
				if(totalPage < (section-1)*pageListCount+i) { break; }
				//3. 인자로 가져온 페이지값과 계산해서 나온 페이지값이 같으면 링크를 안 붙이고 다르면 다른 페이지로 이동할수 있는 링크를 붙임.
				if(pageNum != (section-1)*pageListCount + i) {
					pageList += " <a href=list?page=" + Integer.toString((section-1)*pageListCount+i) + ">" + Integer.toString((section-1)*pageListCount+i) + "</a>";
				}else {
					pageList += "<span style='font-weight: bold'>" + Integer.toString((section-1)*pageListCount+i) + "</span>";
				}
				//4. ▶출력조건
				// i == pageListCount --> 페이지 리스트 갯수만큼 페이지번호를 출력 --> 페이지리스트의 끝
				// totalSection > 1 --> section이 1개 이상 존재
				// totalPage >= i+(section-1)*pageListCount+1 --> 아직까지 출력할 페이지가 남아있음
				if(i==pageListCount && totalSection > 1 && totalPage >= (i+(section-1)*pageListCount+1 )) {
					pageList += " <a href=list?page=" + Integer.toString(section*pageListCount + 1) + ">▶</a>";
				}
			}
		}
		*/
		
		
		
		return pageList;
	}
}


public class PageUtil {

	public String getPageList(int pageNum, int postNum, int listCount, int totalCount, String searchType, String keyword){

	int totalPage = (int) Math.ceil(totalCount/(double)postNum); //전체 페이지 갯수
	int totalSection = (int) Math.ceil(totalPage/(double)listCount); //전체 섹션 개수
	
	pageNum = pageNum-1; //계산에 사용되는 페이지 번호는 0부터 시작
	int section = (int) Math.ceil(pageNum/listCount); //현재 위치한 섹션 번호	

	String pageList = "";
	
	if(totalPage > 1 )
	{
		for(int i=0; i < listCount ; i++){ 
			if(section > 0 && i == 0) 
				pageList +=	"<a href=list?page=" + Integer.toString((section-1)*listCount+(listCount)) + "&searchType=" + searchType + "&keyword=" + keyword + ">◀</a> ";
			if(totalPage == (i+section*listCount)){  break; }
			if(pageNum != (section*listCount + i))
				pageList += " <a href=list?page=" + Integer.toString(i+section*listCount+1) + "&searchType=" + searchType + "&keyword=" + keyword + ">" + Integer.toString(i+section*listCount+1) + "</a> ";
			else pageList += " <span style='font-weight: bold'>" + Integer.toString(section*listCount+i+1) + "</span>";
			if(totalSection >1 && i==(listCount-1) && totalPage != (i+section*listCount+1)) 
				pageList += "<a href=list?page=" + Integer.toString((section+1)*listCount+1) + "&searchType=" + searchType + "&keyword=" + keyword + ">▶</a>";
			
		}
 	} else {
 		pageList = "<span style='font-weight: bold'>1</span>";
 	}
	return pageList==null?"":("[" + pageList + "]"); 
	}
	
	
	
	
	
	
	
	public String getPageAddress(int pageNum, int postNum, int listCount, int totalCount, String addrSearch) throws Exception {

	System.out.println("addrSearch = " + addrSearch);	
		
	int totalPage = (int) Math.ceil(totalCount/(double)postNum); //전체 페이지 갯수
	int totalSection = (int) Math.ceil(totalPage/(double)listCount); //전체 섹션 개수
	
	pageNum = pageNum-1; //계산에 사용되는 페이지 번호는 0부터 시작
	int section = (int) Math.ceil(pageNum/listCount); //현재 위치한 섹션 번호	

	String pageList = "";
	
	if(totalPage != 1 )
	{
		for(int i=0; i < listCount ; i++){ 
			if(section > 0 && i == 0) 
				pageList +=	"<a href=addrSearch?page=" + Integer.toString((section-1)*listCount+(listCount)) + "&addrSearch=" + URLEncoder.encode(addrSearch,"UTF-8" ) + ">◀</a> ";
			if(totalPage == (i+section*listCount)){  break; }
			if(pageNum != (section*listCount + i))
				pageList += " <a href=addrSearch?page=" + Integer.toString(i+section*listCount+1) + "&addrSearch=" + URLEncoder.encode(addrSearch,"UTF-8" ) + ">" + Integer.toString(i+section*listCount+1) + "</a> ";
			else pageList += " <span style='font-weight: bold'>" + Integer.toString(section*listCount+i+1) + "</span>";
			if(totalSection >1 && i==(listCount-1) && totalPage != (i+section*listCount+1)) 
				pageList += "<a href=addrSearch?page=" + Integer.toString((section+1)*listCount+1) + "&addrSearch=" + URLEncoder.encode(addrSearch,"UTF-8" ) + ">▶</a>";
			
		}
 	} 
	return pageList==null?"":("[" + pageList + "]"); 
	}
	
	
	
	public String getPageAddress2(int pageNum, int postNum, int listCount, int totalCount, String addrSearch) throws Exception {
	
		
		
		
		return "";
	}
	
	
}
