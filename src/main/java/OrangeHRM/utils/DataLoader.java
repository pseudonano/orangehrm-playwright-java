package OrangeHRM.utils;

import java.util.List;

public interface DataLoader<T> {
    List<T> load(String filePath);
}
