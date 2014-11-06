package fr.inria.diverse.noveltytesting.samples;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class FunctionsImpl extends haxe.lang.HxObject implements fr.inria.diverse.noveltytesting.samples.Functions
{
	public    FunctionsImpl(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    FunctionsImpl()
	{
		fr.inria.diverse.noveltytesting.samples.FunctionsImpl.__hx_ctor_fr_inria_diverse_noveltytesting_samples_FunctionsImpl(this);
	}
	
	
	public static   void __hx_ctor_fr_inria_diverse_noveltytesting_samples_FunctionsImpl(fr.inria.diverse.noveltytesting.samples.FunctionsImpl __temp_me6)
	{
		{
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new fr.inria.diverse.noveltytesting.samples.FunctionsImpl(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new fr.inria.diverse.noveltytesting.samples.FunctionsImpl();
	}
	
	
	public   int sum(int a, int b)
	{
		return ( a + b );
	}
	
	
	public   boolean inverse(boolean b)
	{
		return  ! (b) ;
	}
	
	
	public   java.lang.String echo(java.lang.String say)
	{
		return say;
	}
	
	
	public   java.lang.String concat(java.lang.String a, java.lang.String b)
	{
		return ( a + b );
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef35 = true;
			switch (field.hashCode())
			{
				case -1354795244:
				{
					if (field.equals("concat")) 
					{
						__temp_executeDef35 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("concat"))) );
					}
					
					break;
				}
				
				
				case 114251:
				{
					if (field.equals("sum")) 
					{
						__temp_executeDef35 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("sum"))) );
					}
					
					break;
				}
				
				
				case 3107365:
				{
					if (field.equals("echo")) 
					{
						__temp_executeDef35 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("echo"))) );
					}
					
					break;
				}
				
				
				case 1959910192:
				{
					if (field.equals("inverse")) 
					{
						__temp_executeDef35 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("inverse"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef35) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_invokeField(java.lang.String field, haxe.root.Array dynargs)
	{
		{
			boolean __temp_executeDef36 = true;
			switch (field.hashCode())
			{
				case -1354795244:
				{
					if (field.equals("concat")) 
					{
						__temp_executeDef36 = false;
						return this.concat(haxe.lang.Runtime.toString(dynargs.__get(0)), haxe.lang.Runtime.toString(dynargs.__get(1)));
					}
					
					break;
				}
				
				
				case 114251:
				{
					if (field.equals("sum")) 
					{
						__temp_executeDef36 = false;
						return this.sum(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 3107365:
				{
					if (field.equals("echo")) 
					{
						__temp_executeDef36 = false;
						return this.echo(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case 1959910192:
				{
					if (field.equals("inverse")) 
					{
						__temp_executeDef36 = false;
						return this.inverse(haxe.lang.Runtime.toBool(dynargs.__get(0)));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef36) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
}


