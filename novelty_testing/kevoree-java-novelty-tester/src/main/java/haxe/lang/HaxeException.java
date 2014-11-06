package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class HaxeException extends RuntimeException
{
	public    HaxeException(Object obj, String msg, Throwable cause)
	{
		super(msg, cause);
		if (( obj instanceof HaxeException))
		{
			HaxeException _obj = ((HaxeException) (obj) );
			obj = _obj.getObject();
		}
		
		this.obj = obj;
	}
	
	
	public static   RuntimeException wrap(Object obj)
	{
		RuntimeException ret = null;
		if (( obj instanceof RuntimeException ))
		{
			ret = ((RuntimeException) (obj) );
		}
		 else 
		{
			if (( obj instanceof String ))
			{
				ret = new HaxeException(((Object) (obj) ), Runtime.toString(obj), ((Throwable) (null) ));
			}
			 else 
			{
				if (( obj instanceof Throwable ))
				{
					ret = new HaxeException(((Object) (obj) ), Runtime.toString(null), ((Throwable) (obj) ));
				}
				 else 
				{
					ret = new HaxeException(((Object) (obj) ), Runtime.toString(null), ((Throwable) (null) ));
				}
				
			}
			
		}
		
		Exceptions.exception.set(((RuntimeException) (ret) ));
		return ret;
	}
	
	
	public  Object obj;
	
	public   Object getObject()
	{
		return this.obj;
	}
	
	
	@Override public   String toString()
	{
		return ( "Haxe Exception: " + haxe.root.Std.string(this.obj) );
	}
	
	
}


