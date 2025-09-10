package org.ohgiraffers.fileupload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class FileUploadController {

    /* 설명. application.yml에서 파일업로드 공통 경로를 불러와 변수에 대입 */
    @Value("${filepath}")
    private String filepath;

    @PostMapping("single-file")
    public String singleFile(@RequestParam MultipartFile singleFile,
                             @RequestParam String singleFileDescription,
                             RedirectAttributes rttr) {

        /* 설명. 1. 저장할 파일의 경로설정 후 파일 저장 */
        /* 설명. 2. 업로드되는 파일의 이름을 리네임(feat. 날짜/시간, Reandom, UUID) */
        /* 설명. 3. DB로 보낼 데이터 만들기(Map<String,String>,List<Map<String,String>> */
        System.out.println("singleFile = " + singleFile);
        System.out.println("singleFileDescription = " + singleFileDescription);

        /* 설명. 사용자가 넘긴 파일의 원본이름을 확인하고 rename해보기 */
        String originFileName = singleFile.getOriginalFilename();

        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        System.out.println("ext = " + ext);

        String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
        System.out.println("saveName = " + saveName);

        /* 설명. 우리가 지정한 경로로 파일을 저장 */
        try {
            singleFile.transferTo(new File(filepath + "/img/single/" + saveName));

            /* 설명. DB로 보낼 데이터를 Map형태로 가공 처리 */
            Map<String, String> file = new HashMap<>();
            file.put("originFileName", originFileName);
            file.put("saveName", saveName);
            file.put("filePath", "/img/single/");
            file.put("singleFileDescription", singleFileDescription);

            /* 설명. 이후 service 계층을 통해 DB에 사용자가 업로드한 나의 파일에 대한 내용을 저정하고 옴 */
            rttr.addFlashAttribute("message", originFileName + " 파일 업로드 성공!");
            rttr.addFlashAttribute("img", "/img/single/" + saveName);
            rttr.addFlashAttribute("singleFileDescription", singleFileDescription);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/result";
    }

    @GetMapping("result")
    public void result() {
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles,
                                  @RequestParam String multiFileDescription,
                                  RedirectAttributes rttr) {

        /* 설명. DB에 보낼 값을 담기 위한 컬렉션 */
        List<Map<String, String>> files = new ArrayList<>();

        /* 설명. 화면에서 각 파일마다 img 태그의 src 속성으로 적용하기 위한 문자열을 담은 컬렉션 */
        List<String> imgSrcs = new ArrayList<>();

        try {
            for (int i = 0; i < multiFiles.size(); i++) {
                /* 설명. 각 파일마다 리네임 */
                String originFileName = multiFiles.get(i).getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String saveName = UUID.randomUUID().toString().replace("-", "") + ext;

                /* 설명. 각 파일을 저장 경로에 저장 */
                multiFiles.get(i).transferTo(new File(filepath + "/img/multi/" + saveName));

                /* 설명. DB에 보낼 값 설정(각 파일마다 Map<String,String>에 저장) */
                Map<String, String> file = new HashMap<>();
                file.put("originFileName", originFileName);
                file.put("saveName", saveName);
                file.put("filePath", "/img/multi/");
                file.put("multiFileDescription", multiFileDescription);

                files.add(file);
                imgSrcs.add("/img/multi/" + saveName);
            }

            /* 설명. DB에 multi 파일 업로드만큼의 insert 성공 후 */
            rttr.addFlashAttribute("message", "다중 파일 업로드 성공!");
            rttr.addFlashAttribute("imgs", imgSrcs);
            rttr.addFlashAttribute("multiFileDescription", multiFileDescription);

        } catch (IOException e) {

            /* 설명. 부분적인 파일 저장 실패와 관련되어 후처리 */
            for (int i = 0; i < files.size(); i++) {
                Map<String, String> file = files.get(i);
                new File(filepath + "/img/multi/" + file.get("saveName")).delete();
            }

            rttr.addFlashAttribute("message", "다중 파일 업로드 실패!");
        }


        return "redirect:/result";
    }
}
