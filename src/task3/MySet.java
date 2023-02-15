package task3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySet<T> implements Set<T> {

    private final Set<T> set;
    private final Lock lock = new ReentrantLock();

    public MySet(Set<T> set) {
        this.set = set;
    }

    @Override
    public int size() {
        lock.lock();
        int size = set.size();
        lock.unlock();
        return size;
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        boolean isEmpty = set.isEmpty();
        lock.unlock();
        return isEmpty;
    }

    @Override
    public boolean contains(Object o) {
        lock.lock();
        boolean contains = set.isEmpty();
        lock.unlock();
        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        lock.lock();
        Iterator<T> iterator = set.iterator();
        lock.unlock();
        return iterator;
    }

    @Override
    public Object[] toArray() {
        lock.lock();
        Object[] objects = set.toArray();
        lock.unlock();
        return objects;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        lock.lock();
        Object[] objects = set.toArray(new Object[0]);
        lock.unlock();
        return (T1[]) objects;
    }

    @Override
    public boolean add(T t) {
        lock.lock();
        boolean add = set.add(t);
        lock.unlock();
        return add;
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        boolean remove = set.remove(o);
        lock.unlock();
        return remove;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        lock.lock();
        boolean containsAll = set.containsAll(c);
        lock.unlock();
        return containsAll;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        lock.lock();
        boolean addAll = set.addAll(c);
        lock.unlock();
        return addAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        lock.lock();
        boolean retainAll = set.retainAll(c);
        lock.unlock();
        return retainAll;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        lock.lock();
        boolean removeAll = set.removeAll(c);
        lock.unlock();
        return removeAll;
    }

    @Override
    public void clear() {
        lock.lock();
        set.clear();
        lock.unlock();
    }
}
