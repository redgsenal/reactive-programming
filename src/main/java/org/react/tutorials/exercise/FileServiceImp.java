package org.react.tutorials.exercise;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileServiceImp implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImp.class);

    @Override
    public Mono<String> read(String fileName) {
        log.info("reading from file: {}", fileName);
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        log.info("writing to {} ", fileName);
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    @Override
    public Mono<Void> delete(String fileName) {
        log.info("deleting file: {}", fileName);
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    private String readFile(String fileName) {
        String result = StringUtils.EMPTY;
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            StringBuilder sb = new StringBuilder();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                sb.append(data);
                System.out.println(data);
            }
            myReader.close();
            result = sb.toString();
        } catch (FileNotFoundException e) {
            log.error("file not found: {}", fileName);
            log.error(e.getMessage());
        }
        return result;
    }

    private void writeFile(String fileName, String content) {
        String result = StringUtils.EMPTY;
        try {
            Path filePath = Path.of(fileName);
            Files.writeString(filePath, content);
            log.info("file updated to {}", filePath.toAbsolutePath());
            result = "file write success!";
        } catch (IOException e) {
            // Handling any I/O exceptions
            log.error(e.getMessage());
            log.error("error writing to file: {}", fileName);
            result = "error writing to file!";
        }
        log.info(result);
    }

    private void deleteFile(String fileName) {
        File fileObj = new File(fileName);
        String path = fileObj.getAbsolutePath();
        String result = (fileObj.delete()) ?
                "File deleted: {} " : "File not deleted: {}";
        log.info(result, path);
    }
}
