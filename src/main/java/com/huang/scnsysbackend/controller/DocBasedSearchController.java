package com.huang.scnsysbackend.controller;

import com.huang.scnsysbackend.pojo.RespBean;
import com.huang.scnsysbackend.service.ExtractEntities;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.*;


@RestController
@RequestMapping("/doc")
public class DocBasedSearchController {

    @Autowired
    private ExtractEntities extractEntities;

    @PostMapping("/upload")
    public RespBean uploadSingleDocFile(@RequestParam(value = "singleDoc") MultipartFile file) throws IOException, OpenXML4JException, XmlException {
        String fileName = file.getOriginalFilename();
        String content = "";
        if (fileName.endsWith(".txt")) {
            InputStreamReader reader = new InputStreamReader(file.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String readStr = null;
            while ((readStr = bufferedReader.readLine()) != null) {
                content += readStr;
            }
            reader.close();
        } else if (fileName.endsWith(".pdf")) {
            File _file = new File(fileName);
            OutputStream out = null;
            try{
                out = new FileOutputStream(_file);
                byte[] ss = file.getBytes();
                for(int i = 0; i < ss.length; i++){
                    out.write(ss[i]);
                }
            }catch(IOException e){
                e.printStackTrace();
            }finally {
                if (out != null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            PDDocument document = PDDocument.load(_file);
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(false);
            content = stripper.getText(document).trim();
            document.close();
            File f = new File(_file.toURI());
            f.delete();
        } else if (fileName.endsWith(".doc")) {
            WordExtractor extractor = new WordExtractor(file.getInputStream());
            content = extractor.getText().trim();
        } else if (fileName.endsWith(".docx")) {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            content = extractor.getText().trim();
        }
        return RespBean.success("提取命名实体完成!", extractEntities.getEntities(content));
    }

}
