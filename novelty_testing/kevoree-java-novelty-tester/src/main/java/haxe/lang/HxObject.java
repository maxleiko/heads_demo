package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class HxObject implements haxe.lang.IHxObject
{
	public    HxObject(haxe.lang.EmptyObject empty)
	{
		{
		}

	}


	public    HxObject()
	{
		HxObject.__hx_ctor_haxe_lang_HxObject(this);
	}


	public static   void __hx_ctor_haxe_lang_HxObject(HxObject __temp_me9)
	{
		{
		}

	}


	public static   Object __hx_createEmpty()
	{
		return new HxObject(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}


	public static   Object __hx_create(haxe.root.Array arr)
	{
		return new HxObject();
	}


	public   boolean __hx_deleteField(String field)
	{
		return false;
	}


	public   Object __hx_lookupField(String field, boolean throwErrors, boolean isCheck)
	{
		if (isCheck)
		{
			return Runtime.undefined;
		}
		 else
		{
			if (throwErrors)
			{
				throw haxe.lang.HaxeException.wrap("Field not found.");
			}
			 else
			{
				return null;
			}

		}

	}


	public   double __hx_lookupField_f(String field, boolean throwErrors)
	{
		if (throwErrors)
		{
			throw haxe.lang.HaxeException.wrap("Field not found or incompatible field type.");
		}
		 else
		{
			return 0.0;
		}

	}


	public   Object __hx_lookupSetField(String field, Object value)
	{
		throw haxe.lang.HaxeException.wrap("Cannot access field for writing.");
	}


	public   double __hx_lookupSetField_f(String field, double value)
	{
		throw haxe.lang.HaxeException.wrap("Cannot access field for writing or incompatible type.");
	}


	public   double __hx_setField_f(String field, double value, boolean handleProperties)
	{
		{
			{
				return this.__hx_lookupSetField_f(field, value);
			}

		}

	}


	public   Object __hx_setField(String field, Object value, boolean handleProperties)
	{
		{
			{
				return this.__hx_lookupSetField(field, value);
			}

		}

	}


	public   Object __hx_getField(String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			{
				return this.__hx_lookupField(field, throwErrors, isCheck);
			}

		}

	}


	public   double __hx_getField_f(String field, boolean throwErrors, boolean handleProperties)
	{
		{
			{
				return this.__hx_lookupField_f(field, throwErrors);
			}

		}

	}


	public   Object __hx_invokeField(String field, haxe.root.Array dynargs)
	{
		{
			{
				return ((Function) (this.__hx_getField(field, true, false, false)) ).__hx_invokeDynamic(dynargs);
			}

		}

	}


	public   void __hx_getFields(haxe.root.Array<String> baseArr)
	{
		{
		}
		
	}
	
	
}


