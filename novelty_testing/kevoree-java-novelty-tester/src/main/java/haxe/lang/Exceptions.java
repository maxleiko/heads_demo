package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Exceptions extends HxObject
{
	static 
	{
		Exceptions.exception = new ThreadLocal<RuntimeException>();
	}
	public    Exceptions(haxe.lang.EmptyObject empty)
	{
		{
		}

	}


	public    Exceptions()
	{
		Exceptions.__hx_ctor_haxe_lang_Exceptions(this);
	}


	public static   void __hx_ctor_haxe_lang_Exceptions(Exceptions __temp_me7)
	{
		{
		}

	}


	public static  ThreadLocal<RuntimeException> exception;

	public static   Object __hx_createEmpty()
	{
		return new Exceptions(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}


	public static   Object __hx_create(haxe.root.Array arr)
	{
		return new Exceptions();
	}
	
	
}


