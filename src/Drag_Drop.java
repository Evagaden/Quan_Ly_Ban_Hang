import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Drag_Drop extends JFrame implements DropTargetListener{

    JLabel imageLabel = new JLabel();
    String picName;
    String picPath;

    public Drag_Drop(JLabel image)
    {
        imageLabel = image;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t = dtde.getTransferable();
        DataFlavor[] df = t.getTransferDataFlavors();

        for(DataFlavor f : df)
        {
            try
            {
                if(f.isFlavorJavaFileListType())
                {
                    List<File> files = (List<File>)t.getTransferData(f);

                    for(File file : files)
                    {
                        this.picName = file.getName();
                        this.picPath = file.getPath();
                        displayImage(file.getPath());
                    }
                }
            }catch (Exception e)
            {

            }
        }
    }

    private void displayImage(String path)
    {
        ImageIcon imgc = null;
        try
        {
            imgc = new ImageIcon(path);
        }catch (Exception e)
        {

        }
        Image img = imgc.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
    }

    public String getPicName() {
        return picName;
    }

    public String getPicPath() {
        return picPath;
    }
}