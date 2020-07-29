package tictactoe;

import java.util.Scanner;

enum GameState {
    GAME_NOT_FINISHED,
    DRAW,
    X_WINS,
    O_WINS
}

enum PlayerTurn {
    X,
    O
}

public class Main {
    public static void main(String[] args) {

        // initial game and print
        String[] game = init();

        // set enums
        PlayerTurn turn = PlayerTurn.X;
        GameState state = GameState.GAME_NOT_FINISHED;

        // main loop
        boolean finished = false;
        while (!finished) {
            switch (state) {
                case GAME_NOT_FINISHED:
                    resolveTurn(game, turn);
                    state = resolveGame(game);
                    turn = alterTurn(turn);
                    break;
                case DRAW:
                    System.out.println("Draw");
                    finished = true;
                    break;
                case X_WINS:
                    System.out.println("X wins");
                    finished = true;
                    break;
                case O_WINS:
                    System.out.println("O wins");
                    finished = true;
                    break;
            }
        }

    }


    public static void resolveTurn(String[] game, PlayerTurn turn) {
        Scanner scanner = new Scanner(System.in);

        boolean cont = true;
        int first;
        int second;

        while (cont) {
            System.out.println("Enter the coordinates: ");
            String input = scanner.nextLine();

            try {
                String [] pieces = input.split(" ");
                first = Integer.parseInt(pieces[0]);
                second = Integer.parseInt(pieces[1]);

            }catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            // check size
            if (first < 1 || first > 3 || second < 1 || second > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                int l = Math.abs(second - 3);
                int c = first - 1;

                // check occupancy
                if (game[(l * 3) + c].equals("_") || game[(l * 3) + c].equals(" ")) {
                    if (turn.equals(PlayerTurn.X)) {
                        game[(l * 3) + c] = "X";
                    } else {
                        game[(l * 3) + c] = "O";
                    }
                    cont = false;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        }
        printGame(game);

    }

    public static String[] init() {
        String[] game = {" ", " " , " ", " ", " ", " ", " ", " ", " " };

        printGame(game);

        return game;
    }

    public static void printGame(String[] gameArray) {
        System.out.println("---------");
        System.out.println(String.format("| %s %s %s |\n| %s %s %s |\n| %s %s %s |",
                gameArray[0],
                gameArray[1],
                gameArray[2],
                gameArray[3],
                gameArray[4],
                gameArray[5],
                gameArray[6],
                gameArray[7],
                gameArray[8]));
        System.out.println("---------");
    }

    public static PlayerTurn alterTurn(PlayerTurn turn) {
        if (turn.equals(PlayerTurn.X)) {
            turn = PlayerTurn.O;
        } else {
            turn = PlayerTurn.X;
        }

        return turn;
    }

    public static GameState resolveGame(String[] game) {

        boolean xWin = false;
        boolean oWin = false;
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < game.length; i++) {

            if (game[i].equals("X")) {
                countX += 1;
            } else if (game[i].equals("O")) {
                countO += 1;
            }

            if (i % 3 == 0 && game[i].equals(game[i + 1]) && game[i + 1].equals(game[i + 2]) && !game[i].contains(" ") && !game[i].contains(" ")) {
                // row win
                if (game[i].equals("X")) {
                    xWin = true;
                } else {
                    oWin = true;
                }
            } else if (i < 3 && game[i].equals(game[i + 3]) && game[i + 3].equals(game[i + 6]) && !game[i].contains(" ") && !game[i].contains(" ")) {
                // column win
                if (game[i].equals("X")) {
                    xWin = true;
                } else {
                    oWin = true;
                }
            } else if (i == 0 && game[i].equals(game[4]) && game[4].equals(game[8]) && !game[i].contains(" ") && !game[i].contains(" ")) {
                // diagonal principal
                if (game[i].equals("X")) {
                    xWin = true;

                } else {
                    oWin = true;
                }
            } else if (i == 2 && game[i].equals(game[4]) && game[4].equals(game[6]) && !game[i].contains(" ") && !game[i].contains(" ")) {
                // diagonal secondary
                if (game[i].equals("X")) {
                    xWin = true;

                } else {
                    oWin = true;
                }
            }

        }

        if (countX == 5 || countO == 5 && !xWin && !oWin) {
            return GameState.DRAW;
        }

        // no winner
        if (!xWin && !oWin) {
            return GameState.GAME_NOT_FINISHED;
        }

        if (xWin) {
            return GameState.X_WINS;
        } else {
            return GameState.O_WINS;
        }
    }

}

