public class ChessBoard {
    /*Задача 2
    Вам предоставлен класс ChessBoard (этот класс сдавайте в каждую последующую задачу, как и ChessPiece).
    В нём есть:
    Поле board, которое представляет собой двумерный массив объектов шахматных фигур.
    Переменная nowPlayer, которая показывает, чей сейчас ход.
    Конструктор и метод nowPlayerColor (на самом деле хорошо бы сделать переменную nowPlayer приватной, но для удобства пусть будет, как есть).
    Метод moveToPosition, который передвигает фигуры, можете поподробнее посмотреть, что же там происходит.
    Метод printBoard, который просто красиво печатает шахматное поле в консоль.
    Этот класс понадобится вам позже, но осуществлять проверку написанного вами кода легче через него.
    Теперь напишите класс Horse (конь). Этот класс должен быть наследником класса ChessPiece, который вы сделали в предыдущей задаче.
    В классе Horse:
    Реализуйте конструктор, который будет принимать лишь цвет фигуры.
    Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
    Реализуйте метод canMoveToPosition() так, чтобы конь не мог выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0) и мог ходить только буквой «Г». Также фигура не может сходить в точку, в которой она сейчас находится. Если конь может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
    Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае конь — это  H.
    Также вы можете добавить и свои методы для удобства.
     */

    public ChessPiece[][] board = new ChessPiece[8][8]; //двумерный массив объектов шахматных фигур.
    String nowPlayer;//чей сейчас ход

    public ChessBoard(String nowPlayer) {//конструктор чей сайчас ход
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {//геттер возвращающий ход какого игрока сейчас идет
        return this.nowPlayer;
    }


    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {//метод принмает стартовые координаты фигуры и координаты хода фигуры
        //проверяем существует ли такая клетка
        if (checkPos(startLine) && checkPos(startColumn)) {
            //если цвет текущего игрока не совпадает с цветом фигуры на данной клетке то ход невозможен (нельзя двигать чужие фигуры)
            // не цвет игрока ==   достка [линия 1] [колонка 1]  геттерЦвета
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
            //если данная фигура может быть сдвинута на эту позицию
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                /*Задача 7
                Теперь реализуем рокировку.
                Рокировка возможна, если ни король, ни ладья ни разу не двигались, и между ними все поля свободны.
                Помните, в самом начале мы создали поле check? Теперь оно поможет нам отследить, двигались ли фигуры или нет.
                Теперь модернизируйте метод moveToPosition(класс ChessBoard) так, чтобы после первого хода ладей и королей
                их переменные check становились false, так мы сможем отслеживать возможность рокировки.
                Метод castling0(), находящийся в классе ChessBoard, отвечает за рокировку по 0 линии (в Codeboard этот метод уже добавлен):*/
                if (board[startLine][startColumn].getSymbol() == "K" || board[startLine][startColumn].getSymbol() == "R") {
                    board[startLine][startColumn].check = false;
                }//снимаем метку первого хода
                //если фигура может двигаться, то мы переместили фигуру на конечную клетку
                //конечный адрес становится равным стартовому адресу
                board[endLine][endColumn] = board[startLine][startColumn];
                //установите значение null в старый адрес (удаляем фигуры со старой клетки)
                board[startLine][startColumn] = null;
                //меняем ход игрока (если был белый станет черным и наоборот)
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;//после выполнения всех условий возвращаем истина
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console красиво печатает шахматное поле в консоль.
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {//проверка позиции
        return pos >= 0 && pos <= 7;//должна быть не менее 0 и не более 7 т.е. от 1-8 (шахматная доска 8х8)
    }
    /* Задание 7
    В классе ChessBoard создайте метод boolean castling7() (аналогично castling0).
    Если во время хода белого игрока вызывается метод castling7(), то надо проверить,
    возможна ли рокировка белого короля с ладьей, стоящей в 7 столбце, и если возможна, то совершить рокировку.
    Если рокировку пытается совершить игрок, играющий черными фигурами, то наши действия аналогичны.
     */
    //РЕКИРОВКА
    public boolean castling0() {
        if (nowPlayer.equals("White")) {//если игрок белый то
            if (board[0][0] == null || board[0][4] == null) return false;//если клетка 00 и 04 пустые то ложь
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // проверяем наличие фигур лодьи и короля
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {//и между ними клетки пустые то
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&//если фигуры одного цвета и
                        board[0][0].check && board[0][4].check &&//и они не ходили
                        !new King("White").isUnderAttack(this, 0, 2)) { //и белый король не попадает под атаку фигур противника то
                    board[0][4] = null;
                    board[0][2] = new King("White");// move King
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }
    public boolean castling7() {
        if (nowPlayer.equals("White")) {//если игрок белый то
            if (board[0][7] == null || board[0][4] == null) return false;//если клетка 07 и 04 пустые то ложь
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // проверяем наличие фигур лодьи и короля
                    board[0][5] == null && board[0][6] == null) {//и между ними клетки пустые то
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&//если фигуры одного цвета и
                        board[0][7].check && board[0][4].check &&//и они не ходили
                        !new King("White").isUnderAttack(this, 0, 6)) { //и белый король не попадает под атаку фигур противника то
                    board[0][4] = null;
                    board[0][6] = new King("White");// move King
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // move Rook
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][5] == null && board[7][6] == null) {// never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // move King
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // move Rook
                    board[7][5].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }
}