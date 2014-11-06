package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Closure extends haxe.lang.VarArgsBase
{
	public    Closure(Object obj, String field)
	{
		super(-1, -1);
		this.obj = obj;
		this.field = field;
	}
	
	
	public  Object obj;
	
	public  String field;
	
	@Override public   Object __hx_invokeDynamic(haxe.root.Array dynArgs)
	{
		return haxe.lang.Runtime.callField(this.obj, this.field, dynArgs);
	}
	
	
}


