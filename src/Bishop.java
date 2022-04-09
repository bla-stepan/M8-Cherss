public class Bishop extends ChessPiece {
    /*    Задача 4
    Напишите класс Bishop (слон). Этот класс должен быть наследником от класса ChessPiece,
    который вы сделали в предыдущей задаче.
    В классе Bishop:    */

    public Bishop (String color){
        super(color);
    }
    //Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
    @Override
    public String getColor() {
        return color;
    }

    /*Реализуйте метод canMoveToPosition() так, чтобы слон не мог выйти за доску
    (доска в нашем случае — это двумерный массив размером 8 на 8, напоминаем, что индексы начинаются с 0)
    и мог ходить только по диагонали, также фигура не может сходить в точку, в которой она сейчас находится.
    Если слон может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
    то функция вернет true, иначе — false.
    */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //начальная точка не равна конечной
        if (line != toLine && column != toColumn &&//можно вынести в абстрактный класс
                //ходит по диагонали
                getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn) &&
                //проверка существования всех еоординат клеток
                checkPos(line) && checkPos(toLine) && checkPos(column) && checkPos(toColumn) &&
                //конечная клетка не пустая или на ней стоит фигура другого цвета
                chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color) &&
                //и стартовая позиция не пустая
                chessBoard.board[line][column] != null) {
            //сверху слева -> направо вниз
            if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                    (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {
                //максимум и минимум нужны чтобы делать обратный ход т.е. сверху справа -> направо вниз
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                //позиции, которые слон походит по пути
                //создаем массив с количством элементов равных количеству ходов по колонке
                int[][] position = new int[toC - fromC][1];
                for (int i = 1; i < toC-fromC; i++) {//проходим цыклом все ходы
                    if (chessBoard.board[fromL - i][fromC + i] == null) {//если ячейка пуста то
                        position[i - 1] = new int[]{fromL - i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        position[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else {//сверху справа -> налево вниз
                int fromL = getMin(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMax(line, toLine);
                int toC = getMax(column, toColumn);
                //позиции, которые слон походит по пути
                //создаем массив с количством элементов равных количеству ходов по колонке
                int[][] position = new int[toC - fromC][1];
                for (int i = 1; i < toC-fromC; i++) {//проходим цыклом все ходы
                    if (chessBoard.board[fromL + i][fromC + i] == null) {//если ячейка пуста то
                        position[i - 1] = new int[]{fromL + i, fromC + i};
                    } else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i == toLine) {
                        position[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else return false;
    }
    //Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае слон —  B.

    @Override
    public String getSymbol() {
        return "B";
    }
}