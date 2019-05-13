import java.util.concurrent.Exchanger;
//Класс Exchanger предназначен для обмена данными между потоками.
class ExchangerDemo {
    public static void main(String args[]) {
        Exchanger<String> exgr = new Exchanger<>();
        new UseString(exgr);
        new MakeString(exgr);
    }
}

// Поток типа Thread, формирующий символьную строку
// Exchanger provides a synchronization point at which two threads can pair and swap elements
// Exchanger waits until two separate threads call its exchange() method

class MakeString implements Runnable {
    Exchanger<String> ex;
    String str;

    MakeString(Exchanger<String> c) {
        ex = c;
        str = new String();
        new Thread(this).start();
    }

    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            // заполнить буфер
            for (int j = 0; j < 5; j++) {
                str += (char) ch++;
            }
            try {
                // обменять заполненный буфер на пустой
                System.out.println("MakeString отправили: " + str);
                str = ex.exchange(str);
                System.out.println("MakeString получили: " + str);
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }
}

// Поток типа Thread, использующий символьную строку

class UseString implements Runnable {
    Exchanger<String> ex;
    String str;

    UseString(Exchanger<String> c) {
        ex = c;
        new Thread(this).start();
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                // обменять пустой буфер на заполненный
                str = ex.exchange("");
                System.out.println("UseString Получено: " + str);
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }
}