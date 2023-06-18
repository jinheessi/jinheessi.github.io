package com.example.wahwah.Hospital.application;

import java.util.List;

import com.example.wahwah.Hospital.dto.HospitalDetailDTO;
import com.example.wahwah.Hospital.dto.HospitalSummaryDTO;
import com.example.wahwah.Hospital.dto.HospitalSummaryDTO.HospitalSummaryDTOBuilder;

import org.jdom2.Element;

public class OpenAPIParser {

    public static HospitalSummaryDTO transferXmlToParser(Element item) {
        HospitalSummaryDTOBuilder builder = HospitalSummaryDTO.builder();
        mapHospitalItemToParser(builder, item);
        List<Element> children = item.getChildren();

        for (Element child : children) {
            mapHospitalItemToParser(builder, child);
        }
        return builder.build();
    }

    public static HospitalDetailDTO transferXmlToParserDetail(Element item) {
        HospitalDetailDTO.HospitalDetailDTOBuilder builder = HospitalDetailDTO.builder();
        mapHospitalDetailItemToParser(builder, item);
        List<Element> children = item.getChildren();

        for (Element child : children) {
            mapHospitalDetailItemToParser(builder, child);
        }

        return builder.build();
    }

    private static void mapHospitalItemToParser(HospitalSummaryDTO.HospitalSummaryDTOBuilder builder, Element item) {
        String value = item.getContent(0).getValue().trim();
        String name = item.getName();

        switch (name) {
            case "dutyAddr":
                builder.dutyAddr(value);
                break;
            case "dutyDiv":
                builder.dutyDiv(value);
                break;
            case "dutyInf":
                builder.dutyInf(value);
                break;
            case "dutyName":
                builder.dutyName(value);
                break;
            case "dutyTel1":
                builder.dutyTel1(value);
                break;
            case "dutyTel3":
                builder.dutyTel3(value);
                break;
            case "dutyTime1c":
                builder.dutyTime1c(value);
                break;
            case "dutyTime1s":
                builder.dutyTime1s(value);
                break;
            case "dutyTime2c":
                builder.dutyTime2c(value);
                break;
            case "dutyTime2s":
                builder.dutyTime2s(value);
                break;
            case "dutyTime3c":
                builder.dutyTime3c(value);
                break;
            case "dutyTime3s":
                builder.dutyTime3s(value);
                break;
            case "dutyTime4c":
                builder.dutyTime4c(value);
                break;
            case "dutyTime4s":
                builder.dutyTime4s(value);
                break;
            case "dutyTime5c":
                builder.dutyTime5c(value);
                break;
            case "dutyTime5s":
                builder.dutyTime5s(value);
                break;
            case "dutyTime6c":
                builder.dutyTime6c(value);
                break;
            case "dutyTime6s":
                builder.dutyTime6s(value);
                break;
            case "dutyTime7c":
                builder.dutyTime7c(value);
                break;
            case "dutyTime7s":
                builder.dutyTime7s(value);
                break;
            case "dutyTime8c":
                builder.dutyTime8c(value);
                break;
            case "dutyTime8s":
                builder.dutyTime8s(value);
                break;
            case "hpid":
                builder.hpid(value);
                break;
            case "postCdn1":
                builder.postCdn1(value);
                break;
            case "wgs84Lon":
                builder.wgs84Lon(Double.parseDouble(value));
                break;
            case "wgs84Lat":
                builder.wgs84Lat(Double.parseDouble(value));
                break;
        }
    }

    private static void mapHospitalDetailItemToParser(HospitalDetailDTO.HospitalDetailDTOBuilder builder,
            Element item) {
        String value = item.getContent(0).getValue().trim();
        String name = item.getName();

        switch (name) {
            case "hpid":
                builder.hpid(value);
                break;
            case "dutyName":
                builder.dutyName(value);
                break;
            case "postCdn1":
                builder.postCdn1(value);
                break;
            case "postCdn2":
                builder.postCdn2(value);
                break;
            case "dutyAddr":
                builder.dutyAddr(value);
                break;
            case "dutyTel1":
                builder.dutyTel1(value);
                break;
            case "dutyTel3":
                builder.dutyTel3(value);
                break;
            case "dutyEryn":
                builder.dutyEryn(value);
                break;
            case "dutyTime1c":
                builder.dutyTime1c(value);
                break;
            case "dutyTime1s":
                builder.dutyTime1s(value);
                break;
            case "dutyTime2c":
                builder.dutyTime2c(value);
                break;
            case "dutyTime2s":
                builder.dutyTime2s(value);
                break;
            case "dutyTime3c":
                builder.dutyTime3c(value);
                break;
            case "dutyTime3s":
                builder.dutyTime1s(value);
                break;
            case "dutyTime4c":
                builder.dutyTime4c(value);
                break;
            case "dutyTime4s":
                builder.dutyTime4s(value);
                break;
            case "dutyTime5c":
                builder.dutyTime5c(value);
                break;
            case "dutyTime5s":
                builder.dutyTime5s(value);
                break;
            case "dutyTime6c":
                builder.dutyTime6c(value);
                break;
            case "dutyTime6s":
                builder.dutyTime6s(value);
                break;
            case "dutyTime7c":
                builder.dutyTime7c(value);
                break;
            case "dutyTime7s":
                builder.dutyTime7s(value);
                break;
            case "dutyTime8c":
                builder.dutyTime8c(value);
                break;
            case "dutyTime8s":
                builder.dutyTime8s(value);
                break;
            case "MKioskTy25":
                builder.MKioskTy25(value);
                break;
            case "o001":
                builder.o001(value);
                break;
            case "o002":
                builder.o002(value);
                break;
            case "o008":
                builder.o008(value);
                break;
            case "o009":
                builder.o009(value);
                break;
            case "o010":
                builder.o010(value);
                break;
            case "o020":
                builder.o020(value);
                break;
            case "o031":
                builder.o031(value);
                break;
            case "hpnicuyn":
                builder.hpnicuyn(value);
                break;
            case "wgs84Lon":
                builder.wgs84Lon(Double.parseDouble(value));
                break;
            case "wgs84Lat":
                builder.wgs84Lat(Double.parseDouble(value));
                break;
        }
    }
}