public class Pawn extends ChessPiece {
    /*Задача 3
    Создайте класс Pawn (пешка), который так же, как и Horse, должен быть наследником класса ChessPiece.
    Реализуйте в классе Pawn, всё тоже самое, что и в классе Horse.
    В классе Pawn:
    Реализуйте конструктор, который будет принимать цвет фигуры.*/
    public Pawn(String color) {
        super(color);
    }
    //Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.

    @Override
    public String getColor() {
        return this.color;
    }

    /*Реализуйте метод canMoveToPosition() так, чтобы
    пешка не могла выйти за доску
    и могла ходить только вперед.
    Помните, что первый ход пешка может сдвинуться на 2 поля вперед, сделать это можно, например, сравнив координаты.
    То есть, если пешка белая (color.equals("White")) и находится в line == 1,
    то она может пойти на 2 поля вперед, иначе — нет, аналогично с черными пешками.
    Также фигура не может сходить в точку, в которой она сейчас находится.
    Если пешка может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
    то функция вернет true, иначе — false.
    */

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0) return false;
        if (column == toColumn) {
            int dir;//на сколько двигаемся вперед
            int start;//стартовая позиция фигуры
            if (color.equals("White")) {//для белых фигур установить следующий алгоритм
                dir = 1;//направление хода для белых вверх по доске
                start = 1;//стартовая линия для пешки всегда 1 (2-я линия)
            } else {//для черных фигур
                dir = -1;//направление хода для черных вниз по доске
                start = 6;//стартовая линия для черной пешки всегда 6 (7-я линия)
            }
            //проверяем можем ли сходить в конечную клетку
            if (line + dir == toLine) {
                //если клетка свободна, то функция возвращает "истина"
                return chessBoard.board[toColumn][toLine] == null;
            }
            //если линия равна стартовой и ходим на 2 клетки
            if (line == start && line + 2 * dir == toLine) {
                //если конечная клетка свободна и на пути нет фигур, функция вернет истина
                return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null;
            }
            if ((column - toColumn == 1 || column - toColumn == -1) && (line - toLine == 1 || line - toLine == -1)
                    //и на этой клетке есть фигура
                    && chessBoard.board[toLine][toColumn] != null) {
                //возвращаем ложь или истина по результатам проверки является ли фигура в конечной клетке другого цвета
                return !chessBoard.board[toLine][toColumn].getColor().equals(color);
            } else return false;//если условие не выполняется возвращаем ложь
        }

        //сначала проверим, что все позиции сущестуют                                             и в этой клетке есть фигура
//        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) && chessBoard.board[line][column] != null) {
//            //не ходим наискосок, то есть не едим фигуру (координата колонки не меняется)
//            if (column == toColumn) {
//                int dir;//на сколько двигаемся вперед
//                int start;//стартовая позиция фигуры
//                if (color.equals("White")) {//для белых фигур установить следующий алгоритм
//                    dir = 1;//направление хода для белых вверх по доске
//                    start = 1;//стартовая линия для пешки всегда 1 (2-я линия)
//                } else {//для черных фигур
//                    dir = -1;//направление хода для черных вниз по доске
//                    start = 6;//стартовая линия для черной пешки всегда 6 (7-я линия)
//                }
//                //проверяем можем ли сходить в конечную клетку
//                if (line + dir == toLine) {
//                    //если клетка свободна, то функция верращает значение логической переменной равным "истина"
//                    return chessBoard.board[toColumn][toLine] == null;
//                }
//                //если линия равна стартовой и ходим на 2 клетки
//                if (line == start && line + 2 * dir == toLine) {
//                    //если конечная клетка свободна и на пути нет фигур, функция вернет истина
//                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null;
//                }
//            }
//        } else {//ходим наискосок, то есть будем рубить фигуру
//            //если по колонке и линии сдвигается на один (признаки хода наискосок - изменение линии и колонки одновременно на 1)
//            if ((column - toColumn == 1 || column - toColumn == -1) && (line - toLine == 1 || line - toLine == -1)
//                    //и на этой клетке есть фигура
//                    && chessBoard.board[toLine][toColumn]!=null){
//                //возвращаем ложь или истина по результатам проверки является ли фигура в конечной клетке другого цвета
//                return !chessBoard.board[toLine][toColumn].getColor().equals(color);
//            } else return false;//если условие не выполняется возвращаем ложь
//        }
        return false;//если никакое условие не прошло возвращаем ложь т.е. ход данной фигурой на данную клетку невозможет
    }

    //Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае пешка — это P. Также вы можете добавить и свои методы для удобства.

    @Override
    public String getSymbol() {
        return "P";
    }

//    public boolean checkPos(int pos) {//проверка позиции
//        return pos >= 0 && pos <= 7;
//        //Также вы можете добавить и свои методы для удобства.
//    }
}