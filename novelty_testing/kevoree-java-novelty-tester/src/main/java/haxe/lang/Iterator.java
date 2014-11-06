package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  interface Iterator<T> extends haxe.lang.IHxObject
{
	   boolean hasNext();
	
	   T next();
	
}


