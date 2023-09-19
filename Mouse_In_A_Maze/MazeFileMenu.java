// Class MazeFileMenu
//
// Author: Alyce Brady
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import edu.kzoo.grid.gui.GridDataFileHandler;
import edu.kzoo.grid.gui.SteppedGridAppFrame;
import edu.kzoo.grid.gui.nuggets.BasicGridFileMenu;

/**
 *  Grid GUI Support Package:<br>
 *
 *  The <code>MazeFileMenu</code> class provides a menu bar
 *  with a file menu for opening a maze and quitting the application.
 *
 *  @author Alyce Brady
 *  @version 29 February 2004
 **/
public class MazeFileMenu extends BasicGridFileMenu
{

  // constructor

    /** Creates a File menu to read maze data files.
     *  (Precondition: frame is not null. )
     *  @param frame  the frame that uses menu bar
     *  @param fileHandler object that can read and write a grid;
     *                     null if this menu should not support file i/o
     **/
    public MazeFileMenu(MouseMazeGUI frame,
                       GridDataFileHandler fileHandler)
    {
        super(frame, fileHandler);
    }

    /* (non-Javadoc)
     * @see edu.kzoo.grid.gui.nuggets.BasicGridFileMenu#makeFileMenu()
     */
    protected void makeFileMenu()
    {
        int menuMask = getToolkit().getMenuShortcutKeyMask();

        JMenuItem mItem;

        if ( getFileMenuActionHandler().supportsFileIO() )
        {
            add(mItem = new JMenuItem("Open grid file..."));
            mItem.addActionListener(
                new ActionListener()
                {   public void actionPerformed(ActionEvent e)
                    {   getFileMenuActionHandler().openFile(); 
                        ((SteppedGridAppFrame)getParentFrame()).initialize();
                    }
                });
           mItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, menuMask));
        }

        addQuitMenuItem();
    }
}


