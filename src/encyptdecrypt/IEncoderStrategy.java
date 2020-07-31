package encyptdecrypt;

public interface IEncoderStrategy {
    String encode(String input, int key);
    String decode(String input, int key);
}
