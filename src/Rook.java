public class Rook extends ChessPiece {
    /* Задача 5
    Аналогично предыдущим фигурам создайте класс Rook (ладья).
    В классе Rook:
    Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
    Реализуйте метод canMoveToPosition() так,
    чтобы ладья не могла выйти за доску и
    мог ходить только по прямой,
    также фигура не может сходить в точку, в которой она сейчас находится. Если ладья может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
    Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае ладья — R.
    Также вы можете добавить и свои методы для удобства.
     */
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //проверяем, что такие клетки существуют на доске
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0) return false;//за пределами доски
        if (line == toLine && column == toColumn) return false;//нельзя ходить не прямо
        if (line - toLine == 0 || column - toColumn == 0) {
            return false;//нельзя ходить на стартовую клетку
        } else {
            if (line==toLine){
                if (column>toColumn){
                    for (int i = 0; i < Math.abs(line-toLine); i++) {
                        return chessBoard.board[toLine][column-i]==null || !chessBoard.board[toLine][column-i].getColor().equals(this.color);
                    }
                } else {
                    for (int i = 0; i < Math.abs(line-toLine); i++) {
                        return chessBoard.board[toLine][column+i]==null || !chessBoard.board[toLine][column+i].getColor().equals(this.color);
                    }
                }
            } else{
                if (line>toLine){
                    for (int i = 0; i < Math.abs(column-toColumn); i++) {
                        return chessBoard.board[line-i][toColumn]== null || !chessBoard.board[line-i][toColumn].getColor().equals(this.color);
                    }
                } else{
                    for (int i = 0; i < Math.abs(column-toColumn); i++) {
                        return chessBoard.board[line+i][toColumn]== null || !chessBoard.board[line+i][toColumn].getColor().equals(this.color);
                    }
                }
            }
        }
        return false;
//        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
//            //если ходим по вертикали (по колонке)
//            if (column == toColumn) {
//                //проходим линии сверху вниз (хотя фактически фигура может двигаться снизу вверх)
//                //i будет проходить от минималного згначения до минимального значения по линии
//                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {
//                    //не занятали следующая клетка
//                    if (chessBoard.board[i][column] != null) {
//                        //нельзя двигаться туда где стоишь
//                        if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;
//                            //нельзя двигаться на клетку, где стоит фигура того же цвета
//                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine) return false;
//                            //можно срубить фигуру другого цвета
//                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine) return true;
//                    }
//                }
//                //если на конечной клетке у нас стоит фигура (все еще двигаемся по колонке)
//                if (chessBoard.board[toLine][column] != null) {
//                    //нельзя ходить если на конечной клетке стоит фигура того-же цвета
//                    //                     цвет фигуры = цвету игрока                   и      текущая клетка не равна конечной
//                    if (chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this)
//                        return false;
//                        //вернет истина если на конечной клетке стоит фигура другого цвета и конечная клетка не совпадает с начальной
//                    else
//                        return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
//                } else return true;
//            } else if (line == toLine) {//ВСЕ ТОЖЕ САМОЕ НО УЖЕ ПО ЛИНИИ
//                for (int i = getMin(column, toColumn); i < getMax(column, toColumn); i++) {
//                    if (chessBoard.board[line][i] != null) {
//                        if (chessBoard.board[line][i] == this && i == getMax(column, toColumn)) return false;
//                        else if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn) return false;
//                        else if (!chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn) return true;
//                    }
//                }
//                if (chessBoard.board[line][toColumn] != null) {
//                    if (chessBoard.board[line][toColumn].getColor().equals(this.color) && chessBoard.board[line][toColumn] != this) return false;
//                    else return !chessBoard.board[line][toColumn].getColor().equals(this.color) && chessBoard.board[line][toColumn] !=this;
//                } else return true;
//            } else return false;
//        } else return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
