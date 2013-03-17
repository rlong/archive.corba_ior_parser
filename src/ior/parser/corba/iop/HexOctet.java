
package ior.parser.corba.iop;

import ior.parser.corba.DecodeError;

public class HexOctet
{
    public static byte decodeOctet( char highNibble, char lowNibble ) 
        throws DecodeError
    {
        byte encodedByte =  (byte)(( lookup( highNibble ) << 4 ) + 
                                   lookup( lowNibble ));
        return encodedByte;
    }
    
    private static byte lookup( char nibble ) throws DecodeError 
    {
        if ( '0' <= nibble && '9' >= nibble  )
        {
            return (byte)( nibble - '0' );
        }
        char lowerCaseNibble = Character.toLowerCase( nibble );
        if ( 'a' <= lowerCaseNibble && 'f' >= lowerCaseNibble )
        {
            return (byte)( nibble + 10 - 'a' );
        }
        String errString = "Expected a value in the ranges 0-9,a-f,A-F." + 
            " Instead got a value of " + nibble;
        
        throw new DecodeError( DecodeError.INVALID_STRING, errString );
    }
}

