package net.battier.test;  
  
import net.battier.dao.impl.BookFacadeImpl1;  
import net.battier.proxy.BookFacadeCglib;  
  
public class TestCglib {  
      
    public static void main(String[] args) {  
        BookFacadeCglib cglib=new BookFacadeCglib();  
        BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(new BookFacadeImpl1());  
        bookCglib.addBook();  
    }  
}  