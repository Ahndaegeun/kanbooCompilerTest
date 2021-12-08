package com.example.compilertest.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class DockerService {

    public String dockerCompile(String projectIdx) {
        return null;
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
