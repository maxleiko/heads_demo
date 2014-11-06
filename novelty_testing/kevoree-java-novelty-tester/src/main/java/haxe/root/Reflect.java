package haxe.root;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Reflect extends haxe.lang.HxObject
{
	public    Reflect(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Reflect()
	{
		Reflect.__hx_ctor__Reflect(this);
	}
	
	
	public static   void __hx_ctor__Reflect(Reflect __temp_me3)
	{
		{
		}
		
	}
	
	
	public static   boolean hasField(Object o, String field)
	{
		
		if (o instanceof haxe.lang.IHxObject)
		return ((haxe.lang.IHxObject) o).__hx_getField(field, false, true, false) != haxe.lang.Runtime.undefined;

		return haxe.lang.Runtime.slowHasField(o, field);
	
	}
	
	
	public static   Object field(Object o, String field)
	{
		
		if (o instanceof haxe.lang.IHxObject)
			return ((haxe.lang.IHxObject) o).__hx_getField(field, false, false, false);

		return haxe.lang.Runtime.slowGetField(o, field, false);
	
	}
	
	
	public static   void setField(Object o, String field, Object value)
	{
		
		if (o instanceof haxe.lang.IHxObject)
			((haxe.lang.IHxObject) o).__hx_setField(field, value, false);
		else
			haxe.lang.Runtime.slowSetField(o, field, value);
	
	}
	
	
	public static   Object getProperty(Object o, String field)
	{
		
		if (o instanceof haxe.lang.IHxObject)
			return ((haxe.lang.IHxObject) o).__hx_getField(field, false, false, true);

		if (haxe.lang.Runtime.slowHasField(o, "get_" + field))
			return haxe.lang.Runtime.slowCallField(o, "get_" + field, null);

		return haxe.lang.Runtime.slowGetField(o, field, false);
	
	}
	
	
	public static   void setProperty(Object o, String field, Object value)
	{
		
		if (o instanceof haxe.lang.IHxObject)
			((haxe.lang.IHxObject) o).__hx_setField(field, value, true);
		else if (haxe.lang.Runtime.slowHasField(o, "set_" + field))
			haxe.lang.Runtime.slowCallField(o, "set_" + field, new Array( new Object[]{value} ));
		else
			haxe.lang.Runtime.slowSetField(o, field, value);
	
	}
	
	
	public static   Object callMethod(Object o, Object func, Array args)
	{
		
		return ((haxe.lang.Function) func).__hx_invokeDynamic(args);
	
	}
	
	
	public static Array<String> fields(Object o)
	{
		
		if (o instanceof haxe.lang.IHxObject)
		{
			Array<String> ret = new Array<String>();
				((haxe.lang.IHxObject) o).__hx_getFields(ret);
			return ret;
		} else if (o instanceof Class) {
			return Type.getClassFields((Class) o);
		} else {
			return new Array<String>();
		}
	
	}
	
	
	public static   boolean isFunction(Object f)
	{
		
		return f instanceof haxe.lang.Function;
	
	}
	
	
	public static  <T> int compare(T a, T b)
	{
		
		return haxe.lang.Runtime.compare(a, b);
	
	}
	
	
	public static   boolean compareMethods(Object f1, Object f2)
	{
		
		if (f1 == f2)
			return true;

		if (f1 instanceof haxe.lang.Closure && f2 instanceof haxe.lang.Closure)
		{
			haxe.lang.Closure f1c = (haxe.lang.Closure) f1;
			haxe.lang.Closure f2c = (haxe.lang.Closure) f2;

			return haxe.lang.Runtime.refEq(f1c.obj, f2c.obj) && f1c.field.equals(f2c.field);
		}


		return false;
	
	}
	
	
	public static   boolean isObject(Object v)
	{
		
		return v != null && !(v instanceof haxe.lang.Enum || v instanceof haxe.lang.Function || v instanceof Enum || v instanceof Number || v instanceof Boolean);
	
	}
	
	
	public static   boolean isEnumValue(Object v)
	{
		
		return v != null && (v instanceof haxe.lang.Enum || v instanceof Enum);
	
	}
	
	
	public static   boolean deleteField(Object o, String field)
	{
		
		return (o instanceof haxe.lang.DynamicObject && ((haxe.lang.DynamicObject) o).__hx_deleteField(field));
	
	}
	
	
	public static  <T> T copy(T o)
	{
		Object o2 = new haxe.lang.DynamicObject(new Array<String>(new String[]{}), new Array<Object>(new Object[]{}), new Array<String>(new String[]{}), new Array<Object>(new Object[]{}));
		{
			int _g = 0;
			Array<String> _g1 = Reflect.fields(o);
			while (( _g < _g1.length ))
			{
				String f = _g1.__get(_g);
				 ++ _g;
				Reflect.setField(o2, f, Reflect.field(o, f));
			}
			
		}
		
		return ((T) (o2) );
	}
	
	
	public static   Object makeVarArgs(haxe.lang.Function f)
	{
		return new haxe.lang.VarArgsFunction(((haxe.lang.Function) (f) ));
	}
	
	
	public static   Object __hx_createEmpty()
	{
		return new Reflect(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   Object __hx_create(Array arr)
	{
		return new Reflect();
	}
	
	
}


