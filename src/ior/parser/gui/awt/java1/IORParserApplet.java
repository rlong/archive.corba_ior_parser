package ior.parser.gui.awt.java1;

import ior.parser.corba.iop.IORModel;

import java.applet.Applet;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;

public class IORParserApplet extends Applet
{
	public IORParserApplet()
	{

	}

	public void init()
	{
		IORModel iorModel = new IORModel();

		GridBagLayout layoutMgr = new GridBagLayout();
		this.setLayout( layoutMgr );

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		AppletControl ac = new AppletControl( iorModel );
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1.0;
		layoutMgr.setConstraints( ac,  constraints );
		this.add( ac );

		IORPanel iorPanel = new IORPanel();
		GUIIORView guiView = new GUIIORView( iorPanel );
		guiView.model( iorModel );

		constraints.weighty = 1.0;
		layoutMgr.setConstraints( iorPanel, constraints );
		this.add( iorPanel );
	}

}

