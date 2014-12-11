package br.com.remotemyhome.dao;

import java.util.List;

public interface InterfaceGenericDAO<T> {

	public Long insert(T obj);

	public Integer update(T obj);

	public Integer delete(T obj);

	public T load(Long id);

	public List<T> loadAll();

}
