package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {
	//uploadForm 시작화면
	@RequestMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("UploadController upLoadFormStart Start...");
		return "upLoadFormStart";
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.GET)
	public void uploadForm() {
		System.out.println("UploadController uploadForm GET Start...");
		System.out.println("");
	}
	
	@RequestMapping(value = "uploadForm", method = RequestMethod.POST)
	public String uploadForm(HttpServletRequest request, Model model)
			throws IOException, Exception {
		
		Part image = request.getPart("file1");
		//업로드할 때 쓰기 위해서 file을 InputStream으로 만들어놓는다.
		InputStream inputStream = image.getInputStream();
		
		//파일 확장자 구하기
		String fileName = image.getSubmittedFileName();
		String[] split = fileName.split("\\.");
		String originalName = split[split.length-2];
		//확장자 .을 기준으로 위 아래로 나뉘어진다
		String suffix = split[split.length-1];
		
		System.out.println("fileName-> "+ fileName);
		System.out.println("originalName -> "+ originalName);
		System.out.println("suffix -> "+ suffix);
		
		//Servlet 상속받지 못했을 때 realPath로 불러오는 방법
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("UploadController uploadForm POST Start...");
		//진짜 업로드하는 logic
		String savedName = uploadFile(originalName, inputStream, uploadPath, suffix);
		//Service --> DB CRUD ----> 프로젝트 할 때 직접 연결시켜주기 (여기서는 연습)
		
		log.info("Return savedName : "+ savedName);
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
	}

	private String uploadFile(String originalName, 
							  InputStream inputStream, 
							  String uploadPath, 
							  String suffix) throws FileNotFoundException, IOException {
		
		System.out.println("UploadController uploadFile Start...");
		
		//universally unique identifier (UUID). -> 식별자를 random하게!
		//만일 업로드 파일 명을 랜덤하게 지어줄 수 있다면 안써도 되는 로직
		UUID uid = UUID.randomUUID();
		//requesPath = requestPath + "/resources/image";
		System.out.println("uploadPath-> "+uploadPath);
		
		//Directory 생성
		File fileDirectory = new File(uploadPath);
		//폴더 없으면 만들기 위한 logic
		if (!fileDirectory.exists()) {
			//신규 폴더(Directory) 생성
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : "+uploadPath);
		}
		
		String savedName = uid.toString() + "_" + originalName +"." +suffix;
		log.info("savedName: "+savedName);
		
		//임시파일 생성 (uploadPath가 포함되어있어서 metadata까지 포함되어있음) -> 그냥 배포해도 가능
		File tempFile = new File(uploadPath+savedName);
		
		//-------------------------------------------------------------------
		//Backup File
		File tempFile3 = new File("c:/backup/"+savedName);
		FileOutputStream outputStream3 = new FileOutputStream(tempFile3);
		//-------------------------------------------------------------------
		
		//생성된 임시 파일에 요청으로 넘어온 file의 inputStream 복사
		try (FileOutputStream outputStream = new FileOutputStream(tempFile)){
			int read;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
													//-1은 파일이 끝날 때 까지 계속
				
				// Target File 에 요청으로 넘어온  file의 inputStream 복사
				outputStream.write(bytes, 0, read);
				//backup 파일에 요청으로 넘어온 file의 inputStream 복사
				outputStream3.write(bytes, 0, read);
				
			}
		} finally {
			System.out.println("UpLoad The End");
		}
		//inputStream으로 부른 것은 자동으로 close 되기 때문에!!
		outputStream3.close();
		
		return savedName;
	}
	
	@RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)
	public String uploadFileDelete(HttpServletRequest request, Model model) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		//Backup Folder
		String uploadPath3 = "c:/backup/";
		
		String deleFile = request.getParameter("delFile");	//delFile
		
		System.out.println("UploadController uploadFileDelete GET Start...");
		String deleteFile = uploadPath + deleFile;
		String deleteFile3 = uploadPath3 + deleFile;
		System.out.println("uploadFileDelete deleteFile-> "+deleteFile);
		
		int delResult = upFileDelete(deleteFile);
		int delResult3 = upFileDelete(deleteFile3);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		
		return "uploadResult";
	}

	private int upFileDelete(String deleteFileName) {
		int result = 0;
		log.info("upFileDelete result-> "+deleteFileName);
		File file = new File(deleteFileName);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("파일 삭제 성공");
				result = 1;
			} else {
				System.out.println("파일 삭제 실패");
				result = 0;
			}
		} else {
			System.out.println("삭제할 파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
	
	
	
}
