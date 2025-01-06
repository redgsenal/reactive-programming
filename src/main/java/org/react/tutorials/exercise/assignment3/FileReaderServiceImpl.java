package org.react.tutorials.exercise.assignment3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.util.Scanner;

public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> new Scanner(path.toFile()),
                (scanner, sink) -> {
                    if (scanner.hasNextLine()) {
                        log.info("reading file content...");
                        String line = scanner.nextLine();
                        sink.next(line);
                        return scanner;
                    }
                    sink.complete();
                    log.info("read file complete!");
                    return scanner;
                },
                scanner -> {
                    try {
                        scanner.close();
                        log.info("reader file scanner closed");
                    } catch (Exception e) {
                        log.warn("error reading file: {} ", path.getFileName());
                    }
                });
    }
}