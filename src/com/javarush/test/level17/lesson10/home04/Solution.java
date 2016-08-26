package com.javarush.test.level17.lesson10.home04;

/* Синхронизированные методы
Установить модификатор synchronized только тем методам, которым необходимо.
Объект класса Solution будет использоваться нитями.
*/
/*И так:
        1) method4() нужно синхронизировать, потому что создается новый объект типа StringBuilder(), который не имеет встроенной синхронизации
        2) method5() не нужно синхронизировать, потому что создается новый объект типа StringBuffer(), который в себе имеет встроенную синхронизацию
        3) method3() нужно синхронизировать, потому он доступается к общему private double param = Math.RANDOM(), который может использовать любой метод внутри класса Solution
        4) method0() не нужно синхронизировать, потому он вызывает в себе method3(), который в свою очередь синхронизированный
        5) method2() не нужно синхронизировать, потому что он не создает новых объектов и не изменяет общие ресурсы и не вызывает других методов
        6) method1() нужно синхронизировать, потому что он вызывает в себе метод, который не синхронизированный, чтоб не получилось так что double i = method3() будет иметь значение из другого потока, а не из вашего
        7) method7() не нужно синхронизировать, потому что он не создает новых объектов и не изменяет общие ресурсы и не вызывает других методов
        8) method6() нужно синхронизировать, потому что он вызывает в себе метод, который не синхронизированный, и у него есть вывод в консоль
*/
public class Solution {
    private double param = Math.random();
    private StringBuilder sb = new StringBuilder();

    private void method0() {
        double i = method3();
    }

    protected synchronized void method1(String param1) {
        Solution solution = new Solution();
        solution.method0();
    }

    public void method2(int param1) {
        param1++;
    }

    synchronized double method3() {
        double random = Math.random();
        param += 40.7;
        return random + param;
    }

    private synchronized void method4() {
        sb.append(1).append(1).append(1).append(1);
    }

    protected void method5(String param2) {
        new StringBuffer().append(param2).append(param2).append(param2);
    }

    public synchronized String method6(int param2) {
        System.out.println("Thinking....");
        method7(5e-2);
        sb = new StringBuilder("Got it!.");
        return sb.toString();
    }

    String method7(double param2) {
        return "" + param2;
    }

}
