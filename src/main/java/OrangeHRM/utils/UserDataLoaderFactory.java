package OrangeHRM.utils;

import OrangeHRM.domain.User;

public class UserDataLoaderFactory {
    public static DataLoader getLoader(String filePath) {
        if (filePath.endsWith(".csv")) {
            return new CsvUserLoader();
        }
//        else if (filePath.endsWith(".json")) {
//            return new JsonDataLoader();
//        }
        throw new IllegalArgumentException("Unsupported file format: " + filePath);
    }
}
