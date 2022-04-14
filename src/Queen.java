public class Queen extends ChessPiece {
    /*Задача 6
    Напишите класс Queen и класс King.
    В этих классах:
    Реализуйте конструктор, который будет принимать лишь цвет фигуры.
    Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
    Реализуйте метод canMoveToPosition() так, чтобы
    фигуры не могли выйти за доску и могли ходить так, как ходят в шахматах
    (Королева ходит и по диагонали и по прямой,
    Король — в любое поле вокруг себя), также фигура не может сходить в точку, в которой она сейчас находится.
    Если фигура может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше),
    то функция вернет true, иначе — false.
    Реализуйте метод getSymbol так, чтобы он возвращал строку — символ фигуры, для короля — K, для ферзя — Q.
    Отдельно в классе King создайте метод isUnderAttack(ChessBoard board, int line, int column), возвращающий логическое (boolean) значение, который  будет проверять, находится ли поле, на котором стоит король (или куда собирается пойти) под атакой. Если это так, то метод должен вернуть true, иначе — false. Это позволит нам проверять шахи.
     */
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0) return false;//если конечная клетка за пределами поля
        if (line==toLine && column==toColumn) return false;
        return line == toLine  || column == toColumn || Math.abs(line-toLine) == Math.abs(column-toColumn);
        //y == Y && x != X || y != Y && x == X || (x - y) == (X - Y) || (x + y) == (X + Y);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}