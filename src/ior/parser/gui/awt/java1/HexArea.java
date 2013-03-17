
package ior.parser.gui.awt.java1;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;

import ior.parser.tui.PrettyPrinter;

public class HexArea extends TextArea
{
	private byte[] m_octetSeq;

	public HexArea( byte[] octetSeq )
	{
		m_octetSeq = octetSeq;
		this.populateData();
	}

	public void populateData()
	{
		this.initTextPanelFont();	
		this.setData( m_octetSeq );
	}

	public void setData( byte[] octetSeq )
	{
		PrettyPrinter pp = new PrettyPrinter();
		pp.addln( octetSeq );
		this.setText( pp.toString() );
	}

	private void initTextPanelFont()
	{
		int fontSize = 12;
		Toolkit tk = Toolkit.getDefaultToolkit();
		String[] fontList = tk.getFontList();
		for( int i = 0; i < fontList.length; i++ )
		{
			if( fontList[ i ].equals( "Courier" ) ||
				fontList[ i ].equals( "Monospaced" ) )
			{
				Font fixedWidthFont = new Font( fontList[ i ], Font.PLAIN,
					fontSize );
				this.setFont( fixedWidthFont );
			}
		}
	}
}

