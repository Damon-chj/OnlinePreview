package onlinePreview;


import java.io.*;
import java.net.ConnectException;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Office2Pdf {

	private static String OpenOffice_HOME = "X:/Program Files (x86)/OpenOffice 4/program/";

	private static String command = "soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";


	public static void convertTo(File sourcefile, File targetfile) {

		Process process = null;
		if (isPDF(sourcefile)) {
			try {
				FileInputStream inStream = new FileInputStream(sourcefile);
				FileOutputStream fs = new FileOutputStream(targetfile);
				byte[] buffer = new byte[10];
				int byteread;;
				try {
					while((byteread =inStream.read(buffer))!=-1) {
						fs.write(buffer, 0, byteread);
					}
					inStream.close();
					fs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
		
				process = startOpenOffice();
			
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				connection.connect();
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			
				converter.convert(sourcefile, targetfile);
				connection.disconnect();
			} catch (ConnectException e) {
				e.printStackTrace();
			} finally {
				
				process.destroy();
			}
		}
	}


	public static Process startOpenOffice() {
		String fullCommand = OpenOffice_HOME + command;

		try {
			return Runtime.getRuntime().exec(fullCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static boolean isPDF(File file) {
		if (file.getName().contains(".pdf") || file.getName().contains(".Pdf") || file.getName().contains(".pDf")
				|| file.getName().contains(".pdF") || file.getName().contains(".PDf") || file.getName().contains(".pDF")
				|| file.getName().contains(".PdF") || file.getName().contains(".PDF")) {
			return true;
		} else
			return false;
	}
public static void main(String[] args) {
	File source = new File("source/1.xlsx");
	File target = new File("WebContent/PDF.js/web/show.pdf");
	Office2Pdf.convertTo(source, target);
}


}
