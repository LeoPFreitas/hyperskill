# Project: Encryption-Decryption

Command line application to encrypt/decrypt strings using various encryption strategies.

## Usage

**Example 1**

Get data from the file road_to_treasure.txt, encrypt the data with the key 5, create a file called protected.txt and write ciphertext to it.

```
java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode
```

**Example 2**

Input

```
java Main -mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode
```

Output

```
\jqhtrj%yt%m~ujwxpnqq&
```

## Params

| Arg   | Values           | Comment                                                                                                     |
| ----- | ---------------- | ----------------------------------------------------------------------------------------------------------- |
| -mode | enc<br>dec       | Required<br>Choose to encode or decode input                                                                |
| -data | `<String>`       | Requires at least one (-data OR -in)<br>input text<br>If both -data and -in exist, -data is prioritised     |
| -alg  | shift<br>unicode | Required<br>Choose algorithm to use                                                                         |
| -key  | `<Integer>`      | Not Required<br>default is 0 (no change to input)                                                           |
| -in   | `<String>`       | Requires at least one (-data OR -in)<br>input filepath<br>If both -data and -in exist, -data is prioritised |
| -out  | `<String>`       | Not Required<br>output filepath<br>if omitted outputs to console window                                     |

## Stages

### Stage #1 Encrypted

Learn to input the data and encrypt it, replacing the letters.

### Stage #2 Knowledge is key

Learn to encrypt messages with a numeric key, using the Caesar cypher.

### Stage #3 Decrypted!

Encrypting a message is only half of the deal: now learn to decrypt it as well, using a standard Unicode table.

### Stage #4 I Command you

Practice working with the command line, using it for passing the mode, the key and the string.

### Stage #5 X-files

Work with files; learn to store your data in a file.

### Stage #6 Choices, Choices

Add the possibility to choose a specific encryption algorithm: Ceasar or Unicode.

## Info

Check [JetBrains Academy](https://hyperskill.org/projects/46) for more details about the project
