package blockchain;

import java.io.*;

public class SerializationUtils {

    public static void serialize(Object obj) {
        try {
            File f = new File("src/blockchain/bc");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // method for serialization of objects
            oos.writeObject(obj);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserialize(String fileName, Object obj) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("src/blockchain/" + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            obj = ois.readObject();
            ois.close();
        } catch (IOException ignored) {
        }

        return obj;
    }


}
