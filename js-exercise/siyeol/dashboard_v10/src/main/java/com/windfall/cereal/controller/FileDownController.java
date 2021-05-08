package com.windfall.cereal.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.windfall.cereal.dto.BoardVO;
import com.windfall.cereal.dto.MemberVO;
import com.windfall.cereal.page.PageMaker;
import com.windfall.cereal.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileDownController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownController.class);
	
	@Inject
	private BoardService service;
	

	@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
	public String fileDown(Model model) throws Exception {
		logger.info("==fileDown==");	
				
		return "fileDown";
	}
	

	

	
	@RequestMapping(value = "/formDownload", method = RequestMethod.POST)
	public void formDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==formDownload==");	
		
		OutputStream out = null;
		
		try {
			Workbook workbook = excelCreate();
	        
	        response.reset();
	        response.setHeader("Content-Disposition", "attachment;filename=stbcs_history.xls");
	        response.setContentType("application/vnd.ms-excel");
	        out = new BufferedOutputStream(response.getOutputStream());
	        
	        workbook.write(out);
	        out.flush();
	        
        } catch (Exception e) {
            logger.error("exception during downloading excel file : {}", e);
        } finally {
            if(out != null) out.close();
        } 
		
	}
	


	@ResponseBody
	@RequestMapping(value = "/ajaxDownload", method = RequestMethod.POST)
	public String ajaxDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==ajaxDownload==");	
				
		OutputStream out = null;
		
		try {
			Workbook workbook = excelCreate();
	        
	        response.reset();
	        response.setHeader("Content-Disposition", "attachment;filename=stbcs_history.xls");
	        response.setContentType("application/vnd.ms-excel");
	        out = new BufferedOutputStream(response.getOutputStream());
	        
	        workbook.write(out);
	        out.flush();
	        
        } catch (Exception e) {
            logger.error("exception during downloading excel file : {}", e);
        } finally {
            if(out != null) out.close();
        } 
		
		return "fileDown ajax test";
	}
	
	
	private Workbook excelCreate() {
		
		// 엑셀 파일 하나를 만듭니다
		  Workbook workbook = new SXSSFWorkbook();
		  // 엑셀 파일 내부에 Sheet 를 하나 생성합니다 (엑셀 파일 하나에는 여러 Sheet가 있을 수 있습니다)
		  Sheet sheet = workbook.createSheet();

		  // 헤더를 생성합니다
		  int rowIndex = 0;
		  Row headerRow = sheet.createRow(rowIndex++);
		  Cell headerCell1 = headerRow.createCell(0);
		  headerCell1.setCellValue("회사");

		  Cell headerCell2 = headerRow.createCell(1);
		  headerCell2.setCellValue("차종");

		  Cell headerCell3 = headerRow.createCell(2);
		  headerCell3.setCellValue("가격");

		  Cell headerCell4 = headerRow.createCell(3);
		  headerCell4.setCellValue("평점");

		    Row bodyRow = sheet.createRow(rowIndex++);
	
		    Cell bodyCell1 = bodyRow.createCell(0);
		    bodyCell1.setCellValue("test data");
	
		    Cell bodyCell2 = bodyRow.createCell(1);
		    bodyCell2.setCellValue("test data");
	
		    Cell bodyCell3 = bodyRow.createCell(2);
		    bodyCell3.setCellValue("test data");
	
		    Cell bodyCell4 = bodyRow.createCell(3);
		    bodyCell4.setCellValue("test data");
		  

		
		return workbook;
	}
	
	 
}
