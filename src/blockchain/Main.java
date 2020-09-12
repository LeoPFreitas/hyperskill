package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter how many zeros the hash must start with: ");
        int n = sc.nextInt();

        BlockChain blockChain = new BlockChain();

        try {
            blockChain = (BlockChain) SerializationUtils.deserialize("bc", blockChain);
        } catch (ClassNotFoundException ignored) {
        }

        for (int i = 0; i < 5; i++) {
            blockChain.generateBlock("Some data here!", n);
        }


        for (int i = 0; i < 5; i++) {
            System.out.println(blockChain.getBlockchain().get(i));
        }

        SerializationUtils.serialize(blockChain);

    }
}
