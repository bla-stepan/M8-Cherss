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
            //если цвет текущего игрока не совпадает с цветом фигуры на данной клетке то ход невозможен
            //(нельзя двигать чужие фигуры)
            // не цветигрока ==   достка [линия 1] [колонка 1]  геттерЦвета
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;
            //если данная фигура может быть сдвинута на эту позицию
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                //если фигура может двигаться, то мы переместили фигуру на конечную клетку
                //конечный адрес стаовится равным стартовому адресу
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
}