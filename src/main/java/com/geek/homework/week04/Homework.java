package com.geek.homework.week04;

import org.springframework.util.StringUtils;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with Intellij IDEA.
 * Project: geek-homework
 * Author: lishibin
 * Date: 2021/7/18
 */
public class Homework {

    private static ExecutorService pool = Executors.newSingleThreadExecutor();

    /**
     * 方式1
     */
    public static class DownLatch {
        public static void main(String[] args) throws Exception {
            DownLatch latch = new DownLatch();
            CountDownLatch downLatch = new CountDownLatch(1);
            String result = latch.test(downLatch);
            downLatch.await();
            System.err.println("我是main线程,结果=" + result);
        }

        public String test(CountDownLatch downLatch) throws ExecutionException, InterruptedException {
            downLatch.countDown();
            Future<String> submit = pool.submit(() -> System.err.println("我是test线程" + Thread.currentThread().getName()), "test");
            pool.shutdown();
            return submit.get();
        }
    }

    /**
     * 方式2
     */
    public static class Barrier {
        public static void main(String[] args) throws Exception {
            Barrier barrier = new Barrier();
            CyclicBarrier cyclicBarrier = new CyclicBarrier(1);
            String result = barrier.test(cyclicBarrier);
            System.err.println("我是main线程,结果=" + result);
        }

        public String test(CyclicBarrier cyclicBarrier) throws BrokenBarrierException, InterruptedException, ExecutionException {
            Future<String> submit = pool.submit(() -> System.err.println("我是test线程" + Thread.currentThread().getName()), "test");
            cyclicBarrier.await();
            pool.shutdown();
            return submit.get();
        }
    }

    /**
     * 方式3
     */
    public static class MySemaphore {
        public static void main(String[] args) throws Exception {
            MySemaphore mySemaphore = new MySemaphore();
            Semaphore semaphore = new Semaphore(1);
            String result = mySemaphore.test(semaphore);
            semaphore.release();
            System.err.println("我是main线程,结果=" + result);
        }

        public String test(Semaphore semaphore) throws InterruptedException, ExecutionException {
            Future<String> submit = pool.submit(() -> System.err.println("我是test线程" + Thread.currentThread().getName()), "test");
            semaphore.acquire();
            pool.shutdown();
            return submit.get();
        }
    }

    /**
     * 方式4
     */
    public static class MyLock {
        public static void main(String[] args) throws Exception {
            MyLock myCondition = new MyLock();
            Lock lock = new ReentrantLock();
            lock.lock();
            String test;
            try {
                test = myCondition.test();
            } finally {
                lock.unlock();
            }
            System.err.println("我是main线程,结果=" + test);
        }

        public String test() throws InterruptedException, ExecutionException {
            Future<String> submit = pool.submit(() -> System.err.println("我是test线程" + Thread.currentThread().getName()), "test");
            pool.shutdown();
            return submit.get();
        }
    }

    /**
     * 方式5
     */
    public static class A {
        public static void main(String[] args) throws Exception {
            A a = new A();
            String test = a.test();
            while (true) {
                if (StringUtils.hasText(test)) {
                    break;
                }
            }
            System.err.println("我是main线程,结果=" + test);
        }

        public String test() throws ExecutionException, InterruptedException {
            Future<String> submit = pool.submit(() -> System.err.println("我是test线程" + Thread.currentThread().getName()), "test");
            pool.shutdown();
            return submit.get();
        }
    }

    /**
     * 方式6
     */
    public static class B {
        public static void main(String[] args) throws Exception {
            B b = new B();
            String test = b.test();
            Thread thread = new Thread();
            thread.join();
            System.err.println("我是main线程,结果=" + test);
        }

        public String test() throws ExecutionException, InterruptedException {
            Future<String> submit = pool.submit(() -> System.err.println("我是test线程" + Thread.currentThread().getName()), "test");
            pool.shutdown();
            return submit.get();
        }
    }

    /**
     * 方式7
     */
    public static class C implements Runnable {
        private String test;

        public static void main(String[] args) throws Exception {
            C c = new C();
            Thread thread = new Thread(c);
            thread.start();
            thread.join();
            System.err.println("我是main线程,结果=" + c.getTest());
        }

        @Override
        public void run() {
            System.err.println("我是test线程" + Thread.currentThread().getName());
            this.test = "test";
        }

        public String getTest() {
            return test;
        }
    }

    /**
     * 方式8
     */
    public static class D extends Thread {
        private String test;

        @Override
        public void run() {
            System.err.println("我是test线程" + Thread.currentThread().getName());
            this.test = "test";
        }

        public static void main(String[] args) throws Exception {
            D d = new D();
            d.start();
            d.join();
            System.err.println("我是main线程,结果=" + d.test);
        }
    }

    /**
     * 方式9
     */
    public static class E implements Callable<String> {

        public static void main(String[] args) throws Exception {
            E e = new E();
            FutureTask<String> futureTask = new FutureTask<>(e);
            new Thread(futureTask).start();
            System.err.println("我是main线程,结果=" + futureTask.get());
        }

        @Override
        public String call() throws Exception {
            System.err.println("我是test线程" + Thread.currentThread().getName());
            return "test";
        }
    }
}
