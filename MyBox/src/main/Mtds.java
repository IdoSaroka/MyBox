package main;

public class Mtds 
{
	public Boolean isUserValid(String str)
	{
		for(int i = 0;i<str.length();i++)
		{
			if( (str.charAt(i)>='0' && str.charAt(i)<='9' ) || ( str.charAt(i)=='_' ) || 
					(str.charAt(i)>='A' && str.charAt(i)<='Z') ||( str.charAt(i)>='a' && str.charAt(i)<='z' ))
				continue;
			else
				return false;
		}
		return true;
	}
	
	public Boolean isPasswordValid(String str)
	{
		for(int i = 0;i<str.length();i++)
			if( str.charAt(i)==' ' || str.charAt(i)=='\\' ||  str.charAt(i)=='/' )
				return false;
		return true;
	}

	public Boolean isPortValid(String str)
	{
		for(int i = 0;i<str.length();i++)
			if( !(str.charAt(i)>='0' && str.charAt(i)<='9'))
				return false;
		return true;
	}

	public Boolean isIPValid(String str)
	{
		for(int i = 0;i<str.length();i++)
			if( !((str.charAt(i)>='0' && str.charAt(i)<='9') ||  str.charAt(i)=='.') )
				return false;
		return true;
	}

}
