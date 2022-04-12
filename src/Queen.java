public class Queen extends ChessPiece{
    /*Задача 6
    Напишите класс Queen и класс King.
    В этих классах:
    Реализуйте конструктор, который будет принимать лишь цвет фигуры.
    Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
    Реализуйте метод canMoveToPosition() так, чтобы фигуры не могли выйти за доску (доска в нашем случае — это двумерный массив размером 8 на 8,
    напоминаем, что индексы начинаются с 0) и могли ходить так, как ходят в шахматах (Королева ходит и по диагонали и по прямой,
    Король — в любое поле вокруг себя), также фигура не может сходить в точку, в которой она сейчас находится.
    Если фигура может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
    то функция вернет true, иначе — false.
    Реализуйте метод getSymbol так, чтобы он возвращал строку — символ фигуры, для короля — K, для ферзя — Q.
    Отдельно в классе King создайте метод isUnderAttack(ChessBoard board, int line, int column), возвращающий логическое (boolean) значение, который  будет проверять, находится ли поле, на котором стоит король (или куда собирается пойти) под атакой. Если это так, то метод должен вернуть true, иначе — false. Это позволит нам проверять шахи.
     */
    public Queen(String color){
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        //проверяем существуют ли координаты
        if (checkPos(line) && checkPos(column) && checkPos(column) && checkPos(toColumn)){
            //дополнительное условия для бишопа
            if (line != toLine && column != toColumn &&//можно вынести в абстрактный класс
                    //ходит по диагонали
                    getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn) &&
                    //конечная клетка не пустая или на ней стоит фигура другого цвета
                    chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color) &&
                    //и стартовая позиция не пустая
                    chessBoard.board[line][column] != null) {
                //сверху слева -> направо вниз
                if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) || (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {
                    int fromL = getMax(line, toLine);
                    int fromC = getMin(column, toColumn);
                    int toL = getMin(line, toLine);
                    int toC = getMax(column, toColumn);
                    //позиции, которые слон походит по пути
                    //создаем массив с количством элементов равных количеству ходов по колонке
                    int[][] position = new int[toC - fromC][1];
                    for (int i = 1; i < toC - fromC; i++) {//проходим цыклом все ходы
                        if (chessBoard.board[fromL - i][fromC + i] == null) {//если ячейка пуста то
                            position[i - 1] = new int[]{fromL - i, fromC + i};
                        } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                            position[i - 1] = new int[]{fromL - i, fromC + i};
                        } else return false;
                    }
                    return true;
                    //сверху справа -> налево вниз
                } else {
                    int fromL = getMin(line, toLine);
                    int fromC = getMin(column, toColumn);
                    int toL = getMax(line, toLine);
                    int toC = getMax(column, toColumn);
                    int[][] position = new int[toC - fromC][1];
                    for (int i = 1; i < toC - fromC; i++) {
                        if (chessBoard.board[fromL + i][fromC + i] == null) {
                            position[i - 1] = new int[]{fromL + i, fromC + i};
                        } else if (!chessBoard.board[fromL + i][fromC + i].color.equals(this.color) && fromL + i == toLine) {
                            position[i - 1] = new int[]{fromL + i, fromC + i};
                        } else return false;
                    }
                    return true;
                }
            }
            //ладья=============================
            //если ходим по вертикали (по колонке)
            if (column == toColumn) {
                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {
                    if (chessBoard.board[i][column] != null) {
                        if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;//нельзя двигаться туда где стоишь
                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine) return false;//нельзя двигаться на клетку, где стоит фигура того же цвета
                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine) return true;//можно срубить фигуру другого цвета
                    }
                }
                //если на конечной клетке у нас стоит фигура (все еще двигаемся по колонке)
                if (chessBoard.board[toLine][column] != null) {
                    if (chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this)//нельзя ходить если на конечной клетке стоит фигура того-же цвета
                        return false;
                        //вернет истина если на конечной клетке стоит фигура другого цвета и конечная клетка не совпадает с начальной
                    else
                        return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
                } else return true;
            } else if (line == toLine) {//ВСЕ ТОЖЕ САМОЕ НО УЖЕ ПО ЛИНИИ
                for (int i = getMin(column, toColumn); i < getMax(column, toColumn); i++) {
                    if (chessBoard.board[line][i] != null) {
                        if (chessBoard.board[line][i] == this && i == getMax(column, toColumn)) return false;
                        else if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn) return false;
                        else if (!chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn) return true;
                    }
                }
                if (chessBoard.board[line][toColumn] != null) {
                    if (chessBoard.board[line][toColumn].getColor().equals(this.color) && chessBoard.board[line][toColumn] != this) return false;
                    else return !chessBoard.board[line][toColumn].getColor().equals(this.color) && chessBoard.board[line][toColumn] !=this;
                } else return true;
            } else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}