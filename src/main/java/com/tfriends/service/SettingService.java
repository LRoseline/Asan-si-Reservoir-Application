package com.tfriends.service;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;

import com.tfriends.domain.SettingVO;
import com.tfriends.mapper.SettingMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {
    @Autowired
    private SettingMapper m;

    public SettingVO SettingLoad (String type) {
        return m.selectVal(type);
    }

    public String OperationExecute () {
        String path = "Unknown";
        String os = this.SettingLoad("operation").getValue();

        if (os.equals("Windows")) {
            path = this.SettingLoad("Windows").getValue();
        } else if (os.equals("Linux")) {
            path = this.SettingLoad("Linux").getValue();
        }

        /*
        이 값들을 모두 DBMS로 옮길 것.
        String s = "/home/emilia/keys.txt";
		String s = "C:/Users/amb17/Documents/keys.txt";
		*/

        return path;
    }

    // public String Tricker(int Input_the_line_number) throws Exception {
    //     File f = new File(this.OperationExecute());

	// 	FileReader reader = new FileReader(f);
	// 	BufferedReader br = new BufferedReader(reader);

	// 	String WYF = "/n";

	// 	String str="", l="";
		
	// 	while((l=br.readLine())!=null) {
	// 		str += l+WYF;
	// 	}

	// 	br.close();
	// 	reader.close();

	// 	String[] array = str.trim().split(WYF);

	// 	String [] codesplit = array[Input_the_line_number].trim().split(" : ");

	// 	return codesplit[1];
    // }
}
