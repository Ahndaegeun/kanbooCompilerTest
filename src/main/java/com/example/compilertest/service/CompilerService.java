package com.example.compilertest.service;

import com.example.compilertest.entity.CompilerContent;
import com.example.compilertest.repository.CompilerRepository;
import com.example.compilertest.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompilerService {

    private final DockerService dockerService;
    private final CompilerRepository compilerRepository;
    private final ProjectRepository projectRepository;
    private final CopmilerContentRepository copmilerContentRepository;

    private static final String rootPath = "/Users/andaegeun/Desktop/compilerTest/src/main/resources/static/projectList/";

    public String runCompile(String projectIdx) {
        dockerService.terminalTest();
        return "";
    }

    public boolean createFileOrDir(Map<String, Object> map) {
        String projectName = map.get("project") + "/";
        String projectPath = (map.get("path") + "") + "/";
        String target = map.get("name") + "";

        if(map.get("type").equals("dir")) {
            return addDir(rootPath + projectName + projectPath + target);
        }

        return addFile(rootPath + projectName, projectPath, target);
    }

    public boolean saveFile(Map<String, Object> map) {
        String path = map.get("filePath") + "";
        String fileName = map.get("fileName") + ".java";
        String content = map.get("fileDetail") + "";
        String projectName = map.get("project") + "/";

        File file = new File(rootPath + projectName + path, fileName);
        OutputStream os = null;

        try {
            os = new FileOutputStream(file);

            byte[] c = content.getBytes();

            os.write(c);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(os != null) {
                try {os.close();} catch (Exception e) {}
            }
        }
        return true;
    }

    private boolean addDir(String path) {
        return new File(path).mkdirs();
    }

    private boolean addFile(String rootPath, String parentPath, String fileName) {
        if(!fileName.contains(".java")) {
            fileName += ".java";
        }

        System.out.println("file ==> " + rootPath + parentPath + fileName);
        int data = 0;
        File file = new File(rootPath + parentPath + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            while((data = System.in.read()) != -1) {
                fos.write(data);
            }
        } catch(Exception exception) {
            return false;
        } finally {
            if(fos != null) {try { fos.close(); } catch (Exception e) {}}
        }
        return true;
    }

    public boolean delDir(String path) {
        return loopDelDir(rootPath + path);
    }

    private boolean loopDelDir(String path) {
        boolean result = false;
        File folder = new File(path);
        System.out.println(path);
        if(folder.exists()) {
            File[] fileList = folder.listFiles();

            for(File file : fileList) {
                if(file.isFile()) {
                    file.delete();
                } else {
                    System.out.println(file.getPath());
                    loopDelDir(file.getPath());
                }
                file.delete();
            }
            result = folder.delete();
        }

        return result;
    }

    public boolean delFile(Map<String, Object> map) {

        File file = new File(rootPath + map.get("delPath") + map.get("delName") + ".java");

        if(file.exists()) {
            if(file.delete()) {
                return true;
            }
        }

        return false;
    }

}
