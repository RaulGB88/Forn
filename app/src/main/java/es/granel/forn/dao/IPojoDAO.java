package es.granel.forn.dao;

import java.util.List;

public interface IPojoDAO {

    public long add(Object obj);

    public int update(Object obj);

    public void delete(Object obj);

    public Object search(Object obj);

    public List getAll();
}
