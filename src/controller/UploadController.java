package controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.UploadedImageFile;

@Controller
public class UploadController {

	@RequestMapping("/uploadImage")
	public ModelAndView upload(HttpServletRequest request,UploadedImageFile file)throws Exception{
	
		//生成随机文件名   避免重名
		String name=RandomStringUtils.randomNumeric(10);
		String newFileName=name+".jpg";
		
		File newFile=new File(request.getServletContext().getRealPath("/image"),newFileName);
		newFile.getParentFile().mkdirs();
		file.getImage().transferTo(newFile);//复制文件
		
		ModelAndView mav=new ModelAndView("showUploadedImage");
		mav.addObject("imageName", newFileName);
		return mav;
	}
}
