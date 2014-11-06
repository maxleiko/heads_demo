package haxe.lang;

import haxe.root.Array;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class StringExt
{
	public    StringExt()
	{
		{
		}
		
	}
	
	
	public static   String charAt(String me, int index)
	{
		
			if ( index >= me.length() || index < 0 )
				return "";
			else
				return Character.toString(me.charAt(index));
	
	}
	
	
	public static   Object charCodeAt(String me, int index)
	{
		
			if ( index >= me.length() || index < 0 )
				return null;
			else
				return me.codePointAt(index);
	
	}
	
	
	public static   int indexOf(String me, String str, Object startIndex)
	{
		
			int sIndex = (startIndex != null ) ? (Runtime.toInt(startIndex)) : 0;
			if (sIndex >= me.length() || sIndex < 0)
				return -1;
			return me.indexOf(str, sIndex);
	
	}
	
	
	public static   int lastIndexOf(String me, String str, Object startIndex)
	{
		
			int sIndex = (startIndex != null ) ? (Runtime.toInt(startIndex)) : (me.length() - 1);
			if (sIndex > me.length() || sIndex < 0)
				sIndex = me.length() - 1;
			else if (sIndex < 0)
				return -1;
			return me.lastIndexOf(str, sIndex);
	
	}
	
	
	public static   haxe.root.Array<String> split(String me, String delimiter)
	{
		
			Array<String> ret = new Array<String>();

			int slen = delimiter.length();
			if (slen == 0)
			{
				int len = me.length();
				for (int i = 0; i < len; i++)
				{
					ret.push(me.substring(i, i + 1));
				}
			} else {
				int start = 0;
				int pos = me.indexOf(delimiter, start);

				while (pos >= 0)
				{
					ret.push(me.substring(start, pos));

					start = pos + slen;
					pos = me.indexOf(delimiter, start);
				}

				ret.push(me.substring(start));
			}
			return ret;
	
	}
	
	
	public static   String substr(String me, int pos, Object len)
	{
		
			int meLen = me.length();
			int targetLen = meLen;
			if (len != null)
			{
				targetLen = Runtime.toInt(len);
				if (targetLen == 0)
					return "";
				if( pos != 0 && targetLen < 0 ){
					return "";
				}
			}

			if( pos < 0 ){
				pos = meLen + pos;
				if( pos < 0 ) pos = 0;
			} else if( targetLen < 0 ){
				targetLen = meLen + targetLen - pos;
			}

			if( pos + targetLen > meLen ){
				targetLen = meLen - pos;
			}

			if ( pos < 0 || targetLen <= 0 ) return "";

			return me.substring(pos, pos + targetLen);
	
	}
	
	
	public static   String substring(String me, int startIndex, Object endIndex)
	{
		
		int endIdx;
		int len = me.length();
		if ( endIndex == null) {
			endIdx = len;
		} else if ( (endIdx = Runtime.toInt(endIndex)) < 0 ) {
			endIdx = 0;
		} else if ( endIdx > len ) {
			endIdx = len;
		}

		if ( startIndex < 0 ) {
			startIndex = 0;
		} else if ( startIndex > len ) {
			startIndex = len;
		}

		if ( startIndex > endIdx ) {
			int tmp = startIndex;
			startIndex = endIdx;
			endIdx = tmp;
		}

		return me.substring(startIndex, endIdx);

	
	}
	
	
	public static   String toString(String me)
	{
		return me;
	}
	
	
	public static   String toLowerCase(String me)
	{
		
			return me.toLowerCase();
	
	}
	
	
	public static   String toUpperCase(String me)
	{
		
			return me.toUpperCase();
	
	}
	
	
	public static   String toNativeString(String me)
	{
		return me;
	}
	
	
	public static   String fromCharCode(int code)
	{
		
		return Character.toString( (char) code );
	
	}
	
	
}


