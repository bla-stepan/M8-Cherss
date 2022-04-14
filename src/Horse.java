public class Horse extends ChessPiece {
    /*
    В классе Horse:
    Реализуйте конструктор, который будет принимать лишь цвет фигуры.
    Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
    Реализуйте метод canMoveToPosition() так, чтобы конь не мог выйти за доску
    (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0)
    и мог ходить только буквой «Г». Также фигура не может сходить в точку, в которой она сейчас находится.
    Если конь может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
    то функция вернет true, иначе — false.
    Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае конь — это  H.
     */
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    /*  Реализуйте метод canMoveToPosition() так, чтобы конь не мог выйти за доску
        и мог ходить только буквой «Г». Также фигура не может сходить в точку, в которой она сейчас находится.
        Если конь может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
        то функция вернет true, иначе — false. */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //все кординаты существуют
        if (toLine<0 || toLine>7 || toColumn<0 || toColumn>7) return false;
        if (toLine==line && column==toColumn) return false;
        return  (Math.abs(toLine-line)==1 && Math.abs(column-toColumn)==2 || Math.abs(toLine-line)==2 && Math.abs(toColumn-column)==1);
//        if (toColumn>=0 && toColumn<=7 && toLine>=0 && toLine<=7) {
//            //стартовая координата не равна конечной
//            if (line != toLine && column != toColumn &&
//                    //и конечная клетка пуста или цвет фигуры в конечной клетке не равен текущему
//                    (chessBoard.board[toLine][toColumn] == null || chessBoard.board[toLine][toColumn].color.equals(this.color))
//                    //и стартовая клетка не пуста
//                    && chessBoard.board[line][column] != null) {
//                //если стартовая клетка не равна коню, то не ходим
//                if (!chessBoard.board[line][column].equals(this)) {
//                    return false;
//                }
//                //перечислим все возможные позиции для коня
//                int[][] position = new int[][]{
//                        {line - 2, column - 1}, {line - 2, column + 1}, {line + 2, column - 1}, {line + 2, column + 1},
//                        {line - 1, column - 2}, {line - 1, column + 2}, {line + 1, column - 2}, {line + 1, column + 2}};
//                //проверяем можно ли сходить на нужную позицию
//                for (int i = 0; i < position.length; i++) {
//                    if (position[i][0]==toLine && position[i][1]==toColumn)
//                        return true;
//                }
//            }
//        } else return false;
//        return false;
    }


    //Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае конь — это  H.
    @Override
    public String getSymbol() {
        return "H";
    }

//    public boolean checkPos(int pos) {//проверка позиции
//        return pos >= 0 && pos <= 7;
//        //Также вы можете добавить и свои методы для удобства.
//    }
}



