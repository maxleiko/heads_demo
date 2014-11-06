package haxe.root;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class StringBuf extends haxe.lang.HxObject
{
	public    StringBuf(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    StringBuf()
	{
		StringBuf.__hx_ctor__StringBuf(this);
	}
	
	
	public static   void __hx_ctor__StringBuf(StringBuf __temp_me4)
	{
		__temp_me4.b = new StringBuilder();
	}
	
	
	public static   Object __hx_createEmpty()
	{
		return new StringBuf(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   Object __hx_create(Array arr)
	{
		return new StringBuf();
	}
	
	
	public  StringBuilder b;
	
	public  <T> void add(T x)
	{
		if (haxe.lang.Runtime.isInt(x)) 
		{
			int x1 = ((int) (haxe.lang.Runtime.toInt(((Object) (x) ))) );
			Object xd = x1;
			this.b.append(((Object) (xd) ));
		}
		 else 
		{
			this.b.append(((Object) (x) ));
		}
		
	}
	
	
	@Override public   String toString()
	{
		return this.b.toString();
	}
	
	
	@Override public   Object __hx_setField(String field, Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef29 = true;
			switch (field.hashCode())
			{
				case 98:
				{
					if (field.equals("b")) 
					{
						__temp_executeDef29 = false;
						this.b = ((StringBuilder) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef29) 
			{
				return super.__hx_setField(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   Object __hx_getField(String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef30 = true;
			switch (field.hashCode())
			{
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef30 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((Object) (this) ), haxe.lang.Runtime.toString("toString"))) );
					}
					
					break;
				}
				
				
				case 98:
				{
					if (field.equals("b")) 
					{
						__temp_executeDef30 = false;
						return this.b;
					}
					
					break;
				}
				
				
				case 96417:
				{
					if (field.equals("add")) 
					{
						__temp_executeDef30 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((Object) (this) ), haxe.lang.Runtime.toString("add"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef30) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   Object __hx_invokeField(String field, Array dynargs)
	{
		{
			boolean __temp_executeDef31 = true;
			switch (field.hashCode())
			{
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef31 = false;
						return this.toString();
					}
					
					break;
				}
				
				
				case 96417:
				{
					if (field.equals("add")) 
					{
						__temp_executeDef31 = false;
						this.add(dynargs.__get(0));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef31) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(Array<String> baseArr)
	{
		baseArr.push("b");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


