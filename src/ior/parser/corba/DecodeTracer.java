
package ior.parser.corba;

import java.util.Stack;

public class DecodeTracer
{
    private Stack m_trace = null;
    private OctetIterator m_dataIterator = null;
    
    public DecodeTracer( OctetIterator dataIterator )
    {
        m_trace = new Stack();
        m_dataIterator = dataIterator;
    }
    
    public void goingToDecode( String dataType )
    {
        m_trace.push( dataType );
    }
    
    public void doneDecoding( String dataType )
    {
        if ( m_trace.size() > 0 )
        {
            m_trace.pop();
        }
    }
    
    public String[] getTrace()
    {
        String trace[] = new String[ m_trace.size() ];
        m_trace.copyInto( trace );
        return trace;
    }
}

