package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class StringRefl
{
	static 
	{
		StringRefl.fields = new haxe.root.Array<String>(new String[]{"length", "toUpperCase", "toLowerCase", "charAt", "charCodeAt", "indexOf", "lastIndexOf", "split", "substr", "substring"});
	}
	public    StringRefl()
	{
		{
		}

	}


	public static  haxe.root.Array<String> fields;

	public static   Object handleGetField(String str, String f, boolean throwErrors)
	{
		{
			String __temp_svar47 = (f);
			int __temp_hash49 = __temp_svar47.hashCode();
			boolean __temp_executeDef48 = true;
			switch (__temp_hash49)
			{
				case -1106363674:
				{
					if (__temp_svar47.equals("length"))
					{
						__temp_executeDef48 = false;
						return str.length();
					}

					break;
				}


				case 530542161:case -891529231:case 109648666:case -467511597:case 1943291465:case 397153782:case -1361633751:case -1137582698:case -399551817:
				{
					if (( (( ( __temp_hash49 == 530542161 ) && __temp_svar47.equals("substring") )) || ( (( ( __temp_hash49 == -891529231 ) && __temp_svar47.equals("substr") )) || ( (( ( __temp_hash49 == 109648666 ) && __temp_svar47.equals("split") )) || ( (( ( __temp_hash49 == -467511597 ) && __temp_svar47.equals("lastIndexOf") )) || ( (( ( __temp_hash49 == 1943291465 ) && __temp_svar47.equals("indexOf") )) || ( (( ( __temp_hash49 == 397153782 ) && __temp_svar47.equals("charCodeAt") )) || ( (( ( __temp_hash49 == -1361633751 ) && __temp_svar47.equals("charAt") )) || ( (( ( __temp_hash49 == -1137582698 ) && __temp_svar47.equals("toLowerCase") )) || __temp_svar47.equals("toUpperCase") ) ) ) ) ) ) ) ))
					{
						__temp_executeDef48 = false;
						return new Closure(((Object) (str) ), Runtime.toString(f));
					}

					break;
				}


			}

			if (__temp_executeDef48)
			{
				if (throwErrors)
				{
					throw haxe.lang.HaxeException.wrap(( ( "Field not found: \'" + f ) + "\' in String" ));
				}
				 else
				{
					return null;
				}

			}
			 else
			{
				throw null;
			}

		}

	}


	public static   Object handleCallField(String str, String f, haxe.root.Array args)
	{
		haxe.root.Array _args = new haxe.root.Array(new Object[]{str});
		if (( args == null )) 
		{
			args = _args;
		}
		 else 
		{
			args = _args.concat(args);
		}
		
		return Runtime.slowCallField(StringExt.class, f, args);
	}
	
	
}


