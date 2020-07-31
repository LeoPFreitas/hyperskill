package encyptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Task {
    private String mode = "";
    private String algorithm = "";
    private int key = 0;
    private String inFileName = "";
    private String outFileName = "";
    private String inFileData = "";
    private String inData = "";
    private String input = "";

    public void runTask(String[] args) {
        parseArgs(args);
        processInputData();
        processEncodeDecode();
    }


    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-data":
                    inData = args[i + 1];
                    break;
                case "-alg":
                    algorithm = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-in":
                    inFileName = args[i + 1];
                    break;
                case "-out":
                    outFileName = args[i + 1];
                    break;
            }
        }
    }

    private void processInputData() {
        if (!inFileName.isBlank()) {
            try (Scanner scanner = new Scanner(new File(this.inFileName))) {
                this.inFileData = scanner.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }

        // prioritize data over inFileData
        this.input = !("").equals(this.inData) ? inData : inFileData;

    }

    private void processEncodeDecode() {

        EncoderStrategy encoderStrategy = new EncoderStrategy();

        if ("unicode".equals(algorithm)) {
            encoderStrategy.setStrategy(new UnicodeStrategy());
        } else if ("shift".equals(algorithm)) {
            encoderStrategy.setStrategy(new ShiftStrategy());
        } else {
            throw new IllegalStateException("Unexpected value: " + algorithm);
        }

        String output = "";
        if ("enc".equals(mode)) {
            output = encoderStrategy.encode(input, key);
        } else {
            output = encoderStrategy.decode(input, key);
        }

        if (outFileName.isBlank()) {
            System.out.println(output);
        } else {
            try (FileWriter fileWriter = new FileWriter(new File(outFileName))) {
                fileWriter.write(output);
                fileWriter.flush();
            } catch (IOException e) {
                System.out.println("Error writing result");
            }
        }


    }


}