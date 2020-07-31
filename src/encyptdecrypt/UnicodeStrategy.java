package encyptdecrypt;

public class UnicodeStrategy implements IEncoderStrategy {
    @Override
    public String encode(String input, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append((char) (c + key));
        }
        return result.toString();
    }

    @Override
    public String decode(String input, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append((char) (c - key));
        }
        return result.toString();
    }
}
