//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.corba;

// Needs to be available for the DecodeTracer

final class OctetIterator 
{
    private int m_cursor = 0; 
    private byte[] m_octetSeq = null;
    
    public OctetIterator( byte byteArr[] )
    {
        m_octetSeq = byteArr;
    }
    
    public void ensureOctetsLeft( int amount )
        throws ArrayIndexOutOfBoundsException
    {
        if  ( m_octetSeq.length < ( amount + m_cursor ) )
        {
           throw new ArrayIndexOutOfBoundsException();
        }
    }
    
    public int getSize()
    {
        return m_octetSeq.length;
    }
    
    public int getCursorPos()
    {
        return m_cursor;
    }

	public int getNumBytesLeft()
	{
		return m_octetSeq.length - m_cursor;
	}
    
    public void advanceCursor( int numBytes )
        throws ArrayIndexOutOfBoundsException
    {
        this.ensureOctetsLeft( numBytes );
        m_cursor += numBytes;
    }
    
    public byte getNextOctet()
        throws ArrayIndexOutOfBoundsException
    {
        // may as well test explicitly for the over-running of the array
        this.ensureOctetsLeft( 1 );
        
        return m_octetSeq[ m_cursor++ ];
    }
}
