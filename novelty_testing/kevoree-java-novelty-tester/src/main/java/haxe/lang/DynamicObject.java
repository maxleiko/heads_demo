package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class DynamicObject extends HxObject
{
	public    DynamicObject(EmptyObject empty)
	{
		super(EmptyObject.EMPTY);
	}
	
	
	public    DynamicObject()
	{
		DynamicObject.__hx_ctor_haxe_lang_DynamicObject(((DynamicObject) (this)));
	}
	
	
	public    DynamicObject(haxe.root.Array<String> __hx_hashes, haxe.root.Array<Object> __hx_dynamics, haxe.root.Array<String> __hx_hashes_f, haxe.root.Array<Object> __hx_dynamics_f)
	{
		DynamicObject.__hx_ctor_haxe_lang_DynamicObject(((DynamicObject) (this)), ((haxe.root.Array<String>) (__hx_hashes)), ((haxe.root.Array<Object>) (__hx_dynamics)), ((haxe.root.Array<String>) (__hx_hashes_f)), ((haxe.root.Array<Object>) (__hx_dynamics_f)));
	}
	
	
	public static   void __hx_ctor_haxe_lang_DynamicObject(DynamicObject __temp_me11)
	{
		__temp_me11.__hx_hashes = new haxe.root.Array<String>(new String[]{});
		__temp_me11.__hx_dynamics = new haxe.root.Array<Object>(new Object[]{});
		__temp_me11.__hx_hashes_f = new haxe.root.Array<String>(new String[]{});
		__temp_me11.__hx_dynamics_f = new haxe.root.Array<Object>(new Object[]{});
	}
	
	
	public static   void __hx_ctor_haxe_lang_DynamicObject(DynamicObject __temp_me10, haxe.root.Array<String> __hx_hashes, haxe.root.Array<Object> __hx_dynamics, haxe.root.Array<String> __hx_hashes_f, haxe.root.Array<Object> __hx_dynamics_f)
	{
		__temp_me10.__hx_hashes = __hx_hashes;
		__temp_me10.__hx_dynamics = __hx_dynamics;
		__temp_me10.__hx_hashes_f = __hx_hashes_f;
		__temp_me10.__hx_dynamics_f = __hx_dynamics_f;
	}
	
	
	public static   Object __hx_createEmpty()
	{
		return new DynamicObject(((EmptyObject) (EmptyObject.EMPTY) ));
	}
	
	
	public static   Object __hx_create(haxe.root.Array arr)
	{
		return new DynamicObject(((haxe.root.Array<String>) (arr.__get(0)) ), ((haxe.root.Array<Object>) (arr.__get(1)) ), ((haxe.root.Array<String>) (arr.__get(2)) ), ((haxe.root.Array<Object>) (arr.__get(3)) ));
	}
	
	
	@Override public   String toString()
	{
		Function ts = ((Function) (Runtime.getField(this, "toString", false)) );
		if (( ts != null )) 
		{
			return Runtime.toString(ts.__hx_invoke0_o());
		}
		
		haxe.root.StringBuf ret = new haxe.root.StringBuf();
		ret.add("{");
		boolean first = true;
		{
			int _g = 0;
			haxe.root.Array<String> _g1 = haxe.root.Reflect.fields(this);
			while (( _g < _g1.length ))
			{
				String f = _g1.__get(_g);
				 ++ _g;
				if (first) 
				{
					first = false;
				}
				 else 
				{
					ret.add(",");
				}
				
				ret.add(" ");
				ret.add(f);
				ret.add(" : ");
				ret.add(haxe.root.Reflect.field(this, f));
			}
			
		}
		
		if ( ! (first) ) 
		{
			ret.add(" ");
		}
		
		ret.add("}");
		return ret.toString();
	}
	
	
	@Override public   boolean __hx_deleteField(String field)
	{
		int res = FieldLookup.findHash(field, this.__hx_hashes);
		if (( res >= 0 )) 
		{
			this.__hx_hashes.splice(res, 1);
			this.__hx_dynamics.splice(res, 1);
			return true;
		}
		 else 
		{
			res = FieldLookup.findHash(field, this.__hx_hashes_f);
			if (( res >= 0 )) 
			{
				this.__hx_hashes_f.splice(res, 1);
				this.__hx_dynamics_f.splice(res, 1);
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public  haxe.root.Array<String> __hx_hashes = new haxe.root.Array<String>(new String[]{});
	
	public  haxe.root.Array<Object> __hx_dynamics = new haxe.root.Array<Object>(new Object[]{});
	
	public  haxe.root.Array<String> __hx_hashes_f = new haxe.root.Array<String>(new String[]{});
	
	public  haxe.root.Array<Object> __hx_dynamics_f = new haxe.root.Array<Object>(new Object[]{});
	
	@Override public   Object __hx_lookupField(String field, boolean throwErrors, boolean isCheck)
	{
		int res = FieldLookup.findHash(field, this.__hx_hashes);
		if (( res >= 0 )) 
		{
			return this.__hx_dynamics.__get(res);
		}
		 else 
		{
			res = FieldLookup.findHash(field, this.__hx_hashes_f);
			if (( res >= 0 )) 
			{
				return ((double) (Runtime.toDouble(this.__hx_dynamics_f.__get(res))) );
			}
			
		}
		
		if (isCheck) 
		{
			return Runtime.undefined;
		}
		 else 
		{
			return null;
		}
		
	}
	
	
	@Override public   double __hx_lookupField_f(String field, boolean throwErrors)
	{
		int res = FieldLookup.findHash(field, this.__hx_hashes_f);
		if (( res >= 0 )) 
		{
			return ((double) (Runtime.toDouble(this.__hx_dynamics_f.__get(res))) );
		}
		 else 
		{
			res = FieldLookup.findHash(field, this.__hx_hashes);
			if (( res >= 0 )) 
			{
				return ((double) (Runtime.toDouble(this.__hx_dynamics.__get(res))) );
			}
			
		}
		
		return 0.0;
	}
	
	
	@Override public   Object __hx_lookupSetField(String field, Object value)
	{
		int res = FieldLookup.findHash(field, this.__hx_hashes);
		if (( res >= 0 )) 
		{
			return this.__hx_dynamics.__set(res, value);
		}
		 else 
		{
			int res2 = FieldLookup.findHash(field, this.__hx_hashes_f);
			if (( res2 >= 0 )) 
			{
				this.__hx_hashes_f.splice(res2, 1);
				this.__hx_dynamics_f.splice(res2, 1);
			}
			
		}
		
		this.__hx_hashes.insert( ~ (res) , field);
		this.__hx_dynamics.insert( ~ (res) , value);
		return value;
	}
	
	
	@Override public   double __hx_lookupSetField_f(String field, double value)
	{
		int res = FieldLookup.findHash(field, this.__hx_hashes_f);
		if (( res >= 0 )) 
		{
			return ((double) (Runtime.toDouble(this.__hx_dynamics_f.__set(res, value))) );
		}
		 else 
		{
			int res2 = FieldLookup.findHash(field, this.__hx_hashes);
			if (( res2 >= 0 )) 
			{
				this.__hx_hashes.splice(res2, 1);
				this.__hx_dynamics.splice(res2, 1);
			}
			
		}
		
		this.__hx_hashes_f.insert( ~ (res) , field);
		this.__hx_dynamics_f.insert( ~ (res) , value);
		return value;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<String> baseArr)
	{
		{
			{
				Object __temp_iterator14 = this.__hx_hashes.iterator();
				while (Runtime.toBool(Runtime.callField(__temp_iterator14, "hasNext", null)))
				{
					String __temp_field13 = Runtime.toString(Runtime.callField(__temp_iterator14, "next", null));
					baseArr.push(__temp_field13);
				}
				
			}
			
			{
				Object __temp_iterator15 = this.__hx_hashes_f.iterator();
				while (Runtime.toBool(Runtime.callField(__temp_iterator15, "hasNext", null)))
				{
					String __temp_field12 = Runtime.toString(Runtime.callField(__temp_iterator15, "next", null));
					baseArr.push(__temp_field12);
				}
				
			}
			
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


