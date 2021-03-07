package Dao;

import java.util.List;

public interface DAO <T> {	
		
		T get(long id);
	    
	    List<T> getAll();
	    
	    List<T> read();
	    
	    List<T> filter(String field, String searchCriteria);
	    
	    void create(T t);
	    
	    String insert(Object obj);
	    
	    String  update(Object obj);
	    
	    String delete(Object obj);
}