package com.example.wahwah.Hospital.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.example.wahwah.Hospital.dto.*;

import lombok.Data;

import java.net.URI;

public class OpenAPI {
    
    //시도/시구군로 필터링하여 병의원 목록 가져와주는 함수 => 자세한 정보 말고 가벼운 정보 정도. 진료 요일 + 오늘 진료 시간 정도만!
    public List<HospitalDTO> getHospitalListByAddress(String city1, String county1){
        StringBuilder sb = new StringBuilder("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        String kidCode = "D002";
        // Append query parameters directly without the question mark
        try {
        	sb.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=Ef3rlLsHWZkeStwJGaJaQFaKsRI0sndf5kQ0V4aLcJy7uwPl0AKew5TSrlieUu9rWP7dpxTk0VOBrWMrzgSM3g%3D%3D");
            sb.append("&" + URLEncoder.encode("Q0", "UTF-8") +"=" + URLEncoder.encode(city1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("Q1", "UTF-8") +"=" + URLEncoder.encode(county1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("QD", "UTF-8") +"=" + URLEncoder.encode(kidCode, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL url;

        //hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalDTO> hospitalDTOList = new ArrayList<>();

        try {
            //직접 만들어준 parser 객체를 생성해준다. 
            url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            
            Document document = new SAXBuilder().build(conn.getInputStream());
            Element root = document.getRootElement();
            Element body = root.getChild("body");
            Element items = body.getChild("items");
            List<Element> item = items.getChildren("item");
           
            for (Element element : item){
                HospitalDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                //parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다. 
                hospitalDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }   

        } catch (Exception e) {
            e.printStackTrace();
        }

		return hospitalDTOList;
       }
            
            /*
      


    //검색을 하여 병원 데이터 목록을 뽑아와 주는 함수 => 자세한 정보 말고 가벼운 정보 정도. 진료 요일 + 오늘 진료 시간 정도만! 
    public List<HashMap<String, String>> getHospitaSearchList(String keyword){
        
        return null;
    }

    //응급실 데이터 목록을 뽑아와 주는 함수 => 자세한 정보 말고 가벼운 정보 정도. 진료 요일 + 오늘 진료 시간 정도만. 
    public List<HospitalDTO> getEmemrgencyList(String addr1, String addr2){
        StringBuffer sb = new StringBuffer("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        String EmergencyCode = "D024";
        
        // Append query parameters directly without the question mark
        try {
            sb.append("?serviceKey=" + "fflIVG4Jwejv9sdXgzfj8PPjv6NFFyGBzJgqb2gHGusYJN09on8GYuJp%2BD8TFPcSPwgw7%2BJqsx0O1r3YJqrrVA%3D%3D");
            sb.append("&Q0=" + addr1);
            sb.append("&Q1=" + addr2);
            sb.append("&QD=" + EmergencyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL url;

        //hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalDTO> hospitalDTOList = new ArrayList<>();

        try {
            //직접 만들어준 parser 객체를 생성해준다. 
            url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestMethod("GET");
            conn.connect();

            SAXBuilder builder = new SAXBuilder();

            Document document = builder.build(conn.getInputStream());
            Element root = document.getRootElement();

            Element body = root.getChild("body");
            Element items = body.getChild("items");
            List<Element> item = items.getChildren("item");
         
            for (Element element : item){
                HospitalDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                //parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다. 
                hospitalDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }   

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hospitalDTOList;
    }

    //병원 ID를 이용해서 병원 정보를 가져와 주는 함수 
    //callback URL : http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncLcinfoInqire
    public List<HospitalDetailDTO> getHospitalInformation(String HPID){
        StringBuffer sb = new StringBuffer("http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlBassInfoInqire");
        
        // Append query parameters directly without the question mark
        try {
            sb.append("?serviceKey=" + "PzLhD7ay8y0w%2Bv777oVD2%2F6TIODw1jROQoWA7R%2BOO%2Bse4JTCX9QWBSRWVTcuGfgUK47yvPD6xkqWjjDUetvpyQ%3D%3D");
            sb.append("&HPID=" + URLEncoder.encode(HPID, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url;

        //hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalDetailDTO> hospitalDetailDTOList = new ArrayList<>();
        
        try {
            //직접 만들어준 parser 객체를 생성해준다. 
            url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setRequestMethod("GET");
            conn.connect();

            SAXBuilder builder = new SAXBuilder();

            Document document = builder.build(conn.getInputStream());
            Element root = document.getRootElement();

            Element body = root.getChild("body");
            Element items = body.getChild("items");
            List<Element> item = items.getChildren("item");
         
            for (Element element : item){
                HospitalDetailDTO hospitalDetailDTOParser = OpenAPIParser.transferXmlToParserDetail(element);

                //parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다. 
                hospitalDetailDTOList.add(hospitalDetailDTOParser);
                System.out.println("apartXmlParser = " + hospitalDetailDTOParser);
            }   

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hospitalDetailDTOList;
    }
   */
}
