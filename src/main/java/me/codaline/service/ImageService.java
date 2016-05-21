package me.codaline.service;

import me.codaline.model.CrunchifyFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    PostService service;
    public String[] getImages(HttpServletRequest request) {
        ServletContext context = request.getSession().getServletContext();

        File[] files = null;
        java.awt.List imageList = new java.awt.List();


        File myFolder2 = new File(context.getRealPath("") + File.separator + "resources\\images\\");
        files = myFolder2.listFiles();

        for (File file : files) {
            String s = file.getPath();
            s = s.substring(s.indexOf("resources") - 1, s.length());
            imageList.add(s);
        }
        return imageList.getItems();
    }

    public void saveImages(HttpServletRequest request, CrunchifyFileUpload uploadForm) throws IllegalStateException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String date = new Date(System.currentTimeMillis()).toString();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            ServletContext context = request.getSession().getServletContext();
            String saveDirectory = context.getRealPath("") + File.separator + "resources\\images\\";
            // String saveDirectory = "E:\\blog\\v_mukha-springtemplate-051311f66d8a\\blog-twiter\\src\\main\\webapp\\resources\\images";

            List<MultipartFile> crunchifyFiles = uploadForm.getFiles();

            List<String> fileNames = new ArrayList<String>();

            if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
                for (MultipartFile multipartFile : crunchifyFiles) {

                    String fileName = multipartFile.getOriginalFilename();
                    service.setAction(userDetail.getUsername(), "upload file with name:" +fileName, date);
                    if (!"".equalsIgnoreCase(fileName)) {
                        // Handle file content - multipartFile.getInputStream()
                        multipartFile
                                .transferTo(new File(saveDirectory + fileName));
                        fileNames.add(fileName);
                    }
                }
            }
        }

    }
}
