package com.example.compilertest.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DockerService {

    public String dockerCompile(String projectIdx) {
        String path = "/Users/andaegeun/Desktop/compilerTest/src/main/resources/static/projectList/" + projectIdx + "/src";

        List<String> commandList = new ArrayList<>();
        System.out.println("compile Start");
//        commandList.add("javac " + path);
        commandList.add("javac -cp . -d /Users/andaegeun/Desktop/manifestTest/www/test/class $(find /Users/andaegeun/Desktop/manifestTest/www/test/src -name \"*.java\"); ");
//        commandList.add("jar -cfvm /Users/andaegeun/Desktop/manifestTest/www/test/lib/test.jar /Users/andaegeun/Desktop/manifestTest/www/test/META-INF/Manifest.text /Users/andaegeun/Desktop/manifestTest/www/test/class; ");
//        commandList.add("jar -xvf /Users/andaegeun/Desktop/manifestTest/www/test/lib/test.jar /Users/andaegeun/Desktop/manifestTest/www/test; ");
//        commandList.add("jar -cfvm /Users/andaegeun/Desktop/manifestTest/www/test/lib/test.jar /Users/andaegeun/Desktop/manifestTest/www/test/src/Manifest.txt /Users/andaegeun/Desktop/manifestTest/www/test/class; ");
//        commandList.add("java -jar /Users/andaegeun/Desktop/manifestTest/www/test/lib/test.jar");

        CMD cmd = new CMD();
        String command = cmd.inputCommand(commandList);
        String s = cmd.execCommand(command);
        System.out.println("compile End");
        return s;
    }

    public String apacheCompile() {
        String command = "javac -cp . -d /Users/andaegeun/Desktop/manifestTest/www/test/class $(find ~/Users/andaegeun/Desktop/manifestTest/www/test/src -name \"*.java\")";
//        String[] list1 = {"/Users/andaegeun/Desktop/manifestTest/www/test/src", "-name", "\"*.java\">/Users/andaegeun/Desktop/manifestTest/www/test/list.txt"};


        String[] list2 = {"javac", "-cp", ".", "-d", "/Users/andaegeun/Desktop/manifestTest/www/test/class"};
        String result = "";

        DefaultExecutor executor = new DefaultExecutor();
        try {
            CommandLine commandLine = new CommandLine("find");
//            commandLine.addArguments(list1, false);
            int execute = executor.execute(commandLine);
            System.out.println(commandLine.toString());
            System.out.println(execute);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void terminalTest() {
//        String[] list1 = {"find", "/Users/andaegeun/Desktop/manifestTest/www/test/src", "-name", "*.java"};
        String[] list1 = {"javac", "-cp", ".", "-d", "/Users/andaegeun/Desktop/manifestTest/www/test/class", "$(find /Users/andaegeun/Desktop/manifestTest/www/test/src -name *.java)"};
        String[] list2 = {"/bin/sh", "-c", "javac -cp . -d /Users/andaegeun/Desktop/manifestTest/www/test/class $(find /Users/andaegeun/Desktop/manifestTest/www/test/src -name *.java)"};
        StringBuffer output = new StringBuffer();

        try{
            Process p = Runtime.getRuntime().exec(list2);
            List<String> result = IOUtils.readLines(p.getInputStream());
            for(String line : result) {
                System.out.println(line);
                output.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CMD {
    private StringBuffer buffer;
    private Process process;
    private BufferedReader bufferedReader;
    private StringBuffer stringBuffer;

    public String inputCommand(List<String> cmd) {
        buffer = new StringBuffer();

        for(String c : cmd) {
            buffer.append(c);
        }

        return buffer.toString();
    }

    public String execCommand(String cmd) {
        try {
            System.out.println(cmd);
            process = Runtime.getRuntime().exec(cmd);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            stringBuffer = new StringBuffer();

            while((line = bufferedReader.readLine()) != null) {

                stringBuffer.append(line);
                stringBuffer.append("\n");
            }

            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
