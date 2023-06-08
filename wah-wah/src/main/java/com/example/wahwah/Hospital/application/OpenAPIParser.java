package com.example.wahwah.Hospital.application;

import java.util.List;

import com.example.wahwah.Hospital.dto.HospitalDTO;
import com.example.wahwah.Hospital.dto.HospitalDetailDTO;

import org.jdom2.Element;

public class OpenAPIParser {

    public static HospitalDTO transferXmlToParser(Element item) {
        HospitalDTO.HospitalDTOBuilder builder = HospitalDTO.builder();
     
        List<Element> children = item.getChildren();

        for (Element child : children) {
            mappingFromHospitalItemToParser(builder, child);
        }

        return builder.build();
    }


    public static HospitalDetailDTO transferXmlToParserDetail(Element item) {
        HospitalDetailDTO.HospitalDetailDTOBuilder builder = HospitalDetailDTO.builder();

        List<Element> children = item.getChildren();

        for (Element child : children) {
            mappingFromHospitalDetailItemToParser(builder, child);
        }

        return builder.build();
    }

    public static void mappingFromHospitalItemToParser(HospitalDTO.HospitalDTOBuilder builder, Element item) {
        String value = item.getContent(0).getValue().trim();
        String name = item.getName();

        if (name.equals("")) {
            return;
        } else if (name.equals("dutyAddr")) {
            builder.dutyAddr(value);
        } else if (name.equals("dutyDiv")) {
            builder.dutyDiv(value);
        } else if (name.equals("dutyInf")) {
            builder.dutyInf(value);
        } else if (name.equals("dutyName")) {
            builder.dutyName(value);
        } else if (name.equals("dutyTel1")) {
            builder.dutyTel1(value);
        } else if (name.equals("dutyTel3")) {
            builder.dutyTel3(value);
        } else if (name.equals("dutyTime1c")) {
            builder.dutyTime1c(value);
        } else if (name.equals("dutyTime1s")) {
            builder.dutyTime1s(value);
        } else if (name.equals("dutyTime2c")) {
            builder.dutyTime2c(value);
        } else if (name.equals("dutyTime2s")) {
            builder.dutyTime2s(value);
        } else if (name.equals("dutyTime3c")) {
            builder.dutyTime3c(value);
        } else if (name.equals("dutyTime3s")) {
            builder.dutyTime1s(value);
        } else if (name.equals("dutyTime4c")) {
            builder.dutyTime4c(value);
        } else if (name.equals("dutyTime4s")) {
            builder.dutyTime4s(value);
        } else if (name.equals("dutyTime5c")) {
            builder.dutyTime5c(value);
        } else if (name.equals("dutyTime5s")) {
            builder.dutyTime5s(value);
        } else if (name.equals("dutyTime6c")) {
            builder.dutyTime6c(value);
        } else if (name.equals("dutyTime6s")) {
            builder.dutyTime6s(value);
        } else if (name.equals("dutyTime7c")) {
            builder.dutyTime7c(value);
        } else if (name.equals("dutyTime7s")) {
            builder.dutyTime7s(value);
        } else if (name.equals("dutyTime8c")) {
            builder.dutyTime8c(value);
        } else if (name.equals("dutyTime8s")) {
            builder.dutyTime8s(value);
        } else if (name.equals("hpid")) {
            builder.hpid(value);
        } else if (name.equals("postCdn1")) {
            builder.postCdn1(value);
        } else if (name.equals("wgs84Lon")) {
            builder.wgs84Lon(Double.parseDouble(value));
        } else if (name.equals("wgs84Lat")) {
            builder.wgs84Lat(Double.parseDouble(value));
        }
    }

    public static void mappingFromHospitalDetailItemToParser(HospitalDetailDTO.HospitalDetailDTOBuilder builder,
            Element item) {
        String value = item.getContent(0).getValue().trim();
        String name = item.getName();

        if (name.equals("")) {
            return;
        } else if (name.equals("hpid")) {
            builder.hpid(value);
        } else if (name.equals("dutyName")) {
            builder.dutyName(value);
        } else if (name.equals("postCdn1")) {
            builder.postCdn1(value);
        } else if (name.equals("postCdn2")) {
            builder.postCdn2(value);
        } else if (name.equals("dutyAddr")) {
            builder.dutyAddr(value);
        } else if (name.equals("dutyTel1")) {
            builder.dutyTel1(value);
        } else if (name.equals("dutyTel3")) {
            builder.dutyTel3(value);
        } else if (name.equals("dutyEryn")) {
            builder.dutyEryn(value);
        } else if (name.equals("dutyTime1c")) {
            builder.dutyTime1c(value);
        } else if (name.equals("dutyTime1s")) {
            builder.dutyTime1s(value);
        } else if (name.equals("dutyTime2c")) {
            builder.dutyTime2c(value);
        } else if (name.equals("dutyTime2s")) {
            builder.dutyTime2s(value);
        } else if (name.equals("dutyTime3c")) {
            builder.dutyTime3c(value);
        } else if (name.equals("dutyTime3s")) {
            builder.dutyTime1s(value);
        } else if (name.equals("dutyTime4c")) {
            builder.dutyTime4c(value);
        } else if (name.equals("dutyTime4s")) {
            builder.dutyTime4s(value);
        } else if (name.equals("dutyTime5c")) {
            builder.dutyTime5c(value);
        } else if (name.equals("dutyTime5s")) {
            builder.dutyTime5s(value);
        } else if (name.equals("dutyTime6c")) {
            builder.dutyTime6c(value);
        } else if (name.equals("dutyTime6s")) {
            builder.dutyTime6s(value);
        } else if (name.equals("dutyTime7c")) {
            builder.dutyTime7c(value);
        } else if (name.equals("dutyTime7s")) {
            builder.dutyTime7s(value);
        } else if (name.equals("dutyTime8c")) {
            builder.dutyTime8c(value);
        } else if (name.equals("dutyTime8s")) {
            builder.dutyTime8s(value);
        } else if (name.equals("MKioskTy25")) {
            builder.hpid(value);
        } else if (name.equals("o001")) {
            builder.o001(value);
        } else if (name.equals("o002")) {
            builder.o002(value);
        } else if (name.equals("o008")) {
            builder.o008(value);
        } else if (name.equals("o009")) {
            builder.o009(value);
        } else if (name.equals("o010")) {
            builder.o010(value);
        } else if (name.equals("o020")) {
            builder.o020(value);
        } else if (name.equals("o031")) {
            builder.o031(value);
        } else if (name.equals("hpnicuyn")) {
            builder.hpnicuyn(value);
        } else if (name.equals("wgs84Lon")) {
            builder.wgs84Lon(Double.parseDouble(value));
        } else if (name.equals("wgs84Lat")) {
            builder.wgs84Lat(Double.parseDouble(value));
        }
    }

}
