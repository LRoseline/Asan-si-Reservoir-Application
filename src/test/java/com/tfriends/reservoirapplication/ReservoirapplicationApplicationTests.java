package com.tfriends.reservoirapplication;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.tfriends.domain.VoirAPI;
import com.tfriends.domain.VoirDaily;
import com.tfriends.domain.Voirs;
import com.tfriends.service.VoirService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@SpringBootTest
class ReservoirapplicationApplicationTests {

    String s = "C:/Users/amb17/Desktop/keys.txt";

    public String Tricker(int Input_the_line_number) throws Exception {
        File f = new File(s);

		FileReader reader = new FileReader(f);
		BufferedReader br = new BufferedReader(reader);

		String WYF = "/n";

		String str="", l="";
		
		while((l=br.readLine())!=null) {
			str += l+WYF;
		}

		br.close();
		reader.close();

		String[] array = str.trim().split(WYF);

		String [] codesplit = array[Input_the_line_number].trim().split(" : ");

		return codesplit[1];
    }

	@Autowired
	private VoirService v;

	@Test
	public void TestCodeRead () {
		List<Voirs> vo = v.VoirLists("");

		System.out.println(vo);
	}

	@Test
	public void TestXMLReservoir () throws Exception {
        VoirAPI vo = v.VoirClick(1);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		Calendar [] dates = {Calendar.getInstance(), Calendar.getInstance()};
		dates[0].add(Calendar.DATE, -2);

		String [] datesdf = {sdf.format(dates[0].getTime()), sdf.format(dates[1].getTime())};

		String xml;
		String URLComp = "http://apis.data.go.kr/B552149/reserviorWaterLevel/reservoirlevel/?serviceKey="+this.Tricker(3)+"&pageNo=1&numOfRows=10&fac_code="+vo.getCode()+"&date_s="+datesdf[0]+"&date_e="+datesdf[1];

        URL url = new URL(URLComp);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
//        http.setConnectTimeout(10000);
        http.setUseCaches(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            sb.append(line);
        }
        xml = sb.toString();
        br.close();
        http.disconnect();
        
        if (xml != null) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory
	                .newInstance();
	        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	
	        InputStream is =new ByteArrayInputStream(xml.getBytes());
	        Document doc = documentBuilder.parse(is);
	        Element element = doc.getDocumentElement();

            NodeList [] voir = {
                element.getElementsByTagName("fac_name"),
                element.getElementsByTagName("check_date"),
                element.getElementsByTagName("rate"),
                element.getElementsByTagName("water_level")
            };

			System.out.println(voir[0]);
		}
        System.out.println(vo);
	}

	@Test
	public void Ashley() throws Exception {
        VoirAPI vo = v.VoirClick(1);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		Calendar [] dates = {Calendar.getInstance(), Calendar.getInstance()};
		dates[0].add(Calendar.DATE, -7);

		String [] datesdf = {sdf.format(dates[0].getTime()), sdf.format(dates[1].getTime())};

		String xml;
		String URLComp = "http://apis.data.go.kr/B552149/reserviorWaterLevel/reservoirlevel/?serviceKey="+this.Tricker(3)+"&pageNo=1&numOfRows=10&fac_code="+vo.getCode()+"&date_s="+datesdf[0]+"&date_e="+datesdf[1];

        URL url = new URL(URLComp);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
//        http.setConnectTimeout(10000);
        http.setUseCaches(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            sb.append(line);
        }
        xml = sb.toString();
        br.close();
        http.disconnect();
        
        if (xml != null) {
	        DocumentBuilderFactory factory = DocumentBuilderFactory
	                .newInstance();
	        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	
	        InputStream is =new ByteArrayInputStream(xml.getBytes());
	        Document doc = documentBuilder.parse(is);
	        Element element = doc.getDocumentElement();

            NodeList [] voir = {
                element.getElementsByTagName("fac_name"),
                element.getElementsByTagName("check_date"),
                element.getElementsByTagName("rate"),
                element.getElementsByTagName("water_level")
            };

            List<VoirDaily> appmedia = new ArrayList<VoirDaily>();
            for (int i = 0; i < 8; i++) {
                VoirDaily days = new VoirDaily();
                
                days.setDate(voir[1].item(i).getFirstChild().getNodeValue());
                days.setRate(voir[2].item(i).getFirstChild().getNodeValue());
                days.setWlevel(voir[3].item(i).getFirstChild().getNodeValue());
                appmedia.add(i, days);
                vo.setDaily(appmedia);
            }
        }
        System.out.println(vo);
    }
}
