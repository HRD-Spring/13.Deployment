package com.demo.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.event.IIOReadProgressListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.registery.RegisteryDAO;
import com.demo.pojo.User;

@Controller
public class SignUpController {

	int BUFFER_LENGTH = 4096;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView loadSignup() {
		ModelAndView modelAndView = new ModelAndView("signup");
		return modelAndView;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView doSignUpProcess(HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("signup");

		String message = "";

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				String username = data.get(0).getString();
				String password = data.get(1).getString();
				String repassword = data.get(2).getString();
				String gender = data.get(3).getString();
				String vehicle = data.get(4).getString();
				String country = data.get(5).getString();

				String image = new File(data.get(6).getName()).getName();

				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setGender(gender);
				user.setCountry(country);
				user.setVehicle(vehicle);
				user.setImage(image);

				if (password.equals(repassword)) {

					// message = RegisteryDAO.userDAO.doSignUp(username,
					// repassword, gender, vehicle, country, image);

					message = RegisteryDAO.userDAO.doHibernateSigup(user);

					String uploadDir = System.getenv("OPEN_DATA_DIR");
					InputStream iStream = data.get(6).getInputStream();
					FileOutputStream oStream = new FileOutputStream(uploadDir + image);
					byte[] bytes = new byte[BUFFER_LENGTH];
					int read = 0;
					while ((read = iStream.read(bytes, 0, BUFFER_LENGTH)) != -1) {
						oStream.write(bytes, 0, read);

					}
					oStream.flush();
					oStream.close();
					iStream.close();
				} else {
					message = "Password does not match.. please try again";
				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				message = "Please try again...";
			}
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/dynamic/{message}", method = RequestMethod.GET)
	public ModelAndView dynamicDemo(@PathVariable("message") String message) {
		ModelAndView modelAndView = new ModelAndView("signup");

		return modelAndView;
	}

	@RequestMapping(value = "/getImage/{imageName}", method = RequestMethod.GET)
	public void getImage(@PathVariable("imageName") String imageName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			String url = request.getRequestURI();
			File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + url.replaceAll("/OpenShiftApp/getImage/", "/"));
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = response.getOutputStream();

			response.setContentLength((int) file.length());
			response.setContentType(new MimetypesFileTypeMap().getContentType(file));

			int read = 0;
			byte[] bytes=new byte[BUFFER_LENGTH];
			while ((read=inputStream.read(bytes, 0, BUFFER_LENGTH))!=-1) {
				outputStream.write(bytes,0,read);
				
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			response.getOutputStream().print("Error "+e.getMessage());
		}
	}
}
