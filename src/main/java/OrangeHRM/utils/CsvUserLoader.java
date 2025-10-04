package OrangeHRM.utils;

import OrangeHRM.domain.User;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CsvUserLoader implements DataLoader<User>{
    @Override
    public List<User> load(String filePath) {
        List<User> users = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (!lines.isEmpty()) {
                lines.removeFirst(); // remove header
                for (String line : lines) {
                    String[] parts = line.split(",");
                    users.add(new User(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CSV: " + filePath, e);
        }
        return users;
    }
}
