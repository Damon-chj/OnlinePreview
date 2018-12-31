package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import onlinePreview.Docx2PDF;
import onlinePreview.Office2Pdf;

/**
 * Servlet implementation class transfer
 */
@WebServlet(name = "transfer", urlPatterns = { "/transfer", "/servlet/transfer" }, loadOnStartup = 1)
public class transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public transfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String WEB_INFpath = this.getClass().getClassLoader().getResource("/").getPath();//获取项目WEB-INF/classes/ 路径
		String path = WEB_INFpath.substring(0,WEB_INFpath.indexOf("/WEB-INF"));// get Project root path
		path =removeCharAt(path, 0);
		request.setCharacterEncoding("UTF-8"); //set character
		String fileName=request.getParameter("fileName");
		System.out.println(fileName);
		String fileNamePDF=fileName.substring(0, fileName.indexOf("."))+".pdf";
		System.out.println(fileNamePDF);
		request.setAttribute("fileName", fileNamePDF);
		File target = new File(path+"/source/"+fileNamePDF);
		if(fileName.contains(".docx")) {
			Docx2PDF.doc2pdf(path+"/source/"+fileName,WEB_INFpath, target);//Due to copyright issues, I can only get a package for aspose for java,
		}else {																//which achieves better conversion of words than Open Office.
			Office2Pdf.convertTo(new File(path+"/source/"+fileName), target);//use OpenOffice +jodConverter to achieve conversion
		}
		try {
			Thread.currentThread();
			Thread.sleep(300);				//sleep o.3 sec
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("showPDF.jsp").forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	 * 字符串删除某个位置的字符
	 * @param path
	 * @param i
	 * @return
	 */
	private String removeCharAt(String path, int i) {
		return path.substring(0, i) + path.substring(i + 1);
		
	}
}


