
package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;
import ior.parser.mvc.ExceptionMessage;


public class IORExceptionMessage extends ExceptionMessage
{
	
	IORExceptionMessage( DecodeError de )
	{
		super( IORMessageTypes.BAD_IOR, de );
	}

}

