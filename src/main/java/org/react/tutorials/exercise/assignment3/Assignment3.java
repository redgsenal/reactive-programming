package org.react.tutorials.exercise.assignment3;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Assignment3 {

    private static final Logger log = LoggerFactory.getLogger(Assignment3.class);

    public static void main(String[] args) {
            FileReaderServiceImpl reader = new FileReaderServiceImpl();
            Path path = Path.of("src/main/resources/sample.txt");
            log.info("reading contents from file: {}", path.toAbsolutePath());
            reader.read(path)
                    .subscribe(Util.subscriber());
    }
}