package net.tsz.afinal.bitmap.core;


public interface Cache<K, V> {
	public boolean exists(K key);
	public void put(K key,V value);
	public void remove(K key);
	public void delete(K key);
	public V get(K key);
	public void update(K key,V bitmap);
	public void clean();
}
