package test;

import java.io.File;
import java.io.IOException;

public class s {
public static void main(String[] args) {
	File file= new File("X:/Users/Administrator/Desktop/m.docx");
	if(file.exists()) {
		file.delete();
	}
	else {
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
