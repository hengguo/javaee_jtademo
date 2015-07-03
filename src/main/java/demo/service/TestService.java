package demo.service;

import org.springframework.stereotype.Service;

@Service("testService")  
public class TestService {  
  
    private static int cnt = 0;  
  
    public TestService() {  
        super();  
        System.out.println("TestService initilized count = " + cnt);  
        cnt++;  
    }  
      
}  