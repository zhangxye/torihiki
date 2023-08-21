package com.tsrs.webedi.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.service.ISysFileMgrService;

@RestController
@RequestMapping("/file")
public class FileUploadController extends BaseController {
	
	@Resource
	ISysFileMgrService sysFileMgrService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {

            Integer fileId = sysFileMgrService.uploadFile(file.getInputStream(), file.getOriginalFilename());
            Map<String,Integer> result = new HashMap<String,Integer>();
            result.put("fileId", fileId);
			return new JsonResult(RETURN_CODE_200, result);
			

            } catch (IOException e) {
                e.printStackTrace();
                return new JsonResult(RETURN_CODE_500, e.getMessage());
            }
			
        } else {
        	return new JsonResult(RETURN_CODE_500, "アップロードファイルが空です");
        }
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public  void download(@RequestParam("file_id") Integer fileId, HttpServletResponse res) {
    	try {
    		sysFileMgrService.downloadFile(fileId, res);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}
