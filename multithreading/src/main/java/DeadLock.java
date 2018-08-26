public class DeadLock {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        new Thread1(a).start();
        new Thread2(b).start();

    }
}

class Thread1 extends Thread {
    private A a;

    public Thread1(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.printBCount();
    }
}

class Thread2 extends Thread {
    private B b;

    public Thread2(B b) {
        this.b = b;
    }

    @Override
    public void run() {
        b.printACount();
    }
}

class A {
    private Integer count;
    private B b;

    public A() {
        count = 200;
    }

    public void setB(B b) {
        this.b = b;
    }

    public synchronized void printBCount() {
        System.out.println("start print B count");
        b.printBCount();
    }

    public synchronized void printACount() {
        System.out.println("A count: " + count);
    }
}

class B {
    private Integer count;
    private A a;


    public B() {
        count = 100;
    }

    public void setA(A a) {
        this.a = a;
    }

    public synchronized void printACount() {
        try {
            System.out.println("start print A count");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.printACount();
    }

    public synchronized void printBCount() {
        System.out.println("B count: " + count);
    }


}
