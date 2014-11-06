package haxe.root;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Std
{
	public    Std()
	{
		{
		}
		
	}
	
	
	public static   boolean is(Object v, Object t)
	{
		if (( v == null )) 
		{
			return haxe.lang.Runtime.eq(t, Object.class);
		}
		
		if (( t == null )) 
		{
			return false;
		}
		
		Class clt = ((Class) (t) );
		if (( ((Object) (clt) ) == ((Object) (null) ) ))
		{
			return false;
		}
		
		String name = clt.getName();
		{
			String __temp_svar26 = (name);
			int __temp_hash28 = __temp_svar26.hashCode();
			switch (__temp_hash28)
			{
				case 761287205:case -1325958191:
				{
					if (( (( ( __temp_hash28 == 761287205 ) && __temp_svar26.equals("java.lang.Double") )) || __temp_svar26.equals("double") )) 
					{
						return haxe.lang.Runtime.isDouble(v);
					}
					
					break;
				}
				
				
				case 1063877011:
				{
					if (__temp_svar26.equals("java.lang.Object")) 
					{
						return true;
					}
					
					break;
				}
				
				
				case -2056817302:case 104431:
				{
					if (( (( ( __temp_hash28 == -2056817302 ) && __temp_svar26.equals("java.lang.Integer") )) || __temp_svar26.equals("int") )) 
					{
						return haxe.lang.Runtime.isInt(v);
					}
					
					break;
				}
				
				
				case 344809556:case 64711720:
				{
					if (( (( ( __temp_hash28 == 344809556 ) && __temp_svar26.equals("java.lang.Boolean") )) || __temp_svar26.equals("boolean") )) 
					{
						return v instanceof Boolean;
					}
					
					break;
				}
				
				
			}
			
		}
		
		Class clv = v.getClass();
		return clt.isAssignableFrom(((Class) (clv) ));
	}
	
	
	public static   String string(Object s)
	{
		return ( haxe.lang.Runtime.toString(s) + "" );
	}
	
	
}


