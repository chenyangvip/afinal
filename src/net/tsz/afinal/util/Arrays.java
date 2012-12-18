package net.tsz.afinal.util;

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
}
