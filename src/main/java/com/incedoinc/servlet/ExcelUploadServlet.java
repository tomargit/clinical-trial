package com.incedoinc.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.incedoinc.file.reader.ExcelFileReaderJSON;

@WebServlet(name = "ExcelUploadServlet", urlPatterns = { "/UploadServlet" })
@MultipartConfig
public class ExcelUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet Start!!!!!!!!!!!!!!");
		final String json = processRequest(request);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	public String processRequest(HttpServletRequest request) throws ServletException, IOException {
		// Create path components to save the file
		final Part filePart = request.getPart("file");
		ExcelFileReaderJSON jsonReader = new ExcelFileReaderJSON();
		OutputStream out = null;
		InputStream filecontent = null;

		try {
			
			filecontent = filePart.getInputStream();
			final String json = jsonReader.processFileRead(filecontent);
			return json;
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();

		}  catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
		return null;
	}
}
