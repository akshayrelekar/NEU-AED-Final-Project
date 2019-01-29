package app.utils;



import app.data.directories.Directory;
import app.entities.Rating;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Ninad Subhedar
 * This class stores all the common methods used across the project. 
 */
public class CommonUtils {
    
    private static final String mmddyyyyFormat = "mm/dd/yyyy";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(mmddyyyyFormat);
    
    public static Date mmddyyyyParseDate(String dateString) throws ParseException{
        if(dateString==null || dateString.equals(""))
            return null;
        return sdf.parse(dateString);
    }
    
    public static String mmddyyyyFormatDate(Date date){
        if(date==null)
            return "";
        return sdf.format(date);
    }
    
    public static String formatCurrency(Double amount){
        return String.format("$%.00f", amount);
    }
    
    public static void initPicPanel(String fileLoc, JLabel photoFrame) throws IOException {       
        
        int w = (photoFrame.getWidth() == 0)?photoFrame.getPreferredSize().width:photoFrame.getWidth();
        int h = (photoFrame.getHeight()== 0)?photoFrame.getPreferredSize().height:photoFrame.getHeight();
        
        ImageIcon imageIcon = new ImageIcon(fileLoc);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(w-2, h-2, Image.SCALE_DEFAULT));
        photoFrame.setIcon(imageIcon);
        
    }
    
    public static void initPicPanel(BufferedImage image, JLabel photoFrame) throws IOException {       
        
        int w = (photoFrame.getWidth() == 0)?photoFrame.getPreferredSize().width:photoFrame.getWidth();
        int h = (photoFrame.getHeight()== 0)?photoFrame.getPreferredSize().height:photoFrame.getHeight();
        
        ImageIcon imageIcon = new ImageIcon(image);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(w-2, h-2, Image.SCALE_DEFAULT));
        photoFrame.setIcon(imageIcon);
        
    }
    
}
