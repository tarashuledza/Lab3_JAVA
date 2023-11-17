package program;

import java.io.*;
import java.util.Map;
import java.util.Objects;

public class FileService {
    public static void writeTextToFile(final String text, final String pathName) throws IOException{
        try {
            FileWriter myWriter = new FileWriter(pathName);
            myWriter.write(text);
            myWriter.close();
        }   catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void writeObjectToFile(final String fileName, final Object productMap) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(productMap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException ioe) {
            throw new IOException(ioe);
        }
    }

    public static Map<Product, Integer> readObjectAsMap(final String fileName) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Map<Product, Integer> productMap = (Map<Product, Integer>) ois.readObject();
            return productMap;
        } catch (IOException ioe) {
            System.out.println("IOE");
            throw new RuntimeException(ioe);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ClassNotFound");
            throw new ClassNotFoundException(cnfe.getMessage());
        }
    }


}
