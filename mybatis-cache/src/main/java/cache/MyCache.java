package cache;

import org.apache.ibatis.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-05 15:55
 */
public class MyCache implements Cache {
    // 读写锁
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    // 这里我们可以用ehcache redis MongoDB 等技术
    Map<Object,Object> map = new ConcurrentHashMap<>();

    // cache的ID
    private String id ;

    public MyCache(){
        System.out.println("无参构造");
    }
    public MyCache(String id){
        this.id = id;
        System.out.println("构造函数id(namespace):"+id);
    }

    @Override
    public String getId() {
        System.out.println("获取id：" + id);
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        map.put(key,value);
    }

    @Override
    public Object getObject(Object key) {
        Object value = map.get(key);
        System.out.println("获取对象：key="+key+", value="+value);
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int getSize() {
        return map.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }
}
