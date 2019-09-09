package pl.pawelec.newsbrowser.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class FileUtils {

    public static String readFile(String fileName) throws Exception {
        String fileContent = null;
        if (Objects.nonNull(fileName) && !fileName.isBlank()) {
            Path path = Path.of(Thread.currentThread().getContextClassLoader().getResource(fileName).toURI());
            fileContent = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        }
        return Optional.ofNullable(fileContent).orElse("");
    }
}
