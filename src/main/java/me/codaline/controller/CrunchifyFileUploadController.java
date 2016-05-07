package me.codaline.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.codaline.model.CrunchifyFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CrunchifyFileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String crunchifyDisplayForm() {
        return "uploadfile";
    }

    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    public String crunchifySave(
            @ModelAttribute("uploadForm") CrunchifyFileUpload uploadForm,
            Model map, HttpServletRequest request) throws IllegalStateException, IOException {

            ServletContext context = request.getSession().getServletContext();
        String saveDirectory = context.getRealPath("") + File.separator + "resources\\images\\";

        List<MultipartFile> crunchifyFiles = uploadForm.getFiles();

        List<String> fileNames = new ArrayList<String>();

        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
            for (MultipartFile multipartFile : crunchifyFiles) {

                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                    // Handle file content - multipartFile.getInputStream()
                    multipartFile
                            .transferTo(new File(saveDirectory + fileName));
                    fileNames.add(fileName);
                }
            }
        }

        map.addAttribute("files", fileNames);
        return "index2";
    }
}