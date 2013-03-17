//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//


package ior.parser.gui.awt.java1;

import ior.parser.corba.iop.IORModel;
import ior.parser.tui.TextView;

import java.awt.BorderLayout;
import java.awt.Button; 
import java.awt.Checkbox;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import java.util.StringTokenizer;


public class AppletControl extends Panel
{
	TextField m_inputArea = null;
	Checkbox m_printToConsole = null;
	Button m_parse = null;
	Button m_clear = null;
	Button m_sample = null;

	IORModel m_iorModel = null;
	GUIIORController m_guiCtrlr = null;
	TextView m_consoleView;

	public AppletControl( IORModel iorModel )
	{
		// netscapes (4.05) version of Panel does not support setting of the 
		// layout manager in the constructor
		// super( new BorderLayout() ); 

		// instead we gotta do this ...
		this.setLayout( new BorderLayout() ); 

		m_iorModel = iorModel;
		m_guiCtrlr = new GUIIORController( m_iorModel );
		m_consoleView = new TextView();

		this.initInputPanel();
		this.initCtrlPanel();
	}

	public boolean action( Event e, Object arg)
	{
		// if the parse button was pressed
		if ( e.target == m_parse )
		{
			dispatchIOR();
			return true;
		}
		// if the clear button was pressed
		else if ( e.target == m_clear )
		{
			m_inputArea.setText( "" );
			m_guiCtrlr.unsetIOR();
			return true;
		}
		// if the sample button was pressed 
		else if ( e.target == m_sample )
		{
			m_inputArea.setText( "IOR:000000000000002849444c3a6f6d672e6f72672f436f734e616d696e672f4e616d696e67436f6e746578743a312e3000000000010000000000000028000100000000000a3132372e302e302e3100040300000010ee97cda08bf7d9bf27d2425cea690159" );
			dispatchIOR();
			return true;
		}
		// if the checkbox was (un)checked
		else if ( e.target == m_printToConsole )
		{
			// if the check-box if checked
			if ( m_printToConsole.getState() )
			{
				m_consoleView.model( m_iorModel );
			}
			else
			{
				m_consoleView.stopModel();
			}
			return true;
		}
		return false;

	}

	private void dispatchIOR()
	{
		StringBuffer ior = new StringBuffer();
		StringTokenizer st = new StringTokenizer( m_inputArea.getText() );
		while( st.hasMoreElements() )
		{
			ior.append( st.nextToken() );
		}
		m_guiCtrlr.setIOR( ior.toString() );
	}

	private void initInputPanel()
	{
		Panel inputPanel = new Panel();
		inputPanel.setLayout( new BorderLayout() );
		inputPanel.add( BorderLayout.WEST, new Label( "IOR : " ) );
		
		m_inputArea = new TextField();
//		m_inputArea = new TextArea( 1, 80 );
		inputPanel.add( BorderLayout.CENTER, m_inputArea );

		this.add( BorderLayout.NORTH, inputPanel );
	}

	private void initCtrlPanel()
	{
		Panel ctrlPanel = new Panel();
		ctrlPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );

		m_parse = new Button( "Parse" );
		ctrlPanel.add( m_parse );

		m_clear = new Button( "Clear" );	
		ctrlPanel.add( m_clear );

		m_sample = new Button( "Sample" );
		ctrlPanel.add( m_sample );

		m_printToConsole = new Checkbox( "Print to console" );
		ctrlPanel.add( m_printToConsole );

		this.add( BorderLayout.SOUTH, ctrlPanel );
	}
}


