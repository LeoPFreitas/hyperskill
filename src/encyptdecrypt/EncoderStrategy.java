package encyptdecrypt;

public class EncoderStrategy {
    IEncoderStrategy strategy;

    public void setStrategy(IEncoderStrategy strategy) {
        this.strategy = strategy;
    }

    public String encode(String input,  int key) {
        return this.strategy.encode(input, key);
    }

    public String decode(String input, int key) {
        return this.strategy.decode(input, key);
    }
}
