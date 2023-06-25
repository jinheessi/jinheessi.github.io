package com.example.wahwah.Hospital.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;

import com.example.wahwah.Hospital.dto.*;

import lombok.Data;

import java.net.URI;

@Service
public class OpenAPI {

    // 시도/시구군로 필터링하여 병의원 목록 가져와주는 함수 => 자세한 정보 말고 가벼운 정보 정도. 진료 요일 + 오늘 진료 시간 정도만!
    public List<HospitalSummaryDTO> getHospitalListByAddress(String city1, String county1) {
        StringBuilder sb = new StringBuilder(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        String kidCode = "D002";
        // Append query parameters directly without the question mark
        try {
            sb.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + "=Ef3rlLsHWZkeStwJGaJaQFaKsRI0sndf5kQ0V4aLcJy7uwPl0AKew5TSrlieUu9rWP7dpxTk0VOBrWMrzgSM3g%3D%3D");
            sb.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(city1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(county1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" + URLEncoder.encode(kidCode, "UTF-8"));
            sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("500", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL url;

        // hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalSummaryDTO> hospitalSummaryDTOList = new ArrayList<>();

        try {
            // 직접 만들어준 parser 객체를 생성해준다.
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

            for (Element element : item) {
                HospitalSummaryDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                hospitalSummaryDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hospitalSummaryDTOList;
    }

    // 시도/시구군로 필터링하여 병의원 목록 가져와주는 함수 => 자세한 정보 말고 가벼운 정보 정도. 진료 요일 + 오늘 진료 시간 정도만!
    public List<HospitalSummaryDTO> getHospitalListByAddressAndName(String city1, String county1, String hospitalName) {
        StringBuilder sb = new StringBuilder(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        String kidCode = "D002";
        // Append query parameters directly without the question mark
        try {
            sb.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + "=Ef3rlLsHWZkeStwJGaJaQFaKsRI0sndf5kQ0V4aLcJy7uwPl0AKew5TSrlieUu9rWP7dpxTk0VOBrWMrzgSM3g%3D%3D");
            sb.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(city1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(county1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" + URLEncoder.encode(kidCode, "UTF-8"));
            sb.append("&" + URLEncoder.encode("QN", "UTF-8") + "=" + URLEncoder.encode(hospitalName, "UTF-8"));

            sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("500", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL url;

        // hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalSummaryDTO> hospitalSummaryDTOList = new ArrayList<>();

        try {
            // 직접 만들어준 parser 객체를 생성해준다.
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

            for (Element element : item) {
                HospitalSummaryDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                hospitalSummaryDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hospitalSummaryDTOList;
    }

    // 검색을 하여 병원 데이터 목록을 뽑아와 주는 함수 => 자세한 정보 말고 가벼운 정보 정도. 진료 요일 + 오늘 진료 시간 정도만!
    public List<HospitalSummaryDTO> getHospitaSearchByHospitalName(String hospitalName) {
        StringBuffer kidCodeSb = new StringBuffer(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        StringBuffer emergencyCodeSb = new StringBuffer(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        String kidCode = "D002";
        String emergencyCode = "D024";

        // Append query parameters directly without the question mark
        try {
            kidCodeSb.append("?serviceKey="
                    + "Ef3rlLsHWZkeStwJGaJaQFaKsRI0sndf5kQ0V4aLcJy7uwPl0AKew5TSrlieUu9rWP7dpxTk0VOBrWMrzgSM3g==");
            kidCodeSb.append("&QN=" + hospitalName);
            kidCodeSb.append("&QD=" + kidCode);
            emergencyCodeSb.append("?serviceKey="
                    + "Ef3rlLsHWZkeStwJGaJaQFaKsRI0sndf5kQ0V4aLcJy7uwPl0AKew5TSrlieUu9rWP7dpxTk0VOBrWMrzgSM3g==");
            emergencyCodeSb.append("&QN=" + hospitalName);
            emergencyCodeSb.append("&QD=" + emergencyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL kidCodeUrl;
        URL emergencyCodeUrl;

        // hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalSummaryDTO> hospitalDTOList = new ArrayList<>();
        List<HospitalSummaryDTO> distsinctedHospitalSummaryDTOList = new ArrayList<>();

        try {
            // 직접 만들어준 parser 객체를 생성해준다.
            kidCodeUrl = new URL(kidCodeSb.toString());
            HttpURLConnection kidConn = (HttpURLConnection) kidCodeUrl.openConnection();

            emergencyCodeUrl = new URL(emergencyCodeSb.toString());
            HttpURLConnection emergencyConn = (HttpURLConnection) emergencyCodeUrl.openConnection();

            kidConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            kidConn.setRequestMethod("GET");
            kidConn.connect();

            emergencyConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            emergencyConn.setRequestMethod("GET");
            emergencyConn.connect();

            SAXBuilder kidBuilder = new SAXBuilder();

            Document kidDocument = kidBuilder.build(kidConn.getInputStream());
            Element kidRoot = kidDocument.getRootElement();

            Element kidBody = kidRoot.getChild("body");
            Element kidItems = kidBody.getChild("items");
            List<Element> kidItem = kidItems.getChildren("item");

            SAXBuilder emergencyBuilder = new SAXBuilder();

            Document emergencyDocument = emergencyBuilder.build(kidConn.getInputStream());
            Element emergencyRoot = emergencyDocument.getRootElement();

            Element emergencyBody = emergencyRoot.getChild("body");
            Element emergencyItems = emergencyBody.getChild("items");
            List<Element> emergencyItem = emergencyItems.getChildren("item");

            for (Element element : kidItem) {
                HospitalSummaryDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                hospitalDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }

            for (Element element : emergencyItem) {
                HospitalSummaryDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                hospitalDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }

            distsinctedHospitalSummaryDTOList = hospitalDTOList.stream().distinct().collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return distsinctedHospitalSummaryDTOList;
    }

    // 응급실 데이터 목록을 뽑아와 주는 함수
    public List<HospitalSummaryDTO> getEmergencyList(String city1, String county1) {
        StringBuffer sb = new StringBuffer(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");
        String EmergencyCode = "D024";

        // Append query parameters directly without the question mark
        try {
            sb.append("?serviceKey="
                    + "fflIVG4Jwejv9sdXgzfj8PPjv6NFFyGBzJgqb2gHGusYJN09on8GYuJp%2BD8TFPcSPwgw7%2BJqsx0O1r3YJqrrVA%3D%3D");
            sb.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(city1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(county1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("QD", "UTF-8") + "=" + URLEncoder.encode(EmergencyCode, "UTF-8"));
            sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("500", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL url;

        // hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalSummaryDTO> HospitalSummaryDTOList = new ArrayList<>();

        try {
            // 직접 만들어준 parser 객체를 생성해준다.
            url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            SAXBuilder builder = new SAXBuilder();

            Document document = builder.build(conn.getInputStream());
            Element root = document.getRootElement();

            Element body = root.getChild("body");
            Element items = body.getChild("items");
            List<Element> item = items.getChildren("item");

            for (Element element : item) {
                HospitalSummaryDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);

                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                HospitalSummaryDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return HospitalSummaryDTOList;
    }

    // 보건소 불러오는 함수
    public List<HospitalSummaryDTO> getPublicHospitalList(String addr1, String addr2) {
        StringBuilder sb = new StringBuilder(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire");

        // Append query parameters directly without the question mark
        try {
            sb.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + "=Ef3rlLsHWZkeStwJGaJaQFaKsRI0sndf5kQ0V4aLcJy7uwPl0AKew5TSrlieUu9rWP7dpxTk0VOBrWMrzgSM3g%3D%3D");
            sb.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(addr1, "UTF-8"));
            sb.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(addr2, "UTF-8"));
            sb.append("&" + URLEncoder.encode("QZ", "UTF-8") + "=" + URLEncoder.encode("R", "UTF-8"));
            sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("500", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        URL url;

        // hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalSummaryDTO> hospitalSummaryDTOList = new ArrayList<>();

        try {
            // 직접 만들어준 parser 객체를 생성해준다.
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

            for (Element element : item) {
                HospitalSummaryDTO hospitalDTOParser = OpenAPIParser.transferXmlToParser(element);
                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                hospitalSummaryDTOList.add(hospitalDTOParser);
                System.out.println("apartXmlParser = " + hospitalDTOParser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("hospitalSummaryDTOList = " + hospitalSummaryDTOList);
        return hospitalSummaryDTOList;
    }

    // 병원 ID를 이용해서 병원 정보를 가져와 주는 함수
    // callback URL :
    // http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncLcinfoInqire
    public List<HospitalDetailDTO> getHospitalInformation(String HPID) {
        StringBuffer sb = new StringBuffer(
                "http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlBassInfoInqire");

        // Append query parameters directly without the question mark
        try {
            sb.append("?serviceKey="
                    + "PzLhD7ay8y0w%2Bv777oVD2%2F6TIODw1jROQoWA7R%2BOO%2Bse4JTCX9QWBSRWVTcuGfgUK47yvPD6xkqWjjDUetvpyQ%3D%3D");
            sb.append("&HPID=" + URLEncoder.encode(HPID, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url;

        // hospital 정보를 담온 hospitalDTOList 생성
        List<HospitalDetailDTO> hospitalDetailDTOList = new ArrayList<>();

        try {
            // 직접 만들어준 parser 객체를 생성해준다.
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

            for (Element element : item) {
                HospitalDetailDTO hospitalDetailDTOParser = OpenAPIParser.transferXmlToParserDetail(element);

                // parser를 통해 만들어진 hospitalDTO 객체들을 hospitalDTOList에 담아준다.
                hospitalDetailDTOList.add(hospitalDetailDTOParser);
                System.out.println("apartXmlParser = " + hospitalDetailDTOParser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hospitalDetailDTOList;
    }
}
