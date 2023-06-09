package Userupload.user.upload.controllers;

import Userupload.user.upload.domain.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
public class UploadController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String UploadCSVFile(@RequestParam("file")MultipartFile file, Model model){

        //validate file
        if (file.isEmpty()){
            model.addAttribute("message", "Please select a csv file to upload");
            model.addAttribute("status",false);
        }else {
            //parse CSV file to create a list of 'User' objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(User.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                //convert 'CsvToBean' object to list of users
                List<User> users = csvToBean.parse();

                //TODO: save users in DB
                //save users list in model
                model.addAttribute("users",users);
                model.addAttribute("status",true);
            }catch (Exception ex){
                model.addAttribute("message","An error occured while processing the CSV file");
                model.addAttribute("status",false);
            }
        }
        return "file-upload-status";
    }
}
