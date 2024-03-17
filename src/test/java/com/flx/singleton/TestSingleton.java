package com.flx.singleton;

public class TestSingleton {

    /**
     * 基础的饱汉模式（不推荐） 饱汉模式的核心就是懒加载。优点是节省资源，一直到实例被第一次访问，才需要初始化单例 缺点是是线程不安全，if 语句存在竞态条件
     */
    static class Singleton01 {

        private static Singleton01 singleton = null;

        public Singleton01() {
        }

        public static Singleton01 getInstance() {
            if (singleton == null) {
                singleton = new Singleton01();
            }
            return singleton;
        }
    }

    /**
     * 线程安全的饱汉模式一（不推荐） 缺点：效率太低了，每个线程在想获得类的实例时候，执行 getInstance() 方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接 return
     * 就行了。方法进行同步效率太低要改进
     */
    static class Singleton02 {

        private static Singleton02 singleton = null;

        public Singleton02() {
        }

        public synchronized static Singleton02 getInstance() {
            if (singleton == null) {
                singleton = new Singleton02();
            }
            return singleton;
        }
    }

    /**
     * 线程安全的饱汉模式二（不推荐）缺点：虽然采用了同步代码块，同样效率太低了
     */
    static class Singleton03 {

        private static Singleton03 singleton = null;

        public Singleton03() {
        }

        public synchronized static Singleton03 getInstance() {
            if (singleton == null) {
                synchronized (Singleton03.class) {
                    singleton = new Singleton03();
                }
            }
            return singleton;
        }
    }

    /**
     * 线程安全的饱汉模式三（推荐）优点：线程安全；延迟加载；效率较高
     */
    static class Singleton04 {

        private static Singleton04 singleton = null;

        public Singleton04() {
        }

        public synchronized static Singleton04 getInstance() {
            if (singleton == null) {
                synchronized (Singleton04.class) {
                    if (singleton == null) {
                        singleton = new Singleton04();
                    }
                }
            }
            return singleton;
        }
    }

    /**
     * 线程安全的饱汉模式四（推荐） 静态内部类方式在 Singleton 类被装载时并不会立即实例化，而是在需要实例化时，调用 getInstance() 方法，才会装载 SingletonInstance 类，从而完成
     * Singleton 的实例化 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM 帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的 优点：避免了线程不安全，延迟加载，效率高
     */
    static class Singleton05 {

        private Singleton05() {
        }

        public static Singleton05 getInstance() {
            return Singleton05Instance.INSTANCE;
        }

        private static class Singleton05Instance {

            private static final Singleton05 INSTANCE = new Singleton05();
        }
    }


    /**
     * 饿汉模式一 （推荐） 优点：这种写法比较简单，就是在类装载的时候就完成实例化。避免了线程同步问题 缺点：在类装载的时候就完成实例化，没有达到 Lazy Loading
     * 的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
     */
    static class Singleton06 {

        private final static Singleton06 INSTANCE = new Singleton06();

        private Singleton06() {
        }

        public static Singleton06 getInstance() {
            return INSTANCE;
        }

    }

    /**
     * 饿汉模式二 （推荐） 将类实例化的过程放在了静态代码块中，也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例
     */
    static class Singleton07 {

        private static Singleton07 instance = null;

        static {
            instance = new Singleton07();
        }

        private Singleton07() {
        }

        public static Singleton07 getInstance() {
            return instance;
        }
    }
}
