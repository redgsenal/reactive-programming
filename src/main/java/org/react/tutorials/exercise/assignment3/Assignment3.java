package org.react.tutorials.exercise.assignment3;

import org.react.tutorials.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.FileSystems;

public class Assignment3 {

    private static final Logger log = LoggerFactory.getLogger(Assignment3.class);

    public static void main(String[] args) {
            FileReaderServiceImpl reader = new FileReaderServiceImpl();
            log.info("{}", FileSystems.getDefault().getPath("").toAbsolutePath());
            reader.read(FileSystems.getDefault().getPath("X:/react/programming-playground/src/main/java/org/react/tutorials/exercise/assignment3/sample.txt"))
                    .subscribe(Util.subscriber());
    }
}