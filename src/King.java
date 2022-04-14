public class King extends ChessPiece {
    /*Задача 6
Напишите класс Queen и класс King.
В этих классах:
Реализуйте конструктор, который будет принимать лишь цвет фигуры.
Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
Реализуйте метод canMoveToPosition() так, чтобы фигуры не могли выйти за доску и могли ходить так, как ходят в шахматах (Королева ходит и по диагонали и по прямой,
Король — в любое поле вокруг себя), также фигура не может сходить в точку, в которой она сейчас находится.
Если фигура может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
то функция вернет true, иначе — false.
Реализуйте метод getSymbol так, чтобы он возвращал строку — символ фигуры, для короля — K, для ферзя — Q.
Отдельно в классе King создайте метод isUnderAttack(ChessBoard board, int line, int column),
возвращающий логическое (boolean) значение, который будет проверять, находится ли поле,
на котором стоит король (или куда собирается пойти) под атакой.
Если это так, то метод должен вернуть true, иначе — false. Это позволит нам проверять шахи.     */
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0) return false;
        if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;
        if (line == toLine && column == toColumn) return false;
        if (isUnderAttack(chessBoard, toLine, toColumn)) return false;
        return true;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {
        //проходим вообще по всем клеткам и проверяем
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if (chessBoard.board[i][j] != null) {//если клетка не пуста, т.е. на ней находится фигура
                    //если фигура другого цвета и она может сходить на стартовую клетку короля то возвращаем истину
                    if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


