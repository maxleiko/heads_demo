package fr.inria.diverse.noveltytesting.samples;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Input;

@ComponentType
public class FunctionsImpl extends haxe.lang.HxObject implements Functions {
    public    FunctionsImpl(haxe.lang.EmptyObject empty) {}
    public    FunctionsImpl()
    {
        FunctionsImpl.__hx_ctor_fr_inria_diverse_noveltytesting_samples_FunctionsImpl(this);
    }
    public static   void __hx_ctor_fr_inria_diverse_noveltytesting_samples_FunctionsImpl(FunctionsImpl __temp_me6) {}
    public static   Object __hx_createEmpty() {
        return new FunctionsImpl(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
    }
    public static   Object __hx_create(haxe.root.Array arr)
    {
        return new FunctionsImpl();
    }

    @Input
    public int sum(int a, int b)
    {
        return ( a + b );
    }

    @Input
    public boolean inverse(boolean b)
    {
        return  ! (b) ;
    }

    @Input
    public String echo(String say)
    {
        return say;
    }

    @Input
    public String concat(String a, String b)
    {
        return ( a + b );
    }


    @Override public   Object __hx_getField(String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
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
                        return ((haxe.lang.Function) (new haxe.lang.Closure(((Object) (this) ), haxe.lang.Runtime.toString("concat"))) );
                    }

                    break;
                }


                case 114251:
                {
                    if (field.equals("sum"))
                    {
                        __temp_executeDef35 = false;
                        return ((haxe.lang.Function) (new haxe.lang.Closure(((Object) (this) ), haxe.lang.Runtime.toString("sum"))) );
                    }

                    break;
                }


                case 3107365:
                {
                    if (field.equals("echo"))
                    {
                        __temp_executeDef35 = false;
                        return ((haxe.lang.Function) (new haxe.lang.Closure(((Object) (this) ), haxe.lang.Runtime.toString("echo"))) );
                    }

                    break;
                }


                case 1959910192:
                {
                    if (field.equals("inverse"))
                    {
                        __temp_executeDef35 = false;
                        return ((haxe.lang.Function) (new haxe.lang.Closure(((Object) (this) ), haxe.lang.Runtime.toString("inverse"))) );
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


    @Override public   Object __hx_invokeField(String field, haxe.root.Array dynargs)
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


