package com.kuso.utility;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void autoClose(Closeable closeable){
		if(closeable != null){
			try{
				closeable.close();
			}
			catch(IOException e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
}
