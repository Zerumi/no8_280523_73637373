import exception.AreYouSeriousException;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Однажды Вася спросил у своего старшего брата-сеньора Пети:
                "Петя, зачем вообще нам нужно запускать общую библиотеку классов?"
                На что Петя ответил: "Братиш, для того, чтобы практик на защите прочитал этот текст.\"""");
        throw new AreYouSeriousException("Скажи честно, ты серьезно?");
    }
}
