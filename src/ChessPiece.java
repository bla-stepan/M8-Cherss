public abstract class ChessPiece {
    /*
    Давайте напишем шахматы. Для начала надо спроектировать нашу игру.
    Самое главное в шахматах — это фигуры, поэтому для начала нам надо будет сделать абстрактный класс фигуры (ChessPiece),
    чтобы в будущем легко написать все типы фигур в игре. Но это ещё не всё, нам надо куда-то ставить фигуры,
    для этого нам нужен класс доски (ChessBoard), который будет отвечать за управление всей игрой.
    Эти шахматы будут максимально простыми, с консольным простеньким интерфейсом, но больше сейчас нам и не нужно.

    Задача 1

    Для начала напишем абстрактный класс ChessPiece (шахматная фигура), у которой должны быть следующие перемененные:
    строковая переменная color — цвет;
    логическая переменная check, по умолчанию true, она понадобится нам сильно позже;
    конструктор, принимающий в себя строковую переменную color.
    И следующие публичные (public) методы:
    абстрактный метод getColor(), возвращающий строку — должен вернуть цвет фигуры;
    абстрактный метод canMoveToPosition(), возвращающий логическое (boolean) значение и паринимающий в себя
        параметры ChessBoard chessBoard, int line, int column, int toLine, int toColumn;
    абстрактный метод getSymbol(), возвращающий строку — тип фигуры.
     */
    String color;
    boolean check = true;//проверить

    public ChessPiece(String color) {//конструктор
        this.color = color;
    }

    public abstract String getColor();//должен вернуть цвет фигуры;

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();//возвращает строку — тип фигуры.

    public boolean checkPos(int pos) {//проверка позиции
        return pos >= 0 && pos <= 7;
    }
}