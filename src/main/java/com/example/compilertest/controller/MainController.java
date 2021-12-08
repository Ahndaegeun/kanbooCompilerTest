package com.example.compilertest.controller;

import com.example.compilertest.entity.CompilerContent;
import com.example.compilertest.service.CompilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CompilerService compilerService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @ResponseBody
    @PostMapping("/compile")
    public String runCompile(String projectIdx) {
        return compilerService.runCompile(projectIdx);
    }

    @PostMapping("/addDir")
    @ResponseBody
    public boolean addFileOrDir(@RequestParam Map<String, Object> map) {
        return compilerService.createFileOrDir(map);
    }

    @PostMapping("/saveFile")
    @ResponseBody
    public boolean saveFile(@RequestParam Map<String, Object> map) {
        return compilerService.saveFile(map);
    }

    @PostMapping("/delFile")
    @ResponseBody
    public boolean delFile(@RequestParam Map<String, Object> map) {
        return compilerService.delFile(map);
    }

    @PostMapping("/delDir")
    @ResponseBody
    public boolean delDir(@RequestParam Map<String, Object> map) {
        return compilerService.delDir(map.get("delDirPath") + "");
    }

}
