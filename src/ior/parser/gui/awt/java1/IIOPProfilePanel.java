//  Copyright (c) 2013 Richard Long & HexBeerium
//
//  Released under the MIT license ( http://opensource.org/licenses/MIT )
//

package ior.parser.gui.awt.java1;

import ior.parser.corba.iiop.ProfileBody;
import ior.parser.tui.PrettyPrinter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;


public class IIOPProfilePanel extends Panel
{
	private GridBagLayout layout;

	private Label m_versionLabel = null;
	private VersionChoice m_version;

	private Label m_hostLabel = null;
	private TextField m_host;

	private Label m_portLabel = null;
	private TextField m_port;

	private Label m_objectKeyLabel = null;
	private HexArea m_objectKey;

	
	public IIOPProfilePanel( ProfileBody pb )
	{
		layout = new GridBagLayout();
		this.setLayout( layout );

		m_versionLabel = new Label( "Version: " );
		m_version = new VersionChoice( pb.iiop_version );

		m_hostLabel = new Label( "Host: " );
		m_host = new TextField();

		m_portLabel = new Label( "Port: " );
		m_port = new TextField();

		m_objectKeyLabel = new Label( "Object key: " );
		m_objectKey = new HexArea( pb.object_key );
		
		this.update( pb );

	}

	public void update( ProfileBody pb )
	{
		m_host.setText( pb.host );
		m_port.setText( Integer.toString( pb.port ) );
		this.populate();
	}

	public void populate()
	{
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		
		constraints.fill = GridBagConstraints.BOTH;

		////////////////////////////////////////////////////////
		constraints.gridy = 0; // row 1 
		constraints.gridx = 0; // far left
		layout.setConstraints( m_versionLabel, constraints );
		this.add( m_versionLabel );

		constraints.gridx = 1; // to the right
//		constraints.gridwidth = GridBagConstraints.REMAINDER; //end of row
		layout.setConstraints( m_version, constraints );
		this.add( m_version );

		////////////////////////////////////////////////////////
		constraints.gridy = 1; // row 2
		constraints.gridx = 0; // far left
		
//		constraints.gridwidth = 1; // reset to the detault
		layout.setConstraints( m_hostLabel, constraints );
		this.add( m_hostLabel );

		constraints.gridx = 1; // to the right
//		constraints.gridwidth = GridBagConstraints.REMAINDER; //end of row
		layout.setConstraints( m_host, constraints );
		this.add( m_host );

		////////////////////////////////////////////////////////
		constraints.gridy = 2; // row 3
		constraints.gridx = 0; // far left

//		constraints.gridwidth = 1; // reset to the detault
		layout.setConstraints( m_portLabel, constraints );
		this.add( m_portLabel );

		constraints.gridx = 1; // to the right
//		constraints.gridwidth = GridBagConstraints.REMAINDER; //end of row
		layout.setConstraints( m_port, constraints );
		this.add( m_port );

		////////////////////////////////////////////////////////
		constraints.gridy = 3; // row 4
		constraints.gridx = 0; // far left

//		constraints.fill = GridBagConstraints.BOTH;
//		constraints.gridwidth = 1; // reset to the detault
		layout.setConstraints( m_objectKeyLabel, constraints );
		this.add( m_objectKeyLabel );

		constraints.gridx = 1; // to the right
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		layout.setConstraints( m_objectKey, constraints );
		this.add( m_objectKey );

		this.validate();
	}
}

