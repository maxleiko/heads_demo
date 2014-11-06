package haxe.root;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class ValueType extends haxe.lang.Enum
{
	static 
	{
		ValueType.constructs = new Array<String>(new String[]{"TNull", "TInt", "TFloat", "TBool", "TObject", "TFunction", "TClass", "TEnum", "TUnknown"});
		ValueType.TNull = new ValueType(((int) (0) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
		ValueType.TInt = new ValueType(((int) (1) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
		ValueType.TFloat = new ValueType(((int) (2) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
		ValueType.TBool = new ValueType(((int) (3) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
		ValueType.TObject = new ValueType(((int) (4) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
		ValueType.TFunction = new ValueType(((int) (5) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
		ValueType.TUnknown = new ValueType(((int) (8) ), ((Array<Object>) (new Array<Object>(new Object[]{})) ));
	}
	public    ValueType(int index, Array<Object> params)
	{
		super(index, params);
	}
	
	
	public static Array<String> constructs;
	
	public static ValueType TNull;
	
	public static ValueType TInt;
	
	public static ValueType TFloat;
	
	public static ValueType TBool;
	
	public static ValueType TObject;
	
	public static ValueType TFunction;
	
	public static ValueType TClass(Class c)
	{
		return new ValueType(((int) (6) ), ((Array<Object>) (new Array<Object>(new Object[]{c})) ));
	}
	
	
	public static ValueType TEnum(Class e)
	{
		return new ValueType(((int) (7) ), ((Array<Object>) (new Array<Object>(new Object[]{e})) ));
	}
	
	
	public static ValueType TUnknown;
	
}


