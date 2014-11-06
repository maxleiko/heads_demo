package haxe.lang;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  interface IHxObject
{
	   boolean __hx_deleteField(String field);
	
	   Object __hx_lookupField(String field, boolean throwErrors, boolean isCheck);
	
	   double __hx_lookupField_f(String field, boolean throwErrors);
	
	   Object __hx_lookupSetField(String field, Object value);
	
	   double __hx_lookupSetField_f(String field, double value);
	
	   double __hx_setField_f(String field, double value, boolean handleProperties);
	
	   Object __hx_setField(String field, Object value, boolean handleProperties);
	
	   Object __hx_getField(String field, boolean throwErrors, boolean isCheck, boolean handleProperties);
	
	   double __hx_getField_f(String field, boolean throwErrors, boolean handleProperties);
	
	   Object __hx_invokeField(String field, haxe.root.Array dynargs);
	
	   void __hx_getFields(haxe.root.Array<String> baseArr);
	
}


