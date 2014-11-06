package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class FieldLookup extends HxObject
{
	public    FieldLookup(EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    FieldLookup()
	{
		FieldLookup.__hx_ctor_haxe_lang_FieldLookup(this);
	}
	
	
	public static   void __hx_ctor_haxe_lang_FieldLookup(FieldLookup __temp_me8)
	{
		{
		}
		
	}
	
	
	public static   int hash(String s)
	{
		
		return s.hashCode();
	
	}
	
	
	public static   int findHash(String hash, haxe.root.Array<String> hashs)
	{
		int min = 0;
		int max = hashs.length;
		while (( min < max ))
		{
			int mid = ( (( max + min )) / 2 );
			int classify = hash.compareTo(hashs.__get(mid));
			if (( classify < 0 ))
			{
				max = mid;
			}
			 else
			{
				if (( classify > 0 ))
				{
					min = ( mid + 1 );
				}
				 else
				{
					return mid;
				}

			}

		}

		return  ~ (min) ;
	}


	public static   Object __hx_createEmpty()
	{
		return new FieldLookup(((EmptyObject) (EmptyObject.EMPTY) ));
	}


	public static   Object __hx_create(haxe.root.Array arr)
	{
		return new FieldLookup();
	}
	
	
}


