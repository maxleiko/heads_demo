package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class VarArgsFunction extends VarArgsBase
{
	public    VarArgsFunction(Function fun)
	{
		super(-1, -1);
		this.fun = fun;
	}
	
	
	public Function fun;
	
	@Override public   Object __hx_invokeDynamic(haxe.root.Array dynArgs)
	{
		return ((Object) (this.fun.__hx_invoke1_o(0.0, dynArgs)) );
	}
	
	
}


