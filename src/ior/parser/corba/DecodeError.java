//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba;

public class DecodeError extends Exception
{
    private static final int UNKNOWN_ERROR = 0;
    public static final int NOT_ENOUGH_DATA = 1;
    public static final int INVALID_DATA = 2; // e.g. boolean = 5 
    public static final int INVALID_STRING = 3;

    private static final int MIN_ERROR = NOT_ENOUGH_DATA;
    private static final int MAX_ERROR = INVALID_STRING;

    private int m_reason	= UNKNOWN_ERROR;
    private String m_detail = null;
    private DecodeTracer m_trace = null;
    
    public DecodeError( int reason, String detail, DecodeTracer trace  )
    {
        if( reason < MIN_ERROR || reason > MAX_ERROR )
        {
            m_reason = UNKNOWN_ERROR;
        }
        else
        {
            m_reason = reason;
        }
        m_detail = detail;
        m_trace = trace;
    }
    
    public DecodeError( int reason, String detail )
    {
        this( reason, detail, null );
    }
    
    public DecodeError( int reason, DecodeTracer trace )
    {
        this( reason, null, trace );
    }

    public String lookupError( boolean verbose )
    {
        switch ( m_reason )
        {
            case ( NOT_ENOUGH_DATA ) :
            {
                if( verbose )
                {
                    return "Not enought data was available, data stream too short";
                }
                else
                {
                    return "Not enought data !";
                }
            }
            case ( INVALID_DATA ) :
            {
                if ( verbose )
                {
                    return "Invalid data value extracted";
                }
                else
                {
                    return "Invalid data !";
                }
            }
            case ( INVALID_STRING ) :
            {
                if ( verbose )
                {
                    return "Stringified Interoperable Object Reference passed was invalid";
                }
                else
                {
                    return "Invalid String !";
                }
            }
            default :
            {
                return "Unknown error !?!?";
            }
        }
    }

    public String toString()
    {
        String stringRep = this.lookupError( true ) + "\n";
        if( null != m_trace )
        {
            String trace[] = m_trace.getTrace();
            if( trace.length > 0 )
            {
                stringRep += "Error occured while trying to extract :\n";
                for ( int i = (trace.length - 1); -1 < i; i-- )
                {
                    stringRep += trace[ i ];
					if ( i != 0 )
					{
						stringRep += " embedded within ...\n";
					}
					else
					{
						stringRep += "\n";
					}
                }
            }
        }
        if ( null != m_detail )
        {
            stringRep += "Reason given ... \n" + m_detail;
        }
        return stringRep;
    }
}
