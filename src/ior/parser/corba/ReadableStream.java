
package ior.parser.corba;

public class  ReadableStream 
{
    private OctetIterator m_data;
    private Endian m_endian = null;
    private DecodeTracer m_trace = null;
    
    public ReadableStream( byte byteArr[] ) throws DecodeError
    {
        m_data = new OctetIterator( byteArr );
        m_trace = new DecodeTracer( m_data );
        
        m_endian = Endian.lookup( this.decodeBoolean() );
        
    }
    
    private byte getNextOctet() throws DecodeError
    {
        try
        {
            return m_data.getNextOctet();
        }
        catch ( ArrayIndexOutOfBoundsException aiobe )
        {
            throw new DecodeError( DecodeError.NOT_ENOUGH_DATA, m_trace );
        }
    }

	public int getNumBytesLeft()
	{ 
		return m_data.getNumBytesLeft();
	}

    public DecodeTracer getTrace()
    {
        return m_trace;
    }

    public Endian getEndian()
    {
        return m_endian;
    }

    public boolean decodeBoolean() throws DecodeError
    {
        m_trace.goingToDecode( Types.BOOLEAN_TYPE );
        
        boolean retVal = false;
 
        byte boolVal = this.getNextOctet();
        if ( 0 == boolVal )
        {
            retVal = false;
        }
        else if ( 1 == boolVal )
        {
            retVal = true;
        }
        else
        {
          throw new DecodeError( DecodeError.INVALID_DATA,
                "Expected 1 or 0 not " + boolVal, m_trace );
        }
        
        m_trace.doneDecoding( Types.BOOLEAN_TYPE );
        return retVal;
    }

    
    public char decodeChar() throws DecodeError
    {
        m_trace.goingToDecode( Types.CHAR_TYPE );

        char retVal = (char)this.getNextOctet();
        
        m_trace.doneDecoding( Types.CHAR_TYPE );
        
        return retVal;
    }
    
    public byte decodeOctet() throws DecodeError
    {
        m_trace.goingToDecode( Types.OCTET_TYPE );
        
        byte retVal = this.getNextOctet();

        m_trace.doneDecoding( Types.OCTET_TYPE );

        return retVal;
    }

    public byte[] decodeOctetSequence() throws DecodeError
    {
        m_trace.goingToDecode( Types.SEQ_OCTET_TYPE );
        
        int seqLength = this.decodeUnsignedLong();
        byte retVal[] = new byte[ seqLength ];
        for( int i = 0; i < seqLength; i++ )
        {
            retVal[ i ] = this.decodeOctet();
        }
        m_trace.doneDecoding( Types.SEQ_OCTET_TYPE );
        return retVal;
    }

    public String decodeString() throws DecodeError
    {
        m_trace.goingToDecode( Types.STRING_TYPE );

        
        int seqLength = this.decodeUnsignedLong();
        seqLength--; // We don't need the null character
        StringBuffer retVal = new StringBuffer( seqLength );
        
        for( int i = 0; i < seqLength; i++ )
        {
            retVal.append( this.decodeChar() );
        }
        this.decodeChar();  // throw away the null character

        m_trace.doneDecoding( Types.STRING_TYPE );
        
        return retVal.toString();
    }

    public int decodeUnsignedLong() throws DecodeError
    {
        m_trace.goingToDecode( Types.U_LONG_TYPE );
        
		this.skipPadding( Types.U_LONG_ALIGNMENT );
        int retVal = 0;
        
        for( int i = 0; i < Types.U_LONG_SIZE; i++ )
        {
            int octetRead = this.getNextOctet();
            if ( 0 > octetRead ) // account for 2's complement
            {
				octetRead ^= 0xffffff00; // mask off high order bits
            }
            
            int shiftBitsLeft = 8 * i;
            
            if ( m_endian == Endian.BIG )
            {
                shiftBitsLeft =
                    ( ( Types.U_LONG_SIZE - 1 ) * 8 ) - shiftBitsLeft;
            }
            retVal |= octetRead << shiftBitsLeft;
        }
        
        m_trace.doneDecoding( Types.U_LONG_TYPE );
        return retVal;
    }

    public int decodeUnsignedShort() throws DecodeError
    {
        m_trace.goingToDecode( Types.U_SHORT_TYPE );
        
		this.skipPadding( Types.U_SHORT_ALIGNMENT );
        int retVal = 0;
        for( int i = 0; i < Types.U_SHORT_SIZE; i++ )
        {
            int octetRead = this.getNextOctet();
            if ( 0 > octetRead ) // account for 2's complement
            {
				octetRead ^= 0xffffff00; // mask off high order bits
            }
            int shiftBitsLeft = 8 * i;
            if ( m_endian == Endian.BIG )
            {
                shiftBitsLeft =
                    ( ( Types.U_SHORT_SIZE - 1 ) * 8 ) - shiftBitsLeft;
            }
            retVal |= octetRead << shiftBitsLeft;
        }
        m_trace.doneDecoding( Types.U_SHORT_TYPE );
        return retVal;
    }

	private void skipPadding( int alignment ) throws DecodeError
	{
		int misAlignment = m_data.getCursorPos() % alignment;
		if( misAlignment == 0 )
		{
			// data is correctly alligned
			return;
		}
		int bytesToSkip = alignment - misAlignment;	

		for( int i = 0; i < bytesToSkip; i++ )
		{
			this.getNextOctet();	
		}
	}


}
