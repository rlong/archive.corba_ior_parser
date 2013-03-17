
package ior.parser.gui.awt.java1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;

import ior.parser.corba.DecodeError;

public class IORParseErrorDialog extends Dialog
{
	protected DecodeError m_de;
	protected Button m_okButton;
	protected TextArea m_errorReport;
	

	public IORParseErrorDialog( DecodeError de )
	{
		super( new Frame(), de.lookupError( false ), true );
		m_de = de;

		m_errorReport = new TextArea(10, 60);
		m_errorReport.appendText( de.toString() );
		m_errorReport.setEditable(false);
		this.add( BorderLayout.CENTER, m_errorReport );
		
		m_okButton = new Button("OK");
		Panel southPanel = new Panel();
		southPanel.add( m_okButton );
		this.add( BorderLayout.SOUTH, southPanel );

		this.pack();
		this.show();
	}

	public boolean action( Event e, Object arg )
	{
		if ( e.target ==  m_okButton )
		{
			this.dispose();
			return true;
		}
		return false;
	}
}


