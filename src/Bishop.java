public class Bishop extends ChessPiece {
    /*    Задача 4
    Напишите класс Bishop (слон). Этот класс должен быть наследником от класса ChessPiece,
    который вы сделали в предыдущей задаче.
    В классе Bishop:    */

    public Bishop(String color) {
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
        if (toLine > 7 || toLine < 0 || toColumn > 7 || toColumn < 0) return false;//если конечная клетка за пределами поля
        if (Math.abs(line - toLine) == 0) return false;//если линиии совпадают то нельзя ходить т.к. ходит только наискосок
        if (Math.abs(line - toLine) != Math.abs(column - toColumn)) return false;//нельзя ходить не наискосок
        return  (line != toLine && column != toColumn);//нельзя ходить на стартовую клетку
        //return true;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}