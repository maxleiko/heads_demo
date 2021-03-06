package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class VarArgsBase extends Function
{
	public    VarArgsBase(int arity, int type)
	{
		super(arity, type);
	}
	
	
	@Override public   Object __hx_invokeDynamic(haxe.root.Array dynArgs)
	{
		throw haxe.lang.HaxeException.wrap("Abstract implementation");
	}
	
	
	@Override public   Object __hx_invoke6_o(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3, double __fn_float4, Object __fn_dyn4, double __fn_float5, Object __fn_dyn5, double __fn_float6, Object __fn_dyn6)
	{
		return this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) ), ( (( __fn_dyn4 == Runtime.undefined )) ? (__fn_float4) : (((Object) (__fn_dyn4) )) ), ( (( __fn_dyn5 == Runtime.undefined )) ? (__fn_float5) : (((Object) (__fn_dyn5) )) ), ( (( __fn_dyn6 == Runtime.undefined )) ? (__fn_float6) : (((Object) (__fn_dyn6) )) )}));
	}
	
	
	@Override public   double __hx_invoke6_f(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3, double __fn_float4, Object __fn_dyn4, double __fn_float5, Object __fn_dyn5, double __fn_float6, Object __fn_dyn6)
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) ), ( (( __fn_dyn4 == Runtime.undefined )) ? (__fn_float4) : (((Object) (__fn_dyn4) )) ), ( (( __fn_dyn5 == Runtime.undefined )) ? (__fn_float5) : (((Object) (__fn_dyn5) )) ), ( (( __fn_dyn6 == Runtime.undefined )) ? (__fn_float6) : (((Object) (__fn_dyn6) )) )})))) );
	}
	
	
	@Override public   Object __hx_invoke5_o(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3, double __fn_float4, Object __fn_dyn4, double __fn_float5, Object __fn_dyn5)
	{
		return this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) ), ( (( __fn_dyn4 == Runtime.undefined )) ? (__fn_float4) : (((Object) (__fn_dyn4) )) ), ( (( __fn_dyn5 == Runtime.undefined )) ? (__fn_float5) : (((Object) (__fn_dyn5) )) )}));
	}
	
	
	@Override public   double __hx_invoke5_f(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3, double __fn_float4, Object __fn_dyn4, double __fn_float5, Object __fn_dyn5)
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) ), ( (( __fn_dyn4 == Runtime.undefined )) ? (__fn_float4) : (((Object) (__fn_dyn4) )) ), ( (( __fn_dyn5 == Runtime.undefined )) ? (__fn_float5) : (((Object) (__fn_dyn5) )) )})))) );
	}
	
	
	@Override public   Object __hx_invoke4_o(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3, double __fn_float4, Object __fn_dyn4)
	{
		return this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) ), ( (( __fn_dyn4 == Runtime.undefined )) ? (__fn_float4) : (((Object) (__fn_dyn4) )) )}));
	}
	
	
	@Override public   double __hx_invoke4_f(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3, double __fn_float4, Object __fn_dyn4)
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) ), ( (( __fn_dyn4 == Runtime.undefined )) ? (__fn_float4) : (((Object) (__fn_dyn4) )) )})))) );
	}
	
	
	@Override public   Object __hx_invoke3_o(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3)
	{
		return this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) )}));
	}
	
	
	@Override public   double __hx_invoke3_f(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2, double __fn_float3, Object __fn_dyn3)
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) ), ( (( __fn_dyn3 == Runtime.undefined )) ? (__fn_float3) : (((Object) (__fn_dyn3) )) )})))) );
	}
	
	
	@Override public   Object __hx_invoke2_o(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2)
	{
		return this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) )}));
	}
	
	
	@Override public   double __hx_invoke2_f(double __fn_float1, Object __fn_dyn1, double __fn_float2, Object __fn_dyn2)
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) ), ( (( __fn_dyn2 == Runtime.undefined )) ? (__fn_float2) : (((Object) (__fn_dyn2) )) )})))) );
	}
	
	
	@Override public   Object __hx_invoke1_o(double __fn_float1, Object __fn_dyn1)
	{
		return this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) )}));
	}
	
	
	@Override public   double __hx_invoke1_f(double __fn_float1, Object __fn_dyn1)
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(new haxe.root.Array<Object>(new Object[]{( (( __fn_dyn1 == Runtime.undefined )) ? (__fn_float1) : (((Object) (__fn_dyn1) )) )})))) );
	}
	
	
	@Override public   Object __hx_invoke0_o()
	{
		return this.__hx_invokeDynamic(null);
	}
	
	
	@Override public   double __hx_invoke0_f()
	{
		return ((double) (Runtime.toDouble(this.__hx_invokeDynamic(null))) );
	}
	
	
}


