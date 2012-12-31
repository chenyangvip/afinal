package net.tsz.afinal.util;

import java.util.List;

public class Arrays {
	public static boolean isEmpty(Object[] objects){
		boolean isEmpty=false;
		if(null==objects){
			isEmpty=true;
		}else{
			if(objects.length==0){
				isEmpty=true;
			}
		}
		return isEmpty;
	}
	
	public static boolean isEmpty(List objects){
		boolean isEmpty=false;
		if(null==objects){
			isEmpty=true;
		}else{
			if(objects.size()==0){
				isEmpty=true;
			}
		}
		return isEmpty;
	}
}
